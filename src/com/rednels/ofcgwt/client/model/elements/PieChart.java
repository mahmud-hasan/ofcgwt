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
import com.rednels.ofcgwt.client.model.JSONizable;

public class PieChart extends Element implements JSONizable{
    private Integer startAngle; 
    private Collection<String> colours;
    private Boolean animate;
    private Boolean gradientFill;
    private Boolean nolabels;
    private Integer border;
    
    public PieChart() {
        super("pie");
    }
    
    public PieChart setAnimate(boolean animate) {
        this.animate = animate;
        return this;
    }

	public Boolean getAnimate() {
        return animate;
    }
    
    public PieChart setGradientFill(boolean gradientFill) {
		this.gradientFill = gradientFill;
        return this;
	}

	public Boolean getGradientFill() {
		return gradientFill;
	}
    
    public void setNoLabels(boolean nolabels) {
		this.nolabels = nolabels;
	}

	public Boolean getNoLabels() {
		return nolabels;
	}

    public Integer getStartAngle() {
        return startAngle;
    }

    public PieChart setStartAngle(Integer startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    public Collection<String> getColours() {
        return colours;
    }

    public PieChart setColours(Collection<String> colours) {
        checkColours();
        this.colours = colours;
        return this;
    }
    
    public PieChart setColours(String... colours) {
        checkColours();
        this.colours.clear();
        this.colours.addAll(Arrays.asList(colours));
        return this;
    }
    
    public PieChart setColours(List<String> colours) {
        checkColours();
        this.colours.clear();
        this.colours.addAll(colours);
        return this;
    }
    
    public Integer getBorder() {
        return border;
    }
    
    public PieChart setBorder(Integer border) {
        this.border = border;
        return this;
    }

    public PieChart addValues(Number... values) {
        getValues().addAll(Arrays.asList(values));
        return this;
    }
    
    public PieChart addValues(List<Number> values) {
        getValues().addAll(values);
        return this;
    }
    
    public PieChart addSlice(Number value, String text) {
        return addSlices(new Slice(value, text));
    }
    
    public PieChart addSlices(Slice... s) {
        getValues().addAll(Arrays.asList(s));
        return this;
    }
    
    public PieChart addSlices(List<Slice> values) {
        getValues().addAll(values);
        return this;
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (startAngle != null) json.put("start-angle", new JSONNumber(startAngle.doubleValue()));
    	if (animate != null) json.put("animate", JSONBoolean.getInstance(animate));   
    	if (gradientFill != null) json.put("gradient-fill", JSONBoolean.getInstance(gradientFill)); 
    	if (nolabels != null) json.put("nolabels", JSONBoolean.getInstance(nolabels));    
    	if (border != null) json.put("border", new JSONNumber(border.doubleValue()));
    	if (colours == null) return json;
    	JSONArray ary = new JSONArray();
    	int index = 0;
    	for (String s : colours) {
    		ary.set(index++, new JSONString(s));
        }
    	if (index != 0) json.put("colours",ary);	
    	return json;
	}
	
    public static class Slice implements JSONizable{
        private final String label;        
        private final Number value;
        private String labelColour;
        private String fontSize;
        
        public Slice(Number value, String text) {
            this.label = text;
            this.value = value;
        }
        
        public void setLabelColour(String labelColour) {
			this.labelColour = labelColour;
		}

		public String getLabelColour() {
			return labelColour;
		}

		public void setFontSize(String fontSize) {
			this.fontSize = fontSize;
		}

		public String getFontSize() {
			return fontSize;
		}

		public Number getValue() {
            return value;
        }
        
        public String getText() {
            return label;
        }

    	public JSONObject buildJSONObject() {
        	JSONObject json = new JSONObject();
        	if (value != null) json.put("value", new JSONNumber(value.doubleValue())); 	
        	if (label != null) json.put("label", new JSONString(label));
        	if (labelColour != null) json.put("label-colour", new JSONString(labelColour));
        	if (fontSize != null) json.put("font-size", new JSONString(fontSize));
        	return json;
    	}
    }
    
    private synchronized void checkColours() {
        if (colours == null) colours = new ArrayList<String>();
    }
}
