package io.gmi.chartms.api;

import com.google.common.io.Files;
import io.gmi.chartms.service.ChartCreationContext;
import io.gmi.chartms.service.ChartCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by gmead on 10/14/14.
 */
@RestController
@RequestMapping(value = UrlConstants.CHART)
public class ChartController {
  private static final Logger log = LoggerFactory.getLogger(ChartController.class);

  @Autowired
  ChartCreationService chartCreationService;

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] createChart(@RequestBody CreateChartRequest request, HttpServletRequest httpServletRequest) throws Exception {
    ChartCreationContext context = new ChartCreationContext((String) httpServletRequest.getAttribute(ContextIDInterceptor.CONTEXT_ID_KEY), request);
    String savedImage = chartCreationService.createChartImage(context);
    return Files.toByteArray(new File(savedImage));
  }
}
