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

import io.gmi.chart.requests.ChartRequest;


public class ChartBuilderNotFoundException extends ChartBuilderException {
  private ChartRequest chartRequest;

  public ChartBuilderNotFoundException(ChartRequest chartRequest) {
    this.chartRequest = chartRequest;
  }

  public ChartBuilderNotFoundException(String message, ChartRequest chartRequest) {
    super(message);
    this.chartRequest = chartRequest;
  }

  public ChartBuilderNotFoundException(String message, Throwable cause, ChartRequest chartRequest) {
    super(message, cause);
    this.chartRequest = chartRequest;
  }

  public ChartBuilderNotFoundException(Throwable cause, ChartRequest chartRequest) {
    super(cause);
    this.chartRequest = chartRequest;
  }

  public ChartBuilderNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ChartRequest chartRequest) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.chartRequest = chartRequest;
  }

  public ChartRequest getChartRequest() {
    return chartRequest;
  }
}
