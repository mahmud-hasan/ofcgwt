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
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;
/**
 * Class for an OFC stacked bar chart that extends Element   
 * @see com.rednels.ofcgwt.client.model.elements.Element
 */
public class StackedBarChart extends Element implements JSONizable {

    /** The Constant TYPE. */
    private static final transient String TYPE = "bar_stack";
    
    /**
     * Creates a new stacked bar chart.
     */
    public StackedBarChart() {
        super(TYPE);
    }
    
    /**
     * Adds the stack.
     * 
     * @param stacks the stacks
     */
    public void addStack(Stack... stacks) {
    	addStack(Arrays.asList(stacks));
    }
    
    /**
     * Adds the stack.
     * 
     * @param stacks the stacks
     */
    public void addStack(List<Stack> stacks) {    	
    	for (Stack s : stacks) {
    		values.addAll(s.values);
    	}
    }    

    /**
     * Base class for OFC stack bar chart stacks 
     */
    public static class Stack implements JSONizable {
        
        /** The values. */
        private transient ArrayList<Object> values;
        
        /**
         * Creates a new stack.
         */
        public Stack() {
            values = new ArrayList<Object>();
        }
        
        /**
         * Adds the stack values.
         * 
         * @param values the values
         */
        public void addStackValues(StackValue... values) {
        	this.values.addAll(Arrays.asList(values));
        }
        
        /**
         * Adds the stack values.
         * 
         * @param values the values
         */
        public void addStackValues(List<StackValue> values) {
        	this.values.addAll(values);
        }
        
        /**
         * Adds the values.
         * 
         * @param numbers the numbers
         */
        public void addValues(Number... numbers) {
        	this.values.addAll(Arrays.asList(numbers));
        }
        
        /**
         * Adds the values.
         * 
         * @param numbers the numbers
         */
        public void addValues(List<Number> numbers) {
        	this.values.addAll(numbers);
        }
        
        /**
         * Gets the values.
         * 
         * @return the values
         */
        public List<Object> getValues() {
            return this.values;
        }

    	/* (non-Javadoc)
	     * @see com.rednels.ofcgwt.client.model.JSONizable#buildJSONObject()
	     */
	    public JSONObject buildJSONObject() {
        	JSONObject json = new JSONObject();
        	if (values == null) return json;    	
        	JSONArray ary = new JSONArray();
        	int index = 0;
        	for (Object o : values) {
        		if (o instanceof Number) ary.set(index++, new JSONNumber(((Number)o).doubleValue()));
        		if (o instanceof StackValue) ary.set(index++, ((StackValue)o).buildJSONObject());
            }
        	if (index != 0) json.put("labels",ary);
        	return json;
    	}
    }
    
    /**
     * Base class for OFC stack bar chart values 
     */
    public static class StackValue implements JSONizable {
        
        /** The val. */
        private Number val;
        
        /** The colour. */
        private String colour;
        
        /**
         * Creates a new stack value.
         * 
         * @param value the value
         */
        public StackValue(Number value) {
            this(value, null);
        }
        
        /**
         * Creates a new stack value.
         * 
         * @param value the value
         * @param colour the colour
         */
        public StackValue(Number value, String colour) {
            setValue(value);
            setColour(colour);
        }
        
        /**
         * Gets the value.
         * 
         * @return the value
         */
        public Number getValue() {
            return val;
        }
        
        /**
         * Sets the value.
         * 
         * @param val the new value
         */
        public void setValue(Number val) {
            this.val = val;
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
         * @param colour the new colour
         */
        public void setColour(String colour) {
            this.colour = colour;
        }

    	/* (non-Javadoc)
	     * @see com.rednels.ofcgwt.client.model.JSONizable#buildJSONObject()
	     */
	    public JSONObject buildJSONObject() {
        	JSONObject json = new JSONObject();
        	if (val != null) json.put("val", new JSONNumber(val.doubleValue())); 	
        	if (colour != null) json.put("colour", new JSONString(colour));
        	return json;
    	}
    }
}
