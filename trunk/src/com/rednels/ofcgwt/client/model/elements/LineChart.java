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
import com.rednels.ofcgwt.client.model.JSONizable;
import com.rednels.ofcgwt.client.model.elements.dot.BaseDot;
import com.rednels.ofcgwt.client.model.elements.dot.SolidDot;

/**
 * OFC line chart
 */
public class LineChart extends Element implements JSONizable {

	public static class LineStyle implements JSONizable {

		private Number on;
		private Number off;
		private String style;

		/**
		 * Line style - dash
		 * 
		 * @param on
		 *            the on
		 * @param off
		 *            the off
		 */
		public LineStyle(Number on, Number off) {
			style = "dash";
			this.on = on;
			this.off = off;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
		 */
		public JSONValue buildJSON() {
			JSONObject json = new JSONObject();
			if (on != null) json.put("on", new JSONNumber(on.doubleValue()));
			if (off != null) json.put("off", new JSONNumber(off.doubleValue()));
			if (style != null) json.put("style", new JSONString(style));
			return json;
		}
	}

	private Integer width;
	private String text;
	private String colour;
	private boolean rightAxis;
	private BaseDot dotStyle = new SolidDot();
	private LineStyle lineStyle;

	/**
	 * Creates a new line chart with normal style.
	 */
	public LineChart() {
		super("line");
	}

	/**
	 * Creates a new line chart.
	 * 
	 * @param type
	 *            the type
	 */
	protected LineChart(String type) {
		super(type);
	}

	/**
	 * Adds the dots.
	 * 
	 * @param dots
	 *            the dots
	 */
	public void addDots(BaseDot... dots) {
		addDots(Arrays.asList(dots));
	}

	/**
	 * Adds the dots.
	 * 
	 * @param dots
	 *            the dots
	 */
	public void addDots(List<BaseDot> dots) {
		getValues().addAll(dots);
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
		addValues(Arrays.asList(values));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.elements.Element.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (width != null) json.put("width", new JSONNumber(width));
		if (text != null) json.put("text", new JSONString(text));
		if (colour != null) json.put("colour", new JSONString(colour));
		if (this.rightAxis) json.put("axis", new JSONString("right"));
		if (this.dotStyle != null) json.put("dot-style", dotStyle.buildJSON());
		if (this.lineStyle != null) json.put("line-style", lineStyle.buildJSON());
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
	 * @return true if right axis
	 */
	public boolean isRightAxis() {
		return this.rightAxis;
	}

	/**
	 * Get the text
	 * 
	 * @return String text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * Sets the colour.
	 * 
	 * @param colour
	 *            the new colour
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * Sets the dot style.
	 * 
	 * @param dotStyle
	 *            the new dot style
	 */
	public void setDotStyle(BaseDot dotStyle) {
		this.dotStyle = dotStyle;
	}

	/**
	 * Sets the line style.
	 * 
	 * @param lineStyle
	 *            the new line style
	 */
	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

	/**
	 * Sets the right axis
	 * 
	 * @param rightAxis
	 *            the rightAxis
	 */
	public void setRightAxis(boolean rightAxis) {
		this.rightAxis = rightAxis;
	}

	/**
	 * Sets the text string.
	 * 
	 * @param text
	 *            String
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Sets the width.
	 * 
	 * @param width
	 *            the new width
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
}
