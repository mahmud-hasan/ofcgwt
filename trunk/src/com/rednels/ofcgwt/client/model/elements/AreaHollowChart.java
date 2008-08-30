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
