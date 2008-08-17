package com.gwttest.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.*;
import com.rednels.ofcgwt.client.model.axis.*;
import com.rednels.ofcgwt.client.model.axis.Label.Rotation;
import com.rednels.ofcgwt.client.model.elements.*;
import com.rednels.ofcgwt.client.model.elements.LineChart.Style;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class test implements EntryPoint {
	public void onModuleLoad() {
		
		final VerticalPanel vp = new VerticalPanel();
		
		final ChartWidget c1 = new ChartWidget();		
		c1.setSize("400", "300");
		vp.add(c1);
//		final ChartWidget c2 = new ChartWidget();		
//		vp.add(c2);
		
		
		RootPanel.get().add(vp);		
		 // Create a new timer that calls Window.alert().
	    Timer t = new Timer() {
	      public void run() {

				c1.loadJSON(getPieChart().toString());				
//				c2.loadJSON(getAreaChart().toString());
	      }
	    };

	    // Schedule the timer to run once in 5 seconds.
	    t.schedule(5000);


	}
	
	private Chart getPieChart() {
		return new Chart("Pie Chart")
		  .addElements(new PieChart()
		    .setAnimate(true)
		    .setStartAngle(35)
		    .setBorder(2)
		    .addValues(2, 3, 4)
		    .addSlice(6.5f, "label (6.5)")
		    .setColours("#d01f3c", "#356aa0", "#C79810")
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
		return new Chart("X Axis Labels Complex Example")
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

//		chart.getXAxis()
//		  .setStroke(1)
//		  .setColour("#428C3E")
//		  .setGridColour("#86BF83")
//		  .setSteps(1);
//		     
//		chart.getXAxis().getLabels()
//		  .setSteps(1)
//		  .setRotation(Rotation.VERTICAL)
//		  .setColour("#ff0000")
//		  .setSize(16);
	}
}
