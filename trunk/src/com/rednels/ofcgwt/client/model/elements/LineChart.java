package com.rednels.ofcgwt.client.model.elements;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;

public class LineChart extends Element implements JSONizable {
    private static transient final Integer DEFAULT_FONTSIZE = 10;
    
    private Integer width;
    private Integer dotSize; //dot-size
    private Integer haloSize; //halo-size
    private String colour;
    
    public LineChart() {
        this(Style.NORMAL);
    }
    
    public LineChart(Style style) {
        this(style.getStyle());
    }
    
    protected LineChart(String type) {
        super(type);
        setFontSize(DEFAULT_FONTSIZE);
    }

    public Integer getWidth() {
        return width;
    }

    public LineChart setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getDotSize() {
        return dotSize;
    }

    public LineChart setDotSize(Integer dotSize) {
        this.dotSize = dotSize;
        return this;
    }

    public String getColour() {
        return colour;
    }

    public LineChart setColour(String colour) {
        this.colour = colour;
        return this;
    }
    
    public LineChart addValues(Number... values) {
        return addValues(Arrays.asList(values));
    }
    
    public LineChart addValues(List<Number> values) {
        getValues().addAll(values);
        return this;
    }
    
    public LineChart addDots(Dot...dots) {
        return addDots(Arrays.asList(dots));
    }
    
    public LineChart addDots(List<Dot> dots) {
        getValues().addAll(dots);
        return this;
    }
    
    public Integer getHaloSize() {
        return haloSize;
    }

    public LineChart setHaloSize(Integer haloSize) {
        this.haloSize = haloSize;
        return this;
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = super.buildJSONObject();
    	if (width != null) json.put("width", new JSONNumber(width));
    	if (dotSize != null) json.put("dot-size", new JSONNumber(dotSize));
    	if (haloSize != null) json.put("halo-size", new JSONNumber(haloSize));
    	if (colour != null) json.put("colour", new JSONString(colour));
    	return json;
	}

    public static class Dot implements JSONizable {
        private Integer haloSize; //halo-size
        private Integer dotSize; //dot-size
        private Number value;
        private String colour;
        
        public Dot(Number value) {
            this(value, null, null, null);
        }
        
        public Dot(Number value, String colour) {
            this(value, colour, null, null);
        }
        
        public Dot(Number value, String colour, Integer dotSize, Integer haloSize) {
            setValue(value);
            setColour(colour);
            setDotSize(dotSize);
            setHaloSize(haloSize);
        }
        
        public Integer getHaloSize() {
            return haloSize;
        }
        public Dot setHaloSize(Integer haloSize) {
            this.haloSize = haloSize;
            return this;
        }
        public Integer getDotSize() {
            return dotSize;
        }
        public Dot setDotSize(Integer dotSize) {
            this.dotSize = dotSize;
            return this;
        }
        public Number getValue() {
            return value;
        }
        public Dot setValue(Number value) {
            this.value = value;
            return this;
        }
        public String getColour() {
            return colour;
        }
        public Dot setColour(String colour) {
            this.colour = colour;
            return this;
        }

    	public JSONObject buildJSONObject() {
        	JSONObject json = new JSONObject();
        	if (haloSize != null) json.put("halo-size", new JSONNumber(haloSize.doubleValue()));
        	if (dotSize != null) json.put("dot-size", new JSONNumber(dotSize.doubleValue())); 
        	if (value != null) json.put("value", new JSONNumber(value.doubleValue())); 	
        	if (colour != null) json.put("colour", new JSONString(colour));
        	return json;
    	}
    }
    
    public static enum Style {
        NORMAL("line"),
        DOT("line_dot"),
        HOLLOW("line_hollow");
        
        private String style;
        
        Style(String style) {
            this.style = style;
        }
        
        public String getStyle() {
            return style;
        }
    }
}
