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
