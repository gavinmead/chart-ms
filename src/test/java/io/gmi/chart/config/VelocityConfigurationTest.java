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

package io.gmi.chart.config;

import com.google.common.io.Files;
import io.gmi.chart.Application;
import io.gmi.chart.ChartMSConfiguration;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class VelocityConfigurationTest {

  private static final String TEMPLATE = "$test";

  private static final String TEMP_FILE = "temp-file.vm";

  @Autowired
  VelocityEngine velocityEngine;

  @Autowired
  ChartMSConfiguration configuration;

  @Test
  public void testVelocityEngine() throws Exception {
    String tempPath = configuration.DYNAMIC_TEMPLATE_PATH;
    File tempFile = new File(tempPath, TEMP_FILE);
    tempFile.deleteOnExit();
    Files.createParentDirs(tempFile);
    Files.write(TEMPLATE.getBytes(), tempFile);
    Map<String, Object> model = new HashMap<>();
    model.put("test", "tested");

    String result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, TEMP_FILE, "utf-8", model);
    assertThat(result).isNotNull();
    assertThat(result).isEqualTo("tested");

  }
}