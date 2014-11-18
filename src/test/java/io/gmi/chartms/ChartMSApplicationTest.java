package io.gmi.chartms;

import io.gmi.chartms.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChartMSApplication.class)
@WebAppConfiguration
public class ChartMSApplicationTest {

  @Autowired
  AppConfig appConfig;

  @Test
  public void testSetupApplication() throws Exception {
    Path working = Paths.get(appConfig.getWorkingDir());
    File workingDir = working.toFile();
    assertThat(workingDir.exists()).isTrue();
    assertThat(workingDir.isDirectory());


    File bootstrap = new File(appConfig.getBootstrapCss());
    assertThat(bootstrap.isFile()).isTrue();
    assertThat(bootstrap.exists()).isTrue();

    File renderImageScript = new File(appConfig.getRenderImageScript());
    assertThat(renderImageScript.isFile()).isTrue();
    assertThat(renderImageScript.exists()).isTrue();
  }
}