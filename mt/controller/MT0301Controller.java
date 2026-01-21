package com.bplus.pw.mt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.mt.service.MT0301Service;
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
 * Program Name : MT0301Controller
 * Description : 연간휴일 지정를 위한 controller 클래스
 * Author : jit13.
 * Created Date : 2020-04-01
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
@RequestMapping("/pw/mt/*")
public class MT0301Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(MT0301Controller.class);

	@Autowired
	private MT0301Service service;

	/**
	 * <pre>
	 *  목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveMT0301.*")
	public void retrieveMT0301(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0301List = service.retrieveMT0301(map);
		response.setList("ds_MT0301List", ds_MT0301List);
	}

	@RequestMapping("retrieveMT0301Sub.*")
	public void retrieveMT0301Sub(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0301Sub01List = service.retrieveMT0301Sub(map);
		response.setList("ds_MT0301Sub01List", ds_MT0301Sub01List);
	}

	@RequestMapping("saveMT0301List.*")
	public void saveMT0301List(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_MT0301List = request.getGridData("ds_MT0301List", HashMap.class);
		GridData<HashMap> ds_MT0301Sub01List = request.getGridData("ds_MT0301Sub01List", HashMap.class);
		service.saveMT0301Main(ds_MT0301List, ds_MT0301Sub01List);
	}

	@RequestMapping("createMonMT0301List.*")
	public void createMonMT0301List(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0301Sub01List= service.createMonMT0301List(map);
		response.setList("ds_MT0301Sub01List", ds_MT0301Sub01List);
	}







}
