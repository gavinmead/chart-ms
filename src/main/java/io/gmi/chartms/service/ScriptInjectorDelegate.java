package io.gmi.chartms.service;

import org.jsoup.nodes.Document;

/**
 * Created by gmead on 10/14/14.
 */
interface ScriptInjectorDelegate {

  Document injectScript(Document html, ChartCreationContext chartCreationContext);
}
