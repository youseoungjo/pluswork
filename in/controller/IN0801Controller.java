package com.bplus.pw.in.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.in.service.IN0801Service;
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
 * Program Name : IN0801Controller
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
public class IN0801Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0801Controller.class);

	@Autowired
	private IN0801Service service;

	/**
	 * <pre>
	 *  점검결과 작성 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0801.*")
	public void retrieveIN0801(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0801List = service.retrieveIN0801(map);
		response.setList("ds_IN0801List", ds_IN0801List);
	}

	/**
	 * <pre>
	 *  점검결과 작성 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveIN0801List.*")
	public void saveIN0801List(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_IN0801List = request.getGridData("ds_IN0801List", HashMap.class);
		service.saveIN0801List(ds_IN0801List);

	}

}
