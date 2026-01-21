package com.bplus.pw.in.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.in.service.IN1101Service;
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
 * Program Name : IN1101Controller
 * Description : 일상점검결과 작성를 위한 controller 클래스
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
public class IN1101Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN1101Controller.class);

	@Autowired
	private IN1101Service service;

	/**
	 * <pre>
	 *  일상점검결과 작성 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN1101.*")
	public void retrieveIN1101(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN1101List = service.retrieveIN1101(map);
		response.setList("ds_IN1101List", ds_IN1101List);
	}

	/**
	 * <pre>
	 *  일상점검결과 작성 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveIN1101List.*")
	public void saveIN1101List(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_IN1101List = request.getGridData("ds_IN1101List", HashMap.class);
		service.saveIN1101List(ds_IN1101List);

	}

}
