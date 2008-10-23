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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.axis.RadarAxis;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.Element;
/**
 * This is the most important class in the ofcgwt library. Start here,
 * configuring the title, axes, legends, labels, and draw-able elements in your
 * chart. You add an element to the chart data, for example...</br><pre>
 * ChartData cd = new ChartData("Sales by Region");
 * PieChart pie = new PieChart(); 
 * pie.addValues(10,30,40,20);
 * cd.addElements(pie); </pre>
 * 
 * When finished, call toString() and the GWT JSON objects will convert the
 * chart data into a formatted OFC2 JSON data string.
 */
public class ChartData implements JSONizable {
	private Text title;
	private ToolTip tooltip;
	private XAxis x_axis;
	private YAxis y_axis;
	private RadarAxis radar_axis;
	private YAxis y_axis_right;
	private Text y_legend;
	private Text x_legend;
	private String bg_colour;
	private final Collection<Element> elements = new HashSet<Element>();

	/**
	 * Creates a new chart data instance.
	 */
	public ChartData() {
	// nothing...
	}

	/**
	 * Creates a new chart data instance with the given title.
	 * @param titleText
	 */
	public ChartData(String titleText) {
		this(titleText, null);
	}

	/**
	 * Creates a new chart data instance with the given title and style.
	 * @param titleText
	 * @param style
	 */
	public ChartData(String titleText, String style) {
		setTitle(new Text(titleText, style));
	}

	/**
	 * Adds a collection of chart elements to the list of elements 
	 * @param collection of type Element
	 */
	public void addElements(Collection<Element> collection) {
		this.elements.addAll(collection);
	}

	/**
	 * Adds an element to the list of elements 
	 * @param e the element
	 */
	public void addElements(Element... e) {
		this.elements.addAll(Arrays.asList(e));
	}

	/* (non-Javadoc)
	 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	 */
	public JSONValue buildJSON() {
		final JSONObject json = new JSONObject();
		if (this.title != null) json.put("title", this.title.buildJSON());
		if (this.tooltip != null) json.put("tooltip", this.tooltip.buildJSON());
		if (this.x_axis != null) json.put("x_axis", this.x_axis.buildJSON());
		if (this.y_axis != null) json.put("y_axis", this.y_axis.buildJSON());
		if (this.y_axis_right != null) json.put("y_axis_right", this.y_axis_right.buildJSON());
		if (this.radar_axis != null) json.put("radar_axis", this.radar_axis.buildJSON());
		if (this.y_legend != null) json.put("y_legend", this.y_legend.buildJSON());
		if (this.x_legend != null) json.put("x_legend", this.x_legend.buildJSON());
		if (this.bg_colour != null) json.put("bg_colour", new JSONString(this.bg_colour));
		if (this.elements == null) return json;
		final JSONArray ary = new JSONArray();
		int index = 0;
		for (final Element e : this.elements) {
			ary.set(index++, e.buildJSON());
		}
		json.put("elements", ary);
		return json;
	}

	/**
	 * Get the current background colour
	 * @return String background colour
	 */
	public String getBackgroundColour() {
		return this.bg_colour;
	}

	/**
	 * Get the current elements collection
	 * @return Element collection
	 */
	public Collection<Element> getElements() {
		return this.elements;
	}

	/**
	 * Get the current title Text
	 * @return Text title 
	 */
	public Text getTitle() {
		return this.title;
	}

	/**
	 * Get the current XAxis object
	 * @return XAxis object
	 */
	public XAxis getXAxis() {
		if (this.x_axis == null) this.x_axis = new XAxis();
		return this.x_axis;
	}

	/**
	 * Get the current RadarAxis object
	 * @return RadarAxis object
	 */
	public RadarAxis getRadarAxis() {
		if (this.radar_axis == null) this.radar_axis = new RadarAxis();
		return this.radar_axis;
	}

	/**
	 * Get the current x legend Text
	 * @return Text x legend  
	 */
	public Text getXLegend() {
		return this.x_legend;
	}

	/**
	 * Get the current YAxis object (left side)
	 * @return YAxis object 
	 */
	public YAxis getYAxis() {
		if (this.y_axis == null) this.y_axis = new YAxis();
		return this.y_axis;
	}

	/**
	 * Get the current YAxis object (right side)
	 * @return YAxis object
	 */
	public YAxis getYAxisRight() {
		return this.y_axis_right;
	}

	/**
	 * Get the current y legend Text
	 * @return Text y legend
	 */
	public Text getYLegend() {
		return this.y_legend;
	}

	/**
	 * Removes an element from the list of elements
	 * @param e the element
	 * @return true if an element was removed as a result of this call
	 */
	public boolean removeElement(Element e) {
		return this.elements.remove(e);
	}

	/**
	 * Sets the chart background colour in HTML hex format (#ffffff) 
	 * @param bg_colour String colour
	 */
	public void setBackgroundColour(String bg_colour) {
		this.bg_colour = bg_colour;
	}

	/**
	 * Clears and then sets the list of elements to this collection
	 * @param elements Collection
	 */
	public void setElements(Collection<Element> elements) {
		this.elements.clear();
		this.elements.addAll(elements);
	}

	/**
	 * Sets the title to this Text object
	 * @param title Text object
	 */
	public void setTitle(Text title) {
		this.title = title;
	}

	/**
	 * Sets the XAxis to this XAxis object
	 * @param x_axis XAxis object
	 */
	public void setXAxis(XAxis x_axis) {
		this.x_axis = x_axis;
	}

	/**
	 * Sets the RadarAxis to this RadarAxis object
	 * @param radar_axis RadarAxis object
	 */
	public void setRadarAxis(RadarAxis radar_axis) {
		this.radar_axis = radar_axis;
	}

	/**
	 * Sets the x legend to this Text object
	 * @param x_legend Text object
	 */
	public void setXLegend(Text x_legend) {
		this.x_legend = x_legend;
	}

	/**
	 * Sets the left YAxis to this YAxis object
	 * @param y_axis YAxis object
	 */
	public void setYAxis(YAxis y_axis) {
		this.y_axis = y_axis;
	}

	/**
	 * Sets the right YAxis to this YAxis object
	 * @param y_axis_right YAxis object
	 */
	public void setYAxisRight(YAxis y_axis_right) {
		this.y_axis_right = y_axis_right;
	}

	/**
	 * Sets the y legend to this Text object
	 * @param y_legend Text object
	 */
	public void setYLegend(Text y_legend) {
		this.y_legend = y_legend;
	}

	/**
	 * Sets the tooltip.
	 * 
	 * @param tooltip the new tooltip
	 */
	public void setTooltip(ToolTip tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * Gets the tooltip.
	 * 
	 * @return the tooltip
	 */
	public ToolTip getTooltip() {
		return tooltip;
	}

	/**
	 * Returns the json formatted string of this chart data object. Calls.buildJSON()
	 * @return json string
	 */
	public String toString() {
		return buildJSON().toString();
	}
}
