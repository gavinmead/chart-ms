package io.gmi.chart.builder;


import io.gmi.chart.dto.ChartRequestDto;

abstract class ChartBuilderDelegate {
  abstract void handle(ChartBuilderContext context, ChartRequestDto requestDto);
}
