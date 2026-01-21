package com.bplus.pw.kc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.kc.service.KC0101Service;
import com.bplus.pw.kc.service.KC0201Service;
import com.bplus.pw.wo.service.WO0301Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : WO0301Controller
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
@RequestMapping("/pw/kc/*")
public class KC0101Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(KC0101Controller.class);

	@Autowired
	private KC0101Service service;

	/**
	 * <pre>
	 *  고장설계 통계
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveKC0101ChartData.*")
	public void retrieveKC0101ChartData(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> kc0101ChartData = service.retrieveKC0101ChartData(map);
		response.setList("ds_KC0101ChartData", kc0101ChartData);
	}

	/**
	 * <pre>
	 *  고장설계 통계
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveKC0101ChartDetail.*")
	public void retrieveKC0101ChartDetail(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> kc0101ChartDetail = service.retrieveKC0101ChartDetail(map);
		response.setList("ds_KC0101ChartDetail", kc0101ChartDetail);
	}
}
