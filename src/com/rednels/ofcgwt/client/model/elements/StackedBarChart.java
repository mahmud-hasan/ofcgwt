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

public class StackedBarChart extends Element implements JSONizable {
    public StackedBarChart() {
        super("bar_stack");
    }
    
    public StackedBarChart addStack(Stack... stacks) {
        return copy(Arrays.asList(stacks));
    }
    
    public StackedBarChart addStack(List<Stack> stacks) {
        return copy(stacks);
    }
    
    public Stack newStack() {
        Stack s = new Stack();
        copy(Arrays.asList(s));
        return s;
    }
    
    public Stack lastStack() {
        if (getValues().isEmpty()) {
            return newStack();
        } else {
            return stack(getStackCount() - 1);
        }
    }
    
    @SuppressWarnings("unchecked")
	public Stack stack(int index) {
        return new Stack((List<Object>) getValues().get(index));
    }
    
    public int getStackCount() {
        return getValues().size();
    }
    
    private StackedBarChart copy(List<Stack> stacks) {
        for (Stack s : stacks) {
            getValues().add(s.getBackingList());
        }
        return this;
    }

    public static class Stack implements JSONizable {
        private transient List<Object> values;
        
        public Stack() {
            values = new ArrayList<Object>();
        }
        
        Stack(List<Object> values) {
            this.values = values;
        }
        
        public Stack addStackValues(StackValue... values) {
            return doAdd(Arrays.asList(values));
        }
        
        public Stack addStackValues(List<StackValue> values) {
            return doAdd(values);
        }
        
        public Stack addValues(Number... numbers) {
            return doAdd(Arrays.asList(numbers));
        }
        
        public Stack addValues(List<Number> numbers) {
            return doAdd(numbers);
        }
        
        private Stack doAdd(List<? extends Object> values) {
            this.values.addAll(values);
            return this;
        }
        
        List<Object> getBackingList() {
            return this.values;
        }

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
    
    public static class StackValue implements JSONizable {
        private Number val;
        private String colour;
        
        public StackValue(Number value) {
            this(value, null);
        }
        
        public StackValue(Number value, String colour) {
            setValue(value);
            setColour(colour);
        }
        
        public Number getValue() {
            return val;
        }
        
        public StackValue setValue(Number val) {
            this.val = val;
            return this;
        }
        
        public String getColour() {
            return colour;
        }
        
        public StackValue setColour(String colour) {
            this.colour = colour;
            return this;
        }

    	public JSONObject buildJSONObject() {
        	JSONObject json = new JSONObject();
        	if (val != null) json.put("val", new JSONNumber(val.doubleValue())); 	
        	if (colour != null) json.put("colour", new JSONString(colour));
        	return json;
    	}
    }
}
