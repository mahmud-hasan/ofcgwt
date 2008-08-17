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

import java.util.List;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.rednels.ofcgwt.client.model.JSONizable;

public class XAxis extends Axis implements JSONizable {
    private Integer tick_height; 
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
