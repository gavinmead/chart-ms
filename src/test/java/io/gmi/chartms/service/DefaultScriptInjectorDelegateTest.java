package io.gmi.chartms.service;

import io.gmi.chartms.api.CreateChartRequest;
import io.gmi.chartms.config.AppConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultScriptInjectorDelegateTest extends ChartCreateContextAwareTest {

  private static final Logger log = LoggerFactory.getLogger(DefaultScriptInjectorDelegateTest.class);

  private static final String CHART = "{\n" +
      "        chart: {\n" +
      "            type: 'bar'\n" +
      "        },\n" +
      "        title: {\n" +
      "            text: 'Fruit Consumption'\n" +
      "        },\n" +
      "        xAxis: {\n" +
      "            categories: ['Apples', 'Bananas', 'Oranges']\n" +
      "        },\n" +
      "        yAxis: {\n" +
      "            title: {\n" +
      "                text: 'Fruit eaten'\n" +
      "            }\n" +
      "        },\n" +
      "        series: [{\n" +
      "            name: 'Jane',\n" +
      "            data: [1, 0, 4]\n" +
      "        }, {\n" +
      "            name: 'John',\n" +
      "            data: [5, 7, 3]\n" +
      "        }]\n" +
      "    }";

  Document document;

  @Autowired
  DefaultScriptInjectorDelegate defaultScriptInjectorDelegate;

  @Autowired
  AppConfig appConfig;

  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();
    document = Jsoup.parse("");
  }

  @Test
  public void testInjectScriptWithNoDocument() throws Exception {
    CreateChartRequest request = new CreateChartRequest();
    request.setChart(CHART);
    Document generatedDoc = defaultScriptInjectorDelegate.injectScript(document, new ChartCreationContext(contextId, request));
    doBaseAssert(generatedDoc);
    assertThat(generatedDoc.getElementById(contextId)).isNotNull();
    log.info("\n{}\n",generatedDoc);
  }

  @Test
  public void testInjectScriptWithDocument() throws Exception {
    String html = "<div id=\"testDiv\"><div id=\"chartDiv\"></div></div>";
    Document doc = Jsoup.parse(html);
    CreateChartRequest request = new CreateChartRequest();
    request.setChart(CHART);
    request.setChartContainerId("chartDiv");
    request.setHtml(html);

    Document generatedDoc = defaultScriptInjectorDelegate.injectScript(doc, new ChartCreationContext(contextId, request));
    doBaseAssert(generatedDoc);
    Element scriptElement = generatedDoc.getElementById("appScript");
    assertThat(scriptElement.data()).contains("$(\'#chartDiv\').highcharts");

    log.info("\n{}\n",generatedDoc);
  }

  private void doBaseAssert(Document generatedDoc) {
    assertThat(generatedDoc.getElementById("jquery")).isNotNull();
    assertThat(generatedDoc.getElementById("highcharts")).isNotNull();
    assertThat(generatedDoc.getElementById("appScript")).isNotNull();
  }

  @Test
  public void testGetJquery() throws Exception {
    Element jquery = defaultScriptInjectorDelegate.getJquery();
    assertThat(jquery.attr("src")).endsWith(appConfig.getJqueryScript());
  }

  @Test
  public void testGetHighCharts() throws Exception {
    Element highCharts = defaultScriptInjectorDelegate.getHighCharts();
    assertThat(highCharts.attr("src")).endsWith(appConfig.getHighChartsScript());
  }
}