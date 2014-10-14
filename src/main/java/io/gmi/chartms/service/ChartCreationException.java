package io.gmi.chartms.service;

/**
 * Created by gmead on 10/14/14.
 */
public class ChartCreationException extends RuntimeException {
  private ChartCreationContext chartCreationContext;

  public ChartCreationException(ChartCreationContext chartCreationContext) {
    this.chartCreationContext = chartCreationContext;
  }

  public ChartCreationException(String message, ChartCreationContext chartCreationContext) {
    super(message);
    this.chartCreationContext = chartCreationContext;
  }

  public ChartCreationException(String message, Throwable cause, ChartCreationContext chartCreationContext) {
    super(message, cause);
    this.chartCreationContext = chartCreationContext;
  }

  public ChartCreationException(Throwable cause, ChartCreationContext chartCreationContext) {
    super(cause);
    this.chartCreationContext = chartCreationContext;
  }

  public ChartCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ChartCreationContext chartCreationContext) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.chartCreationContext = chartCreationContext;
  }

  public ChartCreationContext getChartCreationContext() {
    return chartCreationContext;
  }
}
