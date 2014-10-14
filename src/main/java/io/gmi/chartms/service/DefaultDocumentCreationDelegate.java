package io.gmi.chartms.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by gmead on 10/14/14.
 */
@Component
class DefaultDocumentCreationDelegate implements DocumentCreationDelegate {

  private static final Logger log = LoggerFactory.getLogger(DefaultDocumentCreationDelegate.class);

  private static final String EMPTY_HTML =
      "<html>" +
      "<body></body>" +
      "</html>";

  @Override
  public Document createDocument(String htmlFragment) {
    if(StringUtils.isEmpty(htmlFragment)) {
      log.info("htmlFragment parameter is empty.  Creating empty document.");
      return Jsoup.parse(EMPTY_HTML);
    } else {
      log.info("Creating HTML document for fragment \n{}\n", htmlFragment);
      return Jsoup.parse(htmlFragment);
    }
  }
}
