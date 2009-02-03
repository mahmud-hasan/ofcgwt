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
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.axis.Label;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.PieChart;
import com.rednels.ofcgwt.client.model.elements.BarChart.BarStyle;

/**
 * Example Test using OFCGWT
 */
public class Snippet implements EntryPoint, ClickListener {

	// Instantiate the dialog box and show it.
	MyDialog d = new MyDialog();

	public class TestPieChart {
		ChartData c;
		PieChart p;

		public TestPieChart() {
			p = new PieChart();
			p.setGradientFill(true);
			p.setAnimate(false);
			p.setAlphaHighlight(true);
			p.setStartAngle(35);
			p.setBorder(2);
			p.setColours("#d01f3c", "#345678", "#356aa0", "#C79810");
			p.setTooltip("#label#<br>$#val# (#percent#)");
			p.setAlpha(0.6f);
			p.setNoLabels(true);

			c = new ChartData("Pie Chart");
			c.addElements(p);
			c.setBackgroundColour("-1");
		}

		public String getJSON() {
			p.getValues().clear();
			int n = Random.nextInt(6) + 2;
			for (int i = 0; i < n; i++) {
				p.addSlice(Random.nextInt(12) * 1000, "Slice #" + (i + 1));
			}
			return c.toString();
		}
	}

	class TestBarChart {
		ChartData c;
		BarChart b;

		public TestBarChart() {
			c = new ChartData("Sales by Month 2006", "font-size: 14px; font-family: Verdana; color: #ffffff; text-align: center;");
			c.setBackgroundColour("-1");

			XAxis xa = new XAxis();
			xa.setColour("#ffffff");
			xa.setGridColour("#888888");
			Label lab = new Label("Q1");
			lab.setColour("#ffffff");
			xa.addLabels(lab);
			lab = new Label("Q2");
			lab.setColour("#ffffff");
			xa.addLabels(lab);
			lab = new Label("Q3");
			lab.setColour("#ffffff");
			xa.addLabels(lab);
			lab = new Label("Q4");
			lab.setColour("#ffffff");
			xa.addLabels(lab);
			c.setXAxis(xa);

			YAxis ya = new YAxis();
			ya.setColour("#ffffff");
			ya.setGridColour("#888888");
			ya.setRange(0, 100);
			ya.setSteps(5);
			c.setYAxisLabelStyle(12, "#ffffff");
			c.setYAxis(ya);

			b = new BarChart(BarStyle.NORMAL);
			b.setTooltip("$#val#");
			c.addElements(b);
		}

		public String getJSON() {
			b.getValues().clear();
			for (int t = 0; t < 4; t++) {
				b.addValues(Random.nextInt(50) + 50);
			}
			return c.toString();
		}
	}

	class MyDialog extends DialogBox {

		public MyDialog() {
			setText("My First Dialog");

			VerticalPanel vp = new VerticalPanel();

			// DOM.setStyleAttribute(vp.getElement(), "border",
			// "6px solid black");
			// DOM.setStyleAttribute(vp.getElement(),
			// "backgroundColor","#000000");

			vp.setStylePrimaryName("chartPanel");

			Button ok = new Button("OK");
			ok.addClickListener(new ClickListener() {
				public void onClick(Widget sender) {
					MyDialog.this.hide();
				}
			});
			vp.add(ok);
			vp.setCellHorizontalAlignment(ok, HasHorizontalAlignment.ALIGN_CENTER);

			// final TestPieChart tpc = new TestPieChart();
			final TestBarChart tpc = new TestBarChart();
			final ChartWidget chart = new ChartWidget();
			chart.setJsonData(tpc.getJSON());
			chart.setSize("300px", "300px");

			vp.add(chart);

			setWidget(vp);
		}
	}

	public void onModuleLoad() {
		Button b = new Button("Click me");
		b.addClickListener(this);

		RootPanel.get().add(b);
	}

	public void onClick(Widget sender) {
		d.show();
	}
}
