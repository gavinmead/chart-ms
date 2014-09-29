/**
 Copyright 2014 the original author or authors.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package io.gmi.chart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by gmead on 8/21/2014.
 */
@ContextConfiguration
@EnableAutoConfiguration(exclude = {VelocityAutoConfiguration.class})
@ComponentScan
public class Application implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @Autowired
  ResourceLoader resourceLoader;

  @Autowired
  ChartMSConfiguration configuration;

  @Value("${version}")
  private String version;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Version=" + version);

  }

  @PostConstruct
  void setupWorkingDir() throws Exception {
    log.info("Creating working directory...");
    Path userDir = Paths.get(System.getProperty("user.dir") + File.separator + "working");
    if(userDir.toFile().exists()) {
      log.info("Deleting existing working directory");
      Files.walkFileTree(userDir, new SimpleFileVisitor<Path>() {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
          Files.delete(file);
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
          Files.delete(dir);
          return FileVisitResult.CONTINUE;
        }
      });
    }

    Files.createDirectory(userDir);
    copyToWorkingDir(configuration.WORKING_DIRECTORY_PATH() + File.separator + "render-image.js", "classpath:render-image.js");
    copyToWorkingDir(configuration.NVD3_CSS_WORKING_PATH(), configuration.NVD3_CSS_PATH());
    copyToWorkingDir(configuration.BOOSTRAP_CSS_WORKING_PATH(), configuration.BOOTSTRAP_CSS_PATH());
    copyToWorkingDir(configuration.ES5_SHIM_SCRIPT_WORKING_PATH(), configuration.ES5_SHIM_SCRIPT_PATH());
    copyToWorkingDir(configuration.ANGULAR_SCRIPT_WORKING_PATH(), configuration.ANGULAR_SCRIPT_PATH());
    copyToWorkingDir(configuration.D3_SCRIPT_WORKING_PATH(), configuration.D3_SCRIPT_PATH());
    copyToWorkingDir(configuration.NVD3_SCRIPT_WORKING_PATH(), configuration.NVD3_SCRIPT_PATH());
    copyToWorkingDir(configuration.ANGULAR_NVD3_SCRIPT_WORKING_PATH(), configuration.ANGULAR_NVD3_SCRIPT_PATH());
  }

  private void copyToWorkingDir(String path, String resource) throws Exception {
    Path pathToUse = Paths.get(path);
    Resource resourceToUse = resourceLoader.getResource(resource);
    Files.copy(resourceToUse.getInputStream(), pathToUse);
  }

}
