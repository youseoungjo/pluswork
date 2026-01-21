package com.bplus.pw.co.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.co.service.CO0301Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**2019-07-30
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : CO0101Controller
 * Description : 스펙코드 등록/수정/삭제를 위한 controller 클래스
 * Author : Jun.
 * Created Date : 2019-07-30
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
@RequestMapping("/pw/co/*")
public class CO0301Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(CO0301Controller.class);

	@Autowired
	private CO0301Service service;

	/**
	 * <pre>
	 *  공통코드 그룹 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0301MList.*")
	public void retrieveCO0301MList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0301MList = service.retrieveCO0301MList(map);
		response.setList("ds_CO0301MList", co0301MList);

	}

	/**
	 * <pre>
	 *  공통코드 그룹 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0301CList.*")
	public void retrieveCO0301CList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0301CList = service.retrieveCO0301CList(map);
		response.setList("ds_CO0301CList", co0301CList);

	}

	/**
	 * <pre>
	 *  공통코드 그룹 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0301JList.*")
	public void retrieveCO0301JList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0301JList = service.retrieveCO0301JList(map);
		response.setList("ds_CO0301JList", co0301JList);

	}

	/**
	 * <pre>
	 *  공통코드 그룹 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0301PList.*")
	public void retrieveCO0301PList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0301PList = service.retrieveCO0301PList(map);
		response.setList("ds_CO0301PList", co0301PList);

	}

	/**
	 * <pre>
	 *  공통코드 그룹/Code 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveCO0301.*")
	public void saveCO0301(BqsRequest request, BqsResponse response) {
		GridData<HashMap> co0301MList= request.getGridData("ds_CO0301MList", HashMap.class); //양식 정보;
		GridData<HashMap> co0301CList= request.getGridData("ds_CO0301CList", HashMap.class); //양식 정보;
		GridData<HashMap> co0301JList= request.getGridData("ds_CO0301JList", HashMap.class); //양식 정보;
		GridData<HashMap> co0301PList= request.getGridData("ds_CO0301PList", HashMap.class); //양식 정보;
		service.saveCO0301(co0301MList, co0301CList, co0301JList, co0301PList);
	}
	
	/**
	 * <pre>
	 *  W/O에 저장된 작업표준-작업 리스트 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoJobList.*")
	public void retrieveWoJobList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_JobList = service.retrieveWoJobList(map);
		response.setList("ds_JobList", ds_JobList);

	}
	
	/**
	 * <pre>
	 *  W/O에 저장된 작업표준-인건비 리스트 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoCraftList.*")
	public void retrieveWoCraftList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_CraftList = service.retrieveWoCraftList(map);
		response.setList("ds_CraftList", ds_CraftList);

	}
	
	/**
	 * <pre>
	 *  W/O에 저장된 작업표준-자재비 리스트 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoMaterialList.*")
	public void retrieveWoMaterialList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MaterialList = service.retrieveWoMaterialList(map);
		response.setList("ds_MaterialList", ds_MaterialList);

	}
	
	/**
	 * <pre>
	 *  W/O에 저장된 작업표준
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveWoStdPopList.*")
	public void retrieveWoStdPopList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_WOStdPopList = service.retrieveWoStdPopList(map);
		response.setList("ds_WOStdPopList", ds_WOStdPopList);

	}
}
