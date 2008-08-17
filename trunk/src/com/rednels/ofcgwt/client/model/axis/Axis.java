package com.rednels.ofcgwt.client.model.axis;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;

public abstract class Axis implements JSONizable {
    private Integer stroke;
    private String colour;
    private String grid_colour;
    private Integer steps;
    private Integer offset;
    private Integer threed; //3d
    private Integer min;
    private Integer max;
    
    public Integer getStroke() {
        return stroke;
    }
    public Axis setStroke(Integer stroke) {
        this.stroke = stroke;
        return this;
    }
    public String getColour() {
        return colour;
    }
    public Axis setColour(String colour) {
        this.colour = colour;
        return this;
    }
    public String getGridColour() {
        return grid_colour;
    }
    public Axis setGridColour(String grid_colour) {
        this.grid_colour = grid_colour;
        return this;
    }
    public Integer getSteps() {
        return steps;
    }
    public Axis setSteps(Integer steps) {
        this.steps = steps;
        return this;
    }
    public Integer getOffset() {
        return offset;
    }
    public Axis setOffset(Boolean offset) {
        if (offset == null) this.offset = null;
        this.offset = offset ? 1 : 0;
        return this;
    }
    public Integer get3D() {
        return threed;
    }
    public Axis set3D(Integer threed) {
        this.threed = threed;
        return this;
    }
    public Integer getMin() {
        return min;
    }
    public Axis setMin(Integer min) {
        this.min = min;
        return this;
    }
    public Integer getMax() {
        return max;
    }
    public Axis setMax(Integer max) {
        this.max = max;
        return this;
    }
    
    public Axis setRange(Integer min, Integer max, Integer step) {
        setMin(min);
        setMax(max);
        setSteps(step);
        return this;
    }
    
    public Axis setRange(Integer min, Integer max) {
        return setRange(min, max, getSteps());
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = new JSONObject();
    	if (stroke != null) json.put("stroke", new JSONNumber(stroke));
    	if (colour != null) json.put("colour", new JSONString(colour));
    	if (grid_colour != null) json.put("grid_colour", new JSONString(grid_colour));
    	if (steps != null) json.put("steps", new JSONNumber(steps));
    	if (offset != null) json.put("offset", new JSONNumber(offset));
    	if (threed != null) json.put("3d", new JSONNumber(threed));
    	if (min != null) json.put("min", new JSONNumber(min));
    	if (max != null) json.put("max", new JSONNumber(max));    	
    	return json;
	}
}
