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
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import io.gmi.chart.ChartBuilderException;
import io.gmi.chart.Constants;
import io.gmi.chart.dto.ChartRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class ChartBuilder {

  private static final Logger log = LoggerFactory.getLogger(ChartBuilder.class);

  protected ChartBuilderContext chartBuilderContext;
  private ListeningExecutorService listeningExecutorService;

  @Autowired
  private ResourceLoader resourceLoader;

  public ListenableFuture<byte[]> buildChart(ChartRequestDto chartRequestDto) throws ChartBuilderException {
    return null;
  }

  public void setChartBuilderContext(ChartBuilderContext chartBuilderContext) {
    this.chartBuilderContext = chartBuilderContext;
  }

  public void setListeningExecutorService(ListeningExecutorService listeningExecutorService) {
    this.listeningExecutorService = listeningExecutorService;
  }

  @VisibleForTesting
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @VisibleForTesting
  public ChartBuilderContext getChartBuilderContext() {
    return chartBuilderContext;
  }

  @VisibleForTesting
  protected Map<String,ListenableFuture<String>> processScriptAndStyleFiles(ChartRequestDto chartRequestDto) throws Exception {
    //Create a list of resources to use
    final Map<String, File> resourceMap = new HashMap<>();
    resourceMap.put(Constants.$ANGULARJS,
            resourceLoader
                    .getResource(chartBuilderContext.getConfiguration().ANGULAR_SCRIPT_PATH())
                    .getFile());
    resourceMap.put(Constants.$D3,
            resourceLoader
                    .getResource(chartBuilderContext.getConfiguration().D3_SCRIPT_PATH())
                    .getFile());
    resourceMap.put(Constants.$NVD3,
            resourceLoader
                    .getResource(chartBuilderContext.getConfiguration().NVD3_SCRIPT_PATH())
                    .getFile());
    resourceMap.put(Constants.$ANGULAR_NVD3,
            resourceLoader
                    .getResource(chartBuilderContext.getConfiguration().ANGULAR_NVD3_SCRIPT_PATH())
                    .getFile());
    resourceMap.put(Constants.$NVD3CSS,
            resourceLoader
                    .getResource(chartBuilderContext.getConfiguration().NVD3_CSS_PATH())
                    .getFile());
    resourceMap.put(Constants.$ES5_SHIM,
            resourceLoader
                    .getResource(chartBuilderContext.getConfiguration().ES5_SHIM_SCRIPT_PATH())
                    .getFile());
    if(chartRequestDto.getUseBootstrap()) {
      resourceMap.put(Constants.$BOOTSTRAP,
              resourceLoader
                      .getResource(chartBuilderContext.getConfiguration().BOOTSTRAP_CSS_PATH())
                      .getFile());
    }

    final Map<String, ListenableFuture<String>> fileFutureMap = new HashMap<>();
    resourceMap.forEach((k, v) -> fileFutureMap.put(k, buildFileString(v)));
    return fileFutureMap;
  }

  private ListenableFuture<String> buildFileString(File file) {
    log.debug("Getting contents for file {}", file.getName());
    FileToStringDelegate fileToStringDelegate = new FileToStringDelegate(listeningExecutorService);
    return fileToStringDelegate.processFile(file);
  }

}
