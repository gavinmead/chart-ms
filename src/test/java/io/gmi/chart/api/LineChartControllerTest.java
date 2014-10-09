package io.gmi.chart.api;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import io.gmi.chart.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class LineChartControllerTest {

  private static final String REQUEST = "classpath:/request.json";

  private static final String FORMAT = "http://localhost:%d/api/chart/line";

  RestTemplate restTemplate = new TestRestTemplate();

  @Value("${local.server.port}")
  int port;

  @Autowired
  ResourceLoader resourceLoader;

  @Test
  public void testCreate() throws Exception {
    Resource resource = resourceLoader.getResource(REQUEST);
    String request = Files.toString(resource.getFile(), Charset.defaultCharset());
    assertThat(request).isNotNull();
    String url = String.format(FORMAT, port);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    requestHeaders.setAccept(Lists.newArrayList(MediaType.IMAGE_PNG));
    HttpEntity<String> requestEntity = new HttpEntity<>(request, requestHeaders);
    ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
    assertThat(responseEntity.getBody()).isNotNull();
  }
}