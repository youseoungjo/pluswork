package com.bplus.pw.in.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.in.service.IN0401Service;
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
 * Program Name : IN0401Controller
 * Description : 점검결과 작성를 위한 controller 클래스
 * Author : jit13.
 * Created Date : 2019-07-24
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
public class IN0401Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0401Controller.class);

	@Autowired
	private IN0401Service service;

	/**
	 * <pre>
	 *  점검결과 작성 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0401.*")
	public void retrieveIN0401(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0401List = service.retrieveIN0401(map);
		response.setList("ds_IN0401List", ds_IN0401List);
	}

	/**
	 * <pre>
	 *  점검결과 작성 상세내역
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0401Sub.*")
	public void retrieveIN0401Sub(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0401Sub01List = service.retrieveIN0401Sub(map);
		response.setList("ds_IN0401Sub01List", ds_IN0401Sub01List);
	}

	/**
	 * <pre>
	 *  점검결과 작성 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveIN0401ListSub.*")
	public void saveIN0401ListSub(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_IN0401Sub01List = request.getGridData("ds_IN0401Sub01List", HashMap.class);
		service. saveIN0401(ds_IN0401Sub01List, map);

	}

}
