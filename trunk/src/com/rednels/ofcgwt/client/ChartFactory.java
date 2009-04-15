package com.rednels.ofcgwt.client;

import java.util.HashMap;
import java.util.Map;

import com.rednels.ofcgwt.client.event.ChartReadyEvent;

public class ChartFactory {

	public static final String BLANK_CHART_JSON_DATA = "{\"title\":{\"text\":\"\"},\"elements\":[]}";

	private static ChartFactory instance = new ChartFactory();

	public static ChartFactory get() {
		return instance;
	}

	private Map<String, ChartWidget> charts = new HashMap<String, ChartWidget>();

	public ChartFactory() {
		init();
	}

	public void register(ChartWidget chart) {
		charts.put(chart.getSwfId(), chart);
	}

	public void unregister(ChartWidget chart) {
		charts.remove(chart);
	}

	@SuppressWarnings("unused")
	private String handleGetData(String id) {
		ChartWidget chart = charts.get(id);
		if (chart != null) {
			return chart.getJsonData();
		}
		return BLANK_CHART_JSON_DATA;
	}

	@SuppressWarnings("unused")
	private void handleOnClick(String id, String evt) {
		ChartWidget chart = charts.get(id);
		if (chart != null) {
			chart.doOnChartClick(evt);
		}
	}

	@SuppressWarnings("unused")
	private void handleOnReady(String id) {
		ChartWidget chart = charts.get(id);
		if (chart != null) {
			chart.fireEvent(new ChartReadyEvent());
		}
	}

	private native void init() /*-{
	      var x = this;
	      $wnd.ofc_ready = function (id) {
	         x.@com.rednels.ofcgwt.client.ChartFactory::handleOnReady(Ljava/lang/String;)(id);
	      };
	      $wnd.open_flash_chart_data = function (id) {
	          return x.@com.rednels.ofcgwt.client.ChartFactory::handleGetData(Ljava/lang/String;)(id);
	      };
	      $wnd.ofc_onclick = function (id, evt) {
	          return x.@com.rednels.ofcgwt.client.ChartFactory::handleOnClick(Ljava/lang/String;Ljava/lang/String;)(id, evt);
	      };
	   }-*/;
}