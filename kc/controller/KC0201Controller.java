package com.bplus.pw.kc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.kc.service.KC0201Service;
import com.bplus.pw.wo.service.WO0301Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : WO0301Controller
 * Description : 기술문서 등록/수정/삭제를 위한 controller 클래스
 * Author : Jun.
 * Created Date : 2013-12-10
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
@RequestMapping("/pw/kc/*")
public class KC0201Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(KC0201Controller.class);

	@Autowired
	private KC0201Service service;

	/**
	 * <pre>
	 *  작업결과서 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0301List.*")
	public void retrieveWO0301List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> wo0301List = service.retrieveWO0301List(map);
		response.setList("ds_WO0301List", wo0301List);
	}

	/**
	 * <pre>
	 *  작업 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoRJobList.*")
	public void retrieveWoJobList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> jobList = service.retrieveWoRJobList(map);
		response.setList("ds_JobList", jobList);

	}

	/**
	 * <pre>
	 * 현상  목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoSymList.*")
	public void retrieveWoSymList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> symList = service.retrieveWoSymList(map);
		response.setList("ds_SymList", symList);

	}

	/**
	 * <pre>
	 * 조치  목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoResultList.*")
	public void retrieveWoResultList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> resultList = service.retrieveWoResultList(map);
		response.setList("ds_ResultList", resultList);

	}

	/**
	 * <pre>
	 *  작업결과서 상세 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0301Detail.*")
	public void retrieveWO0301Detail(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_WO0301Detail = service.retrieveWO0301Detail(map);
		response.setList("ds_WO0301Detail", ds_WO0301Detail);
	}

}
