package com.gwttest.client;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Random;
import com.rednels.ofcgwt.client.model.Chart;
import com.rednels.ofcgwt.client.model.elements.SketchBarChart;

public class TestBarChart implements ITestCharts {

	SketchBarChart b;
	Chart c;
	
	public TestBarChart() {
		b = new SketchBarChart("#81AC00", "#567300", 5);
		c = new Chart(DateTimeFormat.getMediumDateFormat().format(new Date()),"color: #567300; font-size: 14px");
		c.addElements(b);
	}

	public String getJSON() {
		b.getValues().clear();
		int n= Random.nextInt(4) + 3;
		for (int i = 0 ; i < n; i++) {
			b.addValues(Random.nextInt(10));
		}
		return c.toString();
	}

}