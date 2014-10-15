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

package io.gmi.chartms.service;

import com.google.common.annotations.VisibleForTesting;
import io.gmi.chartms.FileUtils;
import io.gmi.chartms.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@EnableScheduling
@Component
public class DirectoryMonitorService {

  private static final Logger log = LoggerFactory.getLogger(DirectoryMonitorService.class);

  @Autowired
  AppConfig appConfig;

  @Scheduled(fixedDelayString = "${app.directoryMonitor.delay}")
  public void cleanUp() {
    try {
      log.debug("Invoking working directory cleanup.");
      FileUtils.processFiles(appConfig.getHtmlDir(), this::deleteOldFile);
      FileUtils.processFiles(appConfig.getImageDir(), this::deleteOldFile);
    } catch (Exception e) {
      log.warn("An error occurred during directory cleanup", e);
    }
  }

  @VisibleForTesting
  protected void deleteOldFile(Path file, BasicFileAttributes attr) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime fileTime = LocalDateTime.ofInstant(attr.creationTime().toInstant(), ZoneId.systemDefault());
    //Get the difference between now and file time
    LocalDateTime offset = fileTime.plusMinutes(appConfig.getDirectoryMonitor().getFileLifetime());
    //More than configured lifetime has passed.  Deleting the file
    if(offset.isBefore(now)) {
      try {
        log.info("File {} creation time {} has exceeded threshold of {} minutes",
            file, fileTime.format(DateTimeFormatter.ISO_TIME), appConfig.getDirectoryMonitor().getFileLifetime());
        Files.delete(file);
      } catch (IOException e) {
        log.warn("An error occurred while trying to delete file " + file, e);
      }
    }
  }
}
