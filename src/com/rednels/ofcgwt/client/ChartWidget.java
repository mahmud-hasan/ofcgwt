/*
Copyright (C) 2008 Grant Slender

This file is part of OFCGWT.

OFCGWT is free software: you can redistribute it and/or modify
it under the terms of the Lesser GNU General Public License as
published by the Free Software Foundation, either version 3 of
the License, or (at your option) any later version.

OFCGWT is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

See <http://www.gnu.org/licenses/lgpl-3.0.txt>.
*/
package com.rednels.ofcgwt.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
/**
 * A gwt chart widget based on Open Flash Chart.</br></br>
 * 
 * Create the ChartWidget and add anywhere a GWT widget can be used.
 * 
 */
public class ChartWidget extends Widget implements IChartData {
	public static final String BLANK_CHART_JSON_DATA = "{\"title\":{\"text\":\"\"},\"elements\":[]}";
	public static final String MIN_PLAYER_VERSION = "9.0.0";
	public static final String ALTERNATE_SWF_SRC = "expressInstall.swf";
	private static ArrayList<IChartData> charts = new ArrayList<IChartData>();

	private ArrayList<IChartListener> listeners = new ArrayList<IChartListener>();
	private static int count = 0;
	private boolean isSWFInjected = false;
	private boolean cacheFixEnabled = true;
	private final String swfId;
	private final String swfDivId;
	private String jsonData = BLANK_CHART_JSON_DATA;
	private String width = "100%";
	private String height = "100%";
	private String innerDivTextForFlashPlayerNotFound = "FlashPlayer ${flashPlayer.version} is required.";
	private boolean hasFlashPlayer = false;

	/**
	 * Creates a new ChartWidget.	 * 
	 */
	public ChartWidget() {
		initJSCallback(this);
		swfId = "swfID_" + count;
		swfDivId = "swfDivID_" + count;
		++count;
		Element element = DOM.createElement("div");
		DOM.setElementProperty(element, "id", swfDivId);
		setElement(element);
		setSize(width, height);
		hasFlashPlayer = hasFlashPlayerVersion(MIN_PLAYER_VERSION);
	}

	protected void onLoad() {
		if (!isSWFInjected) {
			getElement().setInnerHTML("<div id=\"embed_" + swfId + "\">" + emptyInnerDiv() + "</div>");
			String w = getWidth();
			String h = getHeight();
			injectSWF(getSWFURL(isCacheFixEnabled()), swfId, w, h, MIN_PLAYER_VERSION, ALTERNATE_SWF_SRC);
			isSWFInjected = true;
		}
		super.onLoad();
	}
	
	private String emptyInnerDiv() {
		return getInnerDivTextForFlashPlayerNotFound().replaceAll("\\$\\{flashPlayer.version\\}",MIN_PLAYER_VERSION);
	}

	/**
	 * Returns the open flash chart swf url
	 * 
	 * @return the swf url string
	 */
	public static String getSWFURL(boolean iefix) {
		String swfurl = "open-flash-chart.swf";		
		if (iefix) swfurl += ("?id="+(new Date().getTime())); 
		return swfurl;
	}

	/**
	 * Injects the swf into the dom.<br>Internal widget use only - made public for integration. 
	 * 
	 * @param swf url
	 * @param id the dom id
	 * @param w width
	 * @param h height
	 * @param ver version string (9.0.0)
	 * @param alt alternate swf to load if wrong version
	 */
	public static native void injectSWF(String swf, String id, String w, String h,String ver, String alt)
	/*-{ 	     
		var flashvars = {id: id,allowResize: true};
		var params = {scale: 'noscale', allowscriptaccess:'always',wmode: 'transparent'};
	    var attributes = { id: id, name: id };
		$wnd.swfobject.embedSWF(swf, "embed_"+id, w, h, ver, alt, flashvars, params, attributes);	        
	}-*/;
	

	public static native boolean hasFlashPlayerVersion(String v)
	/*-{	    
	  	return $wnd.swfobject.hasFlashPlayerVersion(v);	        
	}-*/;
	

	/**
	 * Inits the call back functions.<br>Internal widget use only - made public for integration.
	 * 
	 * @param chartclass an instance of IChartData
	 */
	public static void initJSCallback (IChartData chartclass) {
		charts.add(chartclass);
		initCallback();
	}
		
	private static native void initCallback () 
	/*-{
	   $wnd.ofcgwtGetJsonData = function (id) {
	       return @com.rednels.ofcgwt.client.ChartWidget::jsonData(Ljava/lang/String;)(id);
	   };
	   
	   $wnd.ofcgwtNotifyReady = function (id) {
	       @com.rednels.ofcgwt.client.ChartWidget::notify(Ljava/lang/String;)(id);
	   };
	   
	}-*/;
	
	@SuppressWarnings("unused")
	private static void notify(String id) {
		for (IChartData chart: charts) {
			if (chart.getSwfId().equals(id)) chart.notifyReady();
		}	
	}
	
	@SuppressWarnings("unused")
	private static String jsonData(String id) {
		for (IChartData chart: charts) {
			if (chart.getSwfId().equals(id)) return chart.getJsonData();
		}	
		return BLANK_CHART_JSON_DATA;
	}

	/**
	 * Gets the current JSON data for this chart.
	 * 
	 * @return a JSON string
	 */
	public void notifyReady() {		
		for (IChartListener chart : listeners) {
			chart.handleChartReadyEvent();
		}
	}

	/**
	 * Calls the load method on the OFC swf.<br>Internal widget use only - made public for integration.
	 * 
	 * @param id the dom id 
	 * @param json a JSON string 
	 */
	public static native void loadJSON (String id,String json) 
	/*-{				
		var swf = $doc.getElementById(id);
  		x = swf.load(json);
	}-*/;

	/**
	 * Gets the current JSON data for this chart.
	 * 
	 * @return a JSON string
	 */
	public String getJsonData() { 
		return jsonData;
	}

	/**
	 * Sets the JSON data for this chart & updates the chart if ready.
	 * Does nothing if the required flash player is not loaded.
	 * 
	 * @param json a JSON string 
	 */
	public void setJsonData(String json) {
		this.jsonData = json;
		if (hasFlashPlayer && isSWFInjected) {
			loadJSON(swfId,jsonData);
		}
	}

	protected void onUnload() {
		getElement().removeChild(DOM.getFirstChild(getElement()));
		isSWFInjected = false;
		super.onUnload();
	}

	public void setHeight(String height) {
		height = height.trim().toLowerCase();
		super.setHeight(height); // Width validation
		this.height = height;
		if (getHeight().equals(height)) {
			if (isSWFInjected) {
				Element elem = DOM.getFirstChild(getElement());
				DOM.setElementAttribute(elem, "height", height);
			}
		}

	}

	public void setWidth(String width) {
		width = width.trim().toLowerCase();
		super.setWidth(width); // Width validation
		this.width = width;
		if (getWidth().equals(width)) {
			if (isSWFInjected) {
				Element elem = DOM.getFirstChild(getElement());
				DOM.setElementAttribute(elem, "width", width);
			}
		}
	}

	/**
	 * Gets the objects width.
	 * 
	 * @return width
	 */
	public String getWidth() {
		return width;
	}	

	/**
	 * Gets the objects height.
	 * 
	 * @return height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * Gets the InnerDiv Text for when flash player is not found or can't be injected.
	 * 
	 * @return string
	 */
	public String getInnerDivTextForFlashPlayerNotFound() {
		return innerDivTextForFlashPlayerNotFound;
	}

	/**
	 * Sets the InnerDiv Text for when flash player is not found or can't be injected.
	 * <br>Defaults to "FlashPlayer ${flashPlayer.version} is required."
	 * 
	 * @param divtext a string 
	 */
	public void setInnerDivTextForFlashPlayerNotFound(String divtext) {
		this.innerDivTextForFlashPlayerNotFound = divtext;
	}

	/**
	 * Enables an fix/workaround that stops caching of the swf which on IE may solve some bugs.
	 * <br>Defaults to true. Enable this if you find problems in IE with multiple charts.
	 * 
	 * @param enable - true to enable, false to disable
	 */
	public void setCacheFixEnabled(boolean enable) {
		this.cacheFixEnabled = enable;
	}

	/**
	 * Is the CacheFix Enabled?
	 * @return true if cache fix is enabled, false if not
	 */	
	public boolean isCacheFixEnabled() {
		return cacheFixEnabled;
	}

	/**
	 * @return the swfId
	 */	
	public String getSwfId() {
		return swfId;
	}

	/**
	 * Removes an IChartListener 
	 * 
	 * @param chart an IChartListener 
	 */
	public void removeChartListeners(IChartListener chart) {
		listeners.remove(chart);
	}

	/**
	 * Adds an IChartListener that implements the handleChartReadyEvent method
	 * 
	 * @param chart an IChartListener 
	 */
	public void addChartListeners(IChartListener chart) {
		listeners.add(chart);
	}
}