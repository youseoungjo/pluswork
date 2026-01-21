package com.bplus.pw.st.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.st.service.ST0101Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : ST0101Controller
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
public class ST0101Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(ST0101Controller.class);

	@Autowired
	private ST0101Service service;

	/**
	 * <pre>
	 *  점검데이터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveST0101Chart.*")
	public void retrieveST0101Chart(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST0101ListChart = service.retrieveST0101Chart(map);
		response.setList("ds_ST0101ListChart", ds_ST0101ListChart);
	}

	@RequestMapping("retrieveST0101List.*")
	public void retrieveST0101List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST0101List = service.retrieveST0101List(map);
		response.setList("ds_ST0101List", ds_ST0101List);
	}

	@RequestMapping("retrieveST0101SubList.*")
	public void retrieveST0101SubList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST0101SubList = service.retrieveST0101SubList(map);
		response.setList("ds_ST0101SubList", ds_ST0101SubList);
	}
}
