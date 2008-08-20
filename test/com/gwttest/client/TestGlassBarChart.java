package com.gwttest.client;

import com.rednels.ofcgwt.client.model.Chart;
import com.rednels.ofcgwt.client.model.Text;
import com.rednels.ofcgwt.client.model.elements.BarChart;

public class TestGlassBarChart implements ITestCharts {

	Chart c;
	String json;
	
	public TestGlassBarChart() {
		
		c = new Chart("No data update!!", "font-size: 20px; color:#0000ff; font-family: Verdana; text-align: center;").setYLegend(new Text("Open Flash Chart", "color: #736AFF; font-size: 12px;")).addElements(new BarChart(com.rednels.ofcgwt.client.model.elements.BarChart.Style.GLASS).setColour("#9933CC").addValues(9, 6, 7, 9, 5).addBars(new BarChart.Bar(7, "#FF0000").setTooltip("RED<br>Mooo<br>#val#")).addValues(6, 9, 7).setTooltip("Tip for purple bars<br>val=#val#, top=#top#").setAlpha(0.5f));
		json = c.toString();
	}
	
	public String getJSON() {
		return json;
	}

}

/*
 * 
 * 
 * "text--": "Page views", "font-size--": 10,
 * 
 * { "type": "bar_glass", "alpha": 0.5, "colour": "#44FF44", "tip":
 * "Tip for green bars<br>val=#val#, top=#top#", "text--": "Page views",
 * "font-size--": 10, "values" : [6,7,9,5,9,{"top":7,"colour":"#FF0000",
 * "tip": "RED<br>W000t<br>#val#"},6,5,2] } ],
 * 
 * "x_axis":{ "stroke": 1, "tick_height": 10, "colour": "#d000d0",
 * "grid_colour": "#00ff00", "labels": { "labels":
 * ["January","February","March"
 * ,"April","May","June","July","August","Spetember"] } },
 * 
 * "y_axis":{ "stroke": 4, "tick_length": 3, "colour": "#d000d0",
 * "grid_colour": "#00ff00", "offset": 0, "max": 20 },
 * 
 * "tooltip":{ "text": "Global Tooltip<br>val=#val#, top=#top#" }
 */