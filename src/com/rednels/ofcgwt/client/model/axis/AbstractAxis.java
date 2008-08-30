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

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;

public abstract class AbstractAxis implements JSONizable {
    private Integer stroke;
    private String colour;
    private String gridColour;
    private Integer steps;
    private Integer offset;
    private Integer zdepth3d; 
    private Integer min;
    private Integer max;
    
    public Integer getStroke() {
        return stroke;
    }
    public void setStroke(Integer stroke) {
        this.stroke = stroke;
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public String getGridColour() {
        return gridColour;
    }
    public void setGridColour(String grid_colour) {
        this.gridColour = grid_colour;
    }
    public Integer getSteps() {
        return steps;
    }
    public void setSteps(Integer steps) {
        this.steps = steps;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Boolean offset) {
        if (offset == null) this.offset = null;
        this.offset = offset ? 1 : 0;
    }
    public Integer getZDepth3D() {
        return zdepth3d;
    }
    public void setZDepth3D(Integer threed) {
        this.zdepth3d = threed;
    }
    public Integer getMin() {
        return min;
    }
    public void setMin(Integer min) {
        this.min = min;
    }
    public Integer getMax() {
        return max;
    }
    public void setMax(Integer max) {
        this.max = max;
    }
    
    public void setRange(Integer min, Integer max, Integer step) {
    	setRange(min,max);
        setSteps(step);
    }
    
    public void setRange(Integer min, Integer max) {
        setMin(min);
        setMax(max);
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = new JSONObject();
    	if (stroke != null) json.put("stroke", new JSONNumber(stroke));
    	if (colour != null) json.put("colour", new JSONString(colour));
    	if (gridColour != null) json.put("grid-colour", new JSONString(gridColour));
    	if (steps != null) json.put("steps", new JSONNumber(steps));
    	if (offset != null) json.put("offset", new JSONNumber(offset));
    	if (zdepth3d != null) json.put("3d", new JSONNumber(zdepth3d));
    	if (min != null) json.put("min", new JSONNumber(min));
    	if (max != null) json.put("max", new JSONNumber(max));    	
    	return json;
	}
}
