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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.Text;
import com.rednels.ofcgwt.client.model.axis.Label;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.LineChart;
import com.rednels.ofcgwt.client.model.elements.BarChart.BarStyle;
import com.rednels.ofcgwt.client.model.elements.LineChart.LineStyle;

/**
 * Example Test using OFCGWT
 */
public class Snippet implements EntryPoint {

	public void onModuleLoad() {
		SimplePanel main = new SimplePanel();
		main.setHeight("400");
		main.setWidth("600");

		main.add(addChart());

		RootPanel.get().add(main);
	}

	private ChartWidget addChart() {
		String seriesColors[] = { "#ffcc33", "#6699ff", "#339966" };

		ChartWidget chartW = new ChartWidget();
		// chartW.setSize("100%", "200");
		ChartData chrtData = new ChartData("Demo", "font-size: 12px; font-family: Tahoma; text-align: center;");
		chrtData.setBackgroundColour("#ffffff");

		// prepare X axis
		XAxis xa = new XAxis();
		Label lblsX[] = new Label[3];
		for (int i = 0; i < 3; i++) {
			lblsX[i] = new Label("2008ww01");
			lblsX[i].setRotationAngle(45);
		}
		xa.addLabels(lblsX);
		chrtData.setXAxis(xa);

		// prepare LEFT Y axis
		double yMax = 155;
		int yStep = (int) (yMax + 2) / 10;
		YAxis ya = new YAxis();
		ya.setMax((int) yMax + 2);
		ya.setSteps(yStep);
		ya.setGridColour("#ffffff");
		chrtData.setYAxis(ya);

		/*
		 * Create three bars for the left Y axis
		 */
		BarChart bchart1 = new BarChart(BarStyle.NORMAL);
		bchart1.setTooltip("$#val#");
		bchart1.setText("bar1");
		bchart1.setColour(seriesColors[0]);
		bchart1.addValues(133, 123, 144);

		BarChart bchart2 = new BarChart(BarStyle.NORMAL);
		bchart2.setTooltip("$#val#");
		bchart2.setText("bar2");
		bchart2.setColour(seriesColors[1]);
		bchart2.addValues(122, 155, 123);

		BarChart bchart3 = new BarChart(BarStyle.NORMAL);
		bchart3.setTooltip("$#val#");
		bchart3.setText("bar2");
		bchart3.setColour(seriesColors[2]);
		bchart3.addValues(135, 153, 123);

		chrtData.addElements(bchart1);
		chrtData.addElements(bchart2);
		chrtData.addElements(bchart3);

		chrtData.setYLegend(new Text("Left Y", "{font-size: 10px; color:#000000}"));

		// Examining right Y axis
		yStep = (int) (500 + 2) / 10;
		ya = new YAxis();
		ya.setMax((int) 500 + 2);
		ya.setSteps(yStep);
		ya.setGridColour("#ffffff");
		chrtData.setYAxisRight(ya);

		/*
		 * Now the question is how to attach line charts to the right Yaxis?
		 */
		LineChart lc1 = new LineChart(LineStyle.NORMAL);
		lc1.setTooltip("$#val#");
		lc1.setText("line1");
		lc1.setColour("#000000");
		lc1.setRightAxis(true);
		lc1.addValues(233, 223, 500);

		chrtData.addElements(lc1);

		chartW.setJsonData(chrtData.toString());

		return chartW;
	}

}