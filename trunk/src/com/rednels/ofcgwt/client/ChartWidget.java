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

import java.util.Date;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class ChartWidget extends Widget implements IChartData {
	public static final String BLANK_CHART_JSON_DATA = "{\"title\":{\"text\":\"\"},\"elements\":[]}";
	public static final String MIN_PLAYER_VERSION = "9.0.0";
	public static final String ALTERNATE_SWF_SRC = "expressInstall.swf";
	
	private static int count = 0;
	private boolean isSWFInjected = false;
	private boolean ieCacheFixEnabled = true;
	private final String swfId;
	private String jsonData = BLANK_CHART_JSON_DATA;
	private String width = "";
	private String height = "";
	private final String src;
	private final String swfDivId;
	private String innerDivTextForFlashPlayerNotFound = "FlashPlayer ${flashPlayer.version} is required.";


	public ChartWidget() {
		initJSCallback(this);
		swfId = "swfID_" + count;
		swfDivId = "swfDivID_" + count;
		src = getSWFURL(isIeCacheFixEnabled());
		++count;
		Element element = DOM.createElement("div");
		DOM.setElementProperty(element, "id", swfDivId);
		setElement(element);
		setSize("300", "300");
	}

	protected void onLoad() {
		if (!isSWFInjected) {
			getElement().setInnerHTML("<div id=\"embed_" + swfId + "\">" + emptyInnerDiv() + "</div>");
			onBeforeSWFInjection();
			String w = getWidth();
			String h = getHeight();
			injectSWF(src, swfId, w, h, MIN_PLAYER_VERSION, ALTERNATE_SWF_SRC);
			isSWFInjected = true;
			onAfterSWFInjection();
		}
		super.onLoad();
	}
	
	private String emptyInnerDiv() {
		return getInnerDivTextForFlashPlayerNotFound().replaceAll("\\$\\{flashPlayer.version\\}",MIN_PLAYER_VERSION);
	}
	
	public static String getSWFURL(boolean iefix) {
		String swfurl = "open-flash-chart.swf";		
		if (iefix) swfurl += ("?id="+new Date()); 
		return swfurl;
	}
	
	public static native void injectSWF(String swf, String id, String w, String h,String ver, String alt)
	/*-{ 	     
		var flashvars = {id: id};
		var params = {allowscriptaccess:'always',wmode: 'transparent'};
	    var attributes = { id: id, name: id };
		$wnd.swfobject.embedSWF(swf, "embed_"+id, w, h, ver, alt, flashvars, params, attributes);
	        
	}-*/;

	public static native void initJSCallback (IChartData chartclass) 
	/*-{
	   $wnd.dataFileJS = function () {
	       return chartclass.@com.rednels.ofcgwt.client.IChartData::getJsonData()();
	   };
	}-*/;
	
	public static native void loadJSON (String id,String json) 
	/*-{				
		var swf = $doc.getElementById(id);
  		x = swf.load(json);
	}-*/;

	public String getJsonData() { 
		return jsonData;
	}

	public void setJsonData(String json) {
		this.jsonData = json;
		loadJSON(swfId,jsonData);
	}

	/**
	 * Override this method to catch information about injection.
	 */
	protected void onAfterSWFInjection() {
	}

	/**
	 * Override this method to catch information about injection.
	 */
	protected void onBeforeSWFInjection() {
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

	public String getWidth() {
		return width;
	}	

	public String getHeight() {
		return height;
	}

	public String getInnerDivTextForFlashPlayerNotFound() {
		return innerDivTextForFlashPlayerNotFound;
	}

	public void setInnerDivTextForFlashPlayerNotFound(String innerDivTextForFlashPlayerNotFound) {
		this.innerDivTextForFlashPlayerNotFound = innerDivTextForFlashPlayerNotFound;
	}
	
	// mulitple swf fix : need to add a unique element to the SWF url or it will be cache and crash?
	public void setIeCacheFixEnabled(boolean ieCacheFixEnabled) {
		this.ieCacheFixEnabled = ieCacheFixEnabled;
	}

	public boolean isIeCacheFixEnabled() {
		return ieCacheFixEnabled;
	}
}