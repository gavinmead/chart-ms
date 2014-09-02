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

public class ChartBuilderResult {

  private boolean hasError;
  private Throwable error;
  private byte[] result;

  ChartBuilderResult(byte[] result) {
    this(false, null, result);
  }

  ChartBuilderResult(boolean hasError, Throwable error) {
    this(hasError, error, null);
  }

  ChartBuilderResult(boolean hasError, Throwable error, byte[] result) {
    this.hasError = hasError;
    this.error = error;
    this.result = result;
  }

  public boolean isHasError() {
    return hasError;
  }

  public Throwable getError() {
    return error;
  }

  public byte[] getResult() {
    return result;
  }
}
