/*
Copyright (C) 2009 Grant Slender

This file is part of OFCGWT.
http://code.google.com/p/ofcgwt/

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
package com.gwttest.client.test;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.elements.PieChart;

/**
 * Example Test using OFCGWT
 */
public class MemoryLeakTest implements EntryPoint {

	public class TestPieChart {
		ChartData c;
		PieChart p;

		public TestPieChart() {
			p = new PieChart();
			p.setGradientFill(true);
			p.setStartAngle(35);
			p.setBorder(2);
			p.setColours("#d01f3c", "#345678", "#356aa0", "#C79810");
			p.setTooltip("#label#<br>$#val# (#percent#)");
			p.setAlpha(0.6f);
			p.setNoLabels(true);

			c = new ChartData("Pie Chart");
			c.addElements(p);
			c.setBackgroundColour("#eeffee");
		}

		public ChartData update() {
			p.getValues().clear();
			int n = Random.nextInt(6) + 2;
			for (int i = 0; i < n; i++) {
				p.addSlice(Random.nextInt(12) * 1000, "Slice #" + (i + 1));
			}
			return c;
		}
	}

	public void onModuleLoad() {
		SimplePanel pieSp = new SimplePanel();
		pieSp.setSize("300", "300");
		final TestPieChart tpc = new TestPieChart();
		final ChartWidget chart = new ChartWidget();
		chart.setChartData(tpc.update());
		pieSp.add(chart);
		RootPanel.get().add(pieSp);

		final Timer t = new Timer() {
			public void run() {
				chart.setChartData(tpc.update());
			}
		};
		t.scheduleRepeating(100);

		Button b = new Button("stop");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				t.cancel();
			}
		});
		RootPanel.get().add(b);
	}
}