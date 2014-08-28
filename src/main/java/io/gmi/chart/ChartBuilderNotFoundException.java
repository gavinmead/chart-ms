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

package io.gmi.chart;

import io.gmi.chart.dto.ChartRequestDto;


public class ChartBuilderNotFoundException extends ChartBuilderException {
  private ChartRequestDto chartRequestDto;

  public ChartBuilderNotFoundException(ChartRequestDto chartRequestDto) {
    this.chartRequestDto = chartRequestDto;
  }

  public ChartBuilderNotFoundException(String message, ChartRequestDto chartRequestDto) {
    super(message);
    this.chartRequestDto = chartRequestDto;
  }

  public ChartBuilderNotFoundException(String message, Throwable cause, ChartRequestDto chartRequestDto) {
    super(message, cause);
    this.chartRequestDto = chartRequestDto;
  }

  public ChartBuilderNotFoundException(Throwable cause, ChartRequestDto chartRequestDto) {
    super(cause);
    this.chartRequestDto = chartRequestDto;
  }

  public ChartBuilderNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ChartRequestDto chartRequestDto) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.chartRequestDto = chartRequestDto;
  }

  public ChartRequestDto getChartRequestDto() {
    return chartRequestDto;
  }
}
