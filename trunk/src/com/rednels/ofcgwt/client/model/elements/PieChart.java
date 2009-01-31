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
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.IChartData;
import com.rednels.ofcgwt.client.IOnClickListener;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * Class for an OFC pie chart that extends Element
 * 
 * @see com.rednels.ofcgwt.client.model.elements.Element
 */
public class PieChart extends Element implements JSONizable {

	/** The start angle. */
	private Integer startAngle;

	/** The colours. */
	private Collection<String> colours;

	/** The alpha. */
	private Float alpha;
	
	/** The alphaHighlight. */
	private Boolean alphaHighlight;

	/** The animate. */
	private Boolean animate;

	/** The gradient fill. */
	private Boolean gradientFill;

	/** The nolabels. */
	private Boolean nolabels;

	/** The border. */
	private Integer border;

	/**
	 * Creates a new pie chart.
	 */
	public PieChart() {
		super("pie");
	}

	/**
	 * Sets alpha colour toggle as the highlight, or slice slide out animation
	 * 
	 * @param alphaHighlight
	 *            true or false
	 */
	public void setAlphaHighlight(boolean alphaHighlight) {
		this.alphaHighlight = alphaHighlight;
	}

	/**
	 * Gets the alphaHighlight value
	 * 
	 * @return true if alphaHighlight is enabled
	 */
	public Boolean getAlphaHighlight() {
		return alphaHighlight;
	}

	/**
	 * Sets if animation of slice rotation build is enabled 
	 * 
	 * @param animate
	 *            true or false
	 */
	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	/**
	 * Gets the animation value
	 * 
	 * @return true if animate is enabled
	 */
	public Boolean getAnimate() {
		return animate;
	}

	/**
	 * Sets the gradient fill.
	 * 
	 * @param gradientFill
	 *            true or false
	 */
	public void setGradientFill(boolean gradientFill) {
		this.gradientFill = gradientFill;
	}

	/**
	 * Gets the gradient fill.
	 * 
	 * @return true if gradient fill is enabled
	 */
	public Boolean getGradientFill() {
		return gradientFill;
	}

	/**
	 * Sets the no labels.
	 * 
	 * @param nolabels
	 *            true or false
	 */
	public void setNoLabels(boolean nolabels) {
		this.nolabels = nolabels;
	}

	/**
	 * Gets the no labels value
	 * 
	 * @return true if no labels is enabled
	 */
	public Boolean getNoLabels() {
		return nolabels;
	}

	/**
	 * Gets the start angle.
	 * 
	 * @return the start angle
	 */
	public Integer getStartAngle() {
		return startAngle;
	}

	/**
	 * Sets the start angle.
	 * 
	 * @param startAngle
	 *            the new start angle
	 */
	public void setStartAngle(Integer startAngle) {
		this.startAngle = startAngle;
	}

	/**
	 * Gets the alpha.
	 * 
	 * @return the alpha
	 */
	public Float getAlpha() {
		return alpha;
	}

	/**
	 * Sets the alpha.
	 * 
	 * @param alpha
	 *            the alpha
	 */
	public void setAlpha(Float alpha) {
		this.alpha = alpha;
	}

	/**
	 * Gets the colours.
	 * 
	 * @return the colours
	 */
	public Collection<String> getColours() {
		return colours;
	}

	/**
	 * Sets colours in HTML hex format (#ffffff)
	 * 
	 * @param colours
	 *            the new colours
	 */
	public void setColours(Collection<String> colours) {
		checkColours();
		this.colours = colours;
	}

	/**
	 * Sets colours in HTML hex format (#ffffff)
	 * 
	 * @param colours
	 *            the new colours
	 */
	public void setColours(String... colours) {
		checkColours();
		this.colours.clear();
		this.colours.addAll(Arrays.asList(colours));
	}

	/**
	 * Sets the colours.
	 * 
	 * @param colours
	 *            the new colours
	 */
	public void setColours(List<String> colours) {
		checkColours();
		this.colours.clear();
		this.colours.addAll(colours);
	}

	/**
	 * Gets the border.
	 * 
	 * @return the border
	 */
	public Integer getBorder() {
		return border;
	}

	/**
	 * Sets the border.
	 * 
	 * @param border
	 *            the new border
	 */
	public void setBorder(Integer border) {
		this.border = border;
	}

	/**
	 * Adds values.
	 * 
	 * @param values
	 *            the values
	 */
	public void addValues(Number... values) {
		getValues().addAll(Arrays.asList(values));
	}

	/**
	 * Adds the values.
	 * 
	 * @param values
	 *            the values
	 */
	public void addValues(List<Number> values) {
		getValues().addAll(values);
	}

	/**
	 * Adds a slice.
	 * 
	 * @param value
	 *            the value
	 * @param text
	 *            the text
	 */
	public void addSlice(Number value, String text) {
		addSlices(new Slice(value, text));
	}

	/**
	 * Adds slices.
	 * 
	 * @param slice
	 *            the slice
	 */
	public void addSlices(Slice... slice) {
		getValues().addAll(Arrays.asList(slice));
	}

	/**
	 * Adds slices.
	 * 
	 * @param slices
	 *            the slices
	 */
	public void addSlices(List<Slice> slices) {
		getValues().addAll(slices);
	}

	/**
	 * Check colours.
	 */
	private synchronized void checkColours() {
		if (colours == null) colours = new ArrayList<String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.elements.Element.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (startAngle != null) json.put("start-angle", new JSONNumber(startAngle.doubleValue()));		
		if (alphaHighlight != null && alphaHighlight) json.put("highlight", new JSONString("alpha"));
		if (animate != null) json.put("animate", JSONBoolean.getInstance(animate));
		if (gradientFill != null) json.put("gradient-fill", JSONBoolean.getInstance(gradientFill));
		if (alpha != null) json.put("alpha", new JSONNumber(alpha));
		if (nolabels != null) json.put("no-labels", JSONBoolean.getInstance(nolabels));
		if (border != null) json.put("border", new JSONNumber(border.doubleValue()));
		if (colours == null) return json;
		JSONArray ary = new JSONArray();
		int index = 0;
		for (String s : colours) {
			ary.set(index++, new JSONString(s));
		}
		if (index != 0) json.put("colours", ary);
		return json;
	}

	/**
	 * Base class for OFC pie slices
	 */
	public static class Slice implements JSONizable {

		/** The label. */
		private final String label;

		/** The text. */
		private final String text;

		/** The value. */
		private final Number value;

		/** The label colour. */
		private String labelColour;

		/** The font size. */
		private String fontSize;

		/** The onClick. */
		private String onClick;

		/**
		 * Creates a new slice.
		 * 
		 * @param value
		 *            the value
		 * @param label
		 *            the label
		 */
		public Slice(Number value, String label) {
			this.label = label;
			this.text = label;
			this.value = value;
		}
		
		/**
		 * Creates a new slice.
		 * 
		 * @param value
		 *            the value
		 * @param label
		 *            the label
		 * @param text
		 *            the text
		 */
		public Slice(Number value, String label,String text) {
			this.label = label;
			this.text = text;
			this.value = value;
		}

		/**
		 * Sets the label colour.
		 * 
		 * @param labelColour
		 *            the new label colour
		 */
		public void setLabelColour(String labelColour) {
			this.labelColour = labelColour;
		}

		/**
		 * Gets the label colour.
		 * 
		 * @return the label colour
		 */
		public String getLabelColour() {
			return labelColour;
		}

		/**
		 * Sets the font size.
		 * 
		 * @param fontSize
		 *            the new font size
		 */
		public void setFontSize(String fontSize) {
			this.fontSize = fontSize;
		}

		/**
		 * Gets the font size.
		 * 
		 * @return the font size
		 */
		public String getFontSize() {
			return fontSize;
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
		 * Gets the value.
		 * 
		 * @return the value
		 */
		public Number getValue() {
			return value;
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
		 * Gets the text.
		 * 
		 * @return the text
		 */
		public String getLabel() {
			return label;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
		 */
		public JSONValue buildJSON() {
			JSONObject json = new JSONObject();
			if (value != null) json.put("value", new JSONNumber(value.doubleValue()));
			if (label != null) json.put("label", new JSONString(label));
			if (text != null) json.put("text", new JSONString(text));
			if (labelColour != null) json.put("label-colour", new JSONString(labelColour));
			if (fontSize != null) json.put("font-size", new JSONString(fontSize));
			if (onClick != null) json.put("on-click", new JSONString(onClick));
			return json;
		}
	}
}
