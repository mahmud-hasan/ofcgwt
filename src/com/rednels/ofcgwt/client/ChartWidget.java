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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.model.ChartData;

/**
 * A gwt chart widget based on Open Flash Chart.</br></br>
 * 
 * Create the ChartWidget and add anywhere a GWT widget can be used. Use
 * {@link ChartData}.toString() to produce a JSON string that this chart widget
 * uses via its {@link #setJsonData(String)} method.
 * 
 */
public class ChartWidget extends Widget implements IChartData {
	public static final String BLANK_CHART_JSON_DATA = "{\"title\":{\"text\":\"\"},\"elements\":[]}";
	public static final String MIN_PLAYER_VERSION = "9.0.0";
	public static final String ALTERNATE_SWF_SRC = "expressInstall.swf";
	private static ArrayList<IChartData> charts = new ArrayList<IChartData>();

	private ArrayList<IChartListener> chartListeners = new ArrayList<IChartListener>();
	private ArrayList<IOnClickListener> clickListeners = new ArrayList<IOnClickListener>();
	private static int count = 0;
	private boolean isSWFInjected = false;
	private boolean cacheFixEnabled = false;
	private final String swfId;
	private final String swfDivId;
	private String jsonData = BLANK_CHART_JSON_DATA;
	private String width = "100%";
	private String height = "100%";
	private String innerDivTextForFlashPlayerNotFound = "FlashPlayer ${flashPlayer.version} is required.";
	private boolean hasFlashPlayer = false;
	private String flashurl = "ofcgwt/open-flash-chart.swf";
	private static final CacheFixImpl cacheFixImpl = GWT.create(CacheFixImpl.class);
	private String urlPrefix = GWT.getModuleBaseURL();

	/**
	 * Creates a new ChartWidget. *
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
		cacheFixEnabled = cacheFixImpl.isCacheFixNeeded();
	}

	protected void onLoad() {
		if (!isSWFInjected) {
			getElement().setInnerHTML("<div id=\"embed_" + swfId + "\">" + emptyInnerDiv() + "</div>");
			String w = getWidth();
			String h = getHeight();
			injectSWF(getInternalSWFURL(isCacheFixEnabled(), urlPrefix + flashurl, swfId), swfId, w, h, MIN_PLAYER_VERSION, ALTERNATE_SWF_SRC);
			isSWFInjected = true;
		}
		super.onLoad();
	}

	private String emptyInnerDiv() {
		return getInnerDivTextForFlashPlayerNotFound().replaceAll("\\$\\{flashPlayer.version\\}", MIN_PLAYER_VERSION);
	}

	/**
	 * Used internally to returns the correct open flash chart swf url
	 * 
	 * @return the swf url string
	 */
	public static String getInternalSWFURL(boolean iefix, String flashurl, String id) {
		if (!iefix) return flashurl;
		return flashurl + ("?id=" + id + (new Date().getTime()));
	}

	/**
	 * Injects the swf into the dom.<br>
	 * Internal widget use only - made public for integration.
	 * 
	 * @param swf
	 *            url
	 * @param id
	 *            the dom id
	 * @param w
	 *            width
	 * @param h
	 *            height
	 * @param ver
	 *            version string (9.0.0)
	 * @param alt
	 *            alternate swf to load if wrong version
	 */
	public static native void injectSWF(String swf, String id, String w, String h, String ver, String alt)
	/*-{ 	     
		var flashvars = {id: id,allowResize: true};
		var params = {scale: 'noscale', allowscriptaccess:'always',wmode: 'transparent'};
	    var attributes = { id: id, name: id };
		$wnd.swfobject.embedSWF(swf, "embed_"+id, w, h, ver, alt, flashvars, params, attributes);	        
	}-*/;

	/**
	 * Returns true if the flash player equals or is higher than the version
	 * string provided
	 * 
	 * @param v
	 *            String version
	 * @return true if flash version equals or is higher
	 */
	public static native boolean hasFlashPlayerVersion(String v)
	/*-{	    
	  	return $wnd.swfobject.hasFlashPlayerVersion(v);	        
	}-*/;

	/**
	 * Inits the call back functions.<br>
	 * Internal widget use only - made public for integration.
	 * 
	 * @param chartclass
	 *            an instance of IChartData
	 */
	public static void initJSCallback(IChartData chartclass) {
		charts.add(chartclass);
		initCallback();
	}

	private static native void initCallback()
	/*-{
	   $wnd.ofcgwtGetJsonData = function (id) {
	       return @com.rednels.ofcgwt.client.ChartWidget::jsonData(Ljava/lang/String;)(id);
	   };
	   
	   $wnd.ofcgwtNotifyReady = function (id) {
	       @com.rednels.ofcgwt.client.ChartWidget::notify(Ljava/lang/String;)(id);
	   };
	   
	   $wnd.ofcgwtOnClick = function (id,evt) {
	       @com.rednels.ofcgwt.client.ChartWidget::onclick(Ljava/lang/String;Ljava/lang/String;)(id,evt);
	   };	   
	}-*/;

	@SuppressWarnings("unused")
	private static String jsonData(String id) {
		for (IChartData chart : charts) {
			if (chart.getSwfId().equals(id)) return chart.getJsonData();
		}
		return BLANK_CHART_JSON_DATA;
	}

	@SuppressWarnings("unused")
	private static void notify(String id) {
		for (IChartData chart : charts) {
			if (chart.getSwfId().equals(id)) chart.notifyReady();
		}
	}

	@SuppressWarnings("unused")
	private static void onclick(String id, String evt) {
		for (IChartData chart : charts) {
			if (chart.getSwfId().equals(id)) chart.notifyOnClick(evt);
		}
	}

	/**
	 * Notifies registered chart listeners that the chart is ready
	 */
	public void notifyOnClick(String evt) {
		for (IOnClickListener clicks : clickListeners) {
			if (evt.equals("" + clicks.hashCode())) clicks.handleOnClickEvent();
		}
	}

	/**
	 * Notifies registered chart listeners that the chart is ready
	 */
	public void notifyReady() {
		for (IChartListener chart : chartListeners) {
			chart.handleChartReadyEvent();
		}
	}

	/**
	 * Gets the current OFC flash URL. Defaults to just "open-flash-chart.swf"
	 * 
	 * @return the flashurl
	 */
	public String getFlashUrl() {
		return flashurl;
	}

	/**
	 * Sets the OFC flash URL. ie "\path\open-flash-chart.swf"
	 * 
	 * @param url
	 *            string
	 */
	public void setFlashUrl(String url) {
		this.flashurl = url;
	}

	/**
	 * Notifies registered chart listeners that the image was saved
	 */
	public void notifyImageSaved() {
		for (IChartListener chart : chartListeners) {
			chart.imageSavedEvent();
		}
	}

	/**
	 * Calls the load method on the OFC swf.<br>
	 * Internal widget use only - made public for integration.
	 * 
	 * @param id
	 *            the dom id
	 * @param json
	 *            a JSON string
	 */
	public static native void loadJSON(String id, String json)
	/*-{				
		var swf = $doc.getElementById(id);
		x = swf.load(json);
	}-*/;

	/**
	 * Calls the save_image method on the OFC swf.<br>
	 * Internal widget use only - made public for integration.
	 * 
	 * @param id
	 *            the dom id
	 * @param url
	 *            the url to call
	 * @param debug
	 *            enable debug
	 */
	public static native void saveImage(String id, String url, boolean debug)
	/*-{				
		var swf = $doc.getElementById(id);
		x = swf.save_image( url, 'this.@com.rednels.ofcgwt.client.ChartWidget::notifyImageSaved()()', debug );
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
	 * Sets the JSON data for this chart & updates the chart if ready. Does
	 * nothing if the required flash player is not loaded.
	 * 
	 * @param json
	 *            a JSON string
	 */
	public void setJsonData(String json) {
		this.jsonData = json;
		if (hasFlashPlayer && isSWFInjected) {
			loadJSON(swfId, jsonData);
		}
	}

	/**
	 * Saves a JPG image of this chart and send the JPG to the url provided.
	 * Call does nothing if the required flash player is not loaded.
	 * 
	 * @param url
	 *            the url to call
	 * @param debug
	 *            enable debug
	 */
	public void saveJpgImagetoURL(String url, boolean debug) {
		if (hasFlashPlayer && isSWFInjected) {
			saveImage(swfId, url, debug);
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
	 * Gets the InnerDiv Text for when flash player is not found or can't be
	 * injected.
	 * 
	 * @return string
	 */
	public String getInnerDivTextForFlashPlayerNotFound() {
		return innerDivTextForFlashPlayerNotFound;
	}

	/**
	 * Sets the InnerDiv Text for when flash player is not found or can't be
	 * injected. <br>
	 * Defaults to "FlashPlayer ${flashPlayer.version} is required."
	 * 
	 * @param divtext
	 *            a string
	 */
	public void setInnerDivTextForFlashPlayerNotFound(String divtext) {
		this.innerDivTextForFlashPlayerNotFound = divtext;
	}

	/**
	 * Enables an fix/workaround that stops caching of the swf which on IE may
	 * solve some bugs. The workaround adds a unique parameter url to each SWF
	 * making each chart widget non-cachable. On IE this feature is enabled by
	 * default, other agents it is disabled. </br></br>Enable this if you find
	 * problems in with multiple charts.
	 * 
	 * @param enable
	 *            - true to enable, false to disable
	 */
	public void setCacheFixEnabled(boolean enable) {
		this.cacheFixEnabled = enable;
	}

	/**
	 * Is the CacheFix Enabled?
	 * 
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
	 * Sets the url prefix of the OFC flash swf file. Defaults to the value of GWT.getModuleBaseURL()
	 * 
	 * @param urlPrefix
	 *            a URL string
	 */
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	/**
	 * @return the urlPrefix
	 */
	public String getUrlPrefix() {
		return urlPrefix;
	}

	/**
	 * Removes an IChartListener
	 * 
	 * @param listener
	 *            an IChartListener
	 */
	public void removeChartListeners(IChartListener listener) {
		chartListeners.remove(listener);
	}

	/**
	 * Adds an IChartListener that implements the handleChartReadyEvent method
	 * 
	 * @param listener
	 *            an IChartListener
	 */
	public void addChartListeners(IChartListener listener) {
		chartListeners.add(listener);
	}

	/**
	 * Adds an IOnClickListener that implements the handleOnClickEvent method.
	 * <p>
	 * <b>Note: not really intended to be used directly </b>- you must add the
	 * returned function signature to the JSON onClick event.
	 * <p>
	 * <i>Easier Option: </i>Use addOnClickListener within the models (such as
	 * Element/Pie.Slice etc) and this method will get function signature,
	 * register the event handler and add to JSON string.
	 * 
	 * @param listener
	 *            an IChartListener
	 * @return function signature String
	 */
	public String addOnClickListener(IOnClickListener listener) {
		clickListeners.add(listener);
		return "ofc_onclick('" + getSwfId() + "','" + listener.hashCode() + "')";
	}
}