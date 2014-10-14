package io.gmi.chartms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by gmead on 10/14/14.
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig implements InitializingBean {

  private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

  private String workingDir;

  private String imageDir;

  private String htmlDir;

  public String getWorkingDir() {
    return workingDir;
  }

  public void setWorkingDir(String workingDir) {
    this.workingDir = workingDir;
  }

  public String getImageDir() {
    return imageDir;
  }

  public void setImageDir(String imageDir) {
    this.imageDir = imageDir;
  }

  public String getHtmlDir() {
    return htmlDir;
  }

  public void setHtmlDir(String htmlDir) {
    this.htmlDir = htmlDir;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("Application workingDir={}", workingDir);
    log.info("Application imageDir={}", imageDir);
    log.info("Application htmlDir={}", htmlDir);
  }
}
