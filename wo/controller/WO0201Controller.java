package com.bplus.pw.wo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.wo.service.WO0201Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : WO0201Controller
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
public class WO0201Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(WO0201Controller.class);

	@Autowired
	private WO0201Service service;

	/**
	 * <pre>
	 *  기술문서 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0201Tree.*")
	public void retrieveWO0201Tree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> wo0201List = service.retrieveWO0201Tree(map);
		response.setList("ds_WO0201Tree", wo0201List);

	}

	/**
	 * <pre>
	 *  작업계획 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0201List.*")
	public void retrieveWO0201List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> wo0201List = service.retrieveWO0201List(map);
		response.setList("ds_WO0201List", wo0201List);
	}

	/**
	 * <pre>
	 *  트리에서 작업계획 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0201ListToTree.*")
	public void retrieveWO0201ListToList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> wo0201List = service.retrieveWO0201ListToTree(map);
		response.setList("ds_WO0201List", wo0201List);
	}

	/**
	 * <pre>
	 *  작업 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoJobList.*")
	public void retrieveWoJobList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> jobList = service.retrieveWoJobList(map);
		response.setList("ds_JobList", jobList);

	}

	/**
	 * <pre>
	 *  Craft 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoCraftList.*")
	public void retrieveWoCraftList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> craftList = service.retrieveWoCraftList(map);
		response.setList("ds_CraftList", craftList);
	}

	/**
	 * <pre>
	 *  자재 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0201PList.*")
	public void retrieveWO0201PList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> materialtList = service.retrieveWO0201PList(map);
		response.setList("ds_MaterialList", materialtList);
	}

	/**
	 * <pre>
	 *  기타경비 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoMaterialList.*")
	public void retrieveWoMaterialList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> materialList = service.retrieveWoMaterialList(map);
		response.setList("ds_MaterialList", materialList);

	}


	/**
	 * <pre>
	 *  기술문서 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveWO0201.*")
	public void saveWO0201(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> wo0201List= request.getGridData("ds_WO0201List", HashMap.class); //설계상세 정보;
		GridData<HashMap> jobList= request.getGridData("ds_JobList", HashMap.class); //작업 정보;
		GridData<HashMap> craftList= request.getGridData("ds_CraftList", HashMap.class); //인건비 정보;
		GridData<HashMap> materialList= request.getGridData("ds_MaterialList", HashMap.class); //인건비 정보;

		service.saveWoProcessAll(wo0201List,jobList,craftList,materialList);
		//List<Map<String, Object>> rslWO0201List = service.retrieveWO0201Tree(map);
		//response.setList("ds_WO0201Tree", rslWO0201List);
	}

	/**
	 * <pre>
	 *  설계취소 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("updatePlanConfirmWO0201.*")
	public void savePlanConfirmWO0201(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> planConfirm= request.getGridData("ds_PlanConfirm", HashMap.class); //설계상세 정보;

		service.updatePlanConfirmWO0201(planConfirm);
	}


	/**
	 * <pre>
	 *  작업설계서 상세 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0201Detail.*")
	public void retrieveWO0201Detail(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_WO0201Detail = service.retrieveWO0201Detail(map);
		response.setList("ds_WO0201Detail", ds_WO0201Detail);
	}

	@RequestMapping("retrieveStNo2.*")
	public void retrieveStNo2(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_StNoListGrid2 = service.retrieveStNo2(map);
		response.setList("ds_StNoListGrid2", ds_StNoListGrid2);
	}

	@RequestMapping("retrieveStNo3.*")
	public void retrieveStNo3(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_StNoListGrid3 = service.retrieveStNo3(map);
		response.setList("ds_StNoListGrid3", ds_StNoListGrid3);
	}

	@RequestMapping("retrieveStNo4.*")
	public void retrieveStNo4(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_StNoListGrid4 = service.retrieveStNo4(map);
		response.setList("ds_StNoListGrid4", ds_StNoListGrid4);
	}

}

