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

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.JSONizable;
/**
 * Class for an OFC filled bar chart (outlined) that extends BarChart   
 * @see com.rednels.ofcgwt.client.model.elements.BarChart
 */
public class FilledBarChart extends BarChart implements JSONizable {
    
    /** The Constant TYPE. */
    private static final transient String TYPE = "bar_filled";
    
    /** The outline colour. */
    private String outlineColour; 
    
    /**
     * Creates a new filled bar chart.
     */
    public FilledBarChart() {
        super(TYPE);
    }
    
    /**
     * Creates a new filled bar chart.
     * 
     * @param colour the colour
     * @param outlineColour the outline colour
     */
    public FilledBarChart(String colour, String outlineColour) {
        super(TYPE);
        setColour(colour);
        setOutlineColour(outlineColour);
    }
    
    /**
     * Creates a new filled bar chart.
     * 
     * @param style the style
     */
    protected FilledBarChart(String style) {
        super(style);
    }
    
    /**
     * Gets the outline colour.
     * 
     * @return the outline colour
     */
    public String getOutlineColour() {
        return outlineColour;
    }
    
    /**
     * Sets the outline colour in HTML hex format (#ffffff) 
     * 
     * @param outlineColour the new outline colour
     */
    public void setOutlineColour(String outlineColour) {
        this.outlineColour = outlineColour;
    }

	/* (non-Javadoc)
	 * @see com.rednels.ofcgwt.client.model.elements.BarChart.buildJSON()
	 */
	public JSONValue buildJSON() {
    	JSONObject json = (JSONObject)super.buildJSON();
    	if (outlineColour != null) json.put("outline-colour", new JSONString(outlineColour)); 	
    	return json;
	}
    
    /**
     * Class for OFC filled bar chart bars, extends BarChart.Bar
     * @see com.rednels.ofcgwt.client.model.elements.BarChart.Bar  
     */
    public static class Bar extends BarChart.Bar implements JSONizable  {
        
        /** The outline colour. */
        private String outlineColour; 
        
        /**
         * Creates a new bar.
         * 
         * @param top the top
         * @param bottom the bottom
         */
        public Bar(Number top, Number bottom) {
            super(top, bottom);
        }
        
        /**
         * Creates a new bar.
         * 
         * @param top the top
         * @param bottom the bottom
         * @param colour the colour
         * @param outlineColour the outline colour
         */
        public Bar(Number top, Number bottom, String colour, String outlineColour) {
            super(top, bottom);
            setColour(colour);
            setOutlineColour(outlineColour);
        }
        
        /**
         * Creates a new bar.
         * 
         * @param top the top
         */
        public Bar(Number top) {
            super(top);
        }
        
        /**
         * Sets the outline colour in HTML hex format (#ffffff) 
         * 
         * @param outlineColour the new outline colour
         */
        public void setOutlineColour(String outlineColour) {
            this.outlineColour = outlineColour;
        }
        
        /**
         * Gets the outline colour.
         * 
         * @return the outline colour
         */
        public String getOutlineColour() {
            return outlineColour;
        }

    	/* (non-Javadoc)
	     * @see com.rednels.ofcgwt.client.model.elements.BarChart.Bar.buildJSON()
	     */
	    public JSONValue buildJSON() {
        	JSONObject json = (JSONObject)super.buildJSON();
        	if (outlineColour != null) json.put("outline-colour", new JSONString(outlineColour)); 	
        	return json;
    	}
    }
}
