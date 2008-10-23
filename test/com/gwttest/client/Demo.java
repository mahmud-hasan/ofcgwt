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
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
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
import com.rednels.ofcgwt.client.model.axis.RadarAxis;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.AreaChart;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.HorizontalBarChart;
import com.rednels.ofcgwt.client.model.elements.LineChart;
import com.rednels.ofcgwt.client.model.elements.PieChart;
import com.rednels.ofcgwt.client.model.elements.ScatterChart;
import com.rednels.ofcgwt.client.model.elements.SketchBarChart;
import com.rednels.ofcgwt.client.model.elements.StackedBarChart;
import com.rednels.ofcgwt.client.model.elements.AreaChart.AreaStyle;
import com.rednels.ofcgwt.client.model.elements.BarChart.BarStyle;
import com.rednels.ofcgwt.client.model.elements.LineChart.LineStyle;
import com.rednels.ofcgwt.client.model.elements.ScatterChart.ScatterStyle;
import com.rednels.ofcgwt.client.model.elements.StackedBarChart.Stack;


/**
 * Example Test using OFCGWT
 */
public class Demo implements EntryPoint {

	private Command updateCmd = null;

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
	         + "This demonstration site will showcase the many different types of charts that can be inserted into a GWT application."
	         );
	    vp.add(homeText);
	    vp.setCellHeight(homeText, "300");
	    vp.add(new HTML("Update Speed <i>(0-off, 4-max)</i>"));
	    final SliderBar slider = new SliderBar(0.0,4.0);
		slider.setStepSize(1.0);
		slider.setCurrentValue(1.0);
		slider.setNumTicks(4);
		slider.setNumLabels(4);
	    slider.setWidth("100%");
	    vp.add(slider);
	    
	    hp.add(vp);
	    hp.setCellWidth(vp, "300");
	    

		//add chart	    
	    DecoratorPanel dp = new DecoratorPanel();
		SimplePanel pieSp = new SimplePanel();
		final ChartWidget chart = new ChartWidget();	
		chart.setSize("400", "300");
		chart.setJsonData(getPieChartData().toString());
		pieSp.add(chart);
		dp.add(pieSp);
		hp.add(dp);
		
		VerticalPanel chartlist = new VerticalPanel();
		chartlist.setSpacing(5);
		Command cmd = new Command(){
			public void execute() {
				chart.setJsonData(getPieChartData().toString());					
			}
		};
		RadioButton rb = createRadioButton("PieChart",cmd);
		updateCmd = cmd;
		rb.setChecked(true);
		chartlist.add(rb);
		
		chartlist.add(createRadioButton("PieChart - Animate",new Command(){
			public void execute() {
				chart.setJsonData(getAniPieChartData().toString());					
			}
		}));
		
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

		chartlist.add(createRadioButton("ScatterChart - Point",new Command(){
			public void execute() {
				chart.setJsonData(getScatterPointChartData().toString());
			}
		}));

		chartlist.add(createRadioButton("ScatterChart - Line",new Command(){
			public void execute() {
				chart.setJsonData(getScatterLineChartData().toString());
			}
		}));

		chartlist.add(createRadioButton("RadarChart",new Command(){
			public void execute() {
				chart.setJsonData(getRadarChartData().toString());
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

		chartlist.add(createRadioButton("StackChart",new Command(){
			public void execute() {
				chart.setJsonData(getStackChartData().toString());
			}
		}));
					
		hp.add(chartlist);
	    hp.setCellWidth(chartlist, "300");
		
		RootPanel.get().add(hp);
		
		final Timer updater = new Timer() {
			public void run() {
				updateCmd.execute();
			}};		
		updater.scheduleRepeating(3000);
		
		slider.addChangeListener(new ChangeListener(){
			public void onChange(Widget sender) {
				switch ((int)(slider.getCurrentValue()))
				{
				case 0:
					updater.cancel();
					break;
					
				case 1:
					updater.scheduleRepeating(3000);
					break;

				case 2:
					updater.scheduleRepeating(1000);
					break;

				case 3:
					updater.scheduleRepeating(200);
					break;

				case 4:
					updater.scheduleRepeating(50);
					break;
				}
			}});
	}

	private RadioButton createRadioButton(String string, final Command command) {
		RadioButton rb = new RadioButton("chartlist", string);
		rb.addClickListener(new ClickListener(){
			public void onClick(Widget sender) {
				updateCmd = command;
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
		pie.addSlices(new PieChart.Slice(Random.nextInt(11)*1000,"AU"));
		pie.addSlices(new PieChart.Slice(Random.nextInt(88)*1000,"USA"));
		pie.addSlices(new PieChart.Slice(Random.nextInt(62)*1000,"UK"));
		pie.addSlices(new PieChart.Slice(Random.nextInt(14)*1000,"JP"));
		pie.addSlices(new PieChart.Slice(Random.nextInt(43)*1000,"EU"));
		cd.addElements(pie);
		return cd;
	}	

	private ChartData getAniPieChartData() {	
		ChartData cd = new ChartData("Results","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		PieChart pie = new PieChart();
		pie.setTooltip("#label# $#val#<br>#percent#");
		pie.setAnimate(true);
		pie.setGradientFill(false);
		pie.setColours("#ff0000","#00ff00","#0000ff","#ff9900","#ff00ff","#FFFF00","#6699FF","#339933");
		for (int t=0;t<Random.nextInt(10)+10;t++) {
			pie.addSlices(new PieChart.Slice(Random.nextDouble()*1.1 + .5,""+(t+1)));			
		}
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
		for (int t=0;t<12;t++) {
			bchart1.addValues(Random.nextInt(50)+50);
		}		
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
		for (int t=0;t<12;t++) {
			bchart2.addValues(Random.nextInt(50)+50);
		}	
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
		for (int t=0;t<12;t++) {
			bchart3.addValues(Random.nextInt(50)+50);
		}	
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

	private ChartData getScatterPointChartData() {
		ChartData cd = new ChartData("X Y Distribution","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		ScatterChart scat = new ScatterChart();
		scat.setDotSize(3);
		for (int n = 0; n < 50; n++) {
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

	private ChartData getScatterLineChartData() {
		ChartData cd = new ChartData("X Y Distribution","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		ScatterChart scat = new ScatterChart(ScatterStyle.LINE);
		scat.setDotSize(3);
		for (int n = 0; n < 25; n++) {
			int x = n*2 - 25;
			int y = Random.nextInt(30)-15;
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

	private ChartData getRadarChartData() {
		ChartData cd2 = new ChartData("Risk Areas","font-size: 12px; text-align: left;");
		cd2.setBackgroundColour("#ffffff");
		RadarAxis ra = new RadarAxis();
		ra.setMax(11);
		ra.setStroke(2);
		ra.setColour("#A1D4B5");
		ra.setGridColour("#C0DEBF");
		ra.setSpokeLabels("Financial","Brand","Legal","Market","Service");
		cd2.setRadarAxis(ra);
		AreaChart area2 = new AreaChart(AreaStyle.LINE);
		area2.setFillAlpha(0.3f);
		area2.setColour("#ff0000");
		area2.setFillColour("#ff0000");
		area2.setLoop(true);
		area2.addValues(Random.nextInt(8)+2,Random.nextInt(8)+2,Random.nextInt(8)+2,Random.nextInt(8)+2,Random.nextInt(8)+2);		
		cd2.addElements(area2);		
		return cd2;
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
		bchart1.addBars(new HorizontalBarChart.Bar(Random.nextInt(87)+100,"#ffff00"));
		bchart1.addBars(new HorizontalBarChart.Bar(Random.nextInt(44)+100,"#0000ff"));
		bchart1.addBars(new HorizontalBarChart.Bar(Random.nextInt(23)+100,"#00ff00"));
		bchart1.addBars(new HorizontalBarChart.Bar(Random.nextInt(33)+100,"#ff0000"));
		cd1.addElements(bchart1);
		cd1.setTooltip(new ToolTip(MouseStyle.FOLLOW));
		return cd1;
	}

	private ChartData getAreaHollowChartData() {
		ChartData cd1 = new ChartData("Volume Consumed","font-size: 14px; font-family: Verdana; text-align: center;");
		cd1.setBackgroundColour("#ffffff");
		AreaChart area1 = new AreaChart(AreaStyle.HOLLOW);
		area1.setFillAlpha(0.6f);
		area1.setDotSize(3);
		XAxis xa = new XAxis();
		int floor = Random.nextInt(3)+3;
		double grade = 1.0 + (Random.nextInt(19)+1)/10.0;
		int ln=0;
		System.out.println(grade);
		for( float i=0; i<6.2; i+=0.2 )
		{
			xa.addLabels(""+ln++);
			area1.addValues(Math.sin(i)* grade + floor);
		}		
		
		xa.getLabels().setSteps(3);
		cd1.setXAxis(xa);
		cd1.addElements(area1);
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
			area2.addValues(n*Random.nextDouble());		
		cd2.addElements(area2);
		AreaChart area3 = new AreaChart(AreaStyle.LINE);
		area3.setFillAlpha(0.3f);
		area3.setColour("#00aa00");
		area3.setFillColour("#00aa00");
		int floor = Random.nextInt(3);
		double grade = (Random.nextInt(4)+1)/10.0;
		for (int n=0;n<12;n++)
			area3.addValues(n*grade+floor);		
		cd2.addElements(area3);
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
		sketch.addValues(Random.nextInt(6)+1,Random.nextInt(5)+1,Random.nextInt(3)+1);
		SketchBarChart.SketchBar skb = new SketchBarChart.SketchBar(Random.nextInt(5)+5);
		skb.setColour("#6666ff");
		skb.setTooltip("Winner!<br>#val# pies");
		sketch.addBars(skb);
		sketch.addValues(Random.nextInt(5)+1,Random.nextInt(5)+1);		
		cd2.addElements(sketch);
		return cd2;
	}

	private ChartData getStackChartData() {	
		ChartData cd = new ChartData("Investments in ($M)","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		
		StackedBarChart stack = new StackedBarChart();
		stack.setTooltip("#total#<br>(bar total)");
		stack.addStack(new Stack(Random.nextDouble()*2.5,Random.nextDouble()*5));
		stack.addStack(new Stack(new StackedBarChart.StackValue(Random.nextDouble()*7,"#ffdd00")));
		stack.addStack(new Stack(new StackedBarChart.StackValue(Random.nextDouble()*5, "#ff0000")));
		Stack s = new Stack(Random.nextDouble()*2, Random.nextDouble()*2, Random.nextDouble()*2);
		s.addStackValues(new StackedBarChart.StackValue(Random.nextDouble()*2, "#ff00ff"));
		stack.addStack(s);
		
		XAxis xa = new XAxis();
		xa.setLabels("John","Frank","Mary","Andy");
		xa.setMax(4);
		cd.setXAxis(xa);
		
		YAxis ya = new YAxis();
		ya.setRange( 0, 14, 7 );
		cd.setYAxis(ya);
				
		cd.addElements(stack);
		return cd;
	}
}