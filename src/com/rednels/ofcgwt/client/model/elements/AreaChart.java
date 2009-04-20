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
package com.rednels.ofcgwt.client.model.elements;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * OFC area chart
 */
public class AreaChart extends LineChart {

	private Float fillAlpha;
	private String fillColour;
	private Boolean loop;

	/**
	 * Creates a new area chart with AreaStyle.LINE style
	 */
	public AreaChart() {
		super("area");
		onShowType = new JSONObject();
		((JSONObject) onShowType).put("type", new JSONString(""));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.elements.LineChart.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (fillAlpha != null)
			json.put("fill-alpha", new JSONNumber(fillAlpha));
		if (fillColour != null)
			json.put("fill", new JSONString(fillColour));
		if (loop != null)
			json.put("loop", JSONBoolean.getInstance(loop));
		return json;
	}

	/**
	 * Gets the fill alpha.
	 * 
	 * @return the fill alpha
	 */
	public Float getFillAlpha() {
		return fillAlpha;
	}

	/**
	 * Gets the fill colour.
	 * 
	 * @return the fill colour
	 */
	public String getFillColour() {
		return fillColour;
	}

	/**
	 * Gets the loop value
	 * 
	 * @return true if loop is enabled
	 */
	public Boolean getLoop() {
		return loop;
	}

	/**
	 * Sets the fill alpha.
	 * 
	 * @param fillAlpha
	 *            the new fill alpha
	 */
	public void setFillAlpha(Float fillAlpha) {
		this.fillAlpha = fillAlpha;
	}

	/**
	 * Sets the fill colour in HTML hex format (#ffffff)
	 * 
	 * @param colour
	 *            the new fill colour
	 */
	public void setFillColour(String colour) {
		this.fillColour = colour;
	}

	/**
	 * Sets loop
	 * 
	 * @param loop
	 *            true or false
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
	}
}
