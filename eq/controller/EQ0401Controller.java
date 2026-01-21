package com.bplus.pw.eq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.eq.service.EQ0401Service;
import com.bplus.pw.kc.service.KC0301Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : EQ0301Controller
 * Description : 기술문서 등록/수정/삭제를 위한 controller 클래스
 * Author : Jun.
 * Created Date : 2013-12-10
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
@RequestMapping("/pw/eq/*")
public class EQ0401Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(EQ0401Controller.class);

	@Autowired
	private EQ0401Service service;

	/**
	 * <pre>
	 *  기술문서 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveKC0201List.*")
	public void retrieveKC0201List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> kc0201List = service.retrieveKC0201List(map);
		response.setList("ds_KC0201List", kc0201List);
	}

	/**
	 * <pre>
	 *  기술문서 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveKC0201.*")
	public void saveKC0201(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> kc0201List= request.getGridData("ds_KC0201List", HashMap.class); //기술문서 정보;

		service.saveKC0201(kc0201List);
		//List<Map<String, Object>> rslEQ0301List = service.retrieveEQ0301Tree(map);
		//response.setList("ds_EQ0301Tree", rslEQ0301List);
	}
}
