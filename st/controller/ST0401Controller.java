package com.bplus.pw.st.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.st.service.ST0401Service;

import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : ST0401Controller
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
public class ST0401Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(ST0401Controller.class);

	@Autowired
	private ST0401Service service;

	/**
	 * <pre>
	 *  작업요청서 추이 분석 차트 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveST0401Chart.*")
	public void retrieveST0401Chart(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST0401ListChart = service.retrieveST0401Chart(map);
		response.setList("ds_ST0401ListChart", ds_ST0401ListChart);
	}

	@RequestMapping("retrieveST0401List.*")
	public void retrieveST0401List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST0401List = service.retrieveST0401List(map);
		response.setList("ds_ST0401List", ds_ST0401List);
	}

	@RequestMapping("retrieveST0401SubList.*")
	public void retrieveST0401SubList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST0401SubList = service.retrieveST0401SubList(map);
		response.setList("ds_ST0401SubList", ds_ST0401SubList);
	}

}
