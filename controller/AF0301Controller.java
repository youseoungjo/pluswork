package com.bplus.pw.af.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.af.service.AF0301Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : AF0301Controller
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
public class AF0301Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(AF0301Controller.class);

	@Autowired
	private AF0301Service service;



	/**
	 * <pre>
	 *  작업요청 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveAF0301List.*")
	public void retrieveAF0301List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> af0301List = service.retrieveAF0301List(map);
		response.setList("ds_AF0301List", af0301List);
	}

	@RequestMapping("retrieveAF0301SubList.*")
	public void retrieveAF0301SubList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> af0301SubList = service.retrieveAF0301SubList(map);
		response.setList("ds_AF0301SubList", af0301SubList);
	}


	@RequestMapping("confirmAF0301List.*")
	public void confirmAF0301List(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_AF0301List= request.getGridData("ds_AF0301List", HashMap.class);
		service.confirmAF0301List(ds_AF0301List);

	}


}
