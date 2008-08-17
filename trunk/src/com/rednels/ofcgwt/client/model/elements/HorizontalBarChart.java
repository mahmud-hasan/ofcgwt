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
    
    public HorizontalBarChart setColour(String colour) {
        this.colour = colour;
        return this;
    }
    
    public HorizontalBarChart addBars(Bar... values) {
        getValues().addAll(Arrays.asList(values));
        return this;
    }
    
    public HorizontalBarChart addBars(List<Bar> values) {
        getValues().addAll(values);
        return this;
    }
        
    public HorizontalBarChart addValues(Number... rightValues) {
        Bar[] values = new Bar[rightValues.length];
        for (int i = 0; i < rightValues.length; ++i) {
            values[i] = new Bar(rightValues[i]);
        }
        return addBars(values);
    }
    
    public HorizontalBarChart addValues(List<Number> rightValues) {
        getValues().addAll(rightValues);
        return this;
    }
    
    public HorizontalBarChart addBar(Number left, Number right) {
        return addBars(new Bar(left, right));
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (colour != null) json.put("colour", new JSONString(colour)); 	
    	return json;
	}
    
    public static class Bar implements JSONizable {
        private final Number right;
        private Number left;
        
        public Bar(Number right) {
            this(null, right);
        }
        
        public Bar(Number left, Number right) {
            if (right == null) throw new NullPointerException("Field is mandatory.");
            this.right = right;
            setLeft(left);
        }
        
        public Number getRight() {
            return right;
        }
        
        public Number getLeft() {
            return left;
        }
        
        public Bar setLeft(Number left) {
            this.left = left;
            return this;
        }

    	public JSONObject buildJSONObject() {
        	JSONObject json = new JSONObject();
        	if (right != null) json.put("right", new JSONNumber(right.doubleValue()));
        	if (left != null) json.put("left", new JSONNumber(left.doubleValue())); 	
        	return json;
    	}
    }
}
