package io.gmi.chartms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by gmead on 10/13/14.
 */
@ComponentScan
@ContextConfiguration
@EnableAutoConfiguration
public class Application {

  private final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setShowBanner(false);
    app.run(args);
  }
}
