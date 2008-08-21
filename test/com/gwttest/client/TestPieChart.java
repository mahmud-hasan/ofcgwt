package com.gwttest.client;

import com.google.gwt.user.client.Random;
import com.rednels.ofcgwt.client.model.Chart;
import com.rednels.ofcgwt.client.model.axis.Label;
import com.rednels.ofcgwt.client.model.elements.PieChart;

public class TestPieChart implements ITestCharts {
	Chart c;
	PieChart p;
	
	public TestPieChart() {
		
		p = new PieChart().setAnimate(false).setStartAngle(35).setBorder(2);
		p.setColours("#d01f3c", "#345678", "#356aa0", "#C79810")
			.setTooltip("#val# of #total#<br>#percent# of 100%")
			.setAlpha(0.6f);		

		c = new Chart("Pie Chart").addElements(p);
	}
	
	public String getJSON() {
		p.getValues().clear();
		int n= Random.nextInt(6) + 2;
		for (int i = 0 ; i < n; i++) {
			p.addSlice(Random.nextInt(12)*1000, "Slice #"+(i+1));
		}
		return c.toString();
	}

}
