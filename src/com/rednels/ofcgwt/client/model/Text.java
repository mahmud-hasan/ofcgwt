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
package com.rednels.ofcgwt.client.model;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * Base class for an OFC text element   
 */
public class Text implements JSONizable {
    private String text;
    private String style;
    
    /**
     * Create a new Text instance
     */
    public Text() {
        this(null, null);
    }
    
    /**
     * Create a new Text instance with the given text
     * @param text String
     */
    public Text(String text) {
        this(text, null);
    }
    
    /**
     * Create a new Text instance with the given text and style. Style is a CSS string for formatting text.  
     * @param text String
     * @param style String
     */
    public Text(String text, String style) {
        setText(text);
        setStyle(style);
    }
    
    /**
     * Get the text
     * @return String text 
     */
    public String getText() {
        return text;
    }
    /**
     * Sets the text string. 
     * @param text String
     */
    public void setText(String text) {
        this.text = text;
    }
    /**
     * Get the style
     * @return String style 
     */
    public String getStyle() {
        return style;
    }
    /**
     * Sets the text string. 
     * @param style String
     */
    public void setStyle(String style) {
        this.style = style;
    }

	/* (non-Javadoc)
	 * @see com.rednels.ofcgwt.client.model.JSONizable#buildJSONObject()
	 */
	public JSONObject buildJSONObject() {
    	JSONObject json = new JSONObject();
    	if (text != null) json.put("text", new JSONString(text));
    	if (style != null) json.put("style", new JSONString(style));
    	return json;
	}
}
