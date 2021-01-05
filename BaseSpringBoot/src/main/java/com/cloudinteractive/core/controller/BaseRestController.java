package com.cloudinteractive.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;

public class BaseRestController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected Environment env;
}
