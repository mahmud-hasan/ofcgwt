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

import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.event.EventElement;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * OFC bar chart 
 */
public class BarChart extends Element implements JSONizable {

	/**
	 * OFC bar chart bars
	 */
	public static class Bar extends EventElement implements JSONizable {

		private Number top;
		private Number bottom;
		private String colour;
		private String tooltip;
		private String onClick;

		/**
		 * Creates a new bar.
		 * 
		 * @param top
		 *            the top
		 */
		public Bar(Number top) {
			this(top, null, null);
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
			this(top, bottom, null);
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
		 */
		public Bar(Number top, Number bottom, String colour) {
			setTop(top);
			setBottom(bottom);
			setColour(colour);
		}

		/**
		 * Creates a new bar.
		 * 
		 * @param top
		 *            the top
		 * @param colour
		 *            the colour
		 */
		public Bar(Number top, String colour) {
			this(top, null, colour);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
		 */
		public JSONValue buildJSON() {
			JSONObject json = new JSONObject();
			if (top != null) json.put("top", new JSONNumber(top.doubleValue()));
			if (bottom != null) json.put("bottom", new JSONNumber(bottom.doubleValue()));
			if (colour != null) json.put("colour", new JSONString(colour));
			if (tooltip != null) json.put("tip", new JSONString(tooltip));
			if (onClick != null) json.put("on-click", new JSONString(onClick));
			return json;
		}

		/**
		 * Gets the bottom.
		 * 
		 * @return the bottom
		 */
		public Number getBottom() {
			return bottom;
		}

		/**
		 * Gets the colour.
		 * 
		 * @return the colour
		 */
		public String getColour() {
			return colour;
		}

		/**
		 * Gets the onClick.
		 * 
		 * @return the onClick
		 */
		public String getOnClick() {
			return onClick;
		}

		/**
		 * Gets the tooltip.
		 * 
		 * @return the tooltip
		 */
		public String getTooltip() {
			return tooltip;
		}

		/**
		 * Gets the top.
		 * 
		 * @return the top
		 */
		public Number getTop() {
			return top;
		}

		/**
		 * Sets the bottom.
		 * 
		 * @param bottom
		 *            the new bottom
		 */
		public void setBottom(Number bottom) {
			this.bottom = bottom;
		}

		/**
		 * Sets the colour in HTML hex format (#ffffff)
		 * 
		 * @param colour
		 *            the new colour
		 */
		public void setColour(String colour) {
			this.colour = colour;
		}

		/**
		 * Sets the onClick.
		 * 
		 * @param onClick
		 *            the onClick javascript method or url
		 */
		public void setOnClick(String onClick) {
			this.onClick = onClick;
		}

		/**
		 * Sets the tooltip.
		 * 
		 * @param tooltip
		 *            the new tooltip
		 */
		public void setTooltip(String tooltip) {
			this.tooltip = tooltip;
		}

		/**
		 * Sets the top.
		 * 
		 * @param top
		 *            the new top
		 */
		public void setTop(Number top) {
			this.top = top;
		}
	}

	public static enum BarStyle {

		NORMAL("bar"),THREED("bar_3d"),GLASS("bar_glass");

		private String style;
		
		BarStyle(String style) {
			this.style = style;
		}

		public String getStyle() {
			return style;
		}
	}

	private String colour;

	/**
	 * Creates a new bar chart with normal style.
	 */
	public BarChart() {
		this(BarStyle.NORMAL);
	}

	/**
	 * Creates a new bar chart.
	 * 
	 * @param style
	 *            the style
	 */
	public BarChart(BarStyle style) {
		super(style.getStyle());
	}

	/**
	 * Creates a new bar chart.
	 * 
	 * @param style
	 *            the style
	 */
	protected BarChart(String style) {
		super(style);
	}

	/**
	 * Adds the bars.
	 * 
	 * @param bars
	 *            the bars
	 */
	public void addBars(Bar... bars) {
		getValues().addAll(Arrays.asList(bars));
	}

	/**
	 * Adds the bars.
	 * 
	 * @param bars
	 *            the bars
	 */
	public void addBars(List<Bar> bars) {
		getValues().addAll(bars);
	}

	/**
	 * Adds the values.
	 * 
	 * @param values
	 *            the values
	 */
	public void addValues(List<Number> values) {
		getValues().addAll(values);
	}

	/**
	 * Adds the values.
	 * 
	 * @param values
	 *            the values
	 */
	public void addValues(Number... values) {
		getValues().addAll(Arrays.asList(values));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.elements.Element.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (colour != null) json.put("colour", new JSONString(colour));
		return json;
	}

	/**
	 * Gets the colour.
	 * 
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * Sets the colour in HTML hex format (#ffffff)
	 * 
	 * @param colour
	 *            the new colour
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
}
