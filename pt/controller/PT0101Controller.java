package com.bplus.pw.pt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.pt.service.PT0101Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**2019-07-30
 * <pre>
 * ---------------------------------------------------------------
 * System Class : PT0101Controller.java
 * Program Name : PT0101Controller
 * Description : 자재마스터 등록/수정/삭제를 위한 controller 클래스
 * Author : sds
 * Created Date : 2022-12-06
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
@RequestMapping("/pw/pt/*")
public class PT0101Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(PT0101Controller.class);

	@Autowired
	private PT0101Service service;

	/**
	 * <pre>
	 *  자재마스터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrievePT0101MList.*")
	public void retrievePT0101MList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> pt0101MList = service.retrievePT0101MList(map);
		response.setList("ds_PT0101MList", pt0101MList);

	}

	/**
	 * <pre>
	 *  자재마스터 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("savePT0101M.*")
	public void savePT0101M(BqsRequest request, BqsResponse response) {
		GridData<HashMap> pt0101MList= request.getGridData("ds_PT0101MList", HashMap.class); //양식 정보;
		service.savePT0101M(pt0101MList);
	}

	/**
	 * <pre>
	 *  자재팝업 : 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveMaterialListPopup.*")
	public void retrieveMaterialListPopup(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> pt0101MList = service.retrieveMaterialListPopup(map);
		response.setList("ds_MaterialPoupList", pt0101MList);

	}

}
