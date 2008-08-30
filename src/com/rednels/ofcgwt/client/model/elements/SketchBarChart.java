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

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.rednels.ofcgwt.client.model.JSONizable;

public class SketchBarChart extends FilledBarChart implements JSONizable {
    private static final transient String TYPE = "bar_sketch";
    private Integer offset; 
    
    public SketchBarChart() {
        super(TYPE);
    }
    
    public SketchBarChart(String colour, String outlineColour, Integer offset) {
        super(TYPE);
        setColour(colour);
        setOutlineColour(outlineColour);
        setOffset(offset);
    }
    
    public Integer getOffset() {
        return offset;
    }
    
    public BarChart setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (offset != null) json.put("offset", new JSONNumber(offset.doubleValue()));
    	return json;
	}
    
    public static class Bar extends FilledBarChart.Bar implements JSONizable {
        private Integer offset; 
        
        public Bar(Number top) {
            super(top);
        }
        
        public Bar(Number top, Integer offset) {
            super(top);
            setOffset(offset);
        }
        
        public Bar(Number top, Number bottom, Integer offset) {
            super(top, bottom);
            setOffset(offset);
        }
        
        public Bar setOffset(Integer offset) {
            this.offset = offset;
            return this;
        }
        
        public Integer getFunFactor() {
            return offset;
        }

    	public JSONObject buildJSONObject() {
        	JSONObject json = super.buildJSONObject();
        	if (offset != null) json.put("offset", new JSONNumber(offset.doubleValue()));
        	return json;
    	}
    }
}
