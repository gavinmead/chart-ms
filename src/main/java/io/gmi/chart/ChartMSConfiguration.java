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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ChartMSConfiguration implements InitializingBean {

  @Autowired
  private Environment environment;

  private static final String SCRIPTS = "scripts";
  private static final String CSS = "css";

  public static final String ANGULAR_SCRIPT_PATH_KEY = SCRIPTS + ".angular.path";
  public static final String D3_SCRIPT_PATH_KEY = SCRIPTS + ".d3.path";
  public static final String NVD3_SCRIPT_PATH_KEY = SCRIPTS + ".nvd3.path";
  public static final String ANGULAR_NVD3_PATH_KEY = SCRIPTS + ".angular-nvd3.path";
  public static final String ES5_SHIM_PATH_KEY = SCRIPTS + ".es5shim.path";

  public static final String NVD3_CSS_PATH_KEY = CSS + ".nvd3.path";
  public static final String BOOTSTRAP_CSS_PATH_KEY = CSS + ".bootstrap.path";

  public static final String USE_BOOTSTRAP_KEY = CSS + ".bootstrap.use";
  public static final String CHARTS = "charts";
  public static final String LINE_CHART = CHARTS + ".line";
  public static final String LINE_CHART_DIRECTIVE = LINE_CHART + ".directive";
  public static final String LINE_CHART_OPTION_TYPE = LINE_CHART + ".option";

  private static final String TEMPLATE_PATH_FORMAT_STRING = "%s" + File.separator + "templates" + File.separator;
  public static final String CHART_BUILDER_THREAD_POOL_KEY = CHARTS + ".builder.poolSize";

  public String DYNAMIC_TEMPLATE_PATH;

  public final String ANGULAR_SCRIPT_PATH() {
     return getProperty(ANGULAR_SCRIPT_PATH_KEY);
  }

  public final String D3_SCRIPT_PATH() {
    return getProperty(D3_SCRIPT_PATH_KEY);
  }

  public final String NVD3_SCRIPT_PATH() {
    return getProperty(NVD3_SCRIPT_PATH_KEY);
  }

  public final String ANGULAR_NVD3_SCRIPT_PATH() {
    return getProperty(ANGULAR_NVD3_PATH_KEY);
  }

  public final String ES5_SHIM_SCRIPT_PATH() { return getProperty(ES5_SHIM_PATH_KEY); }

  public final String NVD3_CSS_PATH() { return getProperty(NVD3_CSS_PATH_KEY); }

  public final String BOOTSTRAP_CSS_PATH() { return getProperty(BOOTSTRAP_CSS_PATH_KEY); }

  public final Boolean USE_BOOTSTRAP_DEFAULT() {
    return environment.getProperty(USE_BOOTSTRAP_KEY, Boolean.class);
  }

  public final String LINE_CHART_DIRECTIVE() { return getProperty(LINE_CHART_DIRECTIVE); }

  public final String LINE_CHART_OPTION() { return getProperty(LINE_CHART_OPTION_TYPE); }

  public final String NVD3_CSS_WORKING_PATH() { return WORKING_DIRECTORY_PATH() + File.separator + "nv.d3.min.css"; }

  public final String BOOSTRAP_CSS_WORKING_PATH() { return WORKING_DIRECTORY_PATH() + File.separator + "bootstrap.min.css"; }

  public final String ES5_SHIM_SCRIPT_WORKING_PATH() { return WORKING_DIRECTORY_PATH() + File.separator + "es5-shim.js"; }

  public final String ANGULAR_SCRIPT_WORKING_PATH() { return WORKING_DIRECTORY_PATH() + File.separator + "angular.min.js"; }

  public final String D3_SCRIPT_WORKING_PATH() { return WORKING_DIRECTORY_PATH() + File.separator + "d3.min.js"; }

  public final String NVD3_SCRIPT_WORKING_PATH() { return WORKING_DIRECTORY_PATH() + File.separator + "nv.d3.min.js";}

  public final String ANGULAR_NVD3_SCRIPT_WORKING_PATH() { return WORKING_DIRECTORY_PATH() + File.separator + "angular-nvd3.min.js";}

  public final Integer CHART_BUILDER_THREAD_POOL_SIZE() {
    return environment.getProperty(CHART_BUILDER_THREAD_POOL_KEY, Integer.class);
  }

  public final String WORKING_DIRECTORY_PATH() {
    return environment.getProperty("user.dir") + File.separator + "working";
  }

  private String getProperty(String property) {
    return environment.getProperty(property);
  }


  @Override
  public void afterPropertiesSet() throws Exception {
    String tempDir = environment.getProperty("java.io.tmpdir");
    DYNAMIC_TEMPLATE_PATH = String.format(TEMPLATE_PATH_FORMAT_STRING, tempDir);

  }
}
