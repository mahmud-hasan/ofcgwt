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
 * Class for an OFC area chart that extends LineChart
 * 
 * @see com.rednels.ofcgwt.client.model.elements.LineChart
 */
public class AreaChart extends LineChart {

	/** The fill alpha. */
	private Float fillAlpha;

	/** The fill colour. */
	private String fillColour;

	/** The animate. */
	private Boolean loop;

	/**
	 * Creates a new area chart with AreaStyle.LINE style
	 */
	public AreaChart() {
		this(AreaStyle.LINE);
	}

	/**
	 * Creates a new area chart with provided style
	 */
	public AreaChart(AreaStyle style) {
		super(style.getStyle());
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
	 * Sets the fill alpha.
	 * 
	 * @param fillAlpha
	 *            the new fill alpha
	 */
	public void setFillAlpha(Float fillAlpha) {
		this.fillAlpha = fillAlpha;
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

	/**
	 * Gets the loop value
	 * 
	 * @return true if loop is enabled
	 */
	public Boolean getLoop() {
		return loop;
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
	 * Sets the fill colour in HTML hex format (#ffffff)
	 * 
	 * @param colour
	 *            the new fill colour
	 */
	public void setFillColour(String colour) {
		this.fillColour = colour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.elements.LineChart.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (fillAlpha != null) json.put("fill-alpha", new JSONNumber(fillAlpha));
		if (fillColour != null) json.put("fill", new JSONString(fillColour));
		if (loop != null) json.put("loop", JSONBoolean.getInstance(loop));
		return json;
	}

	/**
	 * Enumeration AreaStyle - used with AreaChart.
	 */
	public static enum AreaStyle {

		/** NORMAL */
		LINE("area_line"),

		/** HOLLOW */
		HOLLOW("area_hollow");

		/** The style. */
		private String style;

		/**
		 * Creates a new area style.
		 * 
		 * @param style
		 *            the style
		 */
		AreaStyle(String style) {
			this.style = style;
		}

		/**
		 * Gets the style.
		 * 
		 * @return the style
		 */
		public String getStyle() {
			return style;
		}
	}
}
