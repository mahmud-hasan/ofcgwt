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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.IChartData;
import com.rednels.ofcgwt.client.IOnClickListener;
import com.rednels.ofcgwt.client.model.JSONizable;
import com.rednels.ofcgwt.client.model.axis.Keys;

/**
 * Base abstract class for OFC elements
 */
public abstract class Element implements JSONizable {

	/** The type. */
	private final String type;

	/** The text. */
	private String text;

	/** The font size. */
	private Integer fontSize;

	/** The tooltip. */
	private String tooltip;

	/** The onClick. */
	private String onClick;

	/** The keyOnClick. */
	private String keyOnClick;

	/** The keys. */
	private List<Keys> keys = new ArrayList<Keys>();

	/** The values. */
	protected List<Object> values = new ArrayList<Object>();

	/**
	 * Creates a new element.
	 * 
	 * @param type
	 *            the type
	 */
	protected Element(String type) {
		this.type = type;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
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
	 * Gets the font size.
	 * 
	 * @return the font size
	 */
	public Integer getFontSize() {
		return fontSize;
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize
	 *            the font size
	 */
	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * Gets the onClick.
	 * 
	 * @return the onClick
	 */
	public String getOnClick() {
		return onClick;
	}

	/**
	 * Sets the onClick.
	 * 
	 * @param onClick
	 *            the onClick javascript method or url
	 */
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	/**
	 * Gets the keyOnClick.
	 * 
	 * @return the keyOnClick
	 */
	public String getKeyOnClick() {
		return keyOnClick;
	}

	/**
	 * Sets the keyOnClick.
	 * 
	 * @param keyOnClick
	 *            the keyOnClick javascript method or url
	 */
	public void setKeyOnClick(String keyOnClick) {
		this.keyOnClick = keyOnClick;
	}

	/**
	 * If true, sets keyOnClick to toggle visibility
	 * 
	 * @param toggleKeyOnClick
	 *            boolean
	 */
	public void setKeyToggleOnClick(boolean toggleKeyOnClick) {
		if (toggleKeyOnClick)
			this.keyOnClick = "toggle-visibility";
		else
			this.keyOnClick = null;
	}

	/**
	 * Adds an onClick event. Requires an ChartWidget to register the event
	 * with.
	 * 
	 * @param chart
	 *            the IChartData
	 * @param listener
	 *            the onClick Listener
	 */
	public void addOnClickListener(IChartData chart, IOnClickListener listener) {
		this.onClick = chart.addOnClickListener(listener);
	}

	/**
	 * Adds an onClick event. Requires an ChartWidget to register the event
	 * with.
	 * 
	 * @param chart
	 *            the IChartData
	 * @param listener
	 *            the onClick Listener
	 */
	public void addKeyOnClickListener(IChartData chart, IOnClickListener listener) {
		this.keyOnClick = chart.addOnClickListener(listener);
	}

	/**
	 * Gets the values.
	 * 
	 * @return the values
	 */
	public List<Object> getValues() {
		return values;
	}

	public void addNull() {
		values.add(null);
	}

	/**
	 * Sets the stack keys.
	 * 
	 * @param keys
	 *            the keys
	 */
	public void setKeys(List<Keys> keys) {
		this.keys.clear();
		this.keys.addAll(keys);
	}

	/**
	 * Sets the stack keys.
	 * 
	 * @param keys
	 *            the keys
	 */
	public void setKeys(Keys... keys) {
		setKeys(Arrays.asList(keys));
	}

	/**
	 * Sets the values.
	 * 
	 * @param values
	 *            the values
	 */
	@SuppressWarnings("unchecked")
	public void setValues(Collection values) {
		this.values.clear();
		this.values.addAll(values);
	}

	/**
	 * Sets the tooltip text (#val# is the default)
	 * 
	 * @param tooltip
	 *            the tooltip
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * Gets the tooltip.
	 * 
	 * @return the tooltip
	 */
	public String getTooltip() {
		return tooltip;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = new JSONObject();
		if (type != null) json.put("type", new JSONString(type));
		if (text != null) json.put("text", new JSONString(text));
		if (fontSize != null) json.put("font-size", new JSONNumber(fontSize));
		if (tooltip != null) json.put("tip", new JSONString(tooltip));
		if (onClick != null) json.put("on-click", new JSONString(onClick));
		if (keyOnClick != null) json.put("key-on-click", new JSONString(keyOnClick));
		
		JSONArray ary = new JSONArray();
		int index = 0;
		for (Keys k : keys) {			
			ary.set(index++, k.buildJSON());			
		}
		if (index != 0) json.put("keys", ary);
		
		if (values == null) return json;		
		ary = new JSONArray();
		index = 0;
		for (Object o : values) {
			if (o == null) ary.set(index++, null);
			if (o instanceof Number) ary.set(index++, new JSONNumber(((Number) o).doubleValue()));
			if (o instanceof String) ary.set(index++, new JSONString((String) o));
			if (o instanceof JSONizable) ary.set(index++, ((JSONizable) o).buildJSON());
		}
		if (index != 0) json.put("values", ary);
		return json;
	}
}
