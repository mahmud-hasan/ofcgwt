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
 * This is the most important class in the Java OFC library.
 * Start here, configuring the title, axes, legends, labels,
 * and draw-able elements in your chart.  Coerce the
 * object to a String with the toString() method to get the
 * chart data back out.
 */
public class Chart  implements JSONizable {
    private JSONizable title;
    private XAxis x_axis;
    private YAxis y_axis;
    private YAxis y_axis_right;
    private JSONizable y_legend;
    private JSONizable x_legend;
    private String bg_colour;
    private Collection<Element> elements = new HashSet<Element>();

    public XAxis getXAxis() {
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

    public JSONizable getTitle() {
        return title;
    }

    public Chart setTitle(JSONizable title) {
        this.title = title;
        return this;
    }
    
    public JSONizable getXLegend() {
        return x_legend;
    }
    
    public Chart setXLegend(JSONizable x_legend) {
        this.x_legend = x_legend;
        return this;
    }
    
    public JSONizable getYLegend() {
        return y_legend;
    }

    public Chart setYLegend(JSONizable y_legend) {
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
        /*
    {"title":{ "text":  "Many data lines","style": "{font-size: 20px; color:#0000ff; font-family: Verdana; text-align: center;}"},"y_legend":{"text": "Open Flash Chart",    "style": "{color: #736AFF; font-size: 12px;}"  },  "elements":[    {      "type":      "bar",      "alpha":     0.5,      "colour":    "#9933CC",      "text":      "Page views",      "font-size": 10,      "values" :   [9,6,7,9,5,7,6,9,7]    },    {      "type":      "bar",      "alpha":     0.5,      "colour":    "#CC9933",      "text":      "Page views 2",      "font-size": 10,      "values" :   [6,7,9,5,7,6,9,7,3]    }  ],  "x_axis":{    "stroke":1,    "tick_height":10,    "colour":"#d000d0",    "grid_colour":"#00ff00",    "labels": ["January","February","March","April","May","June","July","August","Spetember"]   },  "y_axis":{    "stroke":      4,    "tick_length": 3,    "colour":      "#d000d0",    "grid_colour": "#00ff00",    "offset":      0,    "max":         20  }}
    private Text title;
    private XAxis x_axis;
    private YAxis y_axis;
    private YAxis y_axis_right;
    private Text y_legend;
    private Text x_legend;
    private String bg_colour;
    private Collection<Element> elements = new HashSet<Element>();
         */
    
    @Override
    public String toString() {
        return buildJSONObject().toString();
    }
}
