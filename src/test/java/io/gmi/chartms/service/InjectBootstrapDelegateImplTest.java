package io.gmi.chartms.service;

import io.gmi.chartms.api.CreateChartRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class InjectBootstrapDelegateImplTest extends ChartCreateContextAwareTest {

  Document document;

  @Autowired
  InjectBootstrapDelegate injectBootstrapDelegate;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    document = Jsoup.parse("");
  }

  @Test
  public void testInjectBootstrapCss() throws Exception {
    CreateChartRequest request = new CreateChartRequest();
    request.setUseBootstrap(true);
    ChartCreationContext context = new ChartCreationContext(contextId, request);
    Document result = injectBootstrapDelegate.injectBootstrapCss(document, context);
    Element bootStrap = result.getElementById("bootstrap");
    assertThat(bootStrap).isNotNull();
  }

}