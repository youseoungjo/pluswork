package com.bplus.pw.wo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.wo.service.WO0101Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : WO0101Controller
 * Description : 작업요청 등록/수정/삭제를 위한 controller 클래스
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
public class WO0101Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(WO0101Controller.class);

	@Autowired
	private WO0101Service service;



	/**
	 * <pre>
	 *  작업요청 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0101List.*")
	public void retrieveWO0101List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> wo0101List = service.retrieveWO0101List(map);
		response.setList("ds_WO0101List", wo0101List);
	}

	/**
	 * <pre>
	 *  트리에서 작업요청 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0101ListToTree.*")
	public void retrieveWO0101ListToTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> wo0101List = service.retrieveWO0101ListToTree(map);
		response.setList("ds_WO0101List", wo0101List);
	}

	/**
	 * <pre>
	 *  현상 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveSymptmList.*")
	public void retrieveSymptmList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> symList = service.retrieveSymptmList(map);
		response.setList("ds_SymList", symList);

	}





	/**
	 * <pre>
	 *  작업요청 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveWO0101.*")
	public void saveWO0101(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> wo0101List= request.getGridData("ds_WO0101List", HashMap.class); //작업요청 정보;
		GridData<HashMap> symList= request.getGridData("ds_SymList", HashMap.class); //스펙 정보;

		service.saveWoProcessAll(wo0101List,symList);
		//List<Map<String, Object>> rslWO0101List = service.retrieveWO0101Tree(map);
		//response.setList("ds_WO0101Tree", rslWO0101List);
	}


	/**
	 * W/O 수행생성(작업결과서 생성)
	 * @param request
	 * @param response
	 */
	@RequestMapping("createWorkOrder.*")
	public void createWorkOrder(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> wo0201List= request.getGridData("ds_WO0201List", HashMap.class); //작업요청 정보;

		service.createWorkOrder(wo0201List, map);
	}

	/**
	 * <pre>
	 *  작업요청 상세 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWO0101Detail.*")
	public void retrieveWO0101Detail(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_WO0101Detail = service.retrieveWO0101Detail(map);
		response.setList("ds_WO0101Detail", ds_WO0101Detail);
	}

	/**
	 * <pre>
	 *  작업요청 취소
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveReqCancelWO0101.*")
	public void savePlanCancelWO0201(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> rstlMap = new HashMap<String, Object>();
		int rstlCnt = 0;
		rstlCnt = service.saveReqCancelWO0101(map);
		rstlMap.put("rstlCnt", rstlCnt);

		response.setMap("rstlMap", rstlMap);
	}


	/**
	 * 작업요청접수
	 * @param request
	 * @param response
	 */
	@RequestMapping("wo0101RequestAccep.*")
	public void wo0101RequestAccep(BqsRequest request, BqsResponse response) throws Exception {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> rstlMap = new HashMap<String, Object>();
		int rstlCnt = 0;
		rstlCnt = service.updateRequestAccep(map);
		rstlMap.put("rstlCnt", rstlCnt);

		response.setMap("rstlMap", rstlMap);

	}

	/**
	 * 요청자확인
	 * @param request
	 * @param response
	 */
	@RequestMapping("reqUserConfirm.*")
	public void reqUserConfirm(BqsRequest request, BqsResponse response) throws Exception {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		Map<String, Object> rstlMap = new HashMap<String, Object>();
		int rstlCnt = 0;
		rstlCnt = service.reqUserConfirm(map);
		rstlMap.put("rstlCnt", rstlCnt);

		response.setMap("rstlMap", rstlMap);

	}
}
