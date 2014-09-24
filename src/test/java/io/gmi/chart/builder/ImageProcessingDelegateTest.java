package io.gmi.chart.builder;

import com.google.common.collect.Lists;
import io.gmi.chart.TestChartRequest;
import io.gmi.chart.domain.Image;
import io.gmi.chart.requests.ChartRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageProcessingDelegateTest {

  private ImageProcessingDelegate delegate;
  private ChartBuilderContext context;

  @Before
  public void setUp() throws Exception {
    delegate = new ImageProcessingDelegate();
    context = new ChartBuilderContext();
  }

  @Test
  public void testHandle() throws Exception {
    Image i1 = new Image();
    i1.setKey("1");
    i1.setContent("ABCD");
    Image i2 = new Image();
    i2.setKey("2");
    i2.setContent("DCBA");

    List<Image> imageList = Lists.newArrayList(i1, i2);
    ChartRequest chartRequest = new TestChartRequest();
    chartRequest.setImages(imageList);
    delegate.handle(context, chartRequest);
    Map<String, String> imageMap = context.getContextMapValue("IMAGES", Map.class);
    assertThat(imageMap).hasSize(2);
    assertThat(imageMap.get("1")).isEqualTo("data:image/png;base64, ABCD");
    assertThat(imageMap.get("2")).isEqualTo("data:image/png;base64, DCBA");
  }
}