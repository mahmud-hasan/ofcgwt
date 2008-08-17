package com.rednels.ofcgwt.client.model.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;
import com.rednels.ofcgwt.client.model.axis.Label;


/**
 * The stacked bar chart allows you to draw a bar chart divided
 * into value regions.
 */
/*
 * Implementation note: the Stack class wraps standard List objects.
 * The List objects are preserved in the element value field rather than
 * the Stack object itself so that XStream renders the data correctly. I
 * didn't have much luck trying to use a custom converter or an implicit
 * collection.
 */
public class StackedBarChart extends Element implements JSONizable {
    public StackedBarChart() {
        super("bar_stack");
    }
    
    /**
     * Add stacks to the chart (var-args version).
     * @param stacks the stacks that have not yet been placed into the chart
     * @return the chart element object being operated on
     */
    public StackedBarChart addStack(Stack... stacks) {
        return copy(Arrays.asList(stacks));
    }
    
    /**
     * Add stacks to the chart (Collections version).
     * @param stacks the stacks that have not yet been placed into the chart
     * @return the chart element object being operated on
     */
    public StackedBarChart addStack(List<Stack> stacks) {
        return copy(stacks);
    }
    
    /**
     * Create a stack and add it into the chart.  You do not need to
     * pass this Stack object to addStack.
     * @return the stack that has been created in the chart
     */
    public Stack newStack() {
        Stack s = new Stack();
        copy(Arrays.asList(s));
        return s;
    }
    
    /**
     * Find the most recently created stack, or create one if
     * there are none.
     * @return the last stack in the chart
     */
    public Stack lastStack() {
        if (getValues().isEmpty()) {
            return newStack();
        } else {
            return stack(getStackCount() - 1);
        }
    }
    
    /**
     * Find an arbitrary stack by index number. (Starts at 0.)
     * @param index the index of the stack, 0 to getStackCount() - 1.
     * @return the stack at the specified index
     */
    @SuppressWarnings("unchecked")
    public Stack stack(int index) {
        return new Stack((List<Object>) getValues().get(index));
    }
    
    /**
     * The number of stacks in the chart.
     * @return the number of stacks in the chart
     */
    public int getStackCount() {
        return getValues().size();
    }
    
    private StackedBarChart copy(List<Stack> stacks) {
        for (Stack s : stacks) {
            getValues().add(s.getBackingList());
        }
        return this;
    }

    /**
     * Representation of a stack in the chart.  This class allows
     * you to add numbers or complex values with custom data.
     */
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
     * Representation of data in the stacked bar chart. 
     */
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
