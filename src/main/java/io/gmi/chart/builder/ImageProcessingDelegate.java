package io.gmi.chart.builder;

import io.gmi.chart.domain.Image;
import io.gmi.chart.requests.ChartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gmead on 9/24/14.
 */
class ImageProcessingDelegate extends ChartBuilderDelegate {

  private static final String DATA_URI_FORMAT= "data:image/png;base64, %s";

  private static final Logger log = LoggerFactory.getLogger(ImageProcessingDelegate.class);

  @Override
  void handle(ChartBuilderContext context, ChartRequest request) {
    final Map<String, String> resultsMap = new HashMap<>();
    List<Image> images = request.getImages();
    images.forEach(image -> resultsMap.put(image.getKey(), buildImageSourceString(image)));
    log.info("Added {} images to context under key {}", ChartBuilderContext.IMAGE_KEY, images.size());
    context.getContextMap().put(ChartBuilderContext.IMAGE_KEY, resultsMap);
  }

  private String buildImageSourceString(Image image) {
    log.debug("Building image src tag for {}", image.getKey());
    return String.format(DATA_URI_FORMAT, image.getContent());
  }
}
