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
import com.rednels.ofcgwt.client.model.JSONizable;


public class FilledBarChart extends BarChart implements JSONizable {
    private static final transient String TYPE = "bar_filled";
    private String outlineColour; 
    
    public FilledBarChart() {
        super(TYPE);
    }
    
    public FilledBarChart(String colour, String outlineColour) {
        super(TYPE);
        setColour(colour);
        setOutlineColour(outlineColour);
    }
    
    protected FilledBarChart(String style) {
        super(style);
    }
    
    public String getOutlineColour() {
        return outlineColour;
    }
    
    public BarChart setOutlineColour(String outlineColour) {
        this.outlineColour = outlineColour;
        return this;
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (outlineColour != null) json.put("outline-colour", new JSONString(outlineColour)); 	
    	return json;
	}
    
    public static class Bar extends BarChart.Bar implements JSONizable  {
        private String outlineColour; 
        
        public Bar(Number top, Number bottom) {
            super(top, bottom);
        }
        
        public Bar(Number top, Number bottom, String colour, String outlineColour) {
            super(top, bottom);
            setColour(colour);
            setOutlineColour(outlineColour);
        }
        
        public Bar(Number top) {
            super(top);
        }
        
        public Bar setOutlineColour(String outlineColour) {
            this.outlineColour = outlineColour;
            return this;
        }
        
        public String getOutlineColour() {
            return outlineColour;
        }

    	public JSONObject buildJSONObject() {
        	JSONObject json = super.buildJSONObject();
        	if (outlineColour != null) json.put("outline-colour", new JSONString(outlineColour)); 	
        	return json;
    	}
    }
}
