package com.zt3000.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private static final long serialVersionUID = -8955001188163866079L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	private int pageSize = 15;

	private int pageIndex = 1;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		String t = request.getParameter("pageIndex");
		if (!StringUtils.isEmpty(t)) {
			pageIndex = Integer.parseInt(t);
		}
		return pageIndex;
	}
}
