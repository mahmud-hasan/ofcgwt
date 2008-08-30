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
package com.rednels.ofcgwt.client.model.axis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;

public class YAxis extends AbstractAxis implements JSONizable {
    private Integer tickLength;
    private List<String> labels;
    
    public void setTickLength(Integer tick_length) {
        this.tickLength = tick_length;
    }
    
    public Integer getTickLength() {
        return tickLength;
    }
    
    public void addLabels(String... labels) {
        checkLabelsNotNull();
        this.labels.addAll(Arrays.asList(labels));
    }
    
    public void addLabels(List<String> labels) {
        checkLabelsNotNull();
        this.labels.addAll(labels);
    }
    
    public List<String> getLabels() {
        return labels;
    }
    
    private synchronized void checkLabelsNotNull() {
        if (labels == null) labels = new ArrayList<String>();
    }    

	public JSONObject buildJSONObject() {		
    	JSONObject json = super.buildJSONObject();
    	if (tickLength != null) json.put("tick-length", new JSONNumber(tickLength));
    	if (labels == null) return json;
    	JSONArray ary = new JSONArray();
    	int index = 0;
    	for (String o : labels) {
    		ary.set(index++, new JSONString(o));
        }
    	if (index != 0) json.put("labels",ary);
    	return json;
	}
}
