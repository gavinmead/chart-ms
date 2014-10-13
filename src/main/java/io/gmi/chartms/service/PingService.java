package io.gmi.chartms.service;

import io.gmi.chartms.api.PingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PingService {

  private static final Logger logger  = LoggerFactory.getLogger(PingService.class);

  public CompletableFuture<PingResponse> ping() {
    return CompletableFuture.supplyAsync(() -> {
      logger.info("Ping Request Received");
      PingResponse response = new PingResponse();
      response.setStatus("ok");
      return response;
    });
  }
}