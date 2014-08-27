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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ChartMSConfiguration {

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

  private String getProperty(String property) {
    return environment.getProperty(property);
  }
}
