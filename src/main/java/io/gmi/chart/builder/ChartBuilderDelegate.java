package io.gmi.chart.builder;

import io.gmi.chart.requests.ChartRequest;

abstract class ChartBuilderDelegate {
  abstract void handle(ChartBuilderContext context, ChartRequest request);
}
