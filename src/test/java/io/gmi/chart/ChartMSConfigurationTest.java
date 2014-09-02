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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ChartMSConfigurationTest {

  private static final String FORMAT_KEY = "/* %s */";

  @Autowired
  ChartMSConfiguration configuration;

  @Autowired
  ResourceLoader resourceLoader;

  @Autowired
  Environment environment;

  @Test
  public void testANGULAR_SCRIPT_PATH() throws Exception {
    assertResource(configuration::ANGULAR_SCRIPT_PATH, "angular.min.js");
  }

  @Test
  public void testD3_SCRIPT_PATH() throws Exception {
    assertResource(configuration::D3_SCRIPT_PATH, "d3.min.js");
  }

  @Test
  public void testNVD3_SCRIPT_PATH() throws Exception {
    assertResource(configuration::NVD3_SCRIPT_PATH, "nv.d3.min.js");
  }

  @Test
  public void testANGULAR_NVD3_SCRIPT_PATH() throws Exception {
    assertResource(configuration::ANGULAR_NVD3_SCRIPT_PATH, "angular-nvd3.min.js");
  }

  @Test
  public void testES5_SHIM_SCRIPT_PATH() throws Exception {
    assertResource(configuration::ES5_SHIM_SCRIPT_PATH, "es5-shim.js");
  }

  @Test
  public void testNVD3_CSS_PATH() throws Exception {
    assertResource(configuration::NVD3_CSS_PATH, "nv.d3.min.css");
  }

  @Test
  public void testBOOTSTRAP_CSS_PATH() throws Exception {
    assertResource(configuration::BOOTSTRAP_CSS_PATH, "bootstrap.min.css");
  }

  @Test
  public void testUSE_BOOTSTRAP_DEFAULT() {
    assertThat(configuration.USE_BOOTSTRAP_DEFAULT()).isTrue();
  }

  @Test
  public void testLINE_CHART_DIRECTIVE() {
    assertThat(configuration.LINE_CHART_DIRECTIVE()).isNotNull();
  }

  @Test
  public void testLINE_CHART_OPTION() {
    assertThat(configuration.LINE_CHART_OPTION()).contains("var chartingOptions = { type: 'lineChart' }");
  }

  @Test
  public void testDYNAMIC_TEMPLATE_PATH() {
    assertThat(configuration.DYNAMIC_TEMPLATE_PATH)
            .startsWith(environment.getProperty("java.io.tmpdir") + File.separator);

  }

  private void assertResource(Func0<String> configFunc, String key) throws Exception {
    String configEntry = configFunc.call();
    assertThat(configEntry).isNotNull();
    Resource resource = getResource(configEntry);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
    String firstLine = bufferedReader.readLine();
    assertThat(firstLine).isEqualTo(String.format(FORMAT_KEY, key));
    bufferedReader.close();
  }

  private Resource getResource(String path) {
    return resourceLoader.getResource(path);
  }

}