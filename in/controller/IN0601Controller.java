package com.bplus.pw.in.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.in.service.IN0601Service;
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
 * Program Name : IN0601Controller
 * Description : 이상점검결과 검색를 위한 controller 클래스
 * Author : jit13.
 * Created Date : 2019-07-23
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
public class IN0601Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0601Controller.class);

	@Autowired
	private IN0601Service service;

	/**
	 * <pre>
	 *  미점검 리스트 검색
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0601.*")
	public void retrieveIN0601(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0601List = service.retrieveIN0601(map);
		response.setList("ds_IN0601List", ds_IN0601List);
	}

	/**
	 * <pre>
	 *  트리에서 미점검 리스트 검색
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0601ToTree.*")
	public void retrieveIN0601ToTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0601List = service.retrieveIN0601ToTree(map);
		response.setList("ds_IN0601List", ds_IN0601List);
	}

	/**
	 * <pre>
	 *  작업계획 W/O 발행 저장
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveIN0601ListWo.*")
	public void saveIN0601ListWo(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.saveIN0601ListWo(map);
	}

	/**
	 * <pre>
	 *  작업결과 W/O 발행 저장
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveIN0601ListResult.*")
	public void saveIN0601ListResult(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.saveIN0601ListResult(map);
	}

	/**
	 * <pre>
	 *  저장
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveIN0601List.*")
	public void saveIN0601List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_IN0601List = request.getGridData("ds_IN0601List", HashMap.class);
		service.saveIN0601List(ds_IN0601List);
	}


}
