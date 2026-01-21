package com.bplus.pw.co.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.co.service.CO0501Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**2020-10-14
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : CO0501Controller
 * Description : 현상코드 등록/수정/삭제를 위한 controller 클래스
 * Author : Jun.
 * Created Date : 2020-10-14
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
public class CO0501Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(CO0501Controller.class);

	@Autowired
	private CO0501Service service;

	/**
	 * <pre>
	 *  현상코드 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0501List.*")
	public void retrieveCO0501List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0501List = service.retrieveCO0501List(map);
		response.setList("ds_CO0501List", co0501List);

	}
	
	/**
	 * <pre>
	 *  원인코드 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0502List.*")
	public void retrieveCO0502List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0502List = service.retrieveCO0502List(map);
		response.setList("ds_CO0502List", co0502List);

	}
	
	/**
	 * <pre>
	 *  조치코드 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0503List.*")
	public void retrieveCO0503List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0503List = service.retrieveCO0503List(map);
		response.setList("ds_CO0503List", co0503List);

	}
	
	/**
	 * <pre>
	 *  현상코드 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveCO0501.*")
	public void saveCO0501(BqsRequest request, BqsResponse response) {
		GridData<HashMap> co0501List= request.getGridData("ds_CO0501List", HashMap.class); //양식 정보;
		service.saveCO0501(co0501List);
	}
	
	/**
	 * <pre>
	 *  원인코드 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveCO0502.*")
	public void saveCO0502(BqsRequest request, BqsResponse response) {
		GridData<HashMap> co0502List= request.getGridData("ds_CO0502List", HashMap.class); //양식 정보;
		service.saveCO0501(co0502List);
	}
	
	/**
	 * <pre>
	 *  조치코드 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveCO0503.*")
	public void saveCO0503(BqsRequest request, BqsResponse response) {
		GridData<HashMap> co0503List= request.getGridData("ds_CO0503List", HashMap.class); //양식 정보;
		service.saveCO0501(co0503List);
	}
	
	/**
	 * <pre>
	 *  현상트리 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0502Tree.*")
	public void retrieveCO0502Tree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0502Tree = service.retrieveCO0502Tree(map);
		response.setList("ds_CO0502Tree", co0502Tree);

	}
	
	/**
	 * <pre>
	 *  원인트리 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0503Tree.*")
	public void retrieveCO0503Tree(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0503Tree = service.retrieveCO0503Tree(map);
		response.setList("ds_CO0503Tree", co0503Tree);

	}
	
	/**
	 * <pre>
	 *  현상팝업 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0501PopupList.*")
	public void retrieveCO0501PopupList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0501List = service.retrieveCO0501PopupList(map);
		response.setList("ds_CO0501List", co0501List);

	}
	
	/**
	 * <pre>
	 *  원인팝업 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0502PopupList.*")
	public void retrieveCO0502PopupList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0502List = service.retrieveCO0502PopupList(map);
		response.setList("ds_CO0502List", co0502List);

	}
	
	/**
	 * <pre>
	 *  조치팝업 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveCO0503PopupList.*")
	public void retrieveCO0503PopupList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> co0503List = service.retrieveCO0503PopupList(map);
		response.setList("ds_CO0503List", co0503List);

	}

}
