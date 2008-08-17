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


public class XAxisLabels extends Label implements JSONizable {
    private Integer steps;
    private List<Object> labels;
    
    public XAxisLabels() {
        //when no labels are needed
    }
    
    public XAxisLabels(String... labels) {
        addLabels(labels);
    }
    
    public XAxisLabels(List<String> labels) {
    	 checkLabels();
         this.labels.addAll(labels);
    }    
    
    public List<Object> getLabels() {
        return labels;
    }
    
    public XAxisLabels addLabels(String... labels) {
        checkLabels();
        this.labels.addAll(Arrays.asList(labels));
        return this;
    }
    
    public XAxisLabels addLabels(Label... labels) {
        checkLabels();
        this.labels.addAll(Arrays.asList(labels));
        return this;
    }
    
    public XAxisLabels addLabels(List<Label> labels) {
        checkLabels();
        this.labels.addAll(labels);
        return this;
    }
    
    public XAxisLabels setSteps(Integer steps) {
        this.steps = steps;
        return this;
    }
    
    public Integer getSteps() {
        return steps;
    }
    
    private synchronized void checkLabels() {
        if (labels == null) labels = new ArrayList<Object>();
    }

	public JSONObject buildJSONObject() {		
    	JSONObject json = super.buildJSONObject();
    	if (steps != null) json.put("steps", new JSONNumber(steps));
    	if (labels == null) return json;
    	JSONArray ary = new JSONArray();
    	int index = 0;
    	for (Object o : getLabels()) {
    		if (o instanceof String) ary.set(index++, new JSONString((String)o));
    		if (o instanceof Label) ary.set(index++, ((Label)o).buildJSONObject());
        }
    	if (index != 0) json.put("labels",ary);
    	return json;
	}
}
