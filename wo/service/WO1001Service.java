package com.bplus.pw.wo.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import idr.extend.query.CommonDao;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class : 작업완료현황검색
 * Program Name : WO1001Service
 * Description : 설비별 점검데이터 검색을 위한 @Service 클래스
 * Author : jit13
 * Created Date : 2019-07-24
 * History
 * ---------------------------------------------------------------
 * Updated Date          Name    Reason
 * ---------------------------------------------------------------
 *
 * ---------------------------------------------------------------
 * </pre>
 *
 * @version 1.0
 */
@Service
public class WO1001Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(WO1001Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 작업완료현황조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO1001(Map<String, Object> data) {
		return dao.queryForMapList("wo1001.retrieveWO1001", data);
	}

}
