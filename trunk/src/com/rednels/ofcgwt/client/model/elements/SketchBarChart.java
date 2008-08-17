package com.rednels.ofcgwt.client.model.elements;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.rednels.ofcgwt.client.model.JSONizable;

public class SketchBarChart extends FilledBarChart implements JSONizable {
    private static final transient String TYPE = "bar_sketch";
    private Integer offset; //offset
    
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
        private Integer offset; //offset
        
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
