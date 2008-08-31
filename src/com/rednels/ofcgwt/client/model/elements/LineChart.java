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

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;
/**
 * Class for an OFC line chart that extends Element   
 * @see com.rednels.ofcgwt.client.model.elements.Element
 */
public class LineChart extends Element implements JSONizable {
        
    /** The width. */
    private Integer width;
    
    /** The dot size. */
    private Integer dotSize; 
    
    /** The halo size. */
    private Integer haloSize; 
    
    /** The colour. */
    private String colour;
    
    /**
     * Creates a new line chart with normal style.
     */
    public LineChart() {
        this(LineStyle.NORMAL);
    }
    
    /**
     * Creates a new line chart.
     * 
     * @param style the style
     */
    public LineChart(LineStyle style) {
        this(style.getStyle());
    }
    
    /**
     * Creates a new line chart.
     * 
     * @param type the type
     */
    protected LineChart(String type) {
        super(type);
    }

    /**
     * Gets the width.
     * 
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Sets the width.
     * 
     * @param width the new width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Gets the dot size.
     * 
     * @return the dot size
     */
    public Integer getDotSize() {
        return dotSize;
    }

    /**
     * Sets the dot size.
     * 
     * @param dotSize the new dot size
     */
    public void setDotSize(Integer dotSize) {
        this.dotSize = dotSize;
    }

    /**
     * Gets the colour.
     * 
     * @return the colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * Sets the colour.
     * 
     * @param colour the new colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }
    
    /**
     * Adds the values.
     * 
     * @param values the values
     */
    public void addValues(Number... values) {
        addValues(Arrays.asList(values));
    }
    
    /**
     * Adds the values.
     * 
     * @param values the values
     */
    public void addValues(List<Number> values) {
        getValues().addAll(values);
    }
    
    /**
     * Adds the dots.
     * 
     * @param dots the dots
     */
    public void addDots(Dot...dots) {
        addDots(Arrays.asList(dots));
    }
    
    /**
     * Adds the dots.
     * 
     * @param dots the dots
     */
    public void addDots(List<Dot> dots) {
        getValues().addAll(dots);
    }
    
    /**
     * Gets the halo size.
     * 
     * @return the halo size
     */
    public Integer getHaloSize() {
        return haloSize;
    }

    /**
     * Sets the halo size.
     * 
     * @param haloSize the new halo size
     */
    public void setHaloSize(Integer haloSize) {
        this.haloSize = haloSize;
    }

	/* (non-Javadoc)
	 * @see com.rednels.ofcgwt.client.model.elements.Element#buildJSONObject()
	 */
	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (width != null) json.put("width", new JSONNumber(width));
    	if (dotSize != null) json.put("dot-size", new JSONNumber(dotSize));
    	if (haloSize != null) json.put("halo-size", new JSONNumber(haloSize));
    	if (colour != null) json.put("colour", new JSONString(colour));
    	return json;
	}
    
    /**
     * Enumeration LineStyle - used with LineChart.
     */
    public static enum LineStyle {
        
        /** NORMAL */
        NORMAL("line"),
        
        /** DOT */
        DOT("line_dot"),
        
        /** HOLLOW */
        HOLLOW("line_hollow");
        
        /** The style. */
        private String style;
        
        /**
         * Creates a new line style.
         * 
         * @param style the style
         */
        LineStyle(String style) {
            this.style = style;
        }
        
        /**
         * Gets the style.
         * 
         * @return the style
         */
        public String getStyle() {
            return style;
        }
    }

    /**
     * Base class for OFC line dots 
     */
    public static class Dot implements JSONizable {
        
        /** The halo size. */
        private Integer haloSize; 
        
        /** The dot size. */
        private Integer dotSize; 
        
        /** The value. */
        private Number value;
        
        /** The colour. */
        private String colour;
        
        /**
         * Creates a new dot.
         * 
         * @param value the value
         */
        public Dot(Number value) {
            this(value, null, null, null);
        }
        
        /**
         * Creates a new dot.
         * 
         * @param value the value
         * @param colour the colour
         */
        public Dot(Number value, String colour) {
            this(value, colour, null, null);
        }
        
        /**
         * Creates a new dot.
         * 
         * @param value the value
         * @param colour the colour
         * @param dotSize the dot size
         * @param haloSize the halo size
         */
        public Dot(Number value, String colour, Integer dotSize, Integer haloSize) {
            setValue(value);
            setColour(colour);
            setDotSize(dotSize);
            setHaloSize(haloSize);
        }
        
        /**
         * Gets the halo size.
         * 
         * @return the halo size
         */
        public Integer getHaloSize() {
            return haloSize;
        }
        
        /**
         * Sets the halo size.
         * 
         * @param haloSize the halo size
         * 
         * @return the dot
         */
        public Dot setHaloSize(Integer haloSize) {
            this.haloSize = haloSize;
            return this;
        }
        
        /**
         * Gets the dot size.
         * 
         * @return the dot size
         */
        public Integer getDotSize() {
            return dotSize;
        }
        
        /**
         * Sets the dot size.
         * 
         * @param dotSize the dot size
         */
        public void setDotSize(Integer dotSize) {
            this.dotSize = dotSize;
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
         * Sets the value.
         * 
         * @param value the value
         */
        public void setValue(Number value) {
            this.value = value;
        }
        
        /**
         * Gets the colour.
         * 
         * @return the colour
         */
        public String getColour() {
            return colour;
        }
        
        /**
         * Sets the colour in HTML hex format (#ffffff) 
         * 
         * @param colour the colour
         */
        public void setColour(String colour) {
            this.colour = colour;
        }

    	/* (non-Javadoc)
	     * @see com.rednels.ofcgwt.client.model.JSONizable#buildJSONObject()
	     */
	    public JSONObject buildJSONObject() {
        	JSONObject json = new JSONObject();
        	if (haloSize != null) json.put("halo-size", new JSONNumber(haloSize.doubleValue()));
        	if (dotSize != null) json.put("dot-size", new JSONNumber(dotSize.doubleValue())); 
        	if (value != null) json.put("value", new JSONNumber(value.doubleValue())); 	
        	if (colour != null) json.put("colour", new JSONString(colour));
        	return json;
    	}
    }
}
