package com.cloudinteractive.core.filter;

import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

  @Override
  protected void beforeRequest(HttpServletRequest request, String message) {
    logger.debug(message);
  }

  @Override
  protected void afterRequest(HttpServletRequest request, String message) {
    logger.debug(message.replaceAll("\\r|\\n|\\t", ""));
  }

}
