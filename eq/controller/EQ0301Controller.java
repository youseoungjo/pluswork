package com.bplus.pw.eq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.eq.service.EQ0301Service;

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
public class EQ0301Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(EQ0301Controller.class);

	@Autowired
	private EQ0301Service service;

	/**
	 * <pre>
	 *  기술문서 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0301Tree.*")
	public void retrieveEQ0301Tree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0301List = service.retrieveEQ0301Tree(map);
		response.setList("ds_EQ0301Tree", eq0301List);

	}

	/**
	 * <pre>
	 *  기술문서 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0301List.*")
	public void retrieveEQ0301List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0301List = service.retrieveEQ0301List(map);
		response.setList("ds_EQ0301List", eq0301List);
	}

	/**
	 * <pre>
	 *  트리에서 기술문서 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0301ListToTree.*")
	public void retrieveEQ0301ListToTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0301List = service.retrieveEQ0301ListToTree(map);
		response.setList("ds_EQ0301List", eq0301List);
	}

	/**
	 * <pre>
	 *  기술문서 설비 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEqDocList.*")
	public void retrieveEqList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> eqList = service.retrieveEqDocList(map);
		response.setList("ds_EQList", eqList);

	}


	/**
	 * <pre>
	 *  기술문서 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveEQ0301.*")
	public void saveEQ0301(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> eq0301List= request.getGridData("ds_EQ0301List", HashMap.class); //기술문서 정보;
		GridData<HashMap> eqList= request.getGridData("ds_EQList", HashMap.class); //스펙 정보;

		service.saveEqProcessAll(eq0301List,eqList);
		//List<Map<String, Object>> rslEQ0301List = service.retrieveEQ0301Tree(map);
		//response.setList("ds_EQ0301Tree", rslEQ0301List);
	}
}
