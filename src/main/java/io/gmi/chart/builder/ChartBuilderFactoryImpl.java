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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.gmi.chart.ChartBuilderException;
import io.gmi.chart.ChartBuilderNotFoundException;
import io.gmi.chart.ChartMSConfiguration;
import io.gmi.chart.requests.ChartRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ChartBuilderFactoryImpl implements ChartBuilderFactory, InitializingBean, ApplicationContextAware {

  private static final Logger log = LoggerFactory.getLogger(ChartBuilderFactoryImpl.class);
  private ApplicationContext applicationContext;

  private ExecutorService executorService;

  @Autowired
  private ChartMSConfiguration configuration;

  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public ChartBuilder getChartBuilder(ChartRequest requestDto) throws ChartBuilderException {
    Preconditions.checkNotNull(requestDto);
    Preconditions.checkArgument(StringUtils.isNotEmpty(requestDto.getType()));

    try {
      Object chartBuilderObj = (ChartBuilder) applicationContext.getBean(requestDto.getType());
      if(chartBuilderObj instanceof ChartBuilder) {
        ChartBuilder chartBuilder = (ChartBuilder) chartBuilderObj;
        chartBuilder.setChartBuilderContext(new ChartBuilderContext(configuration));
        chartBuilder.setExecutorService(executorService);
        return chartBuilder;
      } else {
        throw new ChartBuilderNotFoundException("Bean registered with name " +
          requestDto.getType() + " is not a ChartBuilder implementation (" +
                chartBuilderObj.getClass().getName() + ")", requestDto);
      }
    } catch (BeansException be) {
      throw new ChartBuilderNotFoundException("Error getting chart from application context for type " +
        requestDto.getType(), requestDto);
    }
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @VisibleForTesting
  public void setConfiguration(ChartMSConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    executorService = Executors.newFixedThreadPool(configuration.CHART_BUILDER_THREAD_POOL_SIZE());
    log.info("executorService configured with a thread pool if size {}",
            configuration.CHART_BUILDER_THREAD_POOL_SIZE());
  }
}
