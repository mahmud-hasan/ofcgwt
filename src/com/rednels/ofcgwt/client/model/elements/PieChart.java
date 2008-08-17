package com.rednels.ofcgwt.client.model.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;

public class PieChart extends Element implements JSONizable{
    private Integer startAngle; //start-angle
    private Collection<String> colours;
    private Boolean animate;
    private Integer border;
    
    public PieChart() {
        super("pie");
    }
    
    public PieChart setAnimate(boolean animate) {
        this.animate = animate;
        return this;
    }
    
    public Boolean getAnimate() {
        return animate;
    }

    public Integer getStartAngle() {
        return startAngle;
    }

    public PieChart setStartAngle(Integer startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    public Collection<String> getColours() {
        return colours;
    }

    public PieChart setColours(Collection<String> colours) {
        checkColours();
        this.colours = colours;
        return this;
    }
    
    public PieChart setColours(String... colours) {
        checkColours();
        this.colours.clear();
        this.colours.addAll(Arrays.asList(colours));
        return this;
    }
    
    public PieChart setColours(List<String> colours) {
        checkColours();
        this.colours.clear();
        this.colours.addAll(colours);
        return this;
    }
    
    public Integer getBorder() {
        return border;
    }
    
    public PieChart setBorder(Integer border) {
        this.border = border;
        return this;
    }

    public PieChart addValues(Number... values) {
        getValues().addAll(Arrays.asList(values));
        return this;
    }
    
    public PieChart addValues(List<Number> values) {
        getValues().addAll(values);
        return this;
    }
    
    public PieChart addSlice(Number value, String text) {
        return addSlices(new Slice(value, text));
    }
    
    public PieChart addSlices(Slice... s) {
        getValues().addAll(Arrays.asList(s));
        return this;
    }
    
    public PieChart addSlices(List<Slice> values) {
        getValues().addAll(values);
        return this;
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (startAngle != null) json.put("start-angle", new JSONNumber(startAngle.doubleValue()));
    	JSONArray ary = new JSONArray();
    	int index = 0;
    	for (String s : getColours()) {
    		ary.set(index++, new JSONString(s));
        }
    	if (index != 0) json.put("colours",ary);	
    	if (animate != null) json.put("animate", JSONBoolean.getInstance(animate));   
    	if (border != null) json.put("border", new JSONNumber(border.doubleValue()));
    	return json;
	}
	
    public static class Slice implements JSONizable{
        private final String text;
        private final Number value;
        
        public Slice(Number value, String text) {
            this.text = text;
            this.value = value;
        }
        
        public Number getValue() {
            return value;
        }
        
        public String getText() {
            return text;
        }

    	public JSONObject buildJSONObject() {
        	JSONObject json = new JSONObject();
        	if (value != null) json.put("value", new JSONNumber(value.doubleValue())); 	
        	if (text != null) json.put("text", new JSONString(text));
        	return json;
    	}
    }
    
    private synchronized void checkColours() {
        if (colours == null) colours = new ArrayList<String>();
    }
}
