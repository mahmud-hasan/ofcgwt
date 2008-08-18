package com.gwttest.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.*;
import com.rednels.ofcgwt.client.model.axis.*;
import com.rednels.ofcgwt.client.model.axis.Label.Rotation;
import com.rednels.ofcgwt.client.model.elements.*;
import com.rednels.ofcgwt.client.model.elements.LineChart.Style;

public class test implements EntryPoint {
	public void onModuleLoad() {
		VerticalPanel vp = new VerticalPanel();
		
		HorizontalPanel hp = new HorizontalPanel();		
		hp.setSpacing(5);
		hp.add(new com.google.gwt.user.client.ui.Label("Select a Chart : "));		
		final ListBox dropBox = new ListBox(false);
		dropBox.addItem("Pie");
		dropBox.addItem("Area");
		dropBox.addItem("Bar");
		dropBox.addItem("Glass Bar");
	    hp.add(dropBox);
	    
	    vp.add(hp);

		HorizontalPanel hp2 = new HorizontalPanel();		
		hp2.setSpacing(5);
		final ChartWidget chart = new ChartWidget();
		chart.setSize("300", "300");
		hp2.add(chart);

		final ChartWidget chart2 = new ChartWidget();
		chart2.setSize("300", "300");
		hp2.add(chart2);
		
		vp.add(hp2);
		
		RootPanel.get().add(vp);		

		Timer t = new Timer() {
	      public void run() {	    	    
				chart.setJsonData(getPieChart().toString());
	      }
	    };
	    t.schedule(500);
	    
	    dropBox.addChangeListener(new ChangeListener() {
	        public void onChange(Widget sender) {
	        	String json = chart.getJsonData();
	        	System.out.println(json);
	        	chart2.setJsonData(json);
	        	switch(dropBox.getSelectedIndex()) {
	        	case 0: // pie
	        		json = getPieChart().toString();
					break;
	        	case 1: // area
	        		json = getAreaChart().toString();
					break;
	        	case 2: // bar
	        		json = getBarChart().toString();
					break;
	        	case 3: // glass bar
	        		json = getGlassBarChart().toString();
					break;
	        	}
	        	chart.setJsonData(json);
	        }
	      });
	}
	
	private Chart getPieChart() {
		return new Chart("Pie Chart")
		  .addElements(new PieChart()
		    .setAnimate(true)
		    .setStartAngle(35)
		    .setBorder(2)
		    .addValues(2, 3, 4)
		    .addSlice(6.5f, "A label (6.5)")
		    .setColours("#d01f3c", "#345678","#356aa0", "#C79810")
		    .setTooltip("#val# of #total#<br>#percent# of 100%")
		    .setAlpha(0.6f));
	}
	
	private Chart getAreaChart() {
		List<Number> data = new ArrayList<Number>(30);
		for (float i = 0; i < 6.2; i += 0.2) {
		    data.add(new Double(Math.sin(i) * 1.9));
		}
		Chart c = new Chart("Area Chart")
		    .addElements(new AreaHollowChart()
		        .addValues(data)
		        .setWidth(1));
		XAxis xaxis = new XAxis();
		xaxis.setSteps(2);
		xaxis.getLabels().setSteps(4);
		xaxis.getLabels().setRotation(Rotation.VERTICAL);

		YAxis yaxis = new YAxis();
		yaxis.setRange(-2, 2, 2);
		yaxis.setOffset(false);

		c.setYAxis(yaxis);
		c.setXAxis(xaxis);
		return c;
	}
	
	private Chart getBarChart() {
		Chart c = new Chart("X Axis Labels Complex Example")
		  .addElements(new LineChart(Style.DOT)
		    .addValues(9, 8, 7, 6, 5, 4, 3, 2, 1))
		    .setXAxis(new XAxis()
		      .setTickHeight(5)
		      .addLabels("one", "two", "three", "four", "five")
		      .addLabels(
		        new Label("six")
		          .setColour("#0000FF")
		          .setSize(30)
		          .setRotation(Rotation.VERTICAL),
		        new Label("seven")
		          .setColour("#0000FF")
		          .setSize(30)
		          .setRotation(Rotation.VERTICAL),
		        new Label("eight")
		          .setColour("#8C773E")
		          .setSize(16)
		          .setRotation(Rotation.DIAGONAL)
		          .setVisible(true),
		        new Label("nine")
		          .setColour("#2683CF")
		          .setSize(16)
		          .setRotation(Rotation.HORIZONTAL)));

		c.getXAxis()
		  .setStroke(1)
		  .setColour("#428C3E")
		  .setGridColour("#86BF83")
		  .setSteps(1);
		     
		c.getXAxis().getLabels()
		  .setSteps(1)
		  .setRotation(Rotation.VERTICAL)
		  .setColour("#ff0000")
		  .setSize(16);
		return c;
	}

	private Chart getGlassBarChart() {
		Chart c = new Chart("Many data lines","font-size: 20px; color:#0000ff; font-family: Verdana; text-align: center;")
		  .setYLegend(new Text("Open Flash Chart","color: #736AFF; font-size: 12px;"))
		  .addElements(new BarChart(com.rednels.ofcgwt.client.model.elements.BarChart.Style.GLASS)
		  	.setColour("#9933CC")
		    .addValues(9,6,7,9,5)
		    .addBars(new BarChart.Bar(7,"#FF0000").setTooltip("RED<br>Mooo<br>#val#"))
		    .addValues(6,9,7)
		    .setTooltip("Tip for purple bars<br>val=#val#, top=#top#")
		  	.setAlpha(0.5f))		
		  	
		  	;
		
//		    .setXAxis(new XAxis()
//		      .setTickHeight(5)
//		      .addLabels("one", "two", "three", "four", "five")
//		      .addLabels(
//		        new Label("six")
//		          .setColour("#0000FF")
//		          .setSize(30)
//		          .setRotation(Rotation.VERTICAL),
//		        new Label("seven")
//		          .setColour("#0000FF")
//		          .setSize(30)
//		          .setRotation(Rotation.VERTICAL),
//		        new Label("eight")
//		          .setColour("#8C773E")
//		          .setSize(16)
//		          .setRotation(Rotation.DIAGONAL)
//		          .setVisible(true),
//		        new Label("nine")
//		          .setColour("#2683CF")
//		          .setSize(16)
//		          .setRotation(Rotation.HORIZONTAL)));
//
//		c.getXAxis()
//		  .setStroke(1)
//		  .setColour("#428C3E")
//		  .setGridColour("#86BF83")
//		  .setSteps(1);
//		     
//		c.getXAxis().getLabels()
//		  .setSteps(1)
//		  .setRotation(Rotation.VERTICAL)
//		  .setColour("#ff0000")
//		  .setSize(16);
		return c;
	}
	/*
	

      "text--":      "Page views",
      "font-size--": 10,

    {
      "type":      "bar_glass",
      "alpha":     0.5,
      "colour":    "#44FF44",
      "tip":       "Tip for green bars<br>val=#val#, top=#top#",
      "text--":      "Page views",
      "font-size--": 10,
      "values" :   [6,7,9,5,9,{"top":7,"colour":"#FF0000", "tip": "RED<br>W000t<br>#val#"},6,5,2]
    }
  ],

  "x_axis":{
    "stroke":       1,
    "tick_height":  10,
    "colour":      "#d000d0",
    "grid_colour": "#00ff00",
    "labels": {
      "labels": ["January","February","March","April","May","June","July","August","Spetember"]
    }
   },

  "y_axis":{
    "stroke":      4,
    "tick_length": 3,
    "colour":      "#d000d0",
    "grid_colour": "#00ff00",
    "offset":      0,
    "max":         20
  },

  "tooltip":{
    "text": "Global Tooltip<br>val=#val#, top=#top#"
  }
 
	  */
}
