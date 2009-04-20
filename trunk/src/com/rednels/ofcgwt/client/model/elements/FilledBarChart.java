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
package com.rednels.ofcgwt.client.model.elements;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * OFC filled bar chart (outlined)
 */
public class FilledBarChart extends BarChart implements JSONizable {

	/**
	 * OFC filled bar chart bars
	 */
	public static class Bar extends BarChart.Bar implements JSONizable {

		private String outlineColour;

		/**
		 * Creates a new bar.
		 * 
		 * @param top
		 *            the top
		 */
		public Bar(Number top) {
			super(top);
		}

		/**
		 * Creates a new bar.
		 * 
		 * @param top
		 *            the top
		 * @param bottom
		 *            the bottom
		 */
		public Bar(Number top, Number bottom) {
			super(top, bottom);
		}

		/**
		 * Creates a new bar.
		 * 
		 * @param top
		 *            the top
		 * @param bottom
		 *            the bottom
		 * @param colour
		 *            the colour
		 * @param outlineColour
		 *            the outline colour
		 */
		public Bar(Number top, Number bottom, String colour, String outlineColour) {
			super(top, bottom);
			setColour(colour);
			setOutlineColour(outlineColour);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.rednels.ofcgwt.client.model.elements.BarChart.Bar.buildJSON()
		 */
		public JSONValue buildJSON() {
			JSONObject json = (JSONObject) super.buildJSON();
			if (outlineColour != null) json.put("outline-colour", new JSONString(outlineColour));
			return json;
		}

		/**
		 * Gets the outline colour.
		 * 
		 * @return the outline colour
		 */
		public String getOutlineColour() {
			return outlineColour;
		}

		/**
		 * Sets the outline colour in HTML hex format (#ffffff)
		 * 
		 * @param outlineColour
		 *            the new outline colour
		 */
		public void setOutlineColour(String outlineColour) {
			this.outlineColour = outlineColour;
		}
	}

	private String outlineColour;

	/**
	 * Creates a new filled bar chart.
	 */
	public FilledBarChart() {
		this(null, null);
	}

	/**
	 * Creates a new filled bar chart.
	 * 
	 * @param colour
	 *            the colour
	 * @param outlineColour
	 *            the outline colour
	 */
	public FilledBarChart(String colour, String outlineColour) {
		super("bar_filled");
		setColour(colour);
		setOutlineColour(outlineColour);
	}

	/**
	 * Creates a new filled bar chart.
	 * 
	 * @param style
	 *            the style
	 */
	protected FilledBarChart(String style) {
		super(style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.elements.BarChart.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (outlineColour != null) json.put("outline-colour", new JSONString(outlineColour));
		return json;
	}

	/**
	 * Gets the outline colour.
	 * 
	 * @return the outline colour
	 */
	public String getOutlineColour() {
		return outlineColour;
	}

	/**
	 * Sets the outline colour in HTML hex format (#ffffff)
	 * 
	 * @param outlineColour
	 *            the new outline colour
	 */
	public void setOutlineColour(String outlineColour) {
		this.outlineColour = outlineColour;
	}
}
