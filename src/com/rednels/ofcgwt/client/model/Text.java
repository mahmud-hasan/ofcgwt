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
package com.rednels.ofcgwt.client.model;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

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
