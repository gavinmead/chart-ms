package io.gmi.chartms.service;

/**
 * Created by gmead on 10/14/14.
 */
public interface ChartCreationService {

  /**
   * Returns a file path containing captured image.
   * @param chartCreationContext
   * @return
   * @throws ChartCreationException
   */
  public String createChartImage(ChartCreationContext chartCreationContext) throws ChartCreationException;

}
