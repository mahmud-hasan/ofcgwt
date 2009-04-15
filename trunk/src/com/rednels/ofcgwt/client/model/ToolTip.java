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
package com.rednels.ofcgwt.client.model;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * OFC tooltip
 */
public class ToolTip implements JSONizable {

	public static enum MouseStyle {

		CLOSEST(0), NORMAL(1), FOLLOW(2);

		private int style;

		MouseStyle(int style) {
			this.style = style;
		}

		public int getStyle() {
			return style;
		}
	}

	private String titlestyle;
	private String bodystyle;
	private String colour;
	private String backgroundcolour;
	private MouseStyle mouse;
	private Boolean shadow;

	private Integer stroke;

	/**
	 * Create a new ToolTip instance
	 */
	public ToolTip() {}

	/**
	 * Create a new ToolTip instance with the given mouse style
	 * 
	 * @param mouse
	 *            MouseStyle
	 */
	public ToolTip(MouseStyle mouse) {
		setMouse(mouse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = new JSONObject();
		if (titlestyle != null) json.put("title", new JSONString(titlestyle));
		if (bodystyle != null) json.put("body", new JSONString(bodystyle));
		if (colour != null) json.put("colour", new JSONString(colour));
		if (backgroundcolour != null) json.put("background", new JSONString(backgroundcolour));
		if (mouse != null) json.put("mouse", new JSONNumber(mouse.getStyle()));
		if (stroke != null) json.put("stroke", new JSONNumber(stroke));
		if (shadow != null) json.put("shadow", JSONBoolean.getInstance(shadow));
		return json;
	}

	/**
	 * Gets the background colour.
	 * 
	 * @return the backgroundcolour
	 */
	public String getBackgroundcolour() {
		return backgroundcolour;
	}

	/**
	 * Gets the body style.
	 * 
	 * @return the bodystyle
	 */
	public String getBodystyle() {
		return bodystyle;
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
	 * Gets the mouse.
	 * 
	 * @return the mouse
	 */
	public MouseStyle getMouse() {
		return mouse;
	}

	/**
	 * Gets the shadow.
	 * 
	 * @return the shadow
	 */
	public Boolean getShadow() {
		return shadow;
	}

	/**
	 * Gets the stroke.
	 * 
	 * @return the stroke
	 */
	public Integer getStroke() {
		return stroke;
	}

	/**
	 * Gets the title style.
	 * 
	 * @return the titlestyle
	 */
	public String getTitlestyle() {
		return titlestyle;
	}

	/**
	 * Sets the background colour.
	 * 
	 * @param backgroundcolour
	 *            the background colour to set
	 */
	public void setBackgroundcolour(String backgroundcolour) {
		this.backgroundcolour = backgroundcolour;
	}

	/**
	 * Sets the body style.
	 * 
	 * @param bodystyle
	 *            the body style to set
	 */
	public void setBodystyle(String bodystyle) {
		this.bodystyle = bodystyle;
	}

	/**
	 * Sets the colour.
	 * 
	 * @param colour
	 *            the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * Sets the mouse.
	 * 
	 * @param mouse
	 *            the mouse to set
	 */
	public void setMouse(MouseStyle mouse) {
		this.mouse = mouse;
	}

	/**
	 * Sets the shadow.
	 * 
	 * @param shadow
	 *            the shadow
	 */
	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	/**
	 * Sets the stroke.
	 * 
	 * @param stroke
	 *            the stroke to set
	 */
	public void setStroke(Integer stroke) {
		this.stroke = stroke;
	}

	/**
	 * Sets the title style.
	 * 
	 * @param titlestyle
	 *            the title style to set
	 */
	public void setTitlestyle(String titlestyle) {
		this.titlestyle = titlestyle;
	}
}
