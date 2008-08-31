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
/**
 * Class for an OFC area-hollow chart that extends LineChart   
 * @see com.rednels.ofcgwt.client.model.elements.LineChart
 */
public class AreaHollowChart extends LineChart {
        
    /** The fill alpha. */
    private Float fillAlpha; 
    
    /**
     * Creates a new area hollow chart
     */
    public AreaHollowChart() {
        super("area_hollow"); 
    }

    /**
     * Gets the fill alpha.
     * 
     * @return the fill alpha
     */
    public Float getFillAlpha() {
        return fillAlpha;
    }

    /**
     * Sets the fill alpha.
     * 
     * @param fillAlpha the new fill alpha
     */
    public void setFillAlpha(Float fillAlpha) {
        this.fillAlpha = fillAlpha;
    }

	/* (non-Javadoc)
	 * @see com.rednels.ofcgwt.client.model.elements.LineChart#buildJSONObject()
	 */
	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (fillAlpha != null) json.put("fill-alpha", new JSONNumber(fillAlpha));//    	
    	return json;
	}
}
