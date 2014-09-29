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

import io.gmi.chart.Action1;
import io.gmi.chart.ChartMSConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


class ChartBuilderContext {

  static final String RENDERED_CHART_FILE_KEY = "_rendered.chart.file_";
  static final String RENDERED_CHART_KEY = "_rendered.chart_";
  static final String IMAGE_KEY = "_image_";
  static final String HAS_ERROR_KEY = "_has.error_";
  static final String MAIN_TEMPLATE_FILE_KEY = "_main.template.file_";
  static final String JS_APPLICATION_KEY = "_app.content_";
  static final String CHART_TAG_KEY = "_chart.tag_";
  static final String REQUEST_TEMPLATE_CONTENT_KEY = "_request.template.content_";
  static final String FINAL_HTML_FILE_KEY = "_final.html.file_";
  static final String RESOURCES_KEY = "_resources_";

  private String contextId = UUID.randomUUID().toString();
  private ChartMSConfiguration configuration;
  private Map<String, Object> contextMap = new HashMap<>();

  ChartBuilderContext() {
  }

  ChartBuilderContext(ChartMSConfiguration configuration) {
    this.configuration = configuration;
  }

  ChartMSConfiguration getConfiguration() {
    return configuration;
  }

  Map<String, Object> getContextMap() {
    return contextMap;
  }

  <T> T getContextMapValue(String key, Class<T> clazz) {
    return getContextMapValue(key, clazz, s -> {});
  }

  <T> T getContextMapValue(String key, Class<T> clazz, Action1<String> onEmpty) {
    if(contextMap.containsKey(key)) {
      return (T) contextMap.get(key);
    } else {
      onEmpty.run(key);
      return null;
    }
  }

  Boolean hasImageResult() {
    return contextMap.containsKey(RENDERED_CHART_KEY);
  }

  Boolean hasError() {
    return (Boolean) contextMap.getOrDefault(HAS_ERROR_KEY, Boolean.valueOf(false));
  }

  String getContextId() {
    return contextId;
  }
}
