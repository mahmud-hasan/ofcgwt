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
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * Base abstract class for an OFC axis    
 */
public abstract class AbstractAxis implements JSONizable {
    private Integer stroke;
    private String colour;
    private String gridColour;
    private Integer offset;
    private Integer zdepth3d; 
    private Number steps;
    private Number min;
    private Number max;
    
    /**
     * Gets the stroke.
     * 
     * @return the stroke
     */
    public Integer getStroke() {
        return stroke;
    }
    
    /**
     * Sets the stroke.
     * 
     * @param stroke the new stroke
     */
    public void setStroke(Integer stroke) {
        this.stroke = stroke;
    }
    
    /**
     * Gets the colour.
     * 
     * @return the colour
     */
    public String getColour() {
        return colour;
    }
    
    /**
     * Sets the colour in HTML hex format (#ffffff) 
     * 
     * @param colour the new colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }
    
    /**
     * Gets the grid colour.
     * 
     * @return the grid colour
     */
    public String getGridColour() {
        return gridColour;
    }
    
    /**
     * Sets the grid colour in HTML hex format (#ffffff) 
     * 
     * @param grid_colour the new grid colour
     */
    public void setGridColour(String grid_colour) {
        this.gridColour = grid_colour;
    }
    
    /**
     * Gets the steps.
     * 
     * @return the steps
     */
    public Number getSteps() {
        return steps;
    }
    
    /**
     * Sets the steps.
     * 
     * @param steps the new steps
     */
    public void setSteps(Number steps) {
        this.steps = steps;
    }
    
    /**
     * Gets the offset.
     * 
     * @return the offset
     */
    public Integer getOffset() {
        return offset;
    }
    
    /**
     * Sets the offset.
     * 
     * @param offset the new offset
     */
    public void setOffset(Boolean offset) {
        if (offset == null) this.offset = null;
        this.offset = offset ? 1 : 0;
    }
    
    /**
     * Gets the z-depth (3D).
     * 
     * @return the zdepth3d
     */
    public Integer getZDepth3D() {
        return zdepth3d;
    }
    
    /**
     * Sets the z-depth (3D).
     * 
     * @param zdepth3d the new zdepth3d
     */
    public void setZDepth3D(Integer zdepth3d) {
        this.zdepth3d = zdepth3d;
    }
    
    /**
     * Gets the min.
     * 
     * @return the min
     */
    public Number getMin() {
        return min;
    }
    
    /**
     * Sets the min.
     * 
     * @param min the new min
     */
    public void setMin(Number min) {
        this.min = min;
    }
    
    /**
     * Gets the max.
     * 
     * @return the max
     */
    public Number getMax() {
        return max;
    }
    
    /**
     * Sets the max.
     * 
     * @param max the new max
     */
    public void setMax(Number max) {
        this.max = max;
    }
    
    /**
     * Sets the range.
     * 
     * @param min the min
     * @param max the max
     * @param step the step
     */
    public void setRange(Number min, Number max, Number step) {
    	setRange(min,max);
        setSteps(step);
    }
    
    /**
     * Sets the range.
     * 
     * @param min the min
     * @param max the max
     */
    public void setRange(Number min, Number max) {
        setMin(min);
        setMax(max);
    }

	/* (non-Javadoc)
	 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	 */
	public JSONValue buildJSON() {
    	JSONObject json = new JSONObject();
    	if (stroke != null) json.put("stroke", new JSONNumber(stroke));
    	if (colour != null) json.put("colour", new JSONString(colour));
    	if (gridColour != null) json.put("grid-colour", new JSONString(gridColour));
    	if (steps != null) json.put("steps", new JSONNumber(steps.doubleValue()));
    	if (offset != null) json.put("offset", new JSONNumber(offset));
    	if (zdepth3d != null) json.put("3d", new JSONNumber(zdepth3d));
    	if (min != null) json.put("min", new JSONNumber(min.doubleValue()));
    	if (max != null) json.put("max", new JSONNumber(max.doubleValue()));    	
    	return json;
	}
}
