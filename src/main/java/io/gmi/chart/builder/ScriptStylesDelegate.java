package io.gmi.chart.builder;

import com.google.common.annotations.VisibleForTesting;
import io.gmi.chart.ChartBuilderException;
import io.gmi.chart.Constants;
import io.gmi.chart.dto.ChartRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gmead on 9/24/14.
 */
public class ScriptStylesDelegate extends ChartBuilderDelegate implements ResourceLoaderAware {

  private static final Logger log = LoggerFactory.getLogger(ScriptStylesDelegate.class);
  private FileToStringDelegate fileToStringDelegate = new FileToStringDelegate();
  private ResourceLoader resourceLoader;

  @Override
  void handle(ChartBuilderContext context, ChartRequestDto requestDto) {
    //Create a list of resources to use
    try {
      final Map<String, File> resourceMap = new HashMap<>();
      resourceMap.put(Constants.$ANGULARJS,
          resourceLoader
              .getResource(context.getConfiguration().ANGULAR_SCRIPT_PATH())
              .getFile());
      resourceMap.put(Constants.$D3,
          resourceLoader
              .getResource(context.getConfiguration().D3_SCRIPT_PATH())
              .getFile());
      resourceMap.put(Constants.$NVD3,
          resourceLoader
              .getResource(context.getConfiguration().NVD3_SCRIPT_PATH())
              .getFile());
      resourceMap.put(Constants.$ANGULAR_NVD3,
          resourceLoader
              .getResource(context.getConfiguration().ANGULAR_NVD3_SCRIPT_PATH())
              .getFile());
      resourceMap.put(Constants.$NVD3CSS,
          resourceLoader
              .getResource(context.getConfiguration().NVD3_CSS_PATH())
              .getFile());
      resourceMap.put(Constants.$ES5_SHIM,
          resourceLoader
              .getResource(context.getConfiguration().ES5_SHIM_SCRIPT_PATH())
              .getFile());
      if (requestDto.getUseBootstrap()) {
        resourceMap.put(Constants.$BOOTSTRAP,
            resourceLoader
                .getResource(context.getConfiguration().BOOTSTRAP_CSS_PATH())
                .getFile());
      }
      final Map<String, String> resultsMap = new HashMap<>();
      resourceMap.forEach((k, v) -> resultsMap.put(k, buildFileString(v)));
      context.getContextMap().put(Constants.RESOURCE, resultsMap);
    } catch (Exception e) {
      throw new ChartBuilderException(e);
    }
  }

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @VisibleForTesting
  public void setFileToStringDelegate(FileToStringDelegate fileToStringDelegate) {
    this.fileToStringDelegate = fileToStringDelegate;
  }

  private String buildFileString(File file) {
    log.debug("Getting contents for file {}", file.getName());
    return fileToStringDelegate.processFile(file);
  }
}
