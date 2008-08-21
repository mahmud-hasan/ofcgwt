/*
 *    Copyright 2008 Grant K Slender
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *   
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
 * Create the ChartWidget and add anywhere a widget can be used.
 * 
 */
public class ChartWidget extends Widget implements IChartData {
	public static final String BLANK_CHART_JSON_DATA = "{\"title\":{\"text\":\"\"},\"elements\":[]}";
	public static final String MIN_PLAYER_VERSION = "9.0.0";
	public static final String ALTERNATE_SWF_SRC = "expressInstall.swf";
	
	private static int count = 0;
	private boolean isSWFInjected = false;
	private boolean ieCacheFixEnabled = true;
	private final String swfId;
	private String jsonData = BLANK_CHART_JSON_DATA;
	private String width = "100%";
	private String height = "100%";
	private final String src;
	private final String swfDivId;
	private String innerDivTextForFlashPlayerNotFound = "FlashPlayer ${flashPlayer.version} is required.";
	private static ArrayList<IChartData> charts = new ArrayList<IChartData>();

	/**
	 * Creates a new ChartWidget.
	 * 
	 */
	public ChartWidget() {
		initJSCallback(this);
		swfId = "swfID_" + count;
		swfDivId = "swfDivID_" + count;
		src = getSWFURL(isIeCacheFixEnabled());
		++count;
		Element element = DOM.createElement("div");
		DOM.setElementProperty(element, "id", swfDivId);
		setElement(element);
		setSize(width, height);
	}

	protected void onLoad() {
		if (!isSWFInjected) {
			getElement().setInnerHTML("<div id=\"embed_" + swfId + "\">" + emptyInnerDiv() + "</div>");
			String w = getWidth();
			String h = getHeight();
			injectSWF(src, swfId, w, h, MIN_PLAYER_VERSION, ALTERNATE_SWF_SRC);
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
	 * 
	 * @param json a JSON string 
	 */
	public void setJsonData(String json) {
		this.jsonData = json;
		loadJSON(swfId,jsonData);
	}

	protected void onUnload() {
		getElement().removeChild(DOM.getFirstChild(getElement()));
		isSWFInjected = false;
		super.onUnload();
	}

	protected String getSwfDivId() {
		return swfDivId;
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
	 * Enables an IE fix/workaround that stops caching of the swf. Stopping IE caching allows more than one chart to be rendered at once.
	 * <p>Defaults to true. Means the SWF is never cached !!
	 * 
	 * @param enable - true to enable, false to disable
	 */
	// mulitple swf fix : need to add a unique element to the SWF url or it will be cache and crash?
	public void setIeCacheFixEnabled(boolean enable) {
		this.ieCacheFixEnabled = enable;
	}

	/**
	 * @return true if IE cache fix is enabled, false if not
	 */	
	public boolean isIeCacheFixEnabled() {
		return ieCacheFixEnabled;
	}

	public String getSwfId() {
		return swfId;
	}
}