package com.gwttest.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Random;
import com.rednels.ofcgwt.client.model.Chart;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.axis.Label.Rotation;
import com.rednels.ofcgwt.client.model.elements.AreaHollowChart;

public class TestAreaChart implements ITestCharts {
	Chart c;
	AreaHollowChart a;
	
	public TestAreaChart() {
		a = new AreaHollowChart(); 
		c = new Chart("Area Chart").addElements(a.setWidth(1));
		XAxis xaxis = new XAxis();
		xaxis.setSteps(2);
		xaxis.getLabels().setSteps(4);
		xaxis.getLabels().setRotation(Rotation.VERTICAL);
	
		YAxis yaxis = new YAxis();
		yaxis.setRange(-2, 2, 2);
		yaxis.setOffset(false);
	
		c.setYAxis(yaxis);
		c.setXAxis(xaxis);
	}
	
	public String getJSON() {
		double d = Random.nextDouble() * 2.0;
		List<Number> data = new ArrayList<Number>(30);
		for (float i = 0; i < 6.2; i += 0.2) {
			data.add(new Double(Math.sin(i) * d));
		}
		a.setValues(data);
		return c.toString();
	}

}
