package com.rednels.ofcgwt.client.model;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class Text implements JSONizable {
    private String text;
    private String style;
    
    public Text() {
        this(null, null);
    }
    
    public Text(String text) {
        this(text, null);
    }
    
    public Text(String text, String style) {
        setText(text);
        setStyle(style);
    }
    
    public String getText() {
        return text;
    }
    public JSONizable setText(String text) {
        this.text = text;
        return this;
    }
    public String getStyle() {
        return style;
    }
    public JSONizable setStyle(String style) {
        this.style = style;
        return this;
    }

	public JSONObject buildJSONObject() {
    	JSONObject json = new JSONObject();
    	if (text != null) json.put("text", new JSONString(text));
    	if (style != null) json.put("style", new JSONString(style));
    	return json;
	}
}
