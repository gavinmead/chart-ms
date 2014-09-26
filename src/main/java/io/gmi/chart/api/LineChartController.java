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

package io.gmi.chart.api;

import io.gmi.chart.builder.ChartBuilder;
import io.gmi.chart.builder.ChartBuilderFactory;
import io.gmi.chart.builder.ChartBuilderResult;
import io.gmi.chart.requests.LineChartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = UrlConstants.LINE_CHART)
public class LineChartController {

  private static final Logger log = LoggerFactory.getLogger(LineChartController.class);

  @Autowired
  ChartBuilderFactory chartBuilderFactory;

  @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.IMAGE_PNG_VALUE, method = RequestMethod.POST)
  public byte[] create(@RequestBody LineChartRequest chartRequest, HttpServletResponse servletResponse) {
    ChartBuilder chartBuilder = chartBuilderFactory.getChartBuilder(chartRequest);
    ChartBuilderResult result = chartBuilder.build(chartRequest);
    return result.getResult();
  }
}
