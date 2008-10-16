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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.ToolTip;
import com.rednels.ofcgwt.client.model.ToolTip.MouseStyle;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.AreaChart;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.HorizontalBarChart;
import com.rednels.ofcgwt.client.model.elements.LineChart;
import com.rednels.ofcgwt.client.model.elements.PieChart;
import com.rednels.ofcgwt.client.model.elements.ScatterChart;
import com.rednels.ofcgwt.client.model.elements.SketchBarChart;
import com.rednels.ofcgwt.client.model.elements.AreaChart.AreaStyle;
import com.rednels.ofcgwt.client.model.elements.BarChart.BarStyle;
import com.rednels.ofcgwt.client.model.elements.LineChart.LineStyle;


/**
 * Example Test using OFCGWT
 */
public class Demo implements EntryPoint {

	private String lineChartData = null;

	public void onModuleLoad() {
		SimplePanel main = new SimplePanel();
		main.setHeight("100%");
		main.setWidth("100%");
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(10);
							    
	    //add home page
	    HTML homeText = new HTML("<h2>Welcome to OFCGWT</h2>"
	         + "<i>....the OpenFlashChart GWT Library</i></br></br>"
	         + "This demonstration site will showcase the many different types of charts that can be inserted into a GWT application."
	         );
	    hp.add(homeText);
	    hp.setCellWidth(homeText, "300");
	    DecoratorPanel dp = new DecoratorPanel();
	    

		//add pie chart
		SimplePanel pieSp = new SimplePanel();
		final ChartWidget chart = new ChartWidget();	
		chart.setSize("400", "300");
		chart.setJsonData(getPieChartData().toString());
		pieSp.add(chart);
		dp.add(pieSp);
		hp.add(dp);
		
		VerticalPanel chartlist = new VerticalPanel();
		chartlist.setSpacing(5);
		RadioButton rb = createRadioButton("PieChart",new Command(){
			public void execute() {
				chart.setJsonData(getPieChartData().toString());					
			}
		});
		rb.setChecked(true);
		chartlist.add(rb);
		
		chartlist.add(createRadioButton("BarChart - Normal",new Command(){
			public void execute() {
				chart.setJsonData(getBarChartNormalData().toString());					
			}
		}));
		
		chartlist.add(createRadioButton("BarChart - Glass",new Command(){
			public void execute() {
				chart.setJsonData(getBarChartGlassData().toString());					
			}
		}));
		
		chartlist.add(createRadioButton("BarChart - 3D",new Command(){
			public void execute() {
				chart.setJsonData(getBarChart3DData().toString());					
			}
		}));

		chartlist.add(createRadioButton("LineChart",new Command(){
			public void execute() {
				chart.setJsonData(getLineChartData().toString());
			}
		}));

		chartlist.add(createRadioButton("ScatterChart",new Command(){
			public void execute() {
				chart.setJsonData(getScatterChartData().toString());
			}
		}));

		chartlist.add(createRadioButton("Horizontal-BarChart",new Command(){
			public void execute() {
				chart.setJsonData(getHorizBarChartData().toString());
			}
		}));

		chartlist.add(createRadioButton("AreaChart - Hollow",new Command(){
			public void execute() {
				chart.setJsonData(getAreaHollowChartData().toString());
			}
		}));

		chartlist.add(createRadioButton("AreaChart - Line",new Command(){
			public void execute() {
				chart.setJsonData(getAreaLineChartData().toString());
			}
		}));

		chartlist.add(createRadioButton("SketchChart",new Command(){
			public void execute() {
				chart.setJsonData(getSketchChartData().toString());
			}
		}));
					
		hp.add(chartlist);
	    hp.setCellWidth(chartlist, "300");
		
		RootPanel.get().add(hp);
	}

	private RadioButton createRadioButton(String string, final Command command) {
		RadioButton rb = new RadioButton("chartlist", string);
		rb.addClickListener(new ClickListener(){
			public void onClick(Widget sender) {
				command.execute();				
			}});		
		return rb;
	}

	private ChartData getPieChartData() {	
		ChartData cd = new ChartData("Sales by Region","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		PieChart pie = new PieChart();
		pie.setAlpha(0.3f);
		pie.setNoLabels(true);
		pie.setTooltip("#label# $#val#<br>#percent#");
		pie.setAnimate(false);
		pie.setGradientFill(true);
		pie.setColours("#ff0000","#00ff00","#0000ff","#ff9900","#ff00ff");
		pie.addSlices(new PieChart.Slice(11000,"AU"));
		pie.addSlices(new PieChart.Slice(88000,"USA"));
		pie.addSlices(new PieChart.Slice(62000,"UK"));
		pie.addSlices(new PieChart.Slice(14000,"JP"));
		pie.addSlices(new PieChart.Slice(43000,"EU"));
		cd.addElements(pie);
		return cd;
	}	

	private ChartData getBarChartNormalData() {
		ChartData cd1 = new ChartData("Sales by Month 2006","font-size: 14px; font-family: Verdana; text-align: center;");
		cd1.setBackgroundColour("#ffffff");
		XAxis xa = new XAxis();
		xa.setLabels("J","F","M","A","M","J","J","A","S","O","N","D");
		xa.setMax(12);
		cd1.setXAxis(xa);
		YAxis ya = new YAxis();
		ya.setSteps(16);
		ya.setMax(160);
		cd1.setYAxis(ya);
		BarChart bchart1 = new BarChart(BarStyle.NORMAL);		
		bchart1.setTooltip("$#val#");
		bchart1.addValues(133,123,144,122,155,123,135,153,123,122,111,100);		
		cd1.addElements(bchart1);
		return cd1;
	}
	
	private ChartData getBarChartGlassData() {
		ChartData cd2 = new ChartData("Sales by Month 2007","font-size: 14px; font-family: Verdana; text-align: center;");
		cd2.setBackgroundColour("#ffffff");
		XAxis xa = new XAxis();
		xa.setLabels("J","F","M","A","M","J","J","A","S","O","N","D");
		xa.setMax(12);
		cd2.setXAxis(xa);
		YAxis ya = new YAxis();
		ya.setSteps(16);
		ya.setMax(160);
		cd2.setYAxis(ya);
		BarChart bchart2 = new BarChart(BarStyle.GLASS);
		bchart2.setColour("#00aa00");
		bchart2.setTooltip("$#val#");
		bchart2.addValues(123,133,134,112,135,143,151,133,103,102,131,120);		
		cd2.addElements(bchart2);
		return cd2;
	}

	private ChartData getBarChart3DData() {
		ChartData cd3 = new ChartData("Sales by Month 2008","font-size: 14px; font-family: Verdana; text-align: center;");
		cd3.setBackgroundColour("#ffffff");
		XAxis xa = new XAxis();
		xa.setLabels("J","F","M","A","M","J","J","A","S","O","N","D");
		xa.setZDepth3D(5);
		xa.setMax(12);
		xa.setTickHeight(4);
		xa.setOffset(true);
		xa.setColour("#909090");
		cd3.setXAxis(xa);
		YAxis ya = new YAxis();
		ya.setSteps(16);
		ya.setMax(160);
		cd3.setYAxis(ya);
		BarChart bchart3 = new BarChart(BarStyle.THREED);
		bchart3.setColour("#ff8800");
		bchart3.setTooltip("$#val#");
		bchart3.addValues(103,123,133,138,126,117,121,143,140,152,121,105);		
		cd3.addElements(bchart3);		
		return cd3;
	}

	private ChartData getLineChartData() {
		ChartData cd = new ChartData("Relative Performance","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		
		LineChart lc1 = new LineChart(LineStyle.NORMAL);
		lc1.setText("PoorEnterprises Pty");
		lc1.setColour("#ff0000");
		for (int t=0;t<30;t++) {
			lc1.addValues(Random.nextDouble()*.5 - .5);
		}
		LineChart lc2 = new LineChart(LineStyle.HOLLOW);
		lc2.setColour("#00ff00");
		lc2.setText("Ave-Ridge Co LLC");
		for (int t=0;t<30;t++) {
			lc2.addValues(Random.nextDouble()*.8);
		}
		LineChart lc3 = new LineChart(LineStyle.DOT);
		lc3.setColour("#0000ff");
		lc3.setText("Suu Perb Enterprises");
		for (int t=0;t<30;t++) {
			lc3.addValues(Random.nextDouble()*1.1 + .5);
		}
		YAxis ya = new YAxis();
		ya.setMax(2);
		ya.setMin(-1);
		cd.setYAxis(ya);
		
		cd.addElements(lc1);
		cd.addElements(lc2);
		cd.addElements(lc3);
		return cd;
	}	

	private ChartData getScatterChartData() {
		ChartData cd = new ChartData("X Y Distribution","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		ScatterChart scat = new ScatterChart();
		scat.setDotSize(3);
		for (int n = 0; n < 100; n++) {
			int x = Random.nextInt(50)-25;
			int y = Random.nextInt(50)-25;
			scat.addPoints(new ScatterChart.Point(x,y));
		}
		XAxis xa = new XAxis();
		xa.setRange(-25, 25, 5);
		cd.setXAxis(xa);
		YAxis ya = new YAxis();
		ya.setRange(-25, 25, 5);
		cd.setYAxis(ya);
		cd.addElements(scat);
		return cd;
	}

	private ChartData getHorizBarChartData() {
		ChartData cd1 = new ChartData("Top Car Speed","font-size: 14px; font-family: Verdana; text-align: center;");
		cd1.setBackgroundColour("#ffffff");
		XAxis xa = new XAxis();
		xa.setRange(0, 200, 20);
		cd1.setXAxis(xa);
		YAxis ya = new YAxis();
		ya.addLabels("Ford","Mazda","BMW","Porche");
		ya.setOffset(true);
		cd1.setYAxis(ya);
		HorizontalBarChart bchart1 = new HorizontalBarChart();
		bchart1.setTooltip("#val# mph");
		bchart1.addBars(new HorizontalBarChart.Bar(187,"#ffff00"));
		bchart1.addBars(new HorizontalBarChart.Bar(144,"#0000ff"));
		bchart1.addBars(new HorizontalBarChart.Bar(123,"#00ff00"));
		bchart1.addBars(new HorizontalBarChart.Bar(133,"#ff0000"));
		cd1.addElements(bchart1);
		cd1.setTooltip(new ToolTip(MouseStyle.FOLLOW));
		return cd1;
	}

	private ChartData getAreaHollowChartData() {
		ChartData cd1 = new ChartData("Volume Consumed","font-size: 14px; font-family: Verdana; text-align: center;");
		cd1.setBackgroundColour("#ffffff");
		AreaChart area1 = new AreaChart(AreaStyle.HOLLOW);
		area1.setFillAlpha(0.7f);
		area1.setDotSize(3);
		XAxis xa = new XAxis();
		int ln=0;
		for( float i=0; i<6.2; i+=0.2 )
		{
			xa.addLabels(""+ln++);
			area1.addValues(Math.sin(i)* 1.9 + 4);
		}		
		
		xa.getLabels().setSteps(3);
		cd1.setXAxis(xa);
		cd1.addElements(area1);
		System.out.println(cd1.toString());
		
		return cd1;		
	}
	
	private ChartData getAreaLineChartData() {
		ChartData cd2 = new ChartData("Growth per Region","font-size: 14px; font-family: Verdana; text-align: center;");
		cd2.setBackgroundColour("#ffffff");
		XAxis xa = new XAxis();
		xa.setLabels("J","F","M","A","M","J","J","A","S","O","N","D");
		xa.setMax(12);
		cd2.setXAxis(xa);
		AreaChart area2 = new AreaChart(AreaStyle.LINE);
		area2.setFillAlpha(0.3f);
		area2.setColour("#ff0000");
		area2.setFillColour("#ff0000");
		for (int n=0;n<12;n++)
			area2.addValues(n*.8);		
		cd2.addElements(area2);
		AreaChart area3 = new AreaChart(AreaStyle.LINE);
		area3.setFillAlpha(0.3f);
		area3.setColour("#00aa00");
		area3.setFillColour("#00aa00");
		for (int n=0;n<12;n++)
			area3.addValues(n*.3+2);		
		cd2.addElements(area3);
		System.out.println(cd2.toString());
		return cd2;
	}

	private ChartData getSketchChartData() {	
		ChartData cd2 = new ChartData("How many pies were eaten?","font-size: 14px; font-family: Verdana; text-align: center;");
		cd2.setBackgroundColour("#ffffff");
		XAxis xa = new XAxis();
		xa.setLabels("John","Frank","Mary","Andy","Mike","James");
		xa.setMax(6);
		cd2.setXAxis(xa);
		SketchBarChart sketch = new SketchBarChart("#00aa00","#009900",6);
		sketch.setTooltip("#val# pies");
		sketch.addValues(6,4,3);
		SketchBarChart.SketchBar skb = new SketchBarChart.SketchBar(8);
		skb.setColour("#6666ff");
		skb.setTooltip("Winner!<br>#val# pies");
		sketch.addBars(skb);
		sketch.addValues(4,2);		
		cd2.addElements(sketch);
		return cd2;
	}
}