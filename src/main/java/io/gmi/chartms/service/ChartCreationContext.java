package io.gmi.chartms.service;

import io.gmi.chartms.Action1;
import io.gmi.chartms.api.CreateChartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gmead on 10/14/14.
 */
public class ChartCreationContext {

  private static final Logger log = LoggerFactory.getLogger(ChartCreationContext.class);

  public static final String HTML_DOCUMENT_KEY = "html.document.key";
  public static final String IMAGE_KEY = "image.key";

  private String contextId;
  private Map<String, Object> contextMap;
  private CreateChartRequest chartRequest;

  public ChartCreationContext(String contextId, CreateChartRequest chartRequest) {
    if(StringUtils.isEmpty(contextId))
      throw new IllegalArgumentException("contextId cannot be null or empty");

    this.chartRequest = chartRequest;
    contextMap = new HashMap<>();
    this.contextId = contextId;
  }

  public Map<String, Object> getContextMap() {
    return contextMap;
  }

  <T> T getContextMapValue(String key, Class<T> clazz) {
      return getContextMapValue(key, clazz, s -> {});
    }

  <T> T getContextMapValue(String key, Class<T> clazz, Action1<String> onEmpty) {
     if(contextMap.containsKey(key)) {
        return (T) contextMap.get(key);
      } else {
        onEmpty.run(key);
          return null;
      }
  }

  public CreateChartRequest getChartRequest() {
    return chartRequest;
  }

  public String getContextId() {
    return contextId;
  }
}
