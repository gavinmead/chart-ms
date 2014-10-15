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

import com.google.common.collect.Lists;
import io.gmi.chartms.Application;
import io.gmi.chartms.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class DirectoryMonitorServiceTest {

  @Autowired
  DirectoryMonitorService directoryMonitorService;

  @Autowired
  AppConfig appConfig;

  String preserveHtml;
  String deleteHtml;
  String preserveImage;
  String deleteImage;

  @Before
  public void setUp() throws Exception {
    //Create some temp files.
    ZonedDateTime now = ZonedDateTime.now();
    ZonedDateTime exceeded = now.minusMinutes(appConfig.getDirectoryMonitor().getFileLifetime() + 1);

    preserveHtml = appConfig.getHtmlDir() + File.separator + "do-not-delete.html";
    deleteHtml = appConfig.getHtmlDir() + File.separator + "delete.html";

    preserveImage = appConfig.getImageDir() + File.separator + "do-not-delete.png";
    deleteImage = appConfig.getImageDir() + File.separator + "delete.png";

    Path preservedHtmlFilePath = Files.createFile(Paths.get(preserveHtml));
    Path deleteHtmlFilePath = Files.createFile(Paths.get(deleteHtml));
    Path preserveImageFilePath = Files.createFile(Paths.get(preserveImage));
    Path deleteImageFilePath = Files.createFile(Paths.get(deleteImage));

    BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(deleteHtmlFilePath, BasicFileAttributeView.class);
    Instant exceededInstance = exceeded.toInstant();
    basicFileAttributeView.setTimes(FileTime.from(exceededInstance), FileTime.from(exceededInstance), FileTime.from(exceededInstance));

    basicFileAttributeView = Files.getFileAttributeView(deleteImageFilePath, BasicFileAttributeView.class);
    basicFileAttributeView.setTimes(FileTime.from(exceededInstance), FileTime.from(exceededInstance), FileTime.from(exceededInstance));

  }

  @Test
  public void testCleanUp() throws Exception {
    directoryMonitorService.cleanUp();
    List<String> savedFiles = Lists.newArrayList(preserveHtml, preserveImage);
    List<String> deletedfiles = Lists.newArrayList(deleteHtml, deleteImage);

    savedFiles.forEach(f -> {
      Path current = Paths.get(f);
      assertThat(current.toFile().exists()).isTrue();
    });

    deletedfiles.forEach(f -> {
      Path current = Paths.get(f);
      assertThat(current.toFile().exists()).isFalse();
    });

  }

}