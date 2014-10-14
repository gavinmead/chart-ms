package io.gmi.chartms.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultDocumentCreationDelegateTest extends ChartCreateContextAwareTest{

  @Autowired
  DefaultDocumentCreationDelegate defaultDocumentCreationDelegate;

  @Test
  public void testCreateDocument() throws Exception {
    String htmlFragment = "<div id=\"test\"></div>";
    Document document = defaultDocumentCreationDelegate.createDocument(htmlFragment);
    Element div = document.getElementById("test");
    assertThat(div).isNotNull();
  }

  @Test
  public void testCreateEmptyDocument() throws Exception {
    Document document = defaultDocumentCreationDelegate.createDocument(null);
    assertThat(document.getElementById("test")).isNull();
  }
}