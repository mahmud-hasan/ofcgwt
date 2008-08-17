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
package com.rednels.ofcgwt.client.model.elements;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;


public class AreaHollowChart extends LineChart {
    private static transient final Float DEFAULT_ALPHA = 0.35f;
    
    private Float fillAlpha; 
    
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
