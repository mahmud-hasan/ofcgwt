package com.rednels.ofcgwt.client.model.elements;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;



public abstract class Element implements JSONizable {
    private final String type;
    private Float alpha;
    private String text;
    private Integer fontSize; //font-size
    private String tooltip; //tip
    private List<Object> values = new ArrayList<Object>();
    
    protected Element(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }

    public Float getAlpha() {
        return alpha;
    }

    public Element setAlpha(Float alpha) {
        this.alpha = alpha;
        return this;
    }

    public String getText() {
        return text;
    }

    public Element setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public Element setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
        return this;
    }
    
    public List<Object> getValues() {
        return values;
    }
    
    public Element setTooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }
    
    public String getTooltip() {
        return tooltip;
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = new JSONObject();
    	if (type != null) json.put("type", new JSONString(type));
    	if (alpha != null) json.put("alpha", new JSONNumber(alpha));
    	if (text != null) json.put("text", new JSONString(text));
    	if (fontSize != null) json.put("font-size", new JSONNumber(fontSize));
    	if (tooltip != null) json.put("tip", new JSONString(tooltip));
    	JSONArray ary = new JSONArray();
    	int index = 0;
    	for (Object o : getValues()) {
    		if (o instanceof Number) ary.set(index++, new JSONNumber(((Number)o).doubleValue()));
    		if (o instanceof String) ary.set(index++, new JSONString((String)o));
    		if (o instanceof JSONizable) ary.set(index++, ((JSONizable)o).buildJSONObject());
        }
    	if (index != 0) json.put("values",ary);
    	return json;
	}
}
