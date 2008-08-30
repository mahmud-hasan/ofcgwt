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

import java.util.Arrays;
import java.util.Collection;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;

public class ScatterChart extends Element implements JSONizable {
    
    private String colour;
    private Integer dotSize; 
    
    public ScatterChart() {
        super("scatter");
    }
    
    public ScatterChart addPoints(Point... points) {
        getValues().addAll(Arrays.asList(points));
        return this;
    }
    
    public ScatterChart addPoint(Number x, Number y) {
        return addPoints(new Point(x, y));
    }
    
    public ScatterChart addPoints(Collection<Point> points) {
        getValues().addAll(points);
        return this;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getDotSize() {
        return dotSize;
    }

    public void setDotSize(Integer dotSize) {
        this.dotSize = dotSize;
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (dotSize != null) json.put("dot-size", new JSONNumber(dotSize.doubleValue()));
    	if (colour != null) json.put("colour", new JSONString(colour));    	
    	return json;
	}

    public static class Point implements JSONizable {
        private Number x;
        private Number y;
        
        public Point(Number x, Number y) {
            this.x = x;
            this.y = y;
        }

        public Number getX() {
            return x;
        }

        public void setX(Number x) {
            this.x = x;
        }

        public Number getY() {
            return y;
        }

        public void setY(Number y) {
            this.y = y;
        }

    	public JSONObject buildJSONObject() {
        	JSONObject json = new JSONObject();
        	if (x != null) json.put("x", new JSONNumber(x.doubleValue()));
        	if (y != null) json.put("y", new JSONNumber(y.doubleValue()));
        	return json;
    	}
    }
}
