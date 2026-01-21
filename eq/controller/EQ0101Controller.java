package com.bplus.pw.eq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.eq.service.EQ0101Service;
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
 * Program Name : EQ0101Controller
 * Description : 설비 등록/수정/삭제를 위한 controller 클래스
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
public class EQ0101Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(EQ0101Controller.class);

	@Autowired
	private EQ0101Service service;
	@Autowired
	private EQ0201Service service2;

	/**
	 * <pre>
	 *  설비 트리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101Tree.*")
	public void retrieveEQ0101Tree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0101List = service.retrieveEQ0101Tree(map);
		List<Map<String, Object>> eq0101TpList = service2.retrieveEQ0201TpTree(map);
		List<Map<String, Object>> eq0101LocList = service2.retrieveEQ0201LocTree(map);
		response.setList("ds_EQ0101Tree", eq0101List);
		response.setList("ds_EQ0101TpTree", eq0101TpList);
		response.setList("ds_EQ0101LocTree", eq0101LocList);

	}

	/**
	 * <pre>
	 *  설비 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101List.*")
	public void retrieveEQ0101List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0101List = service.retrieveEQ0101List(map);
		response.setList("ds_EQ0101List", eq0101List);
	}

	/**
	 * <pre>
	 *  설비트리에서 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101ListToTree.*")
	public void retrieveEQ0101ListToTree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0101List = service.retrieveEQ0101ListToTree(map);
		response.setList("ds_EQ0101List", eq0101List);
	}

	/**
	 * <pre>
	 *  스펙 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101SpecList.*")
	public void retrieveEQ0101SpecList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> specList = service.retrieveSpecList(map);
		response.setList("ds_SpecList", specList);

	}

	/**
	 * <pre>
	 *  작업이력 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101WoHist.*")
	public void retrieveEQ0101WoHist(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> woHist = service.retrieveWoHist(map);
		response.setList("ds_WoHist", woHist);

	}

	/**
	 * <pre>
	 *  설비히스토리 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101EqHist.*")
	public void retrieveEQ0101EqHist(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> eqHist = service.retrieveEqHist(map);
		response.setList("ds_EqHist", eqHist);

	}

	/**
	 * <pre>
	 *  작업표준 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101EqStd.*")
	public void retrieveEQ0101EqStd(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> eqStd = service.retrieveEqStd(map);
		response.setList("ds_EqStd", eqStd);

	}

	/**
	 * <pre>
	 *  설비 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveEQ0101.*")
	public void saveEQ0101(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> eq0101List= request.getGridData("ds_EQ0101List", HashMap.class); //설비 정보;
		GridData<HashMap> specList= request.getGridData("ds_SpecList", HashMap.class); //스펙 정보;
		GridData<HashMap> eqStd= request.getGridData("ds_EqStd", HashMap.class); //작업표준 정보;
		GridData<HashMap> bomList= request.getGridData("ds_BomList", HashMap.class); //BOM 정보;
		GridData<HashMap> eq0108MList= request.getGridData("ds_EQ0108MList", HashMap.class); //자산정보;

		service.saveEqProcessAll(eq0101List,specList,eqStd, bomList, eq0108MList, map);
		List<Map<String, Object>> rslEQ0101List = service.retrieveEQ0101Tree(map);
		response.setList("ds_EQ0101Tree", rslEQ0101List);
	}

	/**
	 * <pre>
	 *  점검이력 Tab 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101InspHist.*")
	public void retrieveEQ0101InspHist(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> ds_InspHistList = service.retrieveInspectionHistory(map);
		response.setList("ds_InspHistList", ds_InspHistList);

	}

	/**
	 * <pre>
	 *  점검이력 Tab 마스터 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101Insp.*")
	public void retrieveEQ0101Insp(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> ds_InspList = service.retrieveInspection(map);
		response.setList("ds_InspList", ds_InspList);

	}

	/**
	 * <pre>
	 *  설비마스터 BOM 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101BomList.*")
	public void retrieveEQ0101BomList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> bomList = service.retrieveBomList(map);
		response.setList("ds_BomList", bomList);

	}

	@RequestMapping("retrieveEQ0101BomListN.*")
	public void retrieveEQ0101BomListN(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		List<Map<String, Object>> bomList = service.retrieveBomListN(map);
		response.setList("ds_BomList", bomList);

	}

	/**
	 * <pre>
	 *  Revision 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101RList.*")
	public void retrieveEQ0101RList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0101RList = service.retrieveEQ0101RList(map);
		response.setList("ds_EQ0101RList", eq0101RList);
	}

	/**
	 * <pre>
	 *  Revision 스펙 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0102RList.*")
	public void retrieveEQ0102RList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0102RList = service.retrieveEQ0102RList(map);
		response.setList("ds_EQ0102RList", eq0102RList);
	}

	/**
	 * <pre>
	 *  설비 공통규격 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0108MList.*")
	public void retrieveEQ0108MList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0108MList = service.retrieveEQ0108MList(map);
		response.setList("ds_EQ0108MList", eq0108MList);
	}

	/**
	 * <pre>
	 *  설비 공통규격 리비전 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0108RList.*")
	public void retrieveEQ0108RList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0108RList = service.retrieveEQ0108RList(map);
		response.setList("ds_EQ0108RList", eq0108RList);
	}


	/**
	 * <pre>
	 *  표준규격 적용 시 규격항목 데이터 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveEQ0101SpecItemList.*")
	public void retrieveEQ0101SpecItemList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> specItemList = service.retrieveSpecItemList(map);
		response.setList("ds_SpecItemList", specItemList);
	}

	@RequestMapping("retrieveEQ0101Detail.*")
	public void retrieveEQ0101Detail(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> eq0101RList = service.retrieveEQ0101Detail(map);
		response.setList("ds_EQ0101Detail", eq0101RList);

	}

	/**
	 * <pre>
	 *  설비 리비전 삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("deleteEq0101R.*")
	public void deleteEq0101R(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		service.deleteEq0101R(map);
	}

}
