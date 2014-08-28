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

package io.gmi.chart.builder;

import com.google.common.io.Files;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Takes a file and streams the content to a start.
 */
public class FileToStringDelegate {

  private static final Logger log = LoggerFactory.getLogger(FileToStringDelegate.class);
  private ListeningExecutorService listeningExecutorService;

  public FileToStringDelegate(ListeningExecutorService listeningExecutorService) {
    this.listeningExecutorService = listeningExecutorService;
  }

  public ListenableFuture<String> processFile(final File inputFile) {
    return listeningExecutorService.submit(() -> Files.toString(inputFile, Charset.defaultCharset()));
  }

}