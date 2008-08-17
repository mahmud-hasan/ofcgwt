package com.rednels.ofcgwt.client.model.elements;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;

public class BarChart extends Element implements JSONizable {   
    private String colour;
    
    public BarChart() {
        this(Style.NORMAL);
    }
    
    public BarChart(Style style) {
        super(style.getStyle());
    }
    
    protected BarChart(String style) {
        super(style);
    }
    
    public BarChart addValues(Number... values) {
        getValues().addAll(Arrays.asList(values));
        return this;
    }
    
    public BarChart addValues(List<Number> values) {
        getValues().addAll(values);
        return this;
    }
    
    public BarChart addBars(Bar... bars) {
        getValues().addAll(Arrays.asList(bars));
        return this;
    }
    
    public BarChart addBars(List<Bar> bars) {
        getValues().addAll(bars);
        return this;
    }
    
    public String getColour() {
        return colour;
    }

    public BarChart setColour(String colour) {
        this.colour = colour;
        return this;
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (colour != null) json.put("colour", new JSONString(colour)); 	
    	return json;
	}
    
    public static enum Style {
        NORMAL("bar"),
        THREED("bar_3d"),
        GLASS("bar_glass");
        
        private String style;
        
        Style(String style) {
            this.style = style;
        }
        
        public String getStyle() {
            return style;
        }
    }
    
    public static class Bar implements JSONizable {
    	private Number top;
    	private Number bottom;
    	private String colour;
    	private String tooltip; // tip
    	
    	public Bar(Number top, Number bottom, String colour) {
    	    setTop(top);
    	    setBottom(bottom);
    	    setColour(colour);
    	}
    	
    	public Bar(Number top, Number bottom) {
    		this(top, bottom, null);
    	}
    	
    	public Bar(Number top, String colour) {
    	    this(top, null, colour);
    	}
    	
    	public Bar(Number top) {
    	    this(top, null, null);
    	}
    	
		public Number getTop() {
			return top;
		}
		public Bar setTop(Number top) {
			this.top = top;
			return this;
		}
		public Number getBottom() {
			return bottom;
		}
		public Bar setBottom(Number bottom) {
			this.bottom = bottom;
			return this;
		}
		public String getColour() {
			return colour;
		}
		public Bar setColour(String colour) {
			this.colour = colour;
			return this;
		}
		public String getTooltip() {
			return tooltip;
		}
		public Bar setTooltip(String tooltip) {
			this.tooltip = tooltip;
			return this;
		}

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
