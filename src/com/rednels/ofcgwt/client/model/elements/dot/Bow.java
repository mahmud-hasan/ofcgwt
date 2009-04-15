package com.rednels.ofcgwt.client.model.elements.dot;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

/**
 * OFC Star
 */
public class Bow extends BaseDot {

	private Integer rotation;

	public Bow() {
		this(null);
	}

	public Bow(Number value) {
		super("bow", value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (rotation != null) json.put("rotation", new JSONNumber(rotation.intValue()));
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
	 * Sets the rotation.
	 * 
	 * @param rotation
	 *            the rotation
	 */
	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}
}