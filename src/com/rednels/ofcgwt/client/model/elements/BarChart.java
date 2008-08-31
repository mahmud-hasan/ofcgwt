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
import java.util.List;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;
/**
 * Class for an OFC bar chart that extends Element   
 * @see com.rednels.ofcgwt.client.model.elements.Element
 */
public class BarChart extends Element implements JSONizable {   
    
    /** The colour. */
    private String colour;
    
    /**
     * Creates a new bar chart with normal style.
     */
    public BarChart() {
        this(BarStyle.NORMAL);
    }
    
    /**
     * Creates a new bar chart.
     * 
     * @param style the style
     */
    public BarChart(BarStyle style) {
        super(style.getStyle());
    }
    
    /**
     * Creates a new bar chart.
     * 
     * @param style the style
     */
    protected BarChart(String style) {
        super(style);
    }
    
    /**
     * Adds the values.
     * 
     * @param values the values
     */
    public void addValues(Number... values) {
        getValues().addAll(Arrays.asList(values));
    }
    
    /**
     * Adds the values.
     * 
     * @param values the values
     */
    public void addValues(List<Number> values) {
        getValues().addAll(values);
    }
    
    /**
     * Adds the bars.
     * 
     * @param bars the bars
     */
    public void addBars(Bar... bars) {
        getValues().addAll(Arrays.asList(bars));
    }
    
    /**
     * Adds the bars.
     * 
     * @param bars the bars
     */
    public void addBars(List<Bar> bars) {
        getValues().addAll(bars);
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

	/* (non-Javadoc)
	 * @see com.rednels.ofcgwt.client.model.elements.Element#buildJSONObject()
	 */
	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (colour != null) json.put("colour", new JSONString(colour)); 	
    	return json;
	}
    
    /**
     * Enumeration BarStyle - used with BarChart.
     */
    public static enum BarStyle {
        
        /** NORMAL */
        NORMAL("bar"),
        
        /** 3D */
        THREED("bar_3d"),
        
        /** GLASS */
        GLASS("bar_glass");
        
        /** The style. */
        private String style;
        
        /**
         * Creates a new bar style.
         * 
         * @param style the style
         */
        BarStyle(String style) {
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
     * Base class for OFC bar chart bars 
     */
    public static class Bar implements JSONizable {
    	
	    /** The top. */
	    private Number top;
    	
	    /** The bottom. */
	    private Number bottom;
    	
	    /** The colour. */
	    private String colour;
    	
	    /** The tooltip. */
	    private String tooltip;
    	
    	/**
	     * Creates a new bar.
	     * 
	     * @param top the top
	     * @param bottom the bottom
	     * @param colour the colour
	     */
	    public Bar(Number top, Number bottom, String colour) {
    	    setTop(top);
    	    setBottom(bottom);
    	    setColour(colour);
    	}
    	
    	/**
	     * Creates a new bar.
	     * 
	     * @param top the top
	     * @param bottom the bottom
	     */
	    public Bar(Number top, Number bottom) {
    		this(top, bottom, null);
    	}
    	
    	/**
	     * Creates a new bar.
	     * 
	     * @param top the top
	     * @param colour the colour
	     */
	    public Bar(Number top, String colour) {
    	    this(top, null, colour);
    	}
    	
    	/**
	     * Creates a new bar.
	     * 
	     * @param top the top
	     */
	    public Bar(Number top) {
    	    this(top, null, null);
    	}
    	
		/**
		 * Gets the top.
		 * 
		 * @return the top
		 */
		public Number getTop() {
			return top;
		}
		
		/**
		 * Sets the top.
		 * 
		 * @param top the new top
		 */
		public void setTop(Number top) {
			this.top = top;
		}
		
		/**
		 * Gets the bottom.
		 * 
		 * @return the bottom
		 */
		public Number getBottom() {
			return bottom;
		}
		
		/**
		 * Sets the bottom.
		 * 
		 * @param bottom the new bottom
		 */
		public void setBottom(Number bottom) {
			this.bottom = bottom;
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
		 * Gets the tooltip.
		 * 
		 * @return the tooltip
		 */
		public String getTooltip() {
			return tooltip;
		}
		
		/**
		 * Sets the tooltip.
		 * 
		 * @param tooltip the new tooltip
		 */
		public void setTooltip(String tooltip) {
			this.tooltip = tooltip;
		}

    	/* (non-Javadoc)
	     * @see com.rednels.ofcgwt.client.model.JSONizable#buildJSONObject()
	     */
	    public JSONObject buildJSONObject() {
        	JSONObject json = new JSONObject();
        	if (top != null) json.put("top", new JSONNumber(top.doubleValue()));
        	if (bottom != null) json.put("bottom", new JSONNumber(bottom.doubleValue())); 	
        	if (colour != null) json.put("colour", new JSONString(colour));
        	if (tooltip != null) json.put("tip", new JSONString(tooltip));
        	return json;
    	}
    }
}
