package io.gmi.chartms.service;

import io.gmi.chartms.config.AppConfig;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gmead on 10/14/14.
 */
@Component
class InjectBootstrapDelegateImpl implements InjectBootstrapDelegate {

  private static final Logger log = LoggerFactory.getLogger(InjectBootstrapDelegateImpl.class);

  private static final String LINK_FORMAT_STRING = "file://%s";

  @Autowired
  AppConfig appConfig;

  @Override
  public Document injectBootstrapCss(Document baseHtml, ChartCreationContext chartCreationContext) {
    if(chartCreationContext.getChartRequest().getUseBootstrap()) {
      log.info("Injecting bootstrap CSS into document");
      Document clonedDocument = baseHtml.clone();
      Element head = clonedDocument.head();
      Tag linkTag = Tag.valueOf("link");

      Element styleElement = new Element(linkTag, "");
      styleElement.attr("id", "bootstrap");
      styleElement.attr("rel", "stylesheet");
      styleElement.attr("href", String.format(LINK_FORMAT_STRING, appConfig.getBootstrapCss()));
      head.appendChild(styleElement);
      return clonedDocument;
    } else {
      log.info("Bootstrap injection not requested.  Returning original document");
      return baseHtml;
    }
  }
}
