package io.gmi.chart.builder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gmead on 9/24/14.
 */
@Configuration
@ComponentScan
public class BuilderConfig {

  @Bean
  public ChartBuilderDelegate scriptStylesDelegate() {
    return new ScriptStylesDelegate();
  }

}
