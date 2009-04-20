/*
Copyright (C) 2009 Grant Slender

This file is part of OFCGWT.
http://code.google.com/p/ofcgwt/

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

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.event.ChartClickEvent;
import com.rednels.ofcgwt.client.event.ChartClickHandler;
import com.rednels.ofcgwt.client.event.DataValueEvents;
import com.rednels.ofcgwt.client.event.KeyClickHandler;
import com.rednels.ofcgwt.client.model.ChartData;

/**
 * A gwt chart widget based on Open Flash Chart.</br></br>
 * 
 * Create the ChartWidget and add anywhere a GWT widget can be used. Use the
 * model {@link ChartData} to build a chart and then pass to
 * {@link ChartWidget#setChartData(ChartData)} and it will generate and set the
 * correct JSON data.
 * <p/>
 * You can also set JSON via the {@link #setJsonData(String)} method.
 * 
 */
public class ChartWidget extends Widget {
	public static final String MIN_PLAYER_VERSION = "9.0.0";
	public static final String ALTERNATE_SWF_SRC = "expressInstall.swf";

	private static final CacheFixImpl cacheFixImpl = GWT.create(CacheFixImpl.class);
	private static int count = 0;

	private boolean isSWFInjected = false;
	private boolean cacheFixEnabled = false;
	private boolean hasFlashPlayer = false;
	private String swfId;
	private String swfDivId;
	private String jsonData = ChartFactory.BLANK_CHART_JSON_DATA;
	private String width = "100%";
	private String height = "100%";
	private String innerDivTextForFlashPlayerNotFound = "FlashPlayer ${flashPlayer.version} is required.";
	private String flashurl = "ofcgwt/open-flash-chart.swf";
	private String urlPrefix = GWT.getModuleBaseURL();
	private ChartData chartData;
	private Element chartElement;

	/**
	 * Creates a new ChartWidget. *
	 */
	public ChartWidget() {
		swfId = "swfID_" + count;
		swfDivId = "swfDivID_" + count;
		++count;
		chartElement = DOM.createElement("div");
		DOM.setElementProperty(chartElement, "id", swfDivId);
		setElement(chartElement);
		setSize(width, height);
		hasFlashPlayer = hasFlashPlayerVersion(MIN_PLAYER_VERSION);
		cacheFixEnabled = cacheFixImpl.isCacheFixNeeded();
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
	 * Gets the current JSON data for this chart.
	 * 
	 * @return a JSON string
	 */
	public String getJsonData() {
		return jsonData;
	}

	/**
	 * @return the swfId
	 */
	public String getSwfId() {
		return swfId;
	}

	/**
	 * @return the urlPrefix
	 */
	public String getUrlPrefix() {
		return urlPrefix;
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
	 * Is the CacheFix Enabled?
	 * 
	 * @return true if cache fix is enabled, false if not
	 */
	public boolean isCacheFixEnabled() {
		return cacheFixEnabled;
	}

	/**
	 * Obtains raw image data of this chart. Call returns null if the flash
	 * chart is not loaded.
	 * 
	 * @return String chart image data
	 */
	public String getImageData() {
		if (hasFlashPlayer && isSWFInjected) {
			return getImageData(getSwfId());
		}
		return null;
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
	 * Sets this charts ChartData and processes it for handlers/events
	 * 
	 * @param cd
	 *            the ChartData model
	 */
	public void setChartData(ChartData cd) {
		this.chartData = cd;
		for (com.rednels.ofcgwt.client.model.elements.Element e : chartData.getElements()) {
			// add chart click events...
			for (ChartClickHandler cch : e.getChartClickHandlers()) {
				String onclick = "ofc_onclick('" + getSwfId() + "','" + cch.hashCode() + "')";
				e.setOnClick(onclick);
			}
			// add key click events...
			for (KeyClickHandler kch : e.getKeyClickHandlers()) {
				String onclick = "ofc_onclick('" + getSwfId() + "','" + kch.hashCode() + "')";
				e.setKeyOnClick(onclick);
			}
			for (Object o : e.getValues()) {
				if (o instanceof DataValueEvents) {
					// add data value click events...
					DataValueEvents dve = (DataValueEvents) o;
					for (ChartClickHandler ch : dve.getHandlers()) {
						String onclick = "ofc_onclick('" + getSwfId() + "','" + ch.hashCode() + "')";
						dve.setOnClick(onclick);
					}
				}
			}
		}
		setJsonData(chartData.buildJSON().toString());
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
	 * Sets the JSON data for this chart & updates the chart if ready. Does
	 * nothing if the required flash player is not loaded.
	 * 
	 * @param json
	 *            a JSON string
	 */
	public void setJsonData(String json) {
		// System.out.println(json);
		this.jsonData = json;
		if (hasFlashPlayer && isSWFInjected) {
			loadJSON(swfId, jsonData);
		}
	}

	/**
	 * Sets the url prefix of the OFC flash swf file. Defaults to the value of
	 * GWT.getModuleBaseURL()
	 * 
	 * @param urlPrefix
	 *            a URL string
	 */
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
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

	protected void doOnChartClick(String evt) {
		for (com.rednels.ofcgwt.client.model.elements.Element e : chartData.getElements()) {
			for (Object o : e.getValues()) {
				if (o instanceof DataValueEvents) {
					DataValueEvents ee = (DataValueEvents) o;
					for (ChartClickHandler ch : ee.getHandlers()) {
						if (evt.equals("" + ch.hashCode())) {
							ch.onClick(new ChartClickEvent());
						}
					}
				}
			}
		}
	}

	protected List<ChartClickHandler> getHandlers() {
		return null;
	}

	protected void onAttach() {
		chartElement.setInnerHTML("<div id=\"embed_" + swfId + "\">" + emptyInnerDiv() + "</div>");
		ChartFactory.get().register(this);
		if (!isSWFInjected) {
			injectSWF(getInternalSWFURL(isCacheFixEnabled(), urlPrefix + flashurl, swfId), swfId, getWidth(), getHeight(), MIN_PLAYER_VERSION, ALTERNATE_SWF_SRC);
			isSWFInjected = true;
		}
		super.onAttach();
	}

	protected void onDetach() {
		ChartFactory.get().unregister(this);
		chartElement.removeChild(chartElement.getChildNodes().getItem(0));
		isSWFInjected = false;
		super.onDetach();

	}

	private String emptyInnerDiv() {
		return getInnerDivTextForFlashPlayerNotFound().replaceAll("\\$\\{flashPlayer.version\\}", MIN_PLAYER_VERSION);
	}

	/**
	 * Used internally to returns the correct open flash chart swf url
	 * 
	 * @return the swf url string
	 */
	private String getInternalSWFURL(boolean iefix, String flashurl, String id) {
		if (!iefix) return flashurl;
		return flashurl + ("?id=" + id + (new Date().getTime()));
	}

	private native boolean hasFlashPlayerVersion(String v)
	/*-{	    
	  	return $wnd.swfobject.hasFlashPlayerVersion(v);	        
	}-*/;

	private native void injectSWF(String swf, String id, String w, String h, String ver, String alt)
	/*-{ 	     
		var flashvars = {id: id,allowResize: true};
		var params = {scale: 'noscale', allowscriptaccess:'always',wmode: 'transparent'};		
	    var attributes = { data: swf, width: w, height: h, id: id, name: id };
		$wnd.swfobject.embedSWF(swf, "embed_"+id, w, h, ver, alt, flashvars, params, attributes);
	}-*/;

	private native void loadJSON(String id, String json)
	/*-{				
		var swf = $doc.getElementById(id);
		if ('load' in swf) swf.load(json);
	}-*/;

	private native String getImageData(String id)
	/*-{				
		var swf = $doc.getElementById(id);
		var data = null;
		if ('get_img_binary' in swf) data = swf.get_img_binary();
		return data;
	}-*/;
}