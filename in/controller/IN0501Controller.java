package com.bplus.pw.in.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.in.service.IN0501Service;
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
 * Program Name : IN0501Controller
 * Description : 점검결과데이터 검색를 위한 controller 클래스
 * Author : jit13.
 * Created Date : 2019-07-19
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
public class IN0501Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0501Controller.class);

	@Autowired
	private IN0501Service service;

	/**
	 * <pre>
	 *  점검결과 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0501.*")
	public void retrieveIN0501(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0501List = service.retrieveIN0501(map);
		response.setList("ds_IN0501List", ds_IN0501List);
	}

	/**
	 * <pre>
	 *  트리에서 점검결과 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0501ToTree.*")
	public void retrieveIN0501ToTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0501List = service.retrieveIN0501ToTree(map);
		response.setList("ds_IN0501List", ds_IN0501List);
	}

	/**
	 * <pre>
	 *  점검결과 항목 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0501Sub.*")
	public void retrieveIN0501Sub(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> cmplYmdList = service.retrieveCmplYmdList(map);
		List<Map<String, Object>> ds_IN0501Sub01List = service.retrieveIN0501Sub(map);
		response.setList("cmplYmdList", cmplYmdList);
		response.setList("ds_IN0501Sub01List", ds_IN0501Sub01List);

	}

}
