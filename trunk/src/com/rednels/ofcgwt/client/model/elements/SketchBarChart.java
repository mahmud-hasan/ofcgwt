/*
 *    Copyright 2008 Grant K Slender
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *   
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
