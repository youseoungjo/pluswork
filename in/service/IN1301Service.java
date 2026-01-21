package com.bplus.pw.in.service;

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
 * System Class : 담당자별 예방점검 조회
 * Program Name : IN1301Service
 * Description : 땀땅짜뼐 점검데이터 검색을 위한 @Service 클래스
 * Author : sds
 * Created Date : 2022-03-10
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
public class IN1301Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN1301Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 담당자별 예방점검 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN1301List(Map<String, Object> data) {
		return dao.queryForMapList("in1301.retrieveIN1301List", data);
	}

}
