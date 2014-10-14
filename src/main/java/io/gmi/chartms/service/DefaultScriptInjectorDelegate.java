package io.gmi.chartms.service;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

/**
 * Created by gmead on 10/14/14.
 */
@Component
class DefaultScriptInjectorDelegate implements ScriptInjectorDelegate {

  @Override
  public Document injectScript(Document html, ChartCreationContext chartCreationContext) {
    return null;
  }
}
