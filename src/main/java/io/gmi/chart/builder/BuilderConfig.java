package io.gmi.chart.builder;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gmead on 9/24/14.
 */
@Configuration
@ComponentScan
public class BuilderConfig {

  @Autowired
  VelocityEngine velocityEngine;

  @Bean
  public ChartBuilderDelegate scriptStylesDelegate() {
    return new ScriptStylesDelegate();
  }

  @Bean
  public ChartBuilderDelegate mainTemplateDelegate() {
    return new MainTemplateDelegate(velocityEngine);
  }

}
