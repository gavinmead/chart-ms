package io.gmi.chartms.service;

import org.jsoup.nodes.Document;

/**
 * Created by gmead on 10/14/14.
 */
interface InjectBootstrapDelegate {
  Document injectBootstrapCss(Document baseHtml, ChartCreationContext chartCreationContext);
}
