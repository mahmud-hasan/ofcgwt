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
/**
 * Base class for an OFC label  
 */
public class Label implements JSONizable {
    
    /** The text. */
    private String text;
    
    /** The colour. */
    private String colour;
    
    /** The size. */
    private Integer size;
    
    /** The rotate. */
    private Rotation rotate;
    
    /** The visible. */
    private Boolean visible;
    
    /**
     * The Enum Rotation.
     */
    public static enum Rotation {
        
        /** The VERTICAL. */
        VERTICAL("vertical"),
        
        /** The DIAGONAL. */
        DIAGONAL("diagonal"),
        
        /** The HORIZONTAL. */
        HORIZONTAL("horizontal");
        
        /** The text. */
        private final String text;
        
        /**
         * Creates a new rotation.
         * 
         * @param text the text
         */
        Rotation(String text) {
            this.text = text;
        }
        
        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }
    }
    
    /**
     * Creates a new label.
     */
    public Label() {
        this(null);
    }
    
    /**
     * Creates a new label.
     * 
     * @param text the text
     */
    public Label(String text) {
        setText(text);
    }

    /**
     * Gets the text.
     * 
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text.
     * 
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
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
     * @param colour the colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Gets the size.
     * 
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Sets the size.
     * 
     * @param size the size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Gets the rotation.
     * 
     * @return the rotation
     */
    public Rotation getRotation() {
        return rotate;
    }

    /**
     * Sets the rotation.
     * 
     * @param rotate the rotate
     */
    public void setRotation(Rotation rotate) {
        this.rotate = rotate;
    }

    /**
     * Gets the visible.
     * 
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * Sets the visible.
     * 
     * @param visible the visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
	/* (non-Javadoc)
	 * @see com.rednels.ofcgwt.client.model.JSONizable#buildJSONObject()
	 */
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
