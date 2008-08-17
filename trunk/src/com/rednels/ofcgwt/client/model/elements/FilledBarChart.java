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
