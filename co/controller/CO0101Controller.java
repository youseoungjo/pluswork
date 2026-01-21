package com.bplus.pw.co.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.co.service.CO0101Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**2019-07-30
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : CO0101Controller
 * Description : 스펙코드 등록/수정/삭제를 위한 controller 클래스
 * Author : Jun.
 * Created Date : 2019-07-30
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
@RequestMapping("/pw/co/*")
public class CO0101Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(CO0101Controller.class);

	@Autowired
	private CO0101Service service;

	/**
	 * <pre>
	 *  공통코드 그룹 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0101MList.*")
	public void retrieveCO0101MList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0101MList = service.retrieveCO0101MList(map);
		response.setList("ds_CO0101MList", co0101MList);

	}

	/**
	 * <pre>
	 *  공통코드 그룹/Code 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveCO0101.*")
	public void saveCO0101(BqsRequest request, BqsResponse response) {
		GridData<HashMap> co0101MList= request.getGridData("ds_CO0101MList", HashMap.class); //양식 정보;
		GridData<HashMap> co0101DList= request.getGridData("ds_CO0101DList", HashMap.class); //양식 정보;
		service.saveCO0101(co0101MList, co0101DList);
	}

	/**
	 * <pre>
	 *  공통코드 Code 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0101DList.*")
	public void retrieveCO0101DList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0101DList = service.retrieveCO0101DList(map);
		response.setList("ds_CO0101DList", co0101DList);

	}

	/**
	 * <pre>
	 *  SPEC 팝업 - 표준규격(SPEC) 데이터 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveStdSpecList.*")
	public void retrieveStdSpecList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> stdSpecList = service.retrieveStdSpecList(map);
		response.setList("ds_StdSpecList", stdSpecList);

	}

	/**
	 * <pre>
	 *  SPEC 팝업 - 규격항목 데이터 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveSpecItemList.*")
	public void retrieveSpecItemList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> specItemList = service.retrieveSpecItemList(map);
		response.setList("ds_SpecItemList", specItemList);

	}
}
