/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.gmi.chart.api;

import io.gmi.chart.dto.PingResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = UrlConstants.PING)
public class PingController {

  private static final Logger log = LoggerFactory.getLogger(PingController.class);

  @RequestMapping(value = UrlConstants.ASYNC, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Callable<PingResponseDto> pingAsync() {
    return () -> {
      PingResponseDto responseDto = new PingResponseDto();
      responseDto.setStatus("ok");
      return responseDto;
    };
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public PingResponseDto ping() {
    PingResponseDto responseDto = new PingResponseDto();
    responseDto.setStatus("ok");
    return responseDto;
  }

}
