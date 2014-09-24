package io.gmi.chart.builder;

import io.gmi.chart.dto.ChartRequestDto;

/**
 * Created by gmead on 9/24/14.
 */
public interface ChartBuilderFactory {
  ChartBuilder getChartBuilder(ChartRequestDto chartRequestDto);
}
