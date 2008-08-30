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
