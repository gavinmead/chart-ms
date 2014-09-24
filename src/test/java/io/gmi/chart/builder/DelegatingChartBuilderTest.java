package io.gmi.chart.builder;

import com.google.common.collect.Lists;
import io.gmi.chart.ChartBuilderException;
import io.gmi.chart.TestChartRequest;
import io.gmi.chart.requests.ChartRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class DelegatingChartBuilderTest {

  private ChartBuilderDelegate errorDelegate;
  private ChartBuilderDelegate imageProducingDelegate;
  private ChartBuilderDelegate noOpDelegate;

  private ChartRequest testRequest;

  @Before
  public void setUp() throws Exception {
    testRequest = new TestChartRequest();

    errorDelegate = mock(ChartBuilderDelegate.class);
    doThrow(new RuntimeException("Error")).when(errorDelegate).handle(any(ChartBuilderContext.class), eq(testRequest));

    imageProducingDelegate = mock(ChartBuilderDelegate.class);
    doAnswer(invocation -> {
      ChartBuilderContext context = (ChartBuilderContext) invocation.getArguments()[0];
      context.getContextMap().put(ChartBuilderContext.IMAGE_KEY, new byte[]{0, 1, 2});
      return null;
    }).when(imageProducingDelegate).handle(any(ChartBuilderContext.class), eq(testRequest));

    noOpDelegate = mock(ChartBuilderDelegate.class);
  }

  @Test
  public void testBuildNoErrors() throws Exception {
    List<ChartBuilderDelegate> delegates = Lists.newArrayList(noOpDelegate, imageProducingDelegate);
    DelegatingChartBuilder builder = new DelegatingChartBuilder(delegates, null);
    ChartBuilderResult result = builder.build(testRequest);
    assertThat(result).isNotNull();
    assertThat(result.getResult().length).isEqualTo(3);

    verify(noOpDelegate).handle(any(ChartBuilderContext.class), eq(testRequest));
    verify(imageProducingDelegate).handle(any(ChartBuilderContext.class), eq(testRequest));
  }

  @Test
  public void testNoImageProduced() throws Exception {
    List<ChartBuilderDelegate> delegates = Lists.newArrayList(noOpDelegate);
    DelegatingChartBuilder builder = new DelegatingChartBuilder(delegates, null);
    try {
      builder.build(testRequest);
      fail("");
    } catch (ChartBuilderException be) {
      assertThat(be).hasMessage("No delegate provided an image to the chart builder context.");
    } catch (Exception e) {
      fail("");
    }
  }

  @Test
  public void testErrorThrowingDelegate() throws Exception {
    List<ChartBuilderDelegate> delegates = Lists.newArrayList(noOpDelegate, errorDelegate, imageProducingDelegate);
    DelegatingChartBuilder builder = new DelegatingChartBuilder(delegates, null);
    try {
      builder.build(testRequest);
      fail("");
    } catch (ChartBuilderException be) {
      assertThat(be.getCause()).hasMessage("Error");
    } catch (Exception e) {
      fail("");
    }
    verifyZeroInteractions(imageProducingDelegate);
  }
}