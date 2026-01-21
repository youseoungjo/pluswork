package com.bplus.pw.pt.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.pt.service.PT1901Service;

import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**2019-07-30
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : PT1901Controller
 * Description : 품목정보 조회를 위한 controller 클래스
 * Author : SDS
 * Created Date : 2022-12-08
 * History
 * ---------------------------------------------------------------
 * Updated Date          Name    Reason
 * ---------------------------------------------------------------
 *
 * ---------------------------------------------------------------
 * </pre>
 * @author sds
 * @version 1.0
 */
@Controller
@RequestMapping("/pw/pt/*")
public class PT1901Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(PT1901Controller.class);

	@Autowired
	private PT1901Service service;

	/**
	 * <pre>
	 *  품목정보 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrievePT1901List.*")
	public void retrievePT1901List(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> pt1901List = service.retrievePT1901List(map);
		response.setList("ds_PT1901List", pt1901List);

	}

}
