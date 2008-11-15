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
package com.gwttest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.IOnClickListener;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.elements.PieChart;
import com.rednels.ofcgwt.client.model.elements.PieChart.Slice;


/**
 * Example Test using OFCGWT
 */
public class Events implements EntryPoint {
	ChartWidget chart;
	HTML chartLabel = new HTML();
	Button resetBut;
	
	public void onModuleLoad() {
		SimplePanel main = new SimplePanel();
		main.setHeight("100%");
		main.setWidth("100%");
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(10);
						
		VerticalPanel vp = new VerticalPanel();
	    //add home page
	    HTML homeText = new HTML("<h2>Welcome to OFCGWT</h2>"
	         + "<i>....the OpenFlashChart GWT Library</i></br></br>"
	         + "This demonstration showcases the events \"onClick\" feature with a drill-down effect."
	         );
	    vp.add(homeText);
	    vp.setCellHeight(homeText, "300");
	    	    
	    hp.add(vp);
	    hp.setCellWidth(vp, "300");	    

		//add chart	    
	    DecoratorPanel dp = new DecoratorPanel();
		SimplePanel pieSp = new SimplePanel();
		chart = new ChartWidget();	
		chart.setSize("400", "300");
		pieSp.add(chart);
		dp.add(pieSp);
		hp.add(dp);
		
		VerticalPanel chartlist = new VerticalPanel();
		chartlist.setSpacing(5);
		
		resetBut = new Button("Reset",new ClickListener(){
			public void onClick(Widget sender) {
				chart.setJsonData(getPieChartLayer1().toString());
			}
		});	
		chartlist.add(chartLabel);	
		chartlist.add(resetBut);	
		
		chart.setJsonData(getPieChartLayer1().toString());
					
		hp.add(chartlist);
	    hp.setCellWidth(chartlist, "300");
		
		RootPanel.get().add(hp);		
	}

	private ChartData getPieChartLayer1() {	
		ChartData cd = new ChartData("Sales by Region - Layer 1","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		PieChart pie = new PieChart();
		pie.setTooltip("#label# $#val#<br>#percent#");
		pie.setAnimate(false);
		pie.setStartAngle(33);
		pie.setColours(getColours());
		Slice s1 = new Slice(33400,"AU");
		s1.addOnClickListener(chart, new IOnClickListener(){
			public void handleOnClickEvent() {
				chart.setJsonData(getPieChartAULayer2().toString());
			}});
		pie.addSlices(s1);
		Slice s2 = new Slice(75000,"USA");
		s2.addOnClickListener(chart, new IOnClickListener(){
			public void handleOnClickEvent() {
				chart.setJsonData(getPieChartUSLayer2().toString());
			}});
		pie.addSlices(s2);
		Slice s3 = new Slice(63500,"UK");
		s3.addOnClickListener(chart, new IOnClickListener(){
			public void handleOnClickEvent() {
				chart.setJsonData(getPieChartUKLayer2().toString());
			}});
		pie.addSlices(s3);
		cd.addElements(pie);
		chartLabel.setHTML("This graph shows sales in each of our regions.<p>Click a slice to drill-down into a region.");
		resetBut.setEnabled(false);
		return cd;
	}	

	private ChartData getPieChartAULayer2() {	
		ChartData cd = new ChartData("Sales in Australia - Layer 2","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#FFEEEE");
		PieChart pie = new PieChart();
		pie.setTooltip("#label# $#val#<br>#percent#");
		pie.setAnimate(false);
		pie.setStartAngle(33);
		pie.setColours(getColours());
		pie.addSlices(new Slice(6000,"QLD"));
		pie.addSlices(new Slice(8000,"NSW"));
		pie.addSlices(new Slice(9000,"VIC"));
		pie.addSlices(new Slice(3000,"SA"));
		pie.addSlices(new Slice(1400,"TAS"));
		pie.addSlices(new Slice(1000,"NT"));
		pie.addSlices(new Slice(5000,"WA"));
		cd.addElements(pie);
		chartLabel.setHTML("This graph shows sales in each state of Australia.<p>Click reset to go back up again.");
		resetBut.setEnabled(true);
		return cd;
	}	

	private ChartData getPieChartUSLayer2() {	
		ChartData cd = new ChartData("Sales in USA - Layer 2","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#DDFFDD");
		PieChart pie = new PieChart();
		pie.setTooltip("#label# $#val#<br>#percent#");
		pie.setAnimate(false);
		pie.setStartAngle(33);
		pie.setColours(getColours());
		pie.addSlices(new Slice(9000,"CA"));
		pie.addSlices(new Slice(9000,"NY"));
		pie.addSlices(new Slice(3500,"KY"));
		pie.addSlices(new Slice(5000,"CO"));
		pie.addSlices(new Slice(6000,"WA"));
		pie.addSlices(new Slice(5000,"NV"));
		pie.addSlices(new Slice(6000,"MO"));
		pie.addSlices(new Slice(8000,"LA"));
		pie.addSlices(new Slice(9000,"UT"));
		pie.addSlices(new Slice(3000,"TN"));
		pie.addSlices(new Slice(5500,"TX"));
		pie.addSlices(new Slice(1000,"VA"));
		pie.addSlices(new Slice(5000,"AK"));
		cd.addElements(pie);
		chartLabel.setHTML("This graph shows sales in each state of the USA.<p>Click reset to go back up again.");
		resetBut.setEnabled(true);
		return cd;
	}	

	private ChartData getPieChartUKLayer2() {	
		ChartData cd = new ChartData("Sales in UK - Layer 2","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#EEEEFF");
		PieChart pie = new PieChart();
		pie.setTooltip("#label# $#val#<br>#percent#");
		pie.setAnimate(false);
		pie.setStartAngle(33);
		pie.setColours(getColours());
		pie.addSlices(new Slice(9000,"Scotland"));
		pie.addSlices(new Slice(9000,"Wales"));
		pie.addSlices(new Slice(7500,"Central"));
		pie.addSlices(new Slice(13000,"Greater London"));
		pie.addSlices(new Slice(6000,"Midlands"));
		pie.addSlices(new Slice(5000,"East Anglia"));
		pie.addSlices(new Slice(6000,"South"));
		pie.addSlices(new Slice(8000,"North"));
		cd.addElements(pie);
		chartLabel.setHTML("This graph shows sales in each state of the UK.<p>Click reset to go back up again.");
		resetBut.setEnabled(true);
		return cd;
	}	
	
	private String[] getColours() {
		return new String[]{"#ff0000","#00ff00","#0000ff","#ff9900","#ff00ff","#FFFF00","#6699FF","#339933"};		
	}
}