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
    Path scriptFile = Paths.get(userDir.toString() + File.separator + "render-image.js");
    Resource renderScript = resourceLoader.getResource("classpath:render-image.js");
    Files.copy(renderScript.getInputStream(), scriptFile);
  }


}
