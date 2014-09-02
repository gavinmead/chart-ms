/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.gmi.chart.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.gmi.chart.domain.ChartOptions;
import io.gmi.chart.domain.Image;
import io.gmi.chart.domain.LineChartOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(
        {@JsonSubTypes.Type(value= LineChartOptions.class, name = "line")}
)
public abstract class ChartRequestDto {
  private Boolean useBootstrap = true;
  public List<Image> images = new ArrayList<>();
  public Map<String, String> keys;
  public String template;
  public String chartId;
  public Boolean showLegend = true;
  public ChartOptions chartOptions;

  public Boolean getUseBootstrap() {
    return useBootstrap;
  }

  public void setUseBootstrap(Boolean useBootstrap) {
    this.useBootstrap = useBootstrap;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }

  public Map<String, String> getKeys() {
    return keys;
  }

  public void setKeys(Map<String, String> keys) {
    this.keys = keys;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  public abstract String getType();

  public String getChartId() {
    return chartId;
  }

  public void setChartId(String chartId) {
    this.chartId = chartId;
  }

  public Boolean getShowLegend() {
    return showLegend;
  }

  public void setShowLegend(Boolean showLegend) {
    this.showLegend = showLegend;
  }

  public ChartOptions getChartOptions() {
    return chartOptions;
  }

  public void setChartOptions(ChartOptions chartOptions) {
    this.chartOptions = chartOptions;
  }
}
