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
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.BarChart;

/**
 * Example Test using OFCGWT
 */
public class FlexTableTest implements EntryPoint {

	public ChartWidget createChartWidget(int w, int h) {
		ChartWidget chartWidget = new ChartWidget();
		ChartData chartData = new ChartData();
		YAxis y_axis = new YAxis();
		y_axis.setRange(0, 5, 1);
		chartData.setYAxis(y_axis);
		BarChart sc = new BarChart(BarChart.BarStyle.GLASS);
		for (int i = 0; i < 5; i++) {
			sc.addValues(Random.nextDouble() * 5);
		}
		chartData.addElements(sc);
		chartWidget.setChartData(chartData);
		chartWidget.setSize(w+"", h+"");
		return chartWidget;
	}	

	public void onModuleLoad() {		
		FlexTable ft = new FlexTable();
		ft.setBorderWidth(1);		
		ft.setWidget(0, 0, new Label("row 0, col 0"));	
		ft.setWidget(1, 1, new Label("row 1, col 1"));		
		ft.setWidget(2, 2, createChartWidget(100,100) );
		RootPanel.get().add(ft);
	}
}
