package io.gmi.chart.builder;

import com.google.common.io.Files;
import io.gmi.chart.requests.ChartRequest;

import java.io.File;
import java.io.IOException;

/**
 * Created by gmead on 9/26/14.
 */
class RenderedImageWriterDelegate extends ChartBuilderDelegate {

  @Override
  void handle(ChartBuilderContext context, ChartRequest request) {
    if(!context.getContextMap().containsKey(ChartBuilderContext.RENDERED_CHART_FILE_KEY)) {
      throw new IllegalStateException("No rendered image key exists");
    }

    String file = context.getContextMapValue(ChartBuilderContext.RENDERED_CHART_FILE_KEY, String.class);
    try {
      File imageFile = new File(file);
      if(!imageFile.exists()) {
        throw new IllegalStateException("File " + file + " was not found.");
      }
      byte[] imageBytes = Files.toByteArray(imageFile);
      context.getContextMap().put(ChartBuilderContext.RENDERED_CHART_KEY, imageBytes);
    } catch (IOException e) {
      throw new RuntimeException("Error copying bytes from file " + file);
    }
  }
}
