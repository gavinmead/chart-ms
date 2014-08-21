package io.gavinmead.chart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by gmead on 8/21/2014.
 */
@ContextConfiguration
@EnableAutoConfiguration
@ComponentScan
public class Application implements CommandLineRunner {

  @Value("${version}")
  private String version;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Version=" + version);
  }
}
