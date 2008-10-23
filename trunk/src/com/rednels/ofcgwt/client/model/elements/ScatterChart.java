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
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.JSONizable;
/**
 * Class for an OFC scatter chart that extends Element   
 * @see com.rednels.ofcgwt.client.model.elements.Element
 */
public class ScatterChart extends Element implements JSONizable {
    
    /** The colour. */
    private String colour;
    
    /** The dot size. */
    private Integer dotSize; 
    
    /**
     * Creates a new scatter chart with ScatterStyle.POINT style
     */
    public ScatterChart() {
        this(ScatterStyle.POINT);
    }
    
    /**
     * Creates a new scatter chart with provided style.
     */
    public ScatterChart(ScatterStyle style) {
        super(style.getStyle());
    }
    
    /**
     * Adds the points.
     * 
     * @param points the points
     */
    public void addPoints(Point... points) {
        getValues().addAll(Arrays.asList(points));
    }
    
    /**
     * Adds the point.
     * 
     * @param x the x
     * @param y the y
     */
    public void addPoint(Number x, Number y) {
        addPoints(new Point(x, y));
    }
    
    /**
     * Adds the points.
     * 
     * @param points the points
     */
    public void addPoints(Collection<Point> points) {
        getValues().addAll(points);
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
     * Gets the dot size.
     * 
     * @return the dot size
     */
    public Integer getDotSize() {
        return dotSize;
    }

    /**
     * Sets the dot size.
     * 
     * @param dotSize the new dot size
     */
    public void setDotSize(Integer dotSize) {
        this.dotSize = dotSize;
    }

	/* (non-Javadoc)
	 * @see com.rednels.ofcgwt.client.model.elements.Element.buildJSON()
	 */
	public JSONValue buildJSON() {
    	JSONObject json = (JSONObject)super.buildJSON();
    	if (dotSize != null) json.put("dot-size", new JSONNumber(dotSize.doubleValue()));
    	if (colour != null) json.put("colour", new JSONString(colour));    	
    	return json;
	}
	
    /**
     * Enumeration ScatterStyle - used with ScatterChart.
     */
    public static enum ScatterStyle {
        
        /** NORMAL */
        LINE("scatter_line"),
        
        /** HOLLOW */
        POINT("scatter");
        
        /** The style. */
        private String style;
        
        /**
         * Creates a new scatter style.
         * 
         * @param style the style
         */
        ScatterStyle(String style) {
            this.style = style;
        }
        
        /**
         * Gets the style.
         * 
         * @return the style
         */
        public String getStyle() {
            return style;
        }
    }

    /**
     * Base class for OFC scatter points 
     */
    public static class Point implements JSONizable {
        
        /** The x. */
        private Number x;
        
        /** The y. */
        private Number y;
        
        /**
         * Creates a new point.
         * 
         * @param x the x
         * @param y the y
         */
        public Point(Number x, Number y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Gets the x.
         * 
         * @return the x
         */
        public Number getX() {
            return x;
        }

        /**
         * Sets the x.
         * 
         * @param x the new x
         */
        public void setX(Number x) {
            this.x = x;
        }

        /**
         * Gets the y.
         * 
         * @return the y
         */
        public Number getY() {
            return y;
        }

        /**
         * Sets the y.
         * 
         * @param y the new y
         */
        public void setY(Number y) {
            this.y = y;
        }

    	/* (non-Javadoc)
	     * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	     */
	    public JSONValue buildJSON() {
        	JSONObject json = new JSONObject();
        	if (x != null) json.put("x", new JSONNumber(x.doubleValue()));
        	if (y != null) json.put("y", new JSONNumber(y.doubleValue()));
        	return json;
    	}
    }
}
