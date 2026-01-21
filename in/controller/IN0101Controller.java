package com.bplus.pw.in.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.in.service.IN0101Service;
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
 * Program Name : IN0101Controller
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
public class IN0101Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0101Controller.class);

	@Autowired
	private IN0101Service service;

	/**
	 * <pre>
	 *  점검데이터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0101.*")
	public void retrieveIN0101(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0101List = service.retrieveIN0101(map);
		response.setList("ds_IN0101List", ds_IN0101List);
	}

	@RequestMapping("retrieveIN0101Cnt.*")
	public void retrieveIN0101Cnt(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0101ListCnt = service.retrieveIN0101Cnt(map);
		response.setList("ds_IN0101ListCnt", ds_IN0101ListCnt);
	}

	/**
	 * <pre>
	 *  트리에서 점검데이터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0101ToTree.*")
	public void retrieveIN0101ToTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0101List = service.retrieveIN0101ToTree(map);
		response.setList("ds_IN0101List", ds_IN0101List);
	}

	/**
	 * <pre>
	 *  점검항목 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0101Sub.*")
	public void retrieveIN0101Sub(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> in0201DList = service.retrieveIN0101Sub(map);
		response.setList("ds_IN0201DList", in0201DList);
	}

	/**
	 * <pre>
	 *  점검일정 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0101Sub2.*")
	public void retrieveIN0101Sub2(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0101Sub02List = service.retrieveIN0101Sub2(map);
		response.setList("ds_IN0101Sub02List", ds_IN0101Sub02List);
	}

	/**
	 * <pre>
	 *  점검목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveIN0101List.*")
	public void saveIN0101List(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_IN0101List= request.getGridData("ds_IN0101List", HashMap.class);
		GridData<HashMap> ds_IN0201DList = request.getGridData("ds_IN0201DList", HashMap.class);
		GridData<HashMap> ds_IN0201PList = request.getGridData("ds_IN0201PList", HashMap.class);
		service.saveIN0101List(ds_IN0101List, ds_IN0201DList, ds_IN0201PList, map);

	}

	/**
	 * <pre>
	 *  리비전 코멘트 저장
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveIN0101RListCmmnt.*")
	public void saveIN0101RListCmmnt(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_IN0101RList= request.getGridData("ds_IN0101RList", HashMap.class);
		service.saveIN0101RListCmmnt(ds_IN0101RList);

	}


	/**
	 * <pre>
	 *  예방점검 리비전 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0101RList.*")
	public void retrieveIN0101RList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> in0101RList = service.retrieveIN0101RList(map);
		response.setList("ds_IN0101RList", in0101RList);
	}


	/**
	 * <pre>
	 *  점검항목 리비전 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0201DRList.*")
	public void retrieveIN0201DRList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> in0201DRList = service.retrieveIN0201DRList(map);
		response.setList("ds_IN0201DRList", in0201DRList);
	}

	/**
	 * <pre>
	 *  점검부서 리스트 조회(점검마스터 항목 중 점검부서로 지정된 부서리스트 조회)
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveChkOrgList.*")
	public void retrieveChkOrgList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> chkOrgList = service.retrieveChkOrgList(map);
		response.setList("ds_ChkOrgList", chkOrgList);
	}


	/**
	 * <pre>
	 *  점검마스터 담당자 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0201PList.*")
	public void retrieveIN0201PList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> in0201PList = service.retrieveIN0201PList(map);
		response.setList("ds_IN0201PList", in0201PList);
	}

	/**
	 * <pre>
	 *  예방점검마스터 일정생성
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("createSchedule.*")
	public void createSchedule(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0301M = service.createSchedule(map);
		response.setList("ds_IN0301M", ds_IN0301M);
	}

}
