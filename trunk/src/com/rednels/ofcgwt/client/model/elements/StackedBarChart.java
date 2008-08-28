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
