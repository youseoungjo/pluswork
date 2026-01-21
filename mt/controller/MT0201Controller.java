package com.bplus.pw.mt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.mt.service.MT0201Service;
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
 * Program Name : MT0201Controller
 * Description : 점비일정 생성 위한 controller 클래스
 * Author : jit13.
 * Created Date : 2019-07-29
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
public class MT0201Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(MT0201Controller.class);

	@Autowired
	private MT0201Service service;

	/**
	 * <pre>
	 *  설비별 점검데이터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveMT0201.*")
	public void retrieveMT0201(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0201List = service.retrieveMT0201(map);
		response.setList("ds_MT0201List", ds_MT0201List);
	}

	/**
	 * <pre>
	 *  점검결과 작성 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveMT0201ListPlanYmd.*")
	public void saveMT0201ListPlanYmd(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_MT0201ListPlanYmd = request.getGridData("ds_planYmdList", HashMap.class);
		service.saveMT0201ListPlanYmd(ds_MT0201ListPlanYmd);
	}

	/**
	 * <pre>
	 *  전체일정 생성
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("createMT0201List.*")
	public void createMT0201List(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0201List = service.createMT0201List(map);
		response.setList("ds_MT0201List", ds_MT0201List);
	}

	@RequestMapping("retrieveMT0201ListPlan.*")
	public void retrieveMT0201ListPlan(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0201ListPlan = service.retrieveMT0201ListPlan(map);
		response.setList("ds_MT0201ListPlan", ds_MT0201ListPlan);
	}

	@RequestMapping("createMT0201WoNo.*")
	public void createMT0201WoNo(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_MT0201ListWoNo = service.createMT0201WoNo(map);
		response.setList("ds_MT0201ListWoNo", ds_MT0201ListWoNo);
	}

	/* 계획일 하루전으로 변경 W/O 미생성 대상만 적용 */
	@RequestMapping("moveDayMT0201.*")
	public void moveDayMT0201(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		service.moveDayMT0201(map);
	}

	/* 계획일 하루전으로 변경 W/O 미생성 대상만 적용 */
	@RequestMapping("moveDayMinusMT0201.*")
	public void moveDayMinusMT0201(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		service.moveDayMinusMT0201(map);
	}

	/* 계획일 하루후로 변경 W/O 미생성 대상만 적용 */
	@RequestMapping("moveDayPlusMT0201.*")
	public void moveDayPlusMT0201(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		service.moveDayPlusMT0201(map);
	}

}
