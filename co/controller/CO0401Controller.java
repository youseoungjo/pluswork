package com.bplus.pw.co.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bplus.pw.co.service.CO0401Service;

import idr.basis.data.GridData;
import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;

/**2019-07-30
 * <pre>
 * ---------------------------------------------------------------
 * System Class :
 * Program Name : CO0401Controller
 * Description : 거래처 등록/수정/삭제를 위한 controller 클래스
 * Author : Jun.
 * Created Date : 2019-07-30
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
public class CO0401Controller {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(CO0401Controller.class);

	@Autowired
	private CO0401Service service;

	/**
	 * <pre>
	 *  거래처 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveTradeList.*")
	public void retrieveTradeList(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> tradeList = service.retrieveTradeList(map);
		response.setList("ds_TradeList", tradeList);

	}

	/**
	 * <pre>
	 *  거래처Code 목록 추가/수정/삭제
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("saveTrade.*")
	public void saveTrade(BqsRequest request, BqsResponse response) {
		GridData<HashMap> tradeList= request.getGridData("ds_TradeList", HashMap.class); //양식 정보;
		service.saveTrade(tradeList);
	}
	
	/**
	 * <pre>
	 *  거래처 팝업 : 목록 조회
	 * </pre>
	 * @param request
	 * @param response
	 */
	@RequestMapping("retrieveTradeListPopup.*")
	public void retrieveTradeListPopup(BqsRequest request, BqsResponse response) {

		Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
		List<Map<String, Object>> tradeList = service.retrieveTradeListPopup(map);
		response.setList("ds_TradePoupList", tradeList);

	}
}
