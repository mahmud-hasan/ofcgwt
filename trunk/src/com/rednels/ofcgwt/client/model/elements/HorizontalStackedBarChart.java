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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.event.DataValueEvents;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * OFC horizontal stacked bar chart
 */
public class HorizontalStackedBarChart extends Element implements JSONizable {

	/**
	 * OFC horizontal stack bar
	 */
	public static class HStack implements JSONizable {

		private transient ArrayList<Object> values;

		/**
		 * Creates a new stack.
		 */
		public HStack() {
			values = new ArrayList<Object>();
		}

		/**
		 * Creates a new stack with values.
		 */
		public HStack(StackValue... values) {
			this.values = new ArrayList<Object>();
			this.addStackValues(values);
		}

		/**
		 * Adds the stack values.
		 * 
		 * @param values
		 *            the values
		 */
		public void addStackValues(StackValue... values) {
			this.values.addAll(Arrays.asList(values));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
		 */
		public JSONValue buildJSON() {
			JSONArray ary = new JSONArray();
			if (values == null) return ary;
			int index = 0;
			for (Object o : values) {
				ary.set(index++, ((StackValue) o).buildJSON());
			}
			return ary;
		}

		/**
		 * Gets the values.
		 * 
		 * @return the values
		 */
		public List<Object> getValues() {
			return this.values;
		}
	}

	/**
	 * OFC stack bar chart values
	 */
	public static class StackValue extends DataValueEvents implements JSONizable {

		private Number left;
		private Number right;
		private String colour;
		private String text;

		/**
		 * Creates a new stack value.
		 * 
		 * @param left
		 *            the value
		 * @param right
		 *            the value
		 */
		public StackValue(Number left, Number right) {
			this(left, right, null);
		}

		/**
		 * Creates a new stack value.
		 * 
		 * @param left
		 *            the value
		 * @param right
		 *            the value
		 * @param colour
		 *            the colour
		 */
		public StackValue(Number left, Number right, String colour) {
			this(left, right, colour, null);
		}

		/**
		 * Creates a new stack value.
		 * 
		 * @param left
		 *            the value
		 * @param right
		 *            the value
		 * @param colour
		 *            the colour
		 * @param text
		 *            the text
		 */
		public StackValue(Number left, Number right, String colour, String text) {
			setLeft(left);
			setRight(right);
			setColour(colour);
			setText(text);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
		 */
		public JSONValue buildJSON() {
			JSONObject json = new JSONObject();
			if (left != null) json.put("left", new JSONNumber(left.doubleValue()));
			if (right != null) json.put("right", new JSONNumber(right.doubleValue()));
			if (colour != null) json.put("colour", new JSONString(colour));
			if (text != null) json.put("text", new JSONString(text));
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
		 * Gets the left value.
		 * 
		 * @return the value
		 */
		public Number getLeft() {
			return left;
		}

		/**
		 * Gets the right value.
		 * 
		 * @return the value
		 */
		public Number getRight() {
			return right;
		}

		/**
		 * Gets the text.
		 * 
		 * @return the text
		 */
		public String getText() {
			return text;
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
		 * Sets the left value.
		 * 
		 * @param left
		 *            the new value
		 */
		public void setLeft(Number left) {
			this.left = left;
		}

		/**
		 * Sets the right value.
		 * 
		 * @param right
		 *            the new value
		 */
		public void setRight(Number right) {
			this.right = right;
		}

		/**
		 * Sets the text
		 * 
		 * @param text
		 *            the new text
		 */
		public void setText(String text) {
			this.text = text;
		}
	}

	private Number barwidth;

	/**
	 * Creates a new stacked bar chart.
	 */
	public HorizontalStackedBarChart() {
		super("hbar_stack");
	}

	/**
	 * Adds the stack.
	 * 
	 * @param stacks
	 *            the stacks
	 */
	public void addStack(HStack... stacks) {
		addStack(Arrays.asList(stacks));
	}

	/**
	 * Adds the stack.
	 * 
	 * @param stacks
	 *            the stacks
	 */
	public void addStack(List<HStack> stacks) {
		values.addAll(stacks);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.elements.Element.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (barwidth != null) json.put("barwidth", new JSONNumber(barwidth.doubleValue()));
		return json;
	}

	/**
	 * Gets the barwidth.
	 * 
	 * @return the barwidth
	 */
	public Number getBarwidth() {
		return barwidth;
	}

	/**
	 * Sets the barwidth.
	 * 
	 * @param barwidth
	 *            the new barwidth
	 */
	public void setBarwidth(Number barwidth) {
		this.barwidth = barwidth;
	}
}
