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

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * Base class for an OFC keys
 */
public class Keys implements JSONizable {

	/** The text. */
	private String text;

	/** The colour. */
	private String colour;

	/** The font-size. */
	private Integer fontsize;

	/**
	 * Creates a new key.
	 * 
	 * @param text
	 *            the text
	 */
	public Keys(String text, String colour, Integer fontsize) {
		setText(text);
		setColour(colour);
		setFontSize(fontsize);
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
	 * Sets the text.
	 * 
	 * @param text
	 *            the text
	 */
	public void setText(String text) {
		this.text = text;
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
	 *            the colour
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * Gets the font size.
	 * 
	 * @return the font size
	 */
	public Integer getFontSize() {
		return fontsize;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontsize
	 *            the fontsize
	 */
	public void setFontSize(Integer fontsize) {
		this.fontsize = fontsize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = new JSONObject();
		if (text != null) json.put("text", new JSONString(text));
		if (colour != null) json.put("colour", new JSONString(colour));
		if (fontsize != null) json.put("font-size", new JSONNumber(fontsize));
		return json;
	}
}
