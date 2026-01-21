package com.bplus.pw.wo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.wo.service.WO0601Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : WO0601Controller
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
@RequestMapping("/pw/wo/*")
public class WO0601Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(WO0601Controller.class);

	@Autowired
	private WO0601Service service;

	/**
	 * <pre>
	 *  기술문서 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0601Tree.*")
	public void retrieveWO0601Tree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> wo0601List = service.retrieveWO0601Tree(map);
		response.setList("ds_WO0601Tree", wo0601List);

	}

	/**
	 * <pre>
	 *  기술문서 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0601List.*")
	public void retrieveWO0601List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> wo0601List = service.retrieveWO0601List(map);
		response.setList("ds_WO0601List", wo0601List);
	}

	/**
	 * <pre>
	 *  작업 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0601JobList.*")
	public void retrieveWoJobList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> jobList = service.retrieveWoRJobList(map);
		response.setList("ds_JobList", jobList);

	}

	/**
	 * <pre>
	 *  Craft 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0601CraftList.*")
	public void retrieveWoCraftList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> craftList = service.retrieveWoRCraftList(map);
		response.setList("ds_CraftList", craftList);

	}


	/**
	 * <pre>
	 * 현상  목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0601SymList.*")
	public void retrieveWoSymList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> symList = service.retrieveWoSymList(map);
		response.setList("ds_SymList", symList);

	}

	/**
	 * <pre>
	 * 조치  목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0601ResultList.*")
	public void retrieveWoResultList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> resultList = service.retrieveWoResultList(map);
		response.setList("ds_ResultList", resultList);

	}

	/**
	 * <pre>
	 *  기술문서 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveWO0601.*")
	public void saveWO0601(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> wo0601List= request.getGridData("ds_WO0601List", HashMap.class); //설계상세 정보;
		GridData<HashMap> jobList= request.getGridData("ds_JobList", HashMap.class); //작업 정보;
		GridData<HashMap> craftList= request.getGridData("ds_CraftList", HashMap.class); //인건비 정보;

		GridData<HashMap> symList= request.getGridData("ds_SymList", HashMap.class); //현상 정보;
		GridData<HashMap> resultList= request.getGridData("ds_ResultList", HashMap.class); //조치 정보;


		service.saveWoProcessAll(wo0601List,jobList,craftList,symList,resultList);
	}

	/**
	 * <pre>
	 *  작업취소 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveWoCancelWO0601.*")
	public void saveWoCancelWO0601(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> woCancel= request.getGridData("ds_ResultCancel", HashMap.class); //설계상세 정보;

		service.saveWoCancelWO0601(woCancel);
	}

}
