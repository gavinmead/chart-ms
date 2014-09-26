package io.gmi.chart.builder;

import com.google.common.collect.Lists;
import io.gmi.chart.ChartMSConfiguration;
import io.gmi.chart.requests.ChartRequest;
import io.gmi.chart.requests.LineChartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gmead on 9/26/14.
 */
@Component
public class DefaultChartBuilderFactory implements ChartBuilderFactory,
    ApplicationContextAware, InitializingBean {

  private Map<String, List<ChartBuilderDelegate>> chartDelegateBuilders;

  private static final Logger log = LoggerFactory.getLogger(DefaultChartBuilderFactory.class);

  @Autowired
  BuilderConfig builderConfig;

  @Autowired
  ChartMSConfiguration configuration;

  ApplicationContext applicationContext;

  @Override
  public ChartBuilder getChartBuilder(ChartRequest chartRequest) {
    if(!chartDelegateBuilders.containsKey(chartRequest.getType())) {
      throw new IllegalArgumentException("No builder available for chart " + chartRequest.getType());
    }
    return new DelegatingChartBuilder(chartDelegateBuilders.get(chartRequest.getType()), configuration);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    chartDelegateBuilders = new HashMap<>();
    chartDelegateBuilders.put(LineChartRequest.chartType, createLineChartDelegates());
    log.info("Chart Delegate Builders per request type\n{}", chartDelegateBuilders);
  }

  private List<ChartBuilderDelegate> requiredPreChartDelegates() {
    return Lists.newArrayList(builderConfig.scriptStylesDelegate(), builderConfig.imageProcessingDelegate()
        , builderConfig.mainTemplateDelegate());
  }

  private List<ChartBuilderDelegate> createLineChartDelegates() {
    List<ChartBuilderDelegate> chartBuilderDelegates = requiredPreChartDelegates();
    chartBuilderDelegates.add(builderConfig.lineChartContentDelegate());
    chartBuilderDelegates.addAll(requiredPostChartDelegates());
    return chartBuilderDelegates;
  }

  private List<ChartBuilderDelegate> requiredPostChartDelegates() {
    return Lists.newArrayList(builderConfig.requestTemplateDelegate(), builderConfig.mergeContentDelegate(),
        builderConfig.phantomJsInvokerDelegate(), builderConfig.renderedImageWriterDelegate());
  }
}
