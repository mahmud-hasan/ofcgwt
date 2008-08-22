package com.gwttest.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.IChartListener;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Test implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(5);
		hp.add(new com.google.gwt.user.client.ui.Label("Select a Chart : "));
		final ListBox chartDropBox = new ListBox(false);
		chartDropBox.addItem("Pie");
		chartDropBox.addItem("Area");
		chartDropBox.addItem("Bar");
		chartDropBox.addItem("Glass Bar");
		chartDropBox.setSelectedIndex(3);
		hp.add(chartDropBox);
		
		hp.add(new com.google.gwt.user.client.ui.Label("Data update speed : "));
		final ListBox speedDropBox = new ListBox(false);
		speedDropBox.addItem("Crazy");
		speedDropBox.addItem("Fast");
		speedDropBox.addItem("Normal");
		speedDropBox.addItem("Slow");
		speedDropBox.setSelectedIndex(2);
		hp.add(speedDropBox);

		final List<ITestCharts> list = getCharts();
		final ChartWidget chart = new ChartWidget();
		chart.setSize("70%", "70%");
		chart.addChartListeners(new IChartListener(){
			public void handleChartReadyEvent() {
				// chart is ready, so lets load it up !!
				String j = list.get(chartDropBox.getSelectedIndex()).getJSON();
				chart.setJsonData(j);				
			}});

		RootPanel.get().add(hp);
		RootPanel.get().add(chart);
		
		
		final Timer t = new Timer() {
			public void run() {
				// only update if its changed !!
				String j = list.get(chartDropBox.getSelectedIndex()).getJSON();
				if (!chart.getJsonData().equals(j)) chart.setJsonData(j);
			}
		};
		t.scheduleRepeating(500+speedDropBox.getSelectedIndex()*2000);

		chartDropBox.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				chart.setJsonData(list.get(chartDropBox.getSelectedIndex()).getJSON());
			}
		});
		
		speedDropBox.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				t.scheduleRepeating(1000+speedDropBox.getSelectedIndex()*2000);
			}
		});
	}	
	
	private List<ITestCharts> getCharts() {
		ArrayList<ITestCharts> charts = new ArrayList<ITestCharts>();
		charts.add(new TestPieChart());
		charts.add(new TestAreaChart());
		charts.add(new TestBarChart());
		charts.add(new TestGlassBarChart());
		return charts;
	}
}