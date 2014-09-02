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

import io.gmi.chart.Application;
import io.gmi.chart.ChartMSConfiguration;
import io.gmi.chart.TestChartRequestDto;
import io.gmi.chart.domain.Image;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ChartBuilderTest {

  @Autowired
  TestChartBuilder chartBuilder;

  @Autowired
  ChartMSConfiguration configuration;

  @Autowired
  ResourceLoader resourceLoader;

  ChartBuilderContext context;

  private static ExecutorService service = Executors.newFixedThreadPool(5);

  @Before
  public void setUp() throws Exception {
    context = new ChartBuilderContext(configuration);
    chartBuilder = new TestChartBuilder();
    chartBuilder.setExecutorService(service);
    chartBuilder.setChartBuilderContext(context);
    chartBuilder.setResourceLoader(resourceLoader);
  }

  @Test
  public void testProcessScriptAndStyleFiles() throws Exception {
    Map<String,String> results =
            chartBuilder.processScriptAndStyleFiles(new TestChartRequestDto());
    assertThat(results).isNotNull();
    assertThat(results.size()).isEqualTo(7);
    results.forEach((k, v) -> assertThat(StringUtils.isEmpty(v)).isFalse());
  }

  @Test
  public void testProcessImages() throws Exception {
    Image image1 = new Image();
    image1.setContent("ABCD");
    image1.setKey("image1");

    Image image2 = new Image();
    image2.setContent("DCBA");
    image2.setKey("image2");

    TestChartRequestDto requestDto = new TestChartRequestDto();
    requestDto.getImages().add(image1);
    requestDto.getImages().add(image2);

    Map<String,String> results = chartBuilder.processImages(requestDto);
    assertThat(results).isNotNull();
    assertThat(results.size()).isEqualTo(2);
    String kvp1 = results.get("image1");
    assertThat(kvp1).isEqualTo("data:image/png;base64, ABCD");

    String kvp2 = results.get("image2");
    assertThat(kvp2).isEqualTo("data:image/png;base64, DCBA");
  }
}