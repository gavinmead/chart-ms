package io.gmi.chartms.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.gmi.chartms.ChartMSApplication;
import io.gmi.chartms.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChartMSApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@IntegrationTest("server.port=0")
public class ChartControllerTest {

  private static final Logger log = LoggerFactory.getLogger(ChartControllerTest.class);

  private static final String FORMAT = "http://localhost:%d/api/chart";

  RestTemplate restTemplate = new TestRestTemplate();

  @Autowired
  ResourceLoader resourceLoader;

  @Value("${local.server.port}")
  int port;

  @Autowired
  ObjectMapper objectMapper;

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void testCreateChart() throws Exception {
    CreateChartRequest request = new CreateChartRequest();
    request.setHtml(TestUtils.HTML);
    request.setChart(TestUtils.CHART);
    request.setUseBootstrap(true);
    request.setClippingDiv("test");
    request.setChartContainerId("chart");

    String toJson = objectMapper.writeValueAsString(request);

    //Sanity check
    CreateChartRequest fromJson = objectMapper.readValue(toJson, CreateChartRequest.class);

    log.info("JSON Request: \n{}\n", toJson);
    String url = String.format(FORMAT, port);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    requestHeaders.setAccept(Lists.newArrayList(MediaType.IMAGE_PNG));
    HttpEntity<String> requestEntity = new HttpEntity<>(toJson, requestHeaders);
    ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
    assertThat(responseEntity.getBody()).isNotNull();
  }
}