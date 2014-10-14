package io.gmi.chartms.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by gmead on 10/14/14.
 */
@Component
public class DefaultContextIdGeneratorImpl implements ContextIdGenerator {
  @Override
  public String generate() {
    return UUID.randomUUID().toString();
  }
}
