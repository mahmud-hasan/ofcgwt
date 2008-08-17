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

import java.util.Arrays;
import java.util.Collection;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;

public class ScatterChart extends Element implements JSONizable {
    
    private String colour;
    private Integer dotSize; 
    
    protected ScatterChart(String type) {
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
