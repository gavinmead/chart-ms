package io.gmi.chartms.api;

import io.gmi.chartms.service.ContextIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gmead on 10/14/14.
 */
@Component
public class ContextIDInterceptor extends HandlerInterceptorAdapter {

  public static final String CONTEXT_ID_KEY = "context.id";
  private static final String MDC_CONTEXT_KEY = "contextId";

  private static final Logger log = LoggerFactory.getLogger(ContextIDInterceptor.class);

  @Autowired
  ContextIdGenerator contextIdGenerator;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    super.preHandle(request, response, handler);
    if(request.getAttribute(CONTEXT_ID_KEY) != null) {
      MDC.put(MDC_CONTEXT_KEY, (String) request.getAttribute(CONTEXT_ID_KEY));
    } else {
      String contextId = contextIdGenerator.generate();
      request.setAttribute(CONTEXT_ID_KEY, contextId);
      MDC.put(MDC_CONTEXT_KEY, contextId);
      log.info("Starting request {}", MDC.get(MDC_CONTEXT_KEY));
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    super.postHandle(request, response, handler, modelAndView);
    MDC.remove(MDC_CONTEXT_KEY);

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    super.afterCompletion(request, response, handler, ex);
    log.info("Ending request {}", request.getAttribute(CONTEXT_ID_KEY));
    MDC.remove(MDC_CONTEXT_KEY);
  }

  @Override
  public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    super.afterConcurrentHandlingStarted(request, response, handler);
    MDC.remove(MDC_CONTEXT_KEY);
  }
}
