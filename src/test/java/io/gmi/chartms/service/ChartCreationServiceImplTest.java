package io.gmi.chartms.service;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import io.gmi.chartms.TestUtils;
import io.gmi.chartms.api.CreateChartRequest;
import io.gmi.chartms.config.AppConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.annotation.DirtiesContext;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ChartCreationServiceImplTest extends ChartCreateContextAwareTest {

  @Autowired
  AppConfig appConfig;

  @Autowired
  ResourceLoader resourceLoader;

  CreateChartRequest request;

  @Autowired
  ChartCreationServiceImpl chartCreationService;

  @Before
  public void setUp() throws Exception {
    super.setUp();
    request = new CreateChartRequest();
    request.setHtml(TestUtils.HTML);
    request.setChart(TestUtils.CHART);
    request.setUseBootstrap(true);
    request.setClippingDiv("test");
    request.setChartContainerId("chart");
  }

  @Test
  @Ignore
  public void testCreateChartImage() throws Exception{
    ChartCreationContext context = new ChartCreationContext(contextId, request);
    String results = chartCreationService.createChartImage(context);
    assertThat(results).isNotNull();
    File savedImage = new File(results);
    assertThat(savedImage.exists()).isTrue();
    ByteSource savedImageByteSource = Files.asByteSource(savedImage);

    Resource expected = resourceLoader.getResource("classpath:/expected.png");
    ByteSource expectedByteSource = Files.asByteSource(expected.getFile());

    assertThat(expectedByteSource.contentEquals(savedImageByteSource)).isTrue();

  }

}