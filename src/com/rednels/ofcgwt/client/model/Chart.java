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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.Element;

/**
 * This is the most important class in the ofcfwt library.
 * Start here, configuring the title, axes, legends, labels,
 * and draw-able elements in your chart.  
 * 
 * When finished, call toString() and the GWT JSON objects will
 * convert the chart data into a OFC2 JSON data string.
 */
public class Chart  implements JSONizable {
    private Text title;
    private XAxis x_axis;
    private YAxis y_axis;
    private YAxis y_axis_right;
    private Text y_legend;
    private Text x_legend;
    private String bg_colour;
    private Collection<Element> elements = new HashSet<Element>();

    public XAxis getXAxis() {
    	if (x_axis == null) x_axis = new XAxis(); 
        return x_axis;
    }
    
    public Chart() {
        //nothing...
    }
    
    public Chart(String titleText) {
        this(titleText, null);
    }
    
    public Chart(String titleText, String style) {
        this.setTitle(new Text(titleText, style));
    }

    public Chart setXAxis(XAxis x_axis) {
        this.x_axis = x_axis;
        return this;
    }

    public YAxis getYAxis() {
    	if (y_axis == null) y_axis = new YAxis();
        return y_axis;
    }

    public Chart setYAxis(YAxis y_axis) {
        this.y_axis = y_axis;
        return this;
    }
    
    public Chart setYAxisRight(YAxis y_axis_right) {
        this.y_axis_right = y_axis_right;
        return this;
    }
    
    public YAxis getYAxisRight() {
        return y_axis_right;
    }

    public Text getTitle() {
        return title;
    }

    public Chart setTitle(Text title) {
        this.title = title;
        return this;
    }
    
    public Text getXLegend() {
        return x_legend;
    }
    
    public Chart setXLegend(Text x_legend) {
        this.x_legend = x_legend;
        return this;
    }
    
    public Text getYLegend() {
        return y_legend;
    }

    public Chart setYLegend(Text y_legend) {
        this.y_legend = y_legend;
        return this;
    }
    
    public String getBackgroundColour() {
        return bg_colour;
    }
    
    public Chart setBackgroundColour(String bg_colour) {
        this.bg_colour = bg_colour;
        return this;
    }

    public Collection<Element> getElements() {
        return elements;
    }
    
    public Chart setElements(Collection<Element> elements) {
        this.elements.clear();
        this.elements.addAll(elements);
        return this;
    }
    
    public Chart addElements(Element... e) {
        elements.addAll(Arrays.asList(e));
        return this;
    }
    
    public Chart addElements(Collection<Element> coll) {
        elements.addAll(coll);
        return this;
    }
    
    public boolean removeElement(Element e) {
        return elements.remove(e);
    }
    
    public Element getElementByText(String text) {
        for (Element e : getElements()) {
            if (text.equals(e.getText())) return e;
        }
        return null;
    }
    
    public JSONObject buildJSONObject() {
    	JSONObject json = new JSONObject();
    	if (title != null) json.put("title", title.buildJSONObject());
    	if (x_axis != null) json.put("x_axis", x_axis.buildJSONObject());
    	if (y_axis != null) json.put("y_axis", y_axis.buildJSONObject());
    	if (y_axis_right != null) json.put("y_axis_right", y_axis_right.buildJSONObject());
    	if (y_legend != null) json.put("y_legend", y_legend.buildJSONObject());
    	if (x_legend != null) json.put("x_legend", x_legend.buildJSONObject());
    	if (bg_colour != null) json.put("bg_colour", new JSONString(bg_colour));    	
    	JSONArray ary = new JSONArray();
    	int index = 0;
    	for (Element e : getElements()) {
    		ary.set(index++, e.buildJSONObject());
        }
    	if (index != 0) json.put("elements",ary);    	
    	return json;
    }
    
    @Override
    public String toString() {
        return buildJSONObject().toString();
    }
}
