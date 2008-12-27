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
import java.util.Collection;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * Class for an OFC shape that extends Element (polygon drawn under a chart)
 * 
 * @see com.rednels.ofcgwt.client.model.elements.Element
 */
public class Shape extends Element implements JSONizable {

	/** The Constant TYPE. */
	private static final transient String TYPE = "shape";

	/** The alpha. */
	private Float alpha;

	/** The colour. */
	private String colour;

	/**
	 * Creates a new shape.
	 */
	public Shape() {
		super(TYPE);
	}

	/**
	 * Adds the points.
	 * 
	 * @param points
	 *            the points
	 */
	public void addPoints(Point... points) {
		getValues().addAll(Arrays.asList(points));
	}

	/**
	 * Adds the point.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void addPoint(Number x, Number y) {
		addPoints(new Point(x, y));
	}

	/**
	 * Adds the points.
	 * 
	 * @param points
	 *            the points
	 */
	public void addPoints(Collection<Point> points) {
		getValues().addAll(points);
	}

	/**
	 * Gets the alpha.
	 * 
	 * @return the alpha
	 */
	public Float getAlpha() {
		return alpha;
	}

	/**
	 * Sets the alpha.
	 * 
	 * @param alpha
	 *            the alpha
	 */
	public void setAlpha(Float alpha) {
		this.alpha = alpha;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.elements.Element.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (alpha != null) json.put("alpha", new JSONNumber(alpha));
		if (colour != null) json.put("colour", new JSONString(colour));
		return json;
	}

	/**
	 * Base class for OFC shape points
	 */
	public static class Point implements JSONizable {

		/** The x. */
		private Number x;

		/** The y. */
		private Number y;

		/**
		 * Creates a new point.
		 * 
		 * @param x
		 *            the x
		 * @param y
		 *            the y
		 */
		public Point(Number x, Number y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * Gets the x.
		 * 
		 * @return the x
		 */
		public Number getX() {
			return x;
		}

		/**
		 * Sets the x.
		 * 
		 * @param x
		 *            the new x
		 */
		public void setX(Number x) {
			this.x = x;
		}

		/**
		 * Gets the y.
		 * 
		 * @return the y
		 */
		public Number getY() {
			return y;
		}

		/**
		 * Sets the y.
		 * 
		 * @param y
		 *            the new y
		 */
		public void setY(Number y) {
			this.y = y;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
		 */
		public JSONValue buildJSON() {
			JSONObject json = new JSONObject();
			if (x != null) json.put("x", new JSONNumber(x.doubleValue()));
			if (y != null) json.put("y", new JSONNumber(y.doubleValue()));
			return json;
		}
	}
}
