package io.gmi.chart.builder;

import com.google.common.base.Preconditions;
import io.gmi.chart.ChartBuilderException;
import io.gmi.chart.ChartMSConfiguration;
import io.gmi.chart.requests.ChartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Created by gmead on 9/24/14.
 */
public class DelegatingChartBuilder implements ChartBuilder {

  private static final Logger log = LoggerFactory.getLogger(DelegatingChartBuilder.class);

  private Collection<ChartBuilderDelegate> chartBuilderDelegates;

  private ChartMSConfiguration configuration;

  public DelegatingChartBuilder(Collection<ChartBuilderDelegate> chartBuilderDelegates, ChartMSConfiguration configuration) {
    this.chartBuilderDelegates = chartBuilderDelegates;
    this.configuration = configuration;
  }

  @Override
  public ChartBuilderResult build(final ChartRequest chartRequest) {
    Preconditions.checkNotNull(chartRequest);
    final ChartBuilderContext chartBuilderContext = new ChartBuilderContext(configuration);
    log.info("Running chart request {} through {} delegates with context {}"
        , chartRequest, chartBuilderDelegates.size(), chartBuilderContext.getContextId());
    for(ChartBuilderDelegate d : chartBuilderDelegates) {
      log.info("Running delegate {} on context {}", d.getClass().getSimpleName()
          , chartBuilderContext.getContextId());
      try {
        d.handle(chartBuilderContext, chartRequest);
      } catch (Exception e) {
        chartBuilderContext.getContextMap().put(ChartBuilderContext.HAS_ERROR_KEY, Boolean.valueOf(true));
        chartBuilderContext.getContextMap().put("error", e);
        chartBuilderContext.getContextMap().put("error.source", d);
        break;
      }
    }

    if(!chartBuilderContext.hasError()) {
      if(chartBuilderContext.hasImageResult()) {
        return new ChartBuilderResult(chartBuilderContext.getContextMapValue(ChartBuilderContext.RENDERED_CHART_KEY, byte[].class));
      } else {
        throw new ChartBuilderException("No delegate provided an image to the chart builder context.");
      }
    } else {
      ChartBuilderDelegate problemDelegate = chartBuilderContext.getContextMapValue("error.source", ChartBuilderDelegate.class);
      Exception e = chartBuilderContext.getContextMapValue("error", Exception.class);
      log.error("Context {} had an error on delegate {}", chartBuilderContext.getContextId()
          , problemDelegate.getClass().getSimpleName());
      throw new ChartBuilderException(e);
    }
  }

}
