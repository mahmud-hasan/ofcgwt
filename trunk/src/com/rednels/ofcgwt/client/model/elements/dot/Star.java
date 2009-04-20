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
package com.rednels.ofcgwt.client.model.elements.dot;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

/**
 * OFC Star
 */
public class Star extends BaseDot {

	private Integer rotation;
	private Boolean hollow;

	public Star() {
		this(null);
	}

	public Star(Number value) {
		super("star", value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (rotation != null) json.put("rotation", new JSONNumber(rotation.intValue()));
		if (hollow != null) json.put("hollow", JSONBoolean.getInstance(hollow));
		return json;
	}

	/**
	 * Gets the rotation.
	 * 
	 * @return the rotation
	 */
	public Integer getRotation() {
		return rotation;
	}

	/**
	 * @return true if hollow
	 */
	public boolean isHollow() {
		return hollow;
	}

	/**
	 * Sets the hollow.
	 * 
	 * @param hollow
	 *            the hollow
	 */
	public void setHollow(Boolean hollow) {
		this.hollow = hollow;
	}

	/**
	 * Sets the rotation.
	 * 
	 * @param rotation
	 *            the rotation
	 */
	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}
}