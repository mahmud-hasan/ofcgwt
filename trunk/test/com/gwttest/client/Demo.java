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
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
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

	String[] panels = {"Home","Pie","Bar","Line","Scatter","Horizontal Bar","Area","Sketch","Updatable"};

	public void onModuleLoad() {
		SimplePanel main = new SimplePanel();
		main.setHeight("100%");
		main.setWidth("100%");
		
		DecoratedTabPanel tabPanel = new DecoratedTabPanel();
	    tabPanel.setAnimationEnabled(true);
	    
	    //add home page
	    HTML homeText = new HTML("<h2>Welcome to OFCGWT</h2>"
	         + "<i>....the OpenFlashChart GWT Library</i></br></br>"
	         + "This demonstration site will showcase the many different types of</br>charts that can be inserted into a GWT application."
	         );
	    tabPanel.add(homeText, panels[0]);

		//add pie chart
		SimplePanel pieSp = new SimplePanel();
		pieSp.add(addPieChart());
		tabPanel.add(pieSp, panels[1]);

		//add bar charts
		SimplePanel barSp = new SimplePanel();
		barSp.add(addBarCharts());
		tabPanel.add(barSp, panels[2]);

		//add line chart
		SimplePanel lineSp = new SimplePanel();
		lineSp.add(addLineChart());
		tabPanel.add(lineSp, panels[3]);

		//add scatter chart
		SimplePanel scatterSp = new SimplePanel();
		scatterSp.add(addScatterChart());
		tabPanel.add(scatterSp, panels[4]);

		//add horiz bar chart
		SimplePanel horizSp = new SimplePanel();
		horizSp.add(addHorizBarChart());
		tabPanel.add(horizSp, panels[5]);		

		//add area chart
		SimplePanel areaSp = new SimplePanel();
		areaSp.add(addAreaChart());
		tabPanel.add(areaSp, panels[6]);	

		//add sketch chart
		SimplePanel sketchSp = new SimplePanel();
		sketchSp.add(addSketchChart());
		tabPanel.add(sketchSp, panels[7]);			

		//add updatable chart
		SimplePanel updateSp = new SimplePanel();
		updateSp.add(addUpdateChart());
		tabPanel.add(updateSp, panels[8]);			
		
	    tabPanel.selectTab(0);
		RootPanel.get().add(tabPanel);
	}

	private ChartWidget addPieChart() {
		ChartWidget chart = new ChartWidget();		
		ChartData cd = new ChartData("Sales by Region","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		PieChart pie = new PieChart();
		pie.setAlpha(0.3f);
		pie.setNoLabels(true);
		pie.setTooltip("#label# $#val#<br>#percent#");
		pie.setAnimate(true);
		pie.setGradientFill(true);
		pie.setColours("#ff0000","#00ff00","#0000ff","#ff9900","#ff00ff");
		pie.addSlices(new PieChart.Slice(11000,"AU"));
		pie.addSlices(new PieChart.Slice(88000,"USA"));
		pie.addSlices(new PieChart.Slice(62000,"UK"));
		pie.addSlices(new PieChart.Slice(14000,"JP"));
		pie.addSlices(new PieChart.Slice(43000,"EU"));
		cd.addElements(pie);
		chart.setSize("300", "300");
		chart.setJsonData(cd.toString());
		return chart;
	}	

	private Widget addBarCharts() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.setBorderWidth(1);
				
		FlowPanel fp1 = new FlowPanel();
		ChartWidget chart1 = new ChartWidget();		
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
		chart1.setSize("350", "250");
		chart1.setJsonData(cd1.toString());		
		HTML label1 = new HTML("<u>Normal Bar Chart</u>");
		label1.setWidth("100%");
		label1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		fp1.add(label1);
		fp1.add(chart1);

		hp.add(fp1);
		
		FlowPanel fp2 = new FlowPanel();
		ChartWidget chart2 = new ChartWidget();		
		ChartData cd2 = new ChartData("Sales by Month 2007","font-size: 14px; font-family: Verdana; text-align: center;");
		cd2.setBackgroundColour("#ffffff");
		xa = new XAxis();
		xa.setLabels("J","F","M","A","M","J","J","A","S","O","N","D");
		xa.setMax(12);
		cd2.setXAxis(xa);
		ya = new YAxis();
		ya.setSteps(16);
		ya.setMax(160);
		cd2.setYAxis(ya);
		BarChart bchart2 = new BarChart(BarStyle.GLASS);
		bchart2.setColour("#00aa00");
		bchart2.setTooltip("$#val#");
		bchart2.addValues(123,133,134,112,135,143,151,133,103,102,131,120);		
		cd2.addElements(bchart2);
		chart2.setSize("350", "250");
		chart2.setJsonData(cd2.toString());		
		HTML label2 = new HTML("<u>Glass Bar Chart</u>");
		label2.setWidth("100%");
		label2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		fp2.add(label2);
		fp2.add(chart2);		
		
		hp.add(fp2);
		
		FlowPanel fp3 = new FlowPanel();
		ChartWidget chart3 = new ChartWidget();		
		ChartData cd3 = new ChartData("Sales by Month 2008","font-size: 14px; font-family: Verdana; text-align: center;");
		cd3.setBackgroundColour("#ffffff");
		xa = new XAxis();
		xa.setLabels("J","F","M","A","M","J","J","A","S","O","N","D");
		xa.setZDepth3D(5);
		xa.setMax(12);
		xa.setTickHeight(4);
		xa.setOffset(true);
		xa.setColour("#909090");
		cd3.setXAxis(xa);
		ya = new YAxis();
		ya.setSteps(16);
		ya.setMax(160);
		cd3.setYAxis(ya);
		BarChart bchart3 = new BarChart(BarStyle.THREED);
		bchart3.setColour("#ff8800");
		bchart3.setTooltip("$#val#");
		bchart3.addValues(103,123,133,138,126,117,121,143,140,152,121,105);		
		cd3.addElements(bchart3);
		chart3.setSize("350", "250");
		chart3.setJsonData(cd3.toString());		
		HTML label3 = new HTML("<u>3D Bar Chart</u>");
		label3.setWidth("100%");
		label3.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		fp3.add(label3);
		fp3.add(chart3);		
		
		hp.add(fp3);
		
		return hp;
	}

	private Widget addLineChart() {
		ChartWidget chart = new ChartWidget();		
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
		chart.setSize("600", "300");
		chart.setJsonData(cd.toString());
		return chart;
	}	

	private Widget addScatterChart() {
		ChartWidget chart = new ChartWidget();		
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
		chart.setSize("600", "600");
		chart.setJsonData(cd.toString());
		return chart;
	}

	private Widget addHorizBarChart() {
		ChartWidget chart1 = new ChartWidget();		
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
		chart1.setSize("400", "250");
		chart1.setJsonData(cd1.toString());
		return chart1;
	}

	private Widget addAreaChart() {

		HorizontalPanel hp = new HorizontalPanel();
		hp.setBorderWidth(1);
				
		FlowPanel fp1 = new FlowPanel();
		ChartWidget chart1 = new ChartWidget();		
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
		chart1.setSize("400", "300");
		chart1.setJsonData(cd1.toString());	
		HTML label1 = new HTML("<u>Hollow Area Chart</u>");
		label1.setWidth("100%");
		label1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		fp1.add(label1);
		fp1.add(chart1);
		hp.add(fp1);
		
		FlowPanel fp2 = new FlowPanel();
		ChartWidget chart2 = new ChartWidget();		
		ChartData cd2 = new ChartData("Growth per Region","font-size: 14px; font-family: Verdana; text-align: center;");
		cd2.setBackgroundColour("#ffffff");
		xa = new XAxis();
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
		chart2.setSize("400", "300");
		chart2.setJsonData(cd2.toString());		
		HTML label2 = new HTML("<u>Line Area Chart</u>");
		label2.setWidth("100%");
		label2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		fp2.add(label2);
		fp2.add(chart2);		
		hp.add(fp2);	
		
		return hp;		
	}

	private Widget addSketchChart() {
		ChartWidget chart2 = new ChartWidget();		
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
		chart2.setSize("350", "350");
		chart2.setJsonData(cd2.toString());		
		return chart2;
	}


	private Widget addUpdateChart() {

		FlowPanel fp1 = new FlowPanel();
		final ChartWidget chart1 = new ChartWidget();		
		final ChartData cd1 = new ChartData("Sales by Month 2006","font-size: 14px; font-family: Verdana; text-align: center;");
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
		bchart1.setColour("#dd3333");
		bchart1.setTooltip("$#val#");
		bchart1.addValues(133,123,144,122,155,123,135,153,123,122,111,100);		
		cd1.addElements(bchart1);
		final BarChart bchart2 = new BarChart(BarStyle.NORMAL);
		bchart2.setColour("#3333dd");		
		bchart2.setTooltip("$#val#");
		bchart2.addValues(calcValues(50));		
		cd1.addElements(bchart2);
		chart1.setSize("550", "250");
		chart1.setJsonData(cd1.toString());		
		HTML label1 = new HTML("<u>Charts are all dynamically updatable on the client-side...</u>");
		label1.setWidth("100%");
		label1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		fp1.add(label1);
		fp1.add(chart1);
		
		
		final SliderBar slider = new SliderBar(0.0, 100.0);
		slider.setStepSize(5.0);
		slider.setCurrentValue(50.0);
		slider.setNumTicks(10);
		slider.setNumLabels(5);
		slider.setWidth("500px");

		final Timer updater = new Timer() {
			public void run() {
				bchart2.setValues(calcValues(slider.getCurrentValue()));		
				chart1.setJsonData(cd1.toString());	
			}};		
		slider.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender) {
				updater.schedule(300);
			}});
		fp1.add(slider);
		
		return fp1;
	}
	
	private List<Number> calcValues(double v) {
		Number[] vals = new Number[]{133,123,144,122,155,123,135,153,123,122,111,100};
		for (int n = 0; n<vals.length;n++) {
			vals[n] = vals[n].doubleValue()*(v/100);
		}
		ArrayList<Number> nl = new ArrayList<Number>(Arrays.asList(vals));
		return nl;
	}
}