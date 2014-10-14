package io.gmi.chartms.config;

import io.gmi.chartms.api.ContextIDInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Created by gmead on 10/14/14.
 */
@Configuration
public class WebConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

  @Autowired
  ContextIDInterceptor contextIDInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    super.addInterceptors(registry);
    registry.addInterceptor(contextIDInterceptor);
  }


}
