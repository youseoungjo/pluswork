package com.bplus.pw.mt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.mt.service.MT0401Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : MT0401Controller
 * Description : 예방정비UBM작성를 위한 controller 클래스
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
@RequestMapping("/pw/mt/*")
public class MT0401Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(MT0401Controller.class);

	@Autowired
	private MT0401Service service;

	/**
	 * <pre>
	 *  예방정비UBM 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveMT0401.*")
	public void retrieveMT0401(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0401List = service.retrieveMT0401(map);
		response.setList("ds_MT0401List", ds_MT0401List);
	}

	/**
	 * <pre>
	 *  트리에서 예방정비UBM 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveMT0401ToTree.*")
	public void retrieveMT0401ToTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0401List = service.retrieveMT0401ToTree(map);
		response.setList("ds_MT0401List", ds_MT0401List);
	}

	/**
	 * <pre>
	 *  점검항목 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveMT0401Sub.*")
	public void retrieveMT0401Sub(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0401Sub01List = service.retrieveMT0401Sub(map);
		response.setList("ds_MT0401Sub01List", ds_MT0401Sub01List);
	}

	@RequestMapping("retrieveMT0401Sub2.*")
	public void retrieveMT0401Sub2(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0401Sub02List = service.retrieveMT0401Sub2(map);
		response.setList("ds_MT0401Sub02List", ds_MT0401Sub02List);
	}

	@RequestMapping("retrieveMT0401ListPop.*")
	public void retrieveMT0401ListPop(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0401PopList = service.retrieveMT0401ListPop(map);
		response.setList("ds_MT0401PopList", ds_MT0401PopList);
	}

	@RequestMapping("saveMT0401Pop.*")
	public void saveMT0401Pop(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.saveMT0401Pop(map);
		/*List<Map<String, Object>> ds_MT0401ListPop =
				service.saveMT0401Pop(map);
		response.setList("ds_MT0401ListPop", ds_MT0401ListPop);*/
	}

	@RequestMapping("updateMT0401Pop.*")
	public void updateMT0401Pop(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.updateMT0401Pop(map);
		/*List<Map<String, Object>> ds_MT0401ListPop = service.updateMT0401Pop(map);
		response.setList("ds_MT0401ListPop", ds_MT0401ListPop); */
	}

	@RequestMapping("createMT0401Wo.*")
	public void createMT0401Wo(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.createWoNo(map);
		//List<Map<String, Object>> ds_MT0401ListPop = service.createWoNo(map);
		//response.setList("ds_MT0401ListPop", ds_MT0401ListPop);
	}

}
