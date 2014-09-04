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
import com.google.common.io.Files;
import io.gmi.chart.ChartBuilderException;
import io.gmi.chart.Constants;
import io.gmi.chart.domain.Image;
import io.gmi.chart.requests.ChartRequest;
import io.gmi.chart.util.ChartUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public abstract class ChartBuilder {

  private static final Logger log = LoggerFactory.getLogger(ChartBuilder.class);

  private static final String DATA_URI_FORMAT= "data:image/png;base64, %s";
  private static final String DATA_KEY = "data";

  protected ChartBuilderContext chartBuilderContext;
  private ExecutorService executorService;

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private VelocityEngine velocityEngine;

  public CompletableFuture<ChartBuilderResult> buildChart(ChartRequest chartRequest) throws ChartBuilderException {

    return CompletableFuture.supplyAsync(() -> {
      try {
        //Generate the user content template
        log.info("Building chart ");
        Map<String, String> images = processImages(chartRequest);
        Map<String, Object> templateMap = new HashMap<>();
        templateMap.putAll(images);
        templateMap.putAll(chartRequest.getContent());
        String content = generateContentTemplate(templateMap, chartRequest.getTemplate());
        Map<String, String> scriptsAndStyles = processScriptAndStyleFiles(chartRequest);

      } catch (Exception e) {
        throw new ChartBuilderException(e);
      }
      return null;
    }, executorService);
  }

  public void setChartBuilderContext(ChartBuilderContext chartBuilderContext) {
    this.chartBuilderContext = chartBuilderContext;
  }

  @VisibleForTesting
  public void setExecutorService(ExecutorService executorService) {
    this.executorService = executorService;
  }

  @VisibleForTesting
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @VisibleForTesting
  public void setVelocityEngine(VelocityEngine velocityEngine) {
    this.velocityEngine = velocityEngine;
  }

  @VisibleForTesting
  public ChartBuilderContext getChartBuilderContext() {
    return chartBuilderContext;
  }


  @VisibleForTesting
  protected Map<String, String> processScriptAndStyleFiles(ChartRequest chartRequest) throws Exception {
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
    if(chartRequest.getUseBootstrap()) {
      resourceMap.put(Constants.$BOOTSTRAP,
              resourceLoader
                      .getResource(chartBuilderContext.getConfiguration().BOOTSTRAP_CSS_PATH())
                      .getFile());
    }
    final Map<String, String> resultsMap = new HashMap<>();
    resourceMap.forEach((k, v) -> resultsMap.put(k, buildFileString(v)));

    return resultsMap;
  }

  @VisibleForTesting
  protected Map<String, String> processImages(ChartRequest chartRequest) {
    final Map<String, String> resultsMap = new HashMap<>();
    List<Image> images = chartRequest.getImages();
    images.forEach(image -> resultsMap.put(image.getKey(), buildImageSourceString(image)));
    return resultsMap;
  }

  @VisibleForTesting
  protected String generateContentTemplate(Map<String, Object> contentMap, String requestTemplate) throws Exception {
    File contentTemplateFile = ChartUtils.createTempFile(chartBuilderContext.getConfiguration());
    log.info("File {} created to hold requestTemplate for request {}", contentTemplateFile.getPath()
            , chartBuilderContext.getId());
    Files.write(requestTemplate.getBytes(), contentTemplateFile);
    String result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, contentTemplateFile.getName(), "utf-8", contentMap);
    try {
      if(contentTemplateFile.delete())
        log.info("Deleted content template file {} for request {}", contentTemplateFile.getPath(),
                chartBuilderContext.getId());
      else
        log.warn("Unable to delete content template file {} for request {}", contentTemplateFile.getPath(),
                chartBuilderContext.getId());
    } catch (Exception e) {
      log.warn("Error while trying to delete file {}", contentTemplateFile.getName(), e);
    }
    return result;
  }

  protected abstract Collection<Object> getChartData(ChartRequest chartRequest);

  protected abstract String getChartScriptTemplate(ChartRequest chartRequest);

  private String buildFileString(File file) {
    log.debug("Getting contents for file {}", file.getName());
    FileToStringDelegate fileToStringDelegate = new FileToStringDelegate();
    return fileToStringDelegate.processFile(file);
  }

  private String buildImageSourceString(Image image) {
    log.debug("Building image src tag for {}", image.getKey());
    return String.format(DATA_URI_FORMAT, image.getContent());
  }

}
