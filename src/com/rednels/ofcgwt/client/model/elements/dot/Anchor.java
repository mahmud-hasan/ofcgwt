package com.rednels.ofcgwt.client.model.elements.dot;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

/**
 * OFC Star
 */
public class Anchor extends BaseDot {

	private Integer rotation;
	private Integer sides;

	public Anchor() {
		this(null);
	}

	public Anchor(Number value) {
		super("anchor", value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (rotation != null) json.put("rotation", new JSONNumber(rotation.intValue()));
		if (sides != null) json.put("sides", new JSONNumber(sides.intValue()));
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

	/**
	 * Gets the sides.
	 * 
	 * @return the sides
	 */
	public Integer getSides() {
		return sides;
	}

	/**
	 * Sets the sides.
	 * 
	 * @param sides
	 *            the sides
	 */
	public void setSides(Integer sides) {
		this.sides = sides;
	}
}