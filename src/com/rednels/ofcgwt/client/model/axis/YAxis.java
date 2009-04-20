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
package com.rednels.ofcgwt.client.model.axis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * OFC y-axis
 */
public class YAxis extends AbstractAxis implements JSONizable {

	private Integer tickLength;
	private Boolean logscale;
	private List<String> labels;

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
	 * Adds the labels.
	 * 
	 * @param labels
	 *            the labels
	 */
	public void addLabels(String... labels) {
		checkLabelsNotNull();
		this.labels.addAll(Arrays.asList(labels));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.axis.AbstractAxis.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (tickLength != null) json.put("tick-length", new JSONNumber(tickLength));
		if (logscale != null) json.put("log-scale", JSONBoolean.getInstance(logscale));
		if (labels == null) return json;
		JSONArray ary = new JSONArray();
		int index = 0;
		for (String o : labels) {
			ary.set(index++, new JSONString(o));
		}
		if (index != 0) json.put("labels", ary);
		return json;
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
	 * Gets the log scale.
	 * 
	 * @return the logscale
	 */
	public Boolean getLogScale() {
		return logscale;
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
	public void setLabels(List<String> labels) {
		this.labels = labels;
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
	 * Sets the tick length.
	 * 
	 * @param tick_length
	 *            the new tick length
	 */
	public void setTickLength(Integer tick_length) {
		this.tickLength = tick_length;
	}

	/**
	 * Sets the log scale.
	 * 
	 * @param logscale
	 *            the logscale
	 */
	public void setVisible(Boolean logscale) {
		this.logscale = logscale;
	}

	/**
	 * Check labels not null.
	 */
	private synchronized void checkLabelsNotNull() {
		if (labels == null) labels = new ArrayList<String>();
	}
}
