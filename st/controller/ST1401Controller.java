package com.bplus.pw.st.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.st.service.ST1401Service;

import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : ST1401Controller
 * Description : W/O 현황
 * Author : SDS
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
@RequestMapping("/pw/st/*")
public class ST1401Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(ST1401Controller.class);

	@Autowired
	private ST1401Service service;

	/**
	 * <pre>
	 *  담당자별 W/O 현황 차트
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveST1401Chart1.*")
	public void retrieveST1401Chart1(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST1401ListChart1 = service.retrieveST1401Chart1(map);
		response.setList("ds_ST1401ListChart1", ds_ST1401ListChart1);
	}
	/**
	 * <pre>
	 *  요일별 W/O 현황 차트
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveST1401Chart2.*")
	public void retrieveST1401Chart2(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST1401ListChart2 = service.retrieveST1401Chart2(map);
		response.setList("ds_ST1401ListChart2", ds_ST1401ListChart2);
	}
	/**
	 * <pre>
	 *  시간별 W/O 현황 차트
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveST1401Chart3.*")
	public void retrieveST1401Chart3(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> ds_ST1401ListChart3 = service.retrieveST1401Chart3(map);
		List<Map<String, Object>> ds_ST1401ListList3 = service.retrieveST1401List3(map);
		response.setList("ds_ST1401Chart3", ds_ST1401ListChart3);
		response.setList("ds_ST1401List3", ds_ST1401ListList3);
	}

}
