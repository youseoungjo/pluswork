package com.bplus.pw.st.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.st.service.ST0301Service;

import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : ST0301Controller
 * Description : 점검데이터작성를 위한 controller 클래스
 * Author : jit13.
 * Created Date : 2019-07-12
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
public class ST0301Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(ST0301Controller.class);

	@Autowired
	private ST0301Service service;

	/**
	 * <pre>
	 *  점검데이터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveST0301Chart.*")
	public void retrieveST0301Chart(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST0301ListChart = service.retrieveST0301Chart(map);
		response.setList("ds_ST0301ListChart", ds_ST0301ListChart);
	}

	@RequestMapping("retrieveST0301List.*")
	public void retrieveST0301List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST0301List = service.retrieveST0301List(map);
		response.setList("ds_ST0301List", ds_ST0301List);
	}

	@RequestMapping("retrieveST0301SubList.*")
	public void retrieveST0301SubList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST0301SubList = service.retrieveST0301SubList(map);
		response.setList("ds_ST0301SubList", ds_ST0301SubList);
	}
}
