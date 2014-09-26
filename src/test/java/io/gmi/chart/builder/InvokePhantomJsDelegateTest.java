package io.gmi.chart.builder;

import io.gmi.chart.Application;
import io.gmi.chart.ChartMSConfiguration;
import io.gmi.chart.requests.LineChartRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class InvokePhantomJsDelegateTest {

  private static final String TEST_FILE = "classpath:phantomjstest.html";

  @Autowired
  ResourceLoader resourceLoader;


  @Autowired
  @Qualifier("phantomJsInvokerDelegate")
  ChartBuilderDelegate delegate;

  ChartBuilderContext context;

  @Autowired
  ChartMSConfiguration configuration;

  LineChartRequest request = new LineChartRequest();

  @Before
  public void setUp() throws Exception {
    context = new ChartBuilderContext(configuration);
    Resource testFileResource = resourceLoader.getResource(TEST_FILE);
    String testFile = configuration.WORKING_DIRECTORY_PATH() + File.separator + "phantomjstest.html";
    Files.copy(testFileResource.getInputStream(), Paths.get(testFile), StandardCopyOption.REPLACE_EXISTING);
    context.getContextMap().put(ChartBuilderContext.FINAL_HTML_FILE_KEY, testFile);
    request.setClippingDivId("test");
  }

  @Test
  public void testHandle() throws Exception {
    delegate.handle(context, request);
    String expectedFile = context.getConfiguration().WORKING_DIRECTORY_PATH() + File.separator + context.getContextId() + ".png";
    Resource expectedFileResource = resourceLoader.getResource(expectedFile);
    assertThat(expectedFile).isNotNull();

  }
}