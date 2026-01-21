package com.bplus.pw.in.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.in.service.IN0301Service;
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
 * Program Name : IN0301Controller
 * Description : 점검일정 생성 위한 controller 클래스
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
@RequestMapping("/pw/in/*")
public class IN0301Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0301Controller.class);

	@Autowired
	private IN0301Service service;

	/**
	 * <pre>
	 *  설비별 점검데이터 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0301.*")
	public void retrieveIN0301(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0301List = service.retrieveIN0301(map);
		response.setList("ds_IN0301List", ds_IN0301List);
	}
	/**
	 * <pre>
	 *  점검일정합계 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0301Total.*")
	public void retrieveIN0301Total(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0301TotalList = service.retrieveIN0301Total(map);
		response.setList("ds_IN0301TotalList", ds_IN0301TotalList);
	}
	/**
	 * <pre>
	 *  담당자별 점검 수량
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveIN0301Sub.*")
	public void retrieveIN0301Sub(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0301SubList = service.retrieveIN0301Sub(map);
		response.setList("ds_IN0301SubList", ds_IN0301SubList);
	}

	/**
	 * <pre>
	 *  점검결과 작성 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveIN0301ListActlYmd.*")
	public void saveIN0301ListActlYmd(BqsRequest request, BqsResponse response) {
		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_IN0301ListActlYmd = request.getGridData("ds_actlYmdList", HashMap.class);
		service.saveIN0301ListActlYmd(ds_IN0301ListActlYmd);
	}

	/**
	 * <pre>
	 *  전체일정 생성
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("createIN0301List.*")
	public void createIN0301List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0301List = service.createIN0301List(map);
		response.setList("ds_IN0301List", ds_IN0301List);
	}


	/* 계획일 하루전으로 변경 W/O 미생성 대상만 적용 */
	@RequestMapping("moveDayMinusIN0301.*")
	public void moveDayMinusIN0301(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		service.moveDayMinusIN0301(map);
	}

	/* 계획일 하루후로 변경 W/O 미생성 대상만 적용 */
	@RequestMapping("moveDayPlusIN0301.*")
	public void moveDayPlusIN0301(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());

		service.moveDayPlusIN0301(map);
	}

	/**
	 * <pre>
	 *  예방점검마스터 일정생성
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("createScheduleList.*")
	public void createScheduleList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_IN0301M = service.createScheduleList(map);
		response.setList("ds_IN0301M", ds_IN0301M);
	}

	/**
	 * <pre>
	 *  예방점검주기변경
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveIN0101Cycle.*")
	public void saveIN0101Cycle(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		GridData<HashMap> ds_IN0101Info= request.getGridData("ds_IN0101Info", HashMap.class);
		GridData<HashMap> ds_UserList = request.getGridData("ds_UserList", HashMap.class);

		service.saveProcess(ds_IN0101Info, ds_UserList);
	}

}
