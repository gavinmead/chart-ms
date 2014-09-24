package io.gmi.chart.builder;

import io.gmi.chart.Application;
import io.gmi.chart.ChartMSConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ChartBuilderContextTest {

  @Autowired
  private ChartMSConfiguration configuration;
  private ChartBuilderContext context;

  @Before
  public void setUp() throws Exception {
    context = new ChartBuilderContext(configuration);
  }

  @Test
  public void testGetContextMapValue() throws Exception {
    context.getContextMap().put("test", Boolean.valueOf(true));
    assertThat(context.getContextMapValue("test", Boolean.class)).isTrue();
  }

  @Test
  public void testGetContextMapOnMissing() throws Exception {
    boolean hasError = false;
    try {
      context.getContextMapValue("not.found", Boolean.class, this::onError);
      fail("");
    } catch (RuntimeException re) {
      assertThat(re).hasMessage("Error");
      hasError = true;
    }
    assertThat(hasError).isTrue();
  }

  @Test
  public void testHasErrorNotPresent() {
    assertThat(context.hasError()).isFalse();
  }

  @Test
  public void testHasErrorIsPresent() {
    context.getContextMap().put(ChartBuilderContext.HAS_ERROR_KEY, Boolean.valueOf(true));
    assertThat(context.hasError()).isTrue();
  }

  @Test
  public void testHasErrorIsPresentButFalse() {
    context.getContextMap().put(ChartBuilderContext.HAS_ERROR_KEY, Boolean.valueOf(false));
    assertThat(context.hasError()).isFalse();
  }

  public void onError(String key) {
    throw new RuntimeException("Error");
  }
}