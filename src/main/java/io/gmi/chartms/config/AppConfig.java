package io.gmi.chartms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gmead on 10/14/14.
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig implements InitializingBean {

  private static final Logger log = LoggerFactory.getLogger(AppConfig.class);
  private Map<String, String> classpathResourceMap = new HashMap<>();

  private String workingDir;

  private String imageDir;

  private String htmlDir;

  private String bootstrapCss;

  private String renderImageScript;

  private String jqueryScript;

  private String highChartsScript;

  private DirectoryMonitorConfig directoryMonitor;

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

  public String getBootstrapCss() {
    return bootstrapCss;
  }

  public void setBootstrapCss(String bootstrapCss) {
    this.bootstrapCss = bootstrapCss;
  }

  public String getRenderImageScript() {
    return renderImageScript;
  }

  public void setRenderImageScript(String renderImageScript) {
    this.renderImageScript = renderImageScript;
  }

  public String getJqueryScript() {
    return jqueryScript;
  }

  public void setJqueryScript(String jqueryScript) {
    this.jqueryScript = jqueryScript;
  }

  public String getHighChartsScript() {
    return highChartsScript;
  }

  public void setHighChartsScript(String highChartsScript) {
    this.highChartsScript = highChartsScript;
  }

  public Map<String, String> getClasspathResourceMap() {
    return classpathResourceMap;
  }

  public DirectoryMonitorConfig getDirectoryMonitor() {
    return directoryMonitor;
  }

  public void setDirectoryMonitor(DirectoryMonitorConfig directoryMonitor) {
    this.directoryMonitor = directoryMonitor;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("Application workingDir={}", workingDir);
    log.info("Application imageDir={}", imageDir);
    log.info("Application htmlDir={}", htmlDir);

    log.info("Copying classpath resources to {}", workingDir);

    classpathResourceMap.put("classpath:/bootstrap.min.css", bootstrapCss);
    classpathResourceMap.put("classpath:/highcharts.js", highChartsScript);
    classpathResourceMap.put("classpath:/jquery-1.11.1.js", jqueryScript);
    classpathResourceMap.put("classpath:/render-image.js", renderImageScript);
  }
}
