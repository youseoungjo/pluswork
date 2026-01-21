package com.bplus.pw.eq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.eq.service.EQ0201Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : EQ0201Controller
 * Description : 종류 등록/수정/삭제를 위한 controller 클래스
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
public class EQ0201Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(EQ0201Controller.class);

	@Autowired
	private EQ0201Service service;

	/**
	 * <pre>
	 *  설비 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0201EqTree.*")
	public void retrieveEQ0201EqTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eqTreeList = service.retrieveEQ0201EqTree(map);
		response.setList("ds_EQTreeList", eqTreeList);
	}

	/**
	 * <pre>
	 *  공정 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0201PrcsTree.*")
	public void retrieveEQ0201PrcsTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0201PrcsList = service.retrieveEQ0201PrcsTree(map);
		response.setList("ds_EQ0201PrcsTree", eq0201PrcsList);
	}

	/**
	 * <pre>
	 *  종류 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0201TpTree.*")
	public void retrieveEQ0201TpTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0201TpList = service.retrieveEQ0201TpTree(map);
		response.setList("ds_EQ0201TpTree", eq0201TpList);
	}


	/**
	 * <pre>
	 *  위치 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0201LocTree.*")
	public void retrieveEQ0201LocTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0201LocList = service.retrieveEQ0201LocTree(map);
		response.setList("ds_EQ0201LocTree", eq0201LocList);
	}
	/**
	 * <pre>
	 *  종류 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0201List.*")
	public void retrieveEQ0201List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0201List = service.retrieveEQ0201List(map);
		response.setList("ds_EQ0201List", eq0201List);
	}


	/**
	 * <pre>
	 *  종류 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveEQ0201.*")
	public void saveEQ0201(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> eq0201List= request.getGridData("ds_EQ0201List", HashMap.class); //종류 정보;

		service.saveEqProcessAll(eq0201List,map);
		//List<Map<String, Object>> rslEQ0201PrcsList = service.retrieveEQ0201PrcsTree(map);
		//List<Map<String, Object>> rslEQ0201TpList = service.retrieveEQ0201TpTree(map);
		//List<Map<String, Object>> rslEQ0201LocList = service.retrieveEQ0201LocTree(map);
		//response.setList("ds_EQ0201PrcsTree", rslEQ0201PrcsList);
		//response.setList("ds_EQ0201TpTree", rslEQ0201TpList);
		//response.setList("ds_EQ0201LocTree", rslEQ0201LocList);
	}

	/**
	 * <pre>
	 *  설비종류 전력효율 등록/수정
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("savePowerEfficient.*")
	public void savePowerEfficient(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> eq0201List= request.getGridData("ds_EQ0201List", HashMap.class); //종류 정보;

		service.savePowerEfficient(eq0201List);
	}

	/**
	 * <pre>
	 *  종류 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveEQ0201Move.*")
	public void saveEQ0201Move(BqsRequest request, BqsResponse response) {

		GridData<HashMap> eq0201Move= request.getGridData("ds_EQ0201Move", HashMap.class);
		service.saveEQ0201Move(eq0201Move);
	}

	/**
	 * <pre>
	 *  설비분류 리비전 마스터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0202MList.*")
	public void retrieveEQ0202MList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0202MList = service.retrieveEQ0202MList(map);
		response.setList("ds_EQ0202MList", eq0202MList);
	}

	@RequestMapping("insertEQ0202.*")
	public void insertEQ0202(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.insertEQ0202(map);
	}

	/**
	 * <pre>
	 *  공정 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0201PrcsTreePop.*")
	public void retrieveEQ0201PrcsTreePop(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0201PrcsList = service.retrieveEQ0201PrcsTreePop(map);
		response.setList("ds_EQ0201PrcsTree", eq0201PrcsList);
	}

	/**
	 * <pre>
	 *  종류 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0201TpTreePop.*")
	public void retrieveEQ0201TpTreePop(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0201TpList = service.retrieveEQ0201TpTreePop(map);
		response.setList("ds_EQ0201TpTree", eq0201TpList);
	}


	/**
	 * <pre>
	 *  위치 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0201LocTreePop.*")
	public void retrieveEQ0201LocTreePop(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0201LocList = service.retrieveEQ0201LocTreePop(map);
		response.setList("ds_EQ0201LocTree", eq0201LocList);
	}

	@RequestMapping("retrieveLineCd.*")
	public void retrieveLineCd(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_LineCd = service.retrieveLineCd(map);
		response.setList("ds_LineCd", ds_LineCd);
	}


}
