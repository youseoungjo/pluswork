package com.bplus.pw.af.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.af.service.AF0401Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : AF0401Controller
 * Description : 종류 등록/수정/삭제를 위한 controller 클래스
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
@RequestMapping("/pw/af/*")
public class AF0401Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(AF0401Controller.class);

	@Autowired
	private AF0401Service service;

	/**
	 * <pre>
	 *  종류 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveAF0401Tree.*")
	public void retrieveAF0401Tree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> af0401List = service.retrieveAF0401Tree(map);
		response.setList("ds_AF0401Tree", af0401List);

	}

	/**
	 * <pre>
	 *  종류 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveAF0401List.*")
	public void retrieveAF0401List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> af0401List = service.retrieveAF0401List(map);
		response.setList("ds_AF0401List", af0401List);
	}

	/**
	 * <pre>
	 *  종류 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveAF0401Flow.*")
	public void retrieveAF0401Flow(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> af0401List = service.retrieveAF0401Flow(map);
		response.setList("ds_AF0401Flow", af0401List);
	}

	/**
	 * <pre>
	 *  종류 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveAF0401Line.*")
	public void retrieveAF0401Line(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> af0401List = service.retrieveAF0401Line(map);
		response.setList("ds_AF0401Line", af0401List);
	}


	/**
	 * <pre>
	 *  종류 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveAF0401.*")
	public void saveAF0401(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> af0401Flow= request.getGridData("ds_AF0401Flow", HashMap.class); //종류 정보;
		GridData<HashMap> af0401Line= request.getGridData("ds_AF0401Line", HashMap.class); //종류 정보;

		service.saveEqProcessAll(af0401Flow,af0401Line);
	}

}
