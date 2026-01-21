package com.bplus.pw.st.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.st.service.ST1001Service;

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
public class ST1001Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(ST1001Controller.class);

	@Autowired
	private ST1001Service service;

	/**
	 * <pre>
	 *  점검데이터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveST1001List.*")
	public void retrieveST1001List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST1001List = service.retrieveST1001List(map);
		response.setList("ds_ST1001List", ds_ST1001List);
	}

	@RequestMapping("retrieveST1001Sub01List.*")
	public void retrieveST1001Sub01List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST1001Sub01List = service.retrieveST1001Sub01List(map);
		response.setList("ds_ST1001Sub01List", ds_ST1001Sub01List);
	}

	@RequestMapping("saveST1001List.*")
	public void saveST1001List(BqsRequest request, BqsResponse response) {
		GridData<HashMap> st1001List = request.getGridData("ds_ST1001List", HashMap.class); //양식 정보;
		service.saveST1001List(st1001List);
	}

	@RequestMapping("saveMonthlyChkCnt.*")
	public void saveMonthlyChkCnt(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.saveMonthlyChkCnt(map);
	}


}
