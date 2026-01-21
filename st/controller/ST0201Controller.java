package com.bplus.pw.st.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.st.service.ST0201Service;

import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : ST0201Controller
 * Description : 작업요청서 추이 분석을 위한 controller 클래스
 * Author : jit13.
 * Created Date : 2020-04-13
 * History
 * ---------------------------------------------------------------
 * Updated Date          Name    Reason
 * ---------------------------------------------------------------
 *
 * ---------------------------------------------------------------
 * </pre>
 * @author jun
 * @version 1.0
 */
@Controller
@RequestMapping("/pw/st/*")
public class ST0201Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(ST0201Controller.class);

	@Autowired
	private ST0201Service service;

	/**
	 * <pre>
	 *  작업요청서 추이 분석 차트 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveST0201Chart.*")
	public void retrieveST0201Chart(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST0201ListChart = service.retrieveST0201Chart(map);
		response.setList("ds_ST0201ListChart", ds_ST0201ListChart);
	}

	@RequestMapping("retrieveST0201List.*")
	public void retrieveST0201List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST0201List = service.retrieveST0201List(map);
		response.setList("ds_ST0201List", ds_ST0201List);
	}

	@RequestMapping("retrieveST0201SubList.*")
	public void retrieveST0201SubList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_St0201SubList = service.retrieveST0201SubList(map);
		response.setList("ds_St0201SubList", ds_St0201SubList);
	}

}
