package io.gmi.chart.builder;

import com.google.common.collect.Lists;
import io.gmi.chart.Application;
import io.gmi.chart.ChartMSConfiguration;
import io.gmi.chart.domain.ChartOptions;
import io.gmi.chart.domain.LineChartDataPoint;
import io.gmi.chart.domain.LineChartSeries;
import io.gmi.chart.requests.LineChartRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class LineChartContentDelegateTest {

  private static final Logger log = LoggerFactory.getLogger(LineChartContentDelegateTest.class);

  private static final String EXPECTED_OPTIONS = "{ type: \'lineChart\'}";
  private static final String EXPECTED_SERIES1_PUSH1 = "content1.push({x: 1, y: 1})";
  private static final String EXPECTED_SERIES1_PUSH2 = "content1.push({x: 2, y: 2})";
  private static final String EXPECTED_SERIES2_PUSH1 = "content2.push({x: 3, y: 3})";
  private static final String EXPECTED_SERIES2_PUSH2 = "content2.push({x: 4, y: 4})";
  private static final String EXPECTED_LIST_1 = "seriesList.push({values: content1,key: \'Series 1\', color: \'red\'})";
  private static final String EXPECTED_LIST_2 = "seriesList.push({values: content2,key: \'Series 2\', color: \'blue\'})";


  @Autowired
  @Qualifier("lineChartContentDelegate")
  private ChartBuilderDelegate delegate;

  @Autowired
  ChartMSConfiguration configuration;

  private ChartBuilderContext context;
  private LineChartRequest request;

  @Before
  public void setUp() throws Exception {
    context = new ChartBuilderContext(configuration);
    request = new LineChartRequest();

    ChartOptions options = new ChartOptions();
    options.setOptions(EXPECTED_OPTIONS);
    request.setChartOptions(options);

    LineChartSeries series1 = new LineChartSeries();
    series1.setColor("red");
    series1.setKey("Series 1");
    LineChartDataPoint dataPoint1 = new LineChartDataPoint();
    dataPoint1.setX(1);
    dataPoint1.setY(1);
    LineChartDataPoint dataPoint2 = new LineChartDataPoint();
    dataPoint2.setX(2);
    dataPoint2.setY(2);
    series1.setValues(Lists.newArrayList(dataPoint1, dataPoint2));

    LineChartDataPoint dataPoint3 = new LineChartDataPoint();
    dataPoint3.setX(3);
    dataPoint3.setY(3);
    LineChartDataPoint dataPoint4 = new LineChartDataPoint();
    dataPoint4.setX(4);
    dataPoint4.setY(4);
    series1.setValues(Lists.newArrayList(dataPoint1, dataPoint2));

    LineChartSeries series2 = new LineChartSeries();
    series2.setColor("blue");
    series2.setKey("Series 2");
    series2.setValues(Lists.newArrayList(dataPoint3, dataPoint4));

    request.setData(Lists.newArrayList(series1, series2));
  }

  @Test
  public void testHandle() throws Exception {
    delegate.handle(context, request);
    assertThat(context.getContextMap().containsKey(ChartBuilderContext.JS_APPLICATION_KEY)).isTrue();
    String application = context.getContextMapValue(ChartBuilderContext.JS_APPLICATION_KEY, String.class);
    assertThat(application).contains(EXPECTED_OPTIONS);
    assertThat(application).contains(EXPECTED_SERIES1_PUSH1);
    assertThat(application).contains(EXPECTED_SERIES1_PUSH2);
    assertThat(application).contains(EXPECTED_SERIES2_PUSH1);
    assertThat(application).contains(EXPECTED_SERIES2_PUSH2);
    assertThat(application).contains(EXPECTED_LIST_1);
    assertThat(application).contains(EXPECTED_LIST_2);
    log.debug("app\n{}", application);
  }
}