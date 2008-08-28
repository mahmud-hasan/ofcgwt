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

import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;

public class HorizontalBarChart extends Element implements JSONizable {
    private String colour;
    
    public HorizontalBarChart() {
        super("hbar");
    }
    
    public String getColour() {
        return colour;
    }
    
    public void setColour(String colour) {
        this.colour = colour;
    }
    
    public void addBars(Bar... values) {
        getValues().addAll(Arrays.asList(values));
    }
    
    public void addBars(List<Bar> values) {
        getValues().addAll(values);
    }
        
    public void addValues(Number... rightValues) {
        Bar[] values = new Bar[rightValues.length];
        for (int i = 0; i < rightValues.length; ++i) {
            values[i] = new Bar(rightValues[i]);
        }
        addBars(values);
    }
    
    public void addValues(List<Number> rightValues) {
        getValues().addAll(rightValues);
    }
    
    public void addBar(Number left, Number right) {
        addBars(new Bar(left, right));
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (colour != null) json.put("colour", new JSONString(colour)); 	
    	return json;
	}
    

    public static class Bar implements JSONizable {
    	private Number left;
    	private Number right;
    	private String colour;
    	private String tooltip;
    	
    	public Bar(Number top, Number bottom, String colour) {
    	    setTop(top);
    	    setBottom(bottom);
    	    setColour(colour);
    	}
    	
    	public Bar(Number top, Number bottom) {
    		this(top, bottom, null);
    	}
    	
    	public Bar(Number top, String colour) {
    	    this(top, null, colour);
    	}
    	
    	public Bar(Number top) {
    	    this(top, null, null);
    	}
    	
		public Number getTop() {
			return left;
		}
		public void setTop(Number top) {
			this.left = top;
		}
		public Number getBottom() {
			return right;
		}
		public void setBottom(Number bottom) {
			this.right = bottom;
		}
		public String getColour() {
			return colour;
		}
		public void setColour(String colour) {
			this.colour = colour;
		}
		public String getTooltip() {
			return tooltip;
		}
		public void setTooltip(String tooltip) {
			this.tooltip = tooltip;
		}

    	public JSONObject buildJSONObject() {
        	JSONObject json = new JSONObject();
        	if (left != null) json.put("left", new JSONNumber(left.doubleValue()));
        	if (right != null) json.put("right", new JSONNumber(right.doubleValue())); 	
        	if (colour != null) json.put("colour", new JSONString(colour));
        	if (tooltip != null) json.put("tip", new JSONString(tooltip));
        	return json;
    	}	
    }
}
