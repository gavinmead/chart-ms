package io.gmi.chart.domain;

import java.util.Collection;

/**
 * Created by gmead on 9/24/14.
 */
public class LineChartSeries extends Series {
  Collection<LineChartDataPoint> values;

  public Collection<LineChartDataPoint> getValues() {
    return values;
  }

  public void setValues(Collection<LineChartDataPoint> values) {
    this.values = values;
  }
}
