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
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.ScatterChart;

/**
 * Example Test using OFCGWT
 */
public class Snippet implements EntryPoint {
	public void onModuleLoad() {

		AbsolutePanel main = new AbsolutePanel();
		DOM.setStyleAttribute(main.getElement(), "width", "100%");
		DOM.setStyleAttribute(main.getElement(), "height", "100%");
		DOM.setStyleAttribute(main.getElement(), "overflow", "auto");

		// Create a blue panel with z-index at 200000
		main.add(this.createSimplePanel(300, 250, 0, 0, "blue", 200000));

		// Create a blue panel with z-index at 100000
		main.add(this.createSimplePanel(300, 250, 100, 100, "red", 100000));

		RootPanel.get().add(main);
	}

	/**
	 * Creates a SimplePanel which contains a GraphWidget.
	 * 
	 * @param widthel
	 * @param height
	 * @param top
	 * @param left
	 * @param backgroundColor
	 * @param zIndex
	 * @return
	 */
	public SimplePanel createSimplePanel(int width, int height, int top, int left, String backgroundColor, int zIndex) {
		SimplePanel simplePanel = new SimplePanel();
		DOM.setStyleAttribute(simplePanel.getElement(), "position", "absolute");
		DOM.setStyleAttribute(simplePanel.getElement(), "width", width + "px");
		DOM.setStyleAttribute(simplePanel.getElement(), "height", height + "px");
		DOM.setStyleAttribute(simplePanel.getElement(), "top", top + "px");
		DOM.setStyleAttribute(simplePanel.getElement(), "left", left + "px");
		DOM.setStyleAttribute(simplePanel.getElement(), "backgroundColor", backgroundColor);
		DOM.setIntStyleAttribute(simplePanel.getElement(), "zIndex", zIndex);

		// Add a GraphWidget into this panel with a z-index at panel's z-index +
		// 1
		simplePanel.add(this.createChartWidget(zIndex + 1));

		return simplePanel;
	}

	/**
	 * Creates a ChartWidget with the specified z-index.
	 * 
	 * @param zIndex
	 * @return
	 */
	public ChartWidget createChartWidget(int zIndex) {

		/* Create a ChartWidget */
		ChartWidget chartWidget = new ChartWidget();
		ChartData chartData = new ChartData();
		XAxis x_axis = new XAxis();
		x_axis.setRange(0, 50, 10);
		chartData.setXAxis(x_axis);
		YAxis y_axis = new YAxis();
		y_axis.setRange(0, 5, 1);
		chartData.setYAxis(y_axis);
		// Add a fake scatter chart
		ScatterChart sc = new ScatterChart(ScatterChart.ScatterStyle.LINE);
		for (int i = 0; i < 50; i++) {
			// Generate a random value
			double randomValue = Random.nextDouble() * 5;
			// Add the generated value in the chart
			sc.addPoint(i, randomValue);
		}
		chartData.addElements(sc);

		chartWidget.setChartData(chartData);
		DOM.setStyleAttribute(chartWidget.getElement(), "position", "absolute");
		DOM.setStyleAttribute(chartWidget.getElement(), "width", "80%");
		DOM.setStyleAttribute(chartWidget.getElement(), "height", "80%");
		DOM.setStyleAttribute(chartWidget.getElement(), "top", "10%");
		DOM.setStyleAttribute(chartWidget.getElement(), "left", "10%");
		DOM.setIntStyleAttribute(chartWidget.getElement(), "zIndex", zIndex);

		return chartWidget;
	}
}
