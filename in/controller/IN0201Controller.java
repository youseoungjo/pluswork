package com.bplus.pw.in.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.in.service.IN0201Service;
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
 * Program Name : IN0201Controller
 * Description : 설비별 점검데이터 검색를 위한 controller 클래스
 * Author : jit13.
 * Created Date : 2019-07-18
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
public class IN0201Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0201Controller.class);

	@Autowired
	private IN0201Service service;

	/**
	 * <pre>
	 *  점검데이터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0201.*")
	public void retrieveIN0201(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0201List = service.retrieveIN0201(map);
		response.setList("ds_IN0201List", ds_IN0201List);
	}

	/**
	 * <pre>
	 *  트리에서 점검데이터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0201ToTree.*")
	public void retrieveIN0201ToTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0201List = service.retrieveIN0201ToTree(map);
		response.setList("ds_IN0201List", ds_IN0201List);
	}

	/**
	 * <pre>
	 *  설비별 점검데이터 상세내역
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0201Sub.*")
	public void retrieveIN0201Sub(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0201Sub01List = service.retrieveIN0201Sub(map);
		response.setList("ds_IN0201Sub01List", ds_IN0201Sub01List);
	}
}
