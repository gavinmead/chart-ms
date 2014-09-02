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

package io.gmi.chart.builder;

import io.gmi.chart.dto.ChartRequestDto;
import io.gmi.chart.dto.LineChartRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Component(LineChartRequestDto.chartType)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class LineChartBuilder extends ChartBuilder {

  private static final Logger log = LoggerFactory.getLogger(LineChartBuilder.class);

  @Override
  public CompletableFuture<ChartBuilderResult> buildChart(ChartRequestDto chartRequestDto) {
    return null;
  }

  @Override
  protected Collection<Object> getChartData(ChartRequestDto chartRequestDto) {
    return null;
  }

  @Override
  protected String getChartScriptTemplate(ChartRequestDto chartRequestDto) {
    return null;
  }
}
