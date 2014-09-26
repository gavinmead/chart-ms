package io.gmi.chart.builder;

import io.gmi.chart.Application;
import io.gmi.chart.requests.LineChartRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class DefaultChartBuilderFactoryTest {

  @Autowired
  private ChartBuilderFactory factory;

  @Test
  public void testGetLineChartBuilder() throws Exception {
    ChartBuilder chartBuilder = factory.getChartBuilder(new LineChartRequest());
    assertThat(chartBuilder).isNotNull();
    assertThat(chartBuilder).isInstanceOf(DelegatingChartBuilder.class);

    DelegatingChartBuilder delegatingChartBuilder = (DelegatingChartBuilder) chartBuilder;
    assertThat(delegatingChartBuilder.getChartBuilderDelegates()).hasSize(8);

  }
}