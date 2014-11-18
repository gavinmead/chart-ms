package io.gmi.chartms.service;

import io.gmi.chartms.ChartMSApplication;
import io.gmi.chartms.api.ContextIDInterceptor;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by gmead on 10/14/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChartMSApplication.class)
@WebAppConfiguration
public abstract class ChartCreateContextAwareTest {

  protected String contextId;
  private MockHttpServletRequest mockHttpServletRequest;
  private MockHttpServletResponse mockHttpServletResponse;
  private Object handler;

  @Autowired
  ContextIDInterceptor contextIDInterceptor;

  @Before
  public void setUp() throws Exception {
    mockHttpServletRequest = new MockHttpServletRequest();
    mockHttpServletResponse = new MockHttpServletResponse();
    handler = new Object();
    contextIDInterceptor.preHandle(mockHttpServletRequest, mockHttpServletResponse, handler);
    contextId = (String) mockHttpServletRequest.getAttribute(ContextIDInterceptor.CONTEXT_ID_KEY);
  }

  @After
  public void tearDown() throws Exception {
    contextIDInterceptor.afterCompletion(mockHttpServletRequest, mockHttpServletResponse, handler, null);
  }
}
