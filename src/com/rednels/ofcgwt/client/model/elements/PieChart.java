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

import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.event.EventElement;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * OFC pie chart
 */
public class PieChart extends Element implements JSONizable {

	/**
	 * OFC pie slices
	 */
	public static class Slice extends EventElement implements JSONizable {

		private final String label;
		private final Number value;
		private String labelColour;
		private String fontSize;
		private String tooltip;
		private PieAnimation animate;

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
			this.value = value;
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
			if (labelColour != null) json.put("label-colour", new JSONString(labelColour));
			if (fontSize != null) json.put("font-size", new JSONString(fontSize));
			if (onClick != null) json.put("on-click", new JSONString(onClick));
			if (tooltip != null) json.put("tip", new JSONString(tooltip));
			if (animate != null) json.put("animate", animate.buildJSON());
			return json;
		}

		/**
		 * Gets the animation.
		 * 
		 * @return the animation
		 */
		public PieAnimation getAnimation() {
			return animate;
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
		 * Gets the text.
		 * 
		 * @return the text
		 */
		public String getLabel() {
			return label;
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
		 * Gets the tooltip.
		 * 
		 * @return the tooltip
		 */
		public String getTooltip() {
			return tooltip;
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
		 * Sets the animation.
		 * 
		 * @param animate
		 *            the new animate
		 */
		public void setAnimation(PieAnimation animate) {
			this.animate = animate;
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
		 * Sets the label colour.
		 * 
		 * @param labelColour
		 *            the new label colour
		 */
		public void setLabelColour(String labelColour) {
			this.labelColour = labelColour;
		}

		/**
		 * Sets the tooltip.
		 * 
		 * @param tooltip
		 *            the new tooltip
		 */
		public void setTooltip(String tooltip) {
			this.tooltip = tooltip;
		}
	}

	interface PieAnimation extends JSONizable {}

	public class PieFadeAnimation implements PieAnimation {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
		 */
		public JSONValue buildJSON() {
			JSONObject json = new JSONObject();
			json.put("type", new JSONString("fade"));
			return json;
		}
	}

	public class PieBounceAnimation implements PieAnimation {

		private Number distance;

		public PieBounceAnimation(Integer distance) {
			setDistance(distance);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
		 */
		public JSONValue buildJSON() {
			JSONObject json = new JSONObject();
			if (distance != null) json.put("distance", new JSONNumber(distance.intValue()));
			json.put("type", new JSONString("bounce"));
			return json;
		}

		/**
		 * Gets the distance.
		 * 
		 * @return the distance
		 */
		public Number getDistance() {
			return distance;
		}

		/**
		 * Sets the distance
		 * 
		 * @param distance
		 *            the distance to set
		 */
		public void setDistance(Integer distance) {
			this.distance = distance;
		}
	}

	private Integer startAngle;
	private Integer radius;
	private String labelColour;
	private Float alpha;
	private Boolean alphaHighlight;
	private PieAnimation animate;
	private Boolean gradientFill;
	private Boolean nolabels;
	private Integer border;

	/**
	 * Creates a new pie chart.
	 */
	public PieChart() {
		super("pie");
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
	 * @param slices
	 *            the slices
	 */
	public void addSlices(List<Slice> slices) {
		getValues().addAll(slices);
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
	 * Adds the values.
	 * 
	 * @param values
	 *            the values
	 */
	public void addValues(List<Number> values) {
		getValues().addAll(values);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.elements.Element.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (startAngle != null) json.put("start-angle", new JSONNumber(startAngle.intValue()));
		if (radius != null) json.put("radius", new JSONNumber(radius.intValue()));
		if (alphaHighlight != null && alphaHighlight) json.put("highlight", new JSONString("alpha"));
		if (gradientFill != null) json.put("gradient-fill", JSONBoolean.getInstance(gradientFill));
		if (alpha != null) json.put("alpha", new JSONNumber(alpha));
		if (nolabels != null) json.put("no-labels", JSONBoolean.getInstance(nolabels));
		if (labelColour != null) json.put("label-colour", new JSONString(labelColour));
		if (border != null) json.put("border", new JSONNumber(border.doubleValue()));
		JSONArray ary = new JSONArray();
		int index = 0;
		if (animate != null) ary.set(index++, animate.buildJSON());
		if (index != 0) json.put("animate", ary);

		// TODO this is the current on-show animation support to stop build
		// animation in pie
		if (animate == null) json.put("animate", JSONBoolean.getInstance(false));
		return json;
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
	 * Gets the alphaHighlight value
	 * 
	 * @return true if alphaHighlight is enabled
	 */
	public Boolean getAlphaHighlight() {
		return alphaHighlight;
	}

	/**
	 * Gets the animation.
	 * 
	 * @return the animation
	 */
	public PieAnimation getAnimation() {
		return animate;
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
	 * Gets the gradient fill.
	 * 
	 * @return true if gradient fill is enabled
	 */
	public Boolean getGradientFill() {
		return gradientFill;
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
	 * Gets the label colour.
	 * 
	 * @return the label colour
	 */
	public String getLabelColour() {
		return labelColour;
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
	 * @return the radius
	 */
	public Integer getRadius() {
		return radius;
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
	 * Sets alpha colour toggle as the highlight, or slice slide out animation
	 * 
	 * @param alphaHighlight
	 *            true or false
	 */
	public void setAlphaHighlight(boolean alphaHighlight) {
		this.alphaHighlight = alphaHighlight;
	}

	/**
	 * Sets if animation of slice rotation build is enabled
	 * 
	 * @param animate
	 *            true or false
	 */
	public void setAnimate(boolean animate) {
		if (animate) {
			setAnimation(new PieBounceAnimation(10));
		}
		else {
			this.animate = null;
		}
	}

	/**
	 * Sets the animation.
	 * 
	 * @param animate
	 *            the new animate
	 */
	public void setAnimation(PieAnimation animate) {
		this.animate = animate;
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
	 * Sets the gradient fill.
	 * 
	 * @param gradientFill
	 *            true or false
	 */
	public void setGradientFill(boolean gradientFill) {
		this.gradientFill = gradientFill;
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
	 * Sets the label colour.
	 * 
	 * @param labelColour
	 *            the new label colour
	 */
	public void setLabelColour(String labelColour) {
		this.labelColour = labelColour;
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
	 * @param radius
	 *            the radius to set
	 */
	public void setRadius(Integer radius) {
		this.radius = radius;
	}
}
