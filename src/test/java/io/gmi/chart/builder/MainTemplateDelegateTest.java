package io.gmi.chart.builder;

import io.gmi.chart.Application;
import io.gmi.chart.ChartMSConfiguration;
import io.gmi.chart.Constants;
import io.gmi.chart.TestChartRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MainTemplateDelegateTest {

  @Autowired
  @Qualifier("mainTemplateDelegate")
  private ChartBuilderDelegate delegate;

  @Autowired
  ChartMSConfiguration configuration;

  private ChartBuilderContext context;

  private Map<String, String> resources;

  @Before
  public void setUp() throws Exception {
    context = new ChartBuilderContext(configuration);
    resources = new HashMap<>();
    resources.put(Constants.$NVD3CSS, "nvd3css");
    resources.put(Constants.$BOOTSTRAP, "bootstrap");
    resources.put(Constants.$ES5_SHIM, "es5shim");
    resources.put(Constants.$ANGULARJS, "angularjs");
    resources.put(Constants.$D3, "d3");
    resources.put(Constants.$NVD3, "nvd3");
    resources.put(Constants.$ANGULAR_NVD3, "angular-nvd3");
    context.getContextMap().put(Constants.RESOURCE, resources);
  }

  @Test
  public void testConfig() {
    assertThat(delegate).isNotNull();
  }

  @Test
  @Ignore
  public void testHandle() throws Exception {
    delegate.handle(context, new TestChartRequest());
    assertThat(context.getContextMap().containsKey(ChartBuilderContext.MAIN_TEMPLATE_FILE_KEY)).isTrue();
    String mainTemplatePath = context.getContextMapValue(ChartBuilderContext.MAIN_TEMPLATE_FILE_KEY, String.class);
    File input = new File(mainTemplatePath);
    Document document = Jsoup.parse(input, "UTF-8");
    assertThat(document).isNotNull();

    Element nvd3CssElement = document.getElementById("nvd3css");
    assertThat(nvd3CssElement.data()).contains("nvd3css");

    Element bootstrapCssElement = document.getElementById("bootstrap");
    assertThat(bootstrapCssElement.data()).contains("bootstrap");

    Element baseScriptsElement = document.getElementById("baseScripts");
    assertThat(baseScriptsElement.data()).contains("es5shim");
    assertThat(baseScriptsElement.data()).contains("angularjs");
    assertThat(baseScriptsElement.data()).contains("d3");
    assertThat(baseScriptsElement.data()).contains("nvd3");
    assertThat(baseScriptsElement.data()).contains("angular-nvd3");
  }

  @Test
  @Ignore
  public void testHandleNoBootstrap() throws Exception {
    resources.remove(Constants.$BOOTSTRAP);
    delegate.handle(context, new TestChartRequest());
    assertThat(context.getContextMap().containsKey(ChartBuilderContext.MAIN_TEMPLATE_FILE_KEY)).isTrue();
    String mainTemplatePath = context.getContextMapValue(ChartBuilderContext.MAIN_TEMPLATE_FILE_KEY, String.class);
    File input = new File(mainTemplatePath);
    Document document = Jsoup.parse(input, "UTF-8");
    assertThat(document).isNotNull();

    Element nvd3CssElement = document.getElementById("nvd3css");
    assertThat(nvd3CssElement.data()).contains("nvd3css");

    Element bootstrapCssElement = document.getElementById("bootstrap");
    assertThat(bootstrapCssElement).isNull();

    Element baseScriptsElement = document.getElementById("baseScripts");
    assertThat(baseScriptsElement.data()).contains("es5shim");
    assertThat(baseScriptsElement.data()).contains("angularjs");
    assertThat(baseScriptsElement.data()).contains("d3");
    assertThat(baseScriptsElement.data()).contains("nvd3");
    assertThat(baseScriptsElement.data()).contains("angular-nvd3");
  }
}