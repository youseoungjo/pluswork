package com.bplus.pw.in.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.in.service.IN0701Service;
import com.bplus.wp.wk.service.WkInfoService;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : IN0701Controller
 * Description : 미점검 리스트 검색를 위한 controller 클래스
 * Author : jit13.
 * Created Date : 2019-07-19
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
@RequestMapping("/pw/in/*")
public class IN0701Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0701Controller.class);

	@Autowired
	private IN0701Service service;

	/**
	 * <pre>
	 *  미점검 리스트 검색
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0701.*")
	public void retrieveIN0701(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0701List = service.retrieveIN0701(map);
		response.setList("ds_IN0701List", ds_IN0701List);
	}

	/**
	 * <pre>
	 *  트리에서 미점검 리스트 검색
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0701ToTree.*")
	public void retrieveIN0701ToTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0701List = service.retrieveIN0701ToTree(map);
		response.setList("ds_IN0701List", ds_IN0701List);
	}

	/**
	 * <pre>
	 *  점검계획일자 재지정
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateIN0701List.*")
	public void updateIN0701List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.updateIN0701List(map);
	}

	/**
	 * <pre>
	 *  미점검확인
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateIN0701List2.*")
	public void updateIN0701List2(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.updateIN0701List2(map);
	}

}
