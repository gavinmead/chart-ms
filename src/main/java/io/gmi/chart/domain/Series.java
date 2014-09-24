package io.gmi.chart.domain;

/**
 * Created by gmead on 9/24/14.
 */
public abstract class Series {
  private String key;
  private String color;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}
