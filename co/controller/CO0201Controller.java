package com.bplus.pw.co.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.co.service.CO0201Service;

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
public class CO0201Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(CO0201Controller.class);

	@Autowired
	private CO0201Service service;

	/**
	 * <pre>
	 *  공통코드 그룹 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0201MList.*")
	public void retrieveCO0201MList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0201MList = service.retrieveCO0201MList(map);
		response.setList("ds_CO0201MList", co0201MList);

	}

	/**
	 * <pre>
	 *  공통코드 그룹/Code 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveCO0201.*")
	public void saveCO0201(BqsRequest request, BqsResponse response) {
		GridData<HashMap> co0201MList= request.getGridData("ds_CO0201MList", HashMap.class); //양식 정보;
		GridData<HashMap> co0201DList= request.getGridData("ds_CO0201DList", HashMap.class); //양식 정보;
		service.saveCO0201(co0201MList, co0201DList);
	}

	/**
	 * <pre>
	 *  공통코드 Code 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0201DList.*")
	public void retrieveCO0201DList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0201DList = service.retrieveCO0201DList(map);
		response.setList("ds_CO0201DList", co0201DList);

	}
}
