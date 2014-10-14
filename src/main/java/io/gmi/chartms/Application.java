package io.gmi.chartms;

import com.google.common.collect.Lists;
import io.gmi.chartms.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * Created by gmead on 10/13/14.
 */
@ComponentScan
@ContextConfiguration
@EnableAutoConfiguration
public class Application {

  private final Logger log = LoggerFactory.getLogger(Application.class);

  @Autowired
  AppConfig appConfig;

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setShowBanner(false);
    app.run(args);
  }

  @PostConstruct
  public void setupApplication() throws Exception {
    log.info("Creating application work directories");
    Path workingDir = Paths.get(appConfig.getWorkingDir());
    if(workingDir.toFile().exists()) {
      log.info("Deleting existing working directory");
      Files.walkFileTree(workingDir, new SimpleFileVisitor<Path>() {
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
    com.google.common.io.Files.createParentDirs(workingDir.toFile());
    Files.createDirectory(workingDir);

    List<Path> pathsToCreate = Lists.newArrayList(Paths.get(appConfig.getImageDir()), Paths.get(appConfig.getHtmlDir()));
    pathsToCreate.forEach(path -> {
      try {
        Files.createDirectory(path);
        log.debug("Directory {} created", path);
      } catch (Exception e) {
        throw new RuntimeException("Error creating work subdirectories", e);
      }
    });

  }

}
