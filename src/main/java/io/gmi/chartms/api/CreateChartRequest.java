package io.gmi.chartms.api;

/**
 * Created by gmead on 10/14/14.
 */
public class CreateChartRequest {

  private String html;
  private Boolean useBootstrap = false;
  private String clippingDiv;
  private String chart;
  private String chartContainerId;

  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }

  public Boolean getUseBootstrap() {
    return useBootstrap;
  }

  public void setUseBootstrap(Boolean useBootstrap) {
    this.useBootstrap = useBootstrap;
  }

  public String getClippingDiv() {
    return clippingDiv;
  }

  public void setClippingDiv(String clippingDiv) {
    this.clippingDiv = clippingDiv;
  }

  public String getChart() {
    return chart;
  }

  public void setChart(String chart) {
    this.chart = chart;
  }

  public String getChartContainerId() {
    return chartContainerId;
  }

  public void setChartContainerId(String chartContainerId) {
    this.chartContainerId = chartContainerId;
  }

  @Override
  public String toString() {
    return "CreateChartRequest{" +
        "html='" + html + '\'' +
        ", useBootstrap=" + useBootstrap +
        ", clippingDiv='" + clippingDiv + '\'' +
        ", chart='" + chart + '\'' +
        ", chartContainerId='" + chartContainerId + '\'' +
        '}';
  }
}
