package com.bplus.pw.wo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.wo.service.WO0301Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : WO0301Controller
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
public class WO0301Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(WO0301Controller.class);

	@Autowired
	private WO0301Service service;

	/**
	 * <pre>
	 *  트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0301Tree.*")
	public void retrieveWO0301Tree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> wo0301List = service.retrieveWO0301Tree(map);
		response.setList("ds_WO0301Tree", wo0301List);
	}

	/**
	 * <pre>
	 *  작업결과서 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0301List.*")
	public void retrieveWO0301List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> wo0301List = service.retrieveWO0301List(map);
		response.setList("ds_WO0301List", wo0301List);
	}

	/**
	 * <pre>
	 *  트리에서 작업결과서 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0301ListToTree.*")
	public void retrieveWO0301ListToTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> wo0301List = service.retrieveWO0301ListToTree(map);
		response.setList("ds_WO0301List", wo0301List);
	}

	/**
	 * <pre>
	 *  작업 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoRJobList.*")
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
	@RequestMapping("retrieveWoRCraftList.*")
	public void retrieveWoCraftList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> craftList = service.retrieveWoRCraftList(map);
		response.setList("ds_CraftList", craftList);

	}

	/**
	 * <pre>
	 *  기타경비 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoRMaterialList.*")
	public void retrieveWoRMaterialList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> materialList = service.retrieveWoRMaterialList(map);
		response.setList("ds_MaterialList", materialList);

	}

	/**
	 * <pre>
	 * 현상  목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoSymList.*")
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
	@RequestMapping("retrieveWoResultList.*")
	public void retrieveWoResultList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> resultList = service.retrieveWoResultList(map);
		response.setList("ds_ResultList", resultList);

	}

	/**
	 * <pre>
	 *  작업결과서 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveWO0301.*")
	public void saveWO0301(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> wo0301List= request.getGridData("ds_WO0301List", HashMap.class); //설계상세 정보;
		GridData<HashMap> jobList= request.getGridData("ds_JobList", HashMap.class); //작업 정보;
		GridData<HashMap> craftList= request.getGridData("ds_CraftList", HashMap.class); //인건비 정보;
		GridData<HashMap> materialList= request.getGridData("ds_MaterialList", HashMap.class); //인건비 정보;

		GridData<HashMap> symList= request.getGridData("ds_SymList", HashMap.class); //현상 정보;
		GridData<HashMap> resultList= request.getGridData("ds_ResultList", HashMap.class); //조치 정보;


		service.saveWoProcessAll(wo0301List,jobList,craftList,materialList,symList,resultList);
		//List<Map<String, Object>> rslWO0301List = service.retrieveWO0301Tree(map);
		//response.setList("ds_WO0301Tree", rslWO0301List);
	}

	/**
	 * <pre>
	 *  작업취소
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveWoCancelWO0301.*")
	public void saveWoCancelWO0301(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> woCancel= request.getGridData("ds_ResultCancel", HashMap.class); //설계상세 정보;

		service.saveWoCancelWO0301(woCancel);
	}

	/**
	 * <pre>
	 *  작업결과서 상세 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0301Detail.*")
	public void retrieveWO0301Detail(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_WO0301Detail = service.retrieveWO0301Detail(map);
		response.setList("ds_WO0301Detail", ds_WO0301Detail);
	}

}
