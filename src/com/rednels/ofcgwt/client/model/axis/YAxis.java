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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * Class for an OFC y-axis that extends AbstractAxis
 * 
 * @see com.rednels.ofcgwt.client.model.axis.AbstractAxis
 */
public class YAxis extends AbstractAxis implements JSONizable {

	/** The tick length. */
	private Integer tickLength;

	/** The labels. */
	private List<String> labels;

	/**
	 * Sets the tick length.
	 * 
	 * @param tick_length
	 *            the new tick length
	 */
	public void setTickLength(Integer tick_length) {
		this.tickLength = tick_length;
	}

	/**
	 * Gets the tick length.
	 * 
	 * @return the tick length
	 */
	public Integer getTickLength() {
		return tickLength;
	}

	/**
	 * Sets the labels.
	 * 
	 * @param labels
	 *            the new labels
	 */
	public void setLabels(String... labels) {
		setLabels(Arrays.asList(labels));
	}

	/**
	 * Sets the labels.
	 * 
	 * @param labels
	 *            the new labels
	 */
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	/**
	 * Adds the labels.
	 * 
	 * @param labels
	 *            the labels
	 */
	public void addLabels(String... labels) {
		checkLabelsNotNull();
		this.labels.addAll(Arrays.asList(labels));
	}

	/**
	 * Adds the labels.
	 * 
	 * @param labels
	 *            the labels
	 */
	public void addLabels(List<String> labels) {
		checkLabelsNotNull();
		this.labels.addAll(labels);
	}

	/**
	 * Gets the labels.
	 * 
	 * @return the labels
	 */
	public List<String> getLabels() {
		return labels;
	}

	/**
	 * Check labels not null.
	 */
	private synchronized void checkLabelsNotNull() {
		if (labels == null) labels = new ArrayList<String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.axis.AbstractAxis.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (tickLength != null) json.put("tick-length", new JSONNumber(tickLength));
		if (labels == null) return json;
		JSONArray ary = new JSONArray();
		int index = 0;
		for (String o : labels) {
			ary.set(index++, new JSONString(o));
		}
		if (index != 0) json.put("labels", ary);
		return json;
	}
}
