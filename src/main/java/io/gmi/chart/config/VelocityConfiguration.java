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

import io.gmi.chart.ChartMSConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

@Configuration
@ComponentScan
public class VelocityConfiguration {

  private static final Logger log = LoggerFactory.getLogger(VelocityConfiguration.class);

  private static final String CLASSPATH_TEMPLATE = "classpath:/templates/";

  @Autowired
  ChartMSConfiguration chartMSConfiguration;

  @Bean
  public VelocityEngineFactoryBean velocityEngine() {
    VelocityEngineFactoryBean factoryBean = new VelocityEngineFactoryBean();
    factoryBean.setResourceLoaderPath(CLASSPATH_TEMPLATE + "," + "file:" + chartMSConfiguration.DYNAMIC_TEMPLATE_PATH);
    return factoryBean;
  }

}
