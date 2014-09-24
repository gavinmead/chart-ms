package io.gmi.chart.builder;


import io.gmi.chart.requests.ChartRequest;

/**
 * Created by gmead on 9/24/14.
 */
public interface ChartBuilderFactory {
  ChartBuilder getChartBuilder(ChartRequest chartRequest);
}
