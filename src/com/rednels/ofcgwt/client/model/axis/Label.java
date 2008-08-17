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
package com.rednels.ofcgwt.client.model.axis;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;

public class Label implements JSONizable {
    private String text;
    private String colour;
    private Integer size;
    private Rotation rotate;
    private Boolean visible;
    
    public static enum Rotation {
        VERTICAL("vertical"),
        DIAGONAL("diagonal"),
        HORIZONTAL("horizontal");
        
        private final String text;
        Rotation(String text) {
            this.text = text;
        }
        
        @Override
        public String toString() {
            return text;
        }
    }
    
    public Label() {
        this(null);
    }
    
    public Label(String text) {
        setText(text);
    }

    public String getText() {
        return text;
    }

    public Label setText(String text) {
        this.text = text;
        return this;
    }

    public String getColour() {
        return colour;
    }

    public Label setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public Label setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Rotation getRotation() {
        return rotate;
    }

    public Label setRotation(Rotation rotate) {
        this.rotate = rotate;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Label setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }
    
	public JSONObject buildJSONObject() {
    	JSONObject json = new JSONObject();
    	if (text != null) json.put("text", new JSONString(text));
    	if (colour != null) json.put("colour", new JSONString(colour));
    	if (size != null) json.put("size", new JSONNumber(size));
    	if (rotate != null) json.put("rotate", new JSONString(rotate.toString()));    	
    	if (visible != null) json.put("visible", JSONBoolean.getInstance(visible));    	
    	return json;
	}
}
