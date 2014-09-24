package io.gmi.chart.builder;

import io.gmi.chart.Application;
import io.gmi.chart.ChartMSConfiguration;
import io.gmi.chart.Constants;
import io.gmi.chart.TestChartRequest;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ScriptStylesDelegateTest {

  @Autowired
  @Qualifier("scriptStylesDelegate")
  ScriptStylesDelegate delegate;

  @Autowired
  ChartMSConfiguration configuration;

  ChartBuilderContext context;

  @Before
  public void setUp() throws Exception {
    context = new ChartBuilderContext(configuration);
  }

  @Test
  public void testHandle() throws Exception {
    delegate.handle(context, new TestChartRequest());
    Map<String, String> results = (Map<String, String>) context.getContextMap().get(Constants.RESOURCE);
    assertThat(results).isNotNull();
    assertThat(results.size()).isEqualTo(7);
    results.forEach((k, v) -> assertThat(StringUtils.isEmpty(v)).isFalse());
  }
}