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
package com.rednels.ofcgwt.client.model.axis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;

public class YAxis extends Axis implements JSONizable {
    private Integer tick_length;
    private List<String> labels;
    
    public YAxis setTickLength(Integer tick_length) {
        this.tick_length = tick_length;
        return this;
    }
    
    public Integer getTickLength() {
        return tick_length;
    }
    
    public YAxis setLabels(String... labels) {
        checkLabels();
        this.labels.clear();
        return addLabels(labels);
    }
    
    public YAxis addLabels(String... labels) {
        checkLabels();
        this.labels.addAll(Arrays.asList(labels));
        return this;
    }
    
    public YAxis addLabels(List<String> labels) {
        checkLabels();
        this.labels.addAll(labels);
        return this;
    }
    
    public List<String> getLabels() {
        return labels;
    }
    
    private synchronized void checkLabels() {
        if (labels == null) labels = new ArrayList<String>();
    }    

	public JSONObject buildJSONObject() {		
    	JSONObject json = super.buildJSONObject();
    	if (tick_length != null) json.put("tick-length", new JSONNumber(tick_length));
    	if (labels == null) return json;
    	JSONArray ary = new JSONArray();
    	int index = 0;
    	for (String o : getLabels()) {
    		ary.set(index++, new JSONString(o));
        }
    	if (index != 0) json.put("labels",ary);
    	return json;
	}
}
