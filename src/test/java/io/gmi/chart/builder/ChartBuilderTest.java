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

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import io.gmi.chart.Application;
import io.gmi.chart.ChartMSConfiguration;
import io.gmi.chart.TestChartRequestDto;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
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

  private static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(3));


  @Before
  public void setUp() throws Exception {
    context = new ChartBuilderContext(configuration);
    chartBuilder = new TestChartBuilder();
    chartBuilder.setListeningExecutorService(service);
    chartBuilder.setChartBuilderContext(context);
    chartBuilder.setResourceLoader(resourceLoader);
  }

  @Test(timeout = 10000)
  public void testProcessScriptAndStyleFiles() throws Exception {
    Map<String, ListenableFuture<String>> filesFutures = chartBuilder.processScriptAndStyleFiles(new TestChartRequestDto());
    ListenableFuture<List<String>> resultFuture = Futures.successfulAsList(filesFutures.values());
    List<String> results = resultFuture.get();
    assertThat(results.size()).isEqualTo(7);
    for(String result : results) {
      assertThat(StringUtils.isEmpty(result)).isFalse();
    }

  }
}