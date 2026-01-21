package com.bplus.pw.in.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.in.service.IN0901Service;

import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : IN0901Controller
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
@RequestMapping("/pw/in/*")
public class IN0901Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0901Controller.class);

	@Autowired
	private IN0901Service service;

	/**
	 * <pre>
	 *  점검데이터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0901.*")
	public void retrieveIN0901(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0901List = service.retrieveIN0901(map);
		response.setList("ds_IN0901List", ds_IN0901List);
	}

	/**
	 * <pre>
	 *  점검항목 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0901Sub.*")
	public void retrieveIN0901Sub(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0901Sub01List = service.retrieveIN0901Sub(map);
		response.setList("ds_IN0901Sub01List", ds_IN0901Sub01List);
	}

	@RequestMapping("retrieveIN0901Sub2.*")
	public void retrieveIN0901Sub2(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0901Sub02List = service.retrieveIN0901Sub2(map);
		response.setList("ds_IN0901Sub02List", ds_IN0901Sub02List);
	}

	@RequestMapping("retrieveIN0901ListPop.*")
	public void retrieveIN0901ListPop(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0901PopList = service.retrieveIN0901ListPop(map);
		response.setList("ds_IN0901PopList", ds_IN0901PopList);
	}

	@RequestMapping("saveIN0901Pop.*")
	public void saveIN0901Pop(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.saveIN0901Pop(map);
		/*List<Map<String, Object>> ds_IN0901ListPop =
				service.saveIN0901Pop(map);
		response.setList("ds_IN0901ListPop", ds_IN0901ListPop);*/
	}

	@RequestMapping("updateIN0901Pop.*")
	public void updateIN0901Pop(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.updateIN0901Pop(map);
		/*List<Map<String, Object>> ds_IN0901ListPop = service.updateIN0901Pop(map);
		response.setList("ds_IN0901ListPop", ds_IN0901ListPop); */
	}

	@RequestMapping("createIN0901Chk.*")
	public void createIN0901Chk(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.createIN0901Chk(map);
		//List<Map<String, Object>> ds_IN0901ListPop = service.createWoNo(map);
		//response.setList("ds_IN0901ListPop", ds_IN0901ListPop);
	}

}
