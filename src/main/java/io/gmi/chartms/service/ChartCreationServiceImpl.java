package io.gmi.chartms.service;

import com.google.common.annotations.VisibleForTesting;
import io.gmi.chartms.config.AppConfig;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gmead on 10/14/14.
 */
@Service
public class ChartCreationServiceImpl extends AbstractChartCreationService {

  private static final Logger log = LoggerFactory.getLogger(ChartCreationServiceImpl.class);

  @Autowired
  DocumentCreationDelegate documentCreationDelegate;

  @Autowired
  ScriptInjectorDelegate scriptInjectorDelegate;

  @Autowired
  GenerateImageDelegate generateImageDelegate;

  @Autowired
  public ChartCreationServiceImpl(AppConfig appConfig) {
    super(appConfig);
  }

  @Override
  protected Document generateBaseHtmlDocument(String htmlFragment) {
    return documentCreationDelegate.createDocument(htmlFragment);
  }

  @Override
  protected Document injectScript(Document html, ChartCreationContext chartCreationContext) {
    return scriptInjectorDelegate.injectScript(html, chartCreationContext);
  }

  @Override
  protected String generateImage(ChartCreationContext chartCreationContext) {
    return generateImageDelegate.generateImage(chartCreationContext);
  }

  @VisibleForTesting
  public void setDocumentCreationDelegate(DocumentCreationDelegate documentCreationDelegate) {
    this.documentCreationDelegate = documentCreationDelegate;
  }
}
