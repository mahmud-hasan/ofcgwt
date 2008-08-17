package com.rednels.ofcgwt.client.model.elements;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;


public class AreaHollowChart extends LineChart {
    private static transient final Float DEFAULT_ALPHA = 0.35f;
    
    private Float fillAlpha; //fill-alpha
    
    public AreaHollowChart() {
        super("area_hollow"); 
        setFillAlpha(DEFAULT_ALPHA);
    }

    public Float getFillAlpha() {
        return fillAlpha;
    }

    public AreaHollowChart setFillAlpha(Float fillAlpha) {
        this.fillAlpha = fillAlpha;
        return this;
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (fillAlpha != null) json.put("fillAlpha", new JSONNumber(fillAlpha));//    	
    	return json;
	}
}
