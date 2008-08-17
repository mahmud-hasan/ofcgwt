package com.rednels.ofcgwt.client.model.elements;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;


public class FilledBarChart extends BarChart implements JSONizable {
    private static final transient String TYPE = "bar_filled";
    private String outlineColour; //outline-colour
    
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
        private String outlineColour; //outline-colour
        
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
