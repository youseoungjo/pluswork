package com.bplus.pw.af.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.af.service.AF0201Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : AF0201Controller
 * Description : 작업요청 등록/수정/삭제를 위한 controller 클래스
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
public class AF0201Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(AF0201Controller.class);

	@Autowired
	private AF0201Service service;



	/**
	 * <pre>
	 *  작업요청 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveAF0201List.*")
	public void retrieveAF0201List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> af0201List = service.retrieveAF0201List(map);
		response.setList("ds_AF0201List", af0201List);
	}

	@RequestMapping("retrieveAF0201SubList.*")
	public void retrieveAF0201SubList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> af0201SubList = service.retrieveAF0201SubList(map);
		response.setList("ds_AF0201SubList", af0201SubList);
	}

	/**
	 * <pre>
	 *  결재상세정보-예방정비 리비전 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveMT0101Detail.*")
	public void retrieveMT0101Detail(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> mt0101RDetail = service.retrieveMT0101Detail(map);
		response.setList("ds_MT0101Detail", mt0101RDetail);
	}


}
