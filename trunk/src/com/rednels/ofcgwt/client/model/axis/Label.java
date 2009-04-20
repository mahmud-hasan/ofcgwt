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
package com.rednels.ofcgwt.client.model.axis;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * OFC label
 */
public class Label implements JSONizable {

	private String text;
	private String colour;
	private Integer size;
	private Integer rotateAngle;
	private Boolean visible;

	/**
	 * Creates a new label.
	 */
	public Label() {
		this(null);
	}

	/**
	 * Creates a new label.
	 * 
	 * @param text
	 *            the text
	 */
	public Label(String text) {
		setText(text);
	}

	/**
	 * Creates a new label.
	 * 
	 * @param text
	 *            the text
	 * @param angle
	 *            the rotation angle
	 */
	public Label(String text, Integer angle) {
		setText(text);
		setRotationAngle(angle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = new JSONObject();
		if (text != null)
			json.put("text", new JSONString(text));
		if (colour != null)
			json.put("colour", new JSONString(colour));
		if (size != null)
			json.put("size", new JSONNumber(size));
		if (rotateAngle != null)
			json.put("rotate", new JSONNumber(rotateAngle));
		if (visible != null)
			json.put("visible", JSONBoolean.getInstance(visible));
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
	 * Gets the rotation.
	 * 
	 * @return the rotation
	 */
	public Integer getRotationAngle() {
		return rotateAngle;
	}

	/**
	 * Gets the size.
	 * 
	 * @return the size
	 */
	public Integer getSize() {
		return size;
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
	 * Gets the visible.
	 * 
	 * @return the visible
	 */
	public Boolean getVisible() {
		return visible;
	}

	/**
	 * Sets the colour in HTML hex format (#ffffff)
	 * 
	 * @param colour
	 *            the colour
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * Sets the rotation in degrees (ie 45 = diagonal, 90 = vertical).
	 * 
	 * @param angle
	 *            the rotate angle
	 */
	public void setRotationAngle(Integer angle) {
		this.rotateAngle = angle;
	}

	/**
	 * Sets the size.
	 * 
	 * @param size
	 *            the size
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * Sets the text.
	 * 
	 * @param text
	 *            the text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Sets the visible.
	 * 
	 * @param visible
	 *            the visible
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}
