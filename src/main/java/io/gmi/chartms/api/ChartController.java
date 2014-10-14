package io.gmi.chartms.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gmead on 10/14/14.
 */
@RequestMapping(value = UrlConstants.CHART)
public class ChartController {
  private static final Logger log = LoggerFactory.getLogger(ChartController.class);

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] createChart(@RequestBody CreateChartRequest request) {

    return null;
  }
}
