package com.bplus.pw.st.service;

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
 * System Class : 점검데이터 작성
 * Program Name : ST0201Service
 * Description : 작업요청서 추이 분석을 위한 @Service 클래스
 * Author : jit13
 * Created Date : 2020-04-13
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
public class ST0201Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(ST0201Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 점검작성 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveST0201Chart(Map<String, Object> data) {
		System.out.println("=========="+ data);
		data.put("fEndDate", data.get("fEndDate")+"-01");
		return dao.queryForMapList("st0201.retrieveST0201Chart", data);
	}

	public List<Map<String, Object>> retrieveST0201List(Map<String, Object> data) {
		data.put("fEndDate", data.get("fEndDate")+"-01");
		return dao.queryForMapList("st0201.retrieveST0201List", data);
	}

	public List<Map<String, Object>> retrieveST0201SubList(Map<String, Object> data) {
		data.put("fEndDate", data.get("fEndDate")+"-01");
		return dao.queryForMapList("st0201.retrieveST0201SubList", data);
	}

}
