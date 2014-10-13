package io.gmi.chartms.api;

import io.gmi.chartms.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


@RestController
@RequestMapping(value = UrlConstants.PING)
public class PingController {

  @Autowired
  PingService pingService;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<PingResponse> ping() {
    final DeferredResult<PingResponse> response = new DeferredResult<>();

    pingService.ping().thenAccept((result) -> response.setResult(result));

    return response;
  }
}