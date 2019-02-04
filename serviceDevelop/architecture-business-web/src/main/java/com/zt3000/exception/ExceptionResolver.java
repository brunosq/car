package com.zt3000.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class ExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger log = Logger.getLogger(ExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		log.error("[Exception]", ex);
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		ResponseBody body = handlerMethod.getMethodAnnotation(ResponseBody.class);
		if (body == null) {
			return super.doResolveException(request, response, handlerMethod, ex);
		}
		ModelAndView mv = new ModelAndView();
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		try {
//			ex.printStackTrace();
			PrintWriter writer = response.getWriter();
			writer.write("{\"success\": false}");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mv;
	}

}
