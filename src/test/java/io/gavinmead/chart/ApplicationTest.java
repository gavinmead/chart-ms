package io.gavinmead.chart;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.OutputCapture;

import static org.junit.Assert.assertTrue;

public class ApplicationTest {

  @Rule
  public OutputCapture outputCapture = new OutputCapture();

  @Test
  public void testAppStartNoError() throws Exception {
    Application.main(new String[0]);
    String output = this.outputCapture.toString();
    assertTrue(output.contains("Version=1.0.0-SNAPSHOT"));
  }
}