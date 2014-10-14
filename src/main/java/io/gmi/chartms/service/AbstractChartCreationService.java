package io.gmi.chartms.service;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.io.Files;
import io.gmi.chartms.config.AppConfig;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by gmead on 10/14/14.
 */
public abstract class AbstractChartCreationService implements ChartCreationService {

  private static final Logger log  = LoggerFactory.getLogger(AbstractChartCreationService.class);

  private static final String HTML_FORMAT_STRING = "%s.html";

  private AppConfig appConfig;

  public AbstractChartCreationService(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @Override
  public String createChartImage(ChartCreationContext chartCreationContext) throws ChartCreationException {
    String imageFile = null;
    try {
      Document baseDocument = generateBaseHtmlDocument(chartCreationContext.getChartRequest().getHtml());
      Document postProcessedHtmlDocument = postProcessBaseHtmlDocument(baseDocument, chartCreationContext);
      Document completedDocument = injectScript(postProcessedHtmlDocument, chartCreationContext);
      saveHtml(completedDocument, chartCreationContext);
      imageFile = generateImage(chartCreationContext);
    } catch (Exception e) {
      throw new ChartCreationException("Error generating chart", e, chartCreationContext);
    }
    return imageFile;
  }

  protected abstract Document generateBaseHtmlDocument(String htmlFragment);

  protected Document postProcessBaseHtmlDocument(Document html, ChartCreationContext chartCreationContext) {
    return html;
  }

  protected abstract Document injectScript(Document html, ChartCreationContext chartCreationContext);

  @VisibleForTesting
  protected void saveHtml(Document document, ChartCreationContext chartCreationContext) throws IOException {
    String fileName = String.format(HTML_FORMAT_STRING, chartCreationContext.getContextId());
    File file = new File(appConfig.getHtmlDir(), fileName);
    log.info("Saving html document to {}", file);
    Files.write(document.toString().getBytes(), file);
    chartCreationContext.getContextMap().put(ChartCreationContext.HTML_DOCUMENT_KEY, file.toString());
  }

  protected abstract String generateImage(ChartCreationContext chartCreationContext);
}
