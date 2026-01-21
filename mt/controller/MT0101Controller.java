package com.bplus.pw.mt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.mt.service.MT0101Service;
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
 * Program Name : MT0101Controller
 * Description : 예방정비작성를 위한 controller 클래스
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
public class MT0101Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(MT0101Controller.class);

	@Autowired
	private MT0101Service service;

	/**
	 * <pre>
	 *  예방정비 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveMT0101.*")
	public void retrieveMT0101(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0101List = service.retrieveMT0101(map);
		response.setList("ds_MT0101List", ds_MT0101List);
	}

	/**
	 * <pre>
	 *  트리에서 예방정비 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveMT0101ToTree.*")
	public void retrieveMT0101ToTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0101List = service.retrieveMT0101ToTree(map);
		response.setList("ds_MT0101List", ds_MT0101List);
	}

	/**
	 * <pre>
	 *  점검항목 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveMT0101Sub.*")
	public void retrieveMT0101Sub(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0101ListSub = service.retrieveMT0101Sub(map);
		response.setList("ds_MT0101ListSub", ds_MT0101ListSub);
	}

	@RequestMapping("retrieveMT0101SubJob.*")
	public void retrieveMT0101SubJob(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0101SubJobList = service.retrieveMT0101SubJob(map);
		response.setList("ds_MT0101SubJobList", ds_MT0101SubJobList);
	}

	@RequestMapping("retrieveMT0101SubCraft.*")
	public void retrieveMT0101SubCraft(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0101SubCraftList = service.retrieveMT0101SubCraft(map);
		response.setList("ds_MT0101SubCraftList", ds_MT0101SubCraftList);
	}

	@RequestMapping("retrieveMT0101SubParts.*")
	public void retrieveMT0101SubParts(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0101SubPartsList = service.retrieveMT0101SubParts(map);
		response.setList("ds_MT0101SubPartsList", ds_MT0101SubPartsList);
	}

	/**
	 * <pre>
	 *  점검일정 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveMT0101Sub2.*")
	public void retrieveMT0101Sub2(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0101Sub01List = service.retrieveMT0101Sub2(map);
		response.setList("ds_MT0101Sub01List", ds_MT0101Sub01List);
	}

	/**
	 * <pre>
	 *  점검목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveMT0101List.*")
	public void saveMT0101List(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_MT0101List= request.getGridData("ds_MT0101List", HashMap.class);
		GridData<HashMap> ds_MT0101SubJobList = request.getGridData("ds_MT0101SubJobList", HashMap.class);
		GridData<HashMap> ds_MT0101SubCraftList = request.getGridData("ds_MT0101SubCraftList", HashMap.class);
		GridData<HashMap> ds_MT0101SubPartsList = request.getGridData("ds_MT0101SubPartsList", HashMap.class);
		service.saveMainMT0101List(ds_MT0101List, ds_MT0101SubJobList, ds_MT0101SubCraftList, ds_MT0101SubPartsList, map);

	}

	@RequestMapping("retrieveStNo1.*")
	public void retrieveStNo1(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_StNoListGrid1 = service.retrieveStNo1(map);
		response.setList("ds_StNoListGrid1", ds_StNoListGrid1);
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

	@RequestMapping("retrieveMT0101RList.*")
	public void retrieveMT0101RList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> mt0101RList = service.retrieveMT0101RList(map);
		response.setList("ds_MT0101RList", mt0101RList);
	}


	/**
	 * <pre>
	 *  점검일정 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCalender.*")
	public void retrieveCalender(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_Calender = service.retrieveCalender(map);
		response.setList("ds_Calender", ds_Calender);
	}

	@RequestMapping("retrieveMT0101SubJobRList.*")
	public void retrieveMT0101SubJobRList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0101SubJobRList = service.retrieveMT0101SubJobRList(map);
		response.setList("ds_MT0101SubJobRList", ds_MT0101SubJobRList);
	}

	@RequestMapping("retrieveMT0101SubCraftRList.*")
	public void retrieveMT0101SubCraftRList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0101SubCraftRList = service.retrieveMT0101SubCraftRList(map);
		response.setList("ds_MT0101SubCraftRList", ds_MT0101SubCraftRList);
	}

	@RequestMapping("retrieveMT0101SubPartsRList.*")
	public void retrieveMT0101SubPartsRList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0101SubPartsRList = service.retrieveMT0101SubPartsRList(map);
		response.setList("ds_MT0101SubPartsRList", ds_MT0101SubPartsRList);
	}

}
