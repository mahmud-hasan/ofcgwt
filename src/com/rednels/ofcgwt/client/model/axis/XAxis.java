package com.rednels.ofcgwt.client.model.axis;

import java.util.List;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.rednels.ofcgwt.client.model.JSONizable;

public class XAxis extends Axis implements JSONizable {
    private Integer tick_height; //tick-height
    private XAxisLabels labels = new XAxisLabels();
    
    public XAxis setTickHeight(Integer tick_height) {
        this.tick_height = tick_height;
        return this;
    }
    
    public Integer getTickHeight() {
        return tick_height;
    }
    
    public XAxisLabels getLabels() {
        return labels;
    }
    
    public XAxis setXAxisLabels(XAxisLabels labels) {
        this.labels = labels;
        return this;
    }
    
    public XAxis setLabels(String... labels) {
        this.labels = new XAxisLabels(labels);
        return this;
    }

    public XAxis setLabels(List<String> labels) {
        this.labels = new XAxisLabels(labels);
        return this;
    }
    
    public XAxis addLabels(String... labels) {
        this.labels.addLabels(labels);
        return this;
    }
    
    public XAxis addLabels(Label... labels) {
        this.labels.addLabels(labels);
        return this;
    }
    
    public XAxis addLabels(List<Label> labels) {
        this.labels.addLabels(labels);
        return this;
    }

	public JSONObject buildJSONObject() {		
    	JSONObject json = super.buildJSONObject();
    	if (tick_height != null) json.put("tick-height", new JSONNumber(tick_height));    	
    	if (labels != null) json.put("labels", labels.buildJSONObject());
    	return json;
	}
}
