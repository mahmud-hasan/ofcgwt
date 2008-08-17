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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class ChartWidget extends Widget {
	private static int count = 0;
	
	private boolean isSWFInjected = false;
	private final String swfId;
	private String jsonData = "{\"title\":{\"text\":\"\"},\"elements\":[]}"; // THIS IS THE CURRENT MIN CHART YOU MUST HAVE !!
//	private String jsonData = "";
	private final String src;
	private final String alternateSrc;
	private final String swfDivId;
	private String innerDivTextForFlashPlayerNotFound = "FlashPlayer ${flashPlayer.version} is required.";

	public ChartWidget() {
		initJSCallback(this);
		src = "open-flash-chart.swf";
		alternateSrc = "expressInstall.swf";
		swfId = "swfID_" + count;
		swfDivId = "swfDivID_" + count;
		++count;
		Element element = DOM.createElement("div");
		DOM.setElementProperty(element, "id", swfDivId);
		setElement(element);
		setSize("300", "300");
	}

	protected void onLoad() {
		if (!isSWFInjected) {
			initEmptyInnerDiv();
			onBeforeSWFInjection();
			injectSWF(getSrc(), getSwfId(), getWidth(), getHeight(),
					getMinPlayerVersion().toString(), getAlternate());
			isSWFInjected = true;
			onAfterSWFInjection();
		}
		super.onLoad();
	}
	
	private void initEmptyInnerDiv() {
		String notifyText = getInnerDivTextForFlashPlayerNotFound().replaceAll("\\$\\{flashPlayer.version\\}",getMinPlayerVersion().toString());
		getElement().setInnerHTML("<div id=\"embed_" + swfId + "\">" + notifyText + "</div>");
	}
	
	protected native void injectSWF(String swf, String id, String w, String h,String ver, String alt)
	/*-{ 	     
		var flashvars = {};
		var params = {};
	    var attributes = { id: id, name: id };
		$wnd.swfobject.embedSWF(swf, "embed_"+id, w, h, ver, alt, flashvars, params, attributes);
	        
	}-*/;

	private native void initJSCallback (ChartWidget chartclass) 
	/*-{
	   $wnd.dataFileJS = function () {
	       return chartclass.@com.rednels.ofcgwt.client.ChartWidget::getJsonData()();
	   };
	}-*/;
	
	public native void loadJSON (String json) 
	/*-{	
		id = this.@com.rednels.ofcgwt.client.ChartWidget::getSwfId()();				
		var swf = $doc.getElementById(id);
  		x = swf.load(json);
	}-*/;


	@SuppressWarnings("unused") // used by com_rednels_ofcgwt_callback.js
	private String getJsonData() { 
		return jsonData;
	}

	private String getAlternate() {
		return alternateSrc;
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

	public String getSwfId() {
		return swfId;
	}

	public void setHeight(String height) {
		height = height.trim().toLowerCase();
		super.setHeight(height); // Width validation
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
		if (getWidth().equals(width)) {
			if (isSWFInjected) {
				Element elem = DOM.getFirstChild(getElement());
				DOM.setElementAttribute(elem, "width", width);
			}
		}
	}

	public String getSrc() {
		return src;
	}

	public String getWidth() {
		return DOM.getStyleAttribute(getElement(), "width");
	}

	public String getHeight() {
		return DOM.getStyleAttribute(getElement(), "height");
	}

	public String getMinPlayerVersion() {
		return "9.0.0";
	}

	public String getInnerDivTextForFlashPlayerNotFound() {
		return innerDivTextForFlashPlayerNotFound;
	}

	public void setInnerDivTextForFlashPlayerNotFound(String innerDivTextForFlashPlayerNotFound) {
		this.innerDivTextForFlashPlayerNotFound = innerDivTextForFlashPlayerNotFound;
	}
}