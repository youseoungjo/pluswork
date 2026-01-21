package com.bplus.pw.in.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import idr.basis.data.GridData;
import idr.extend.iam.UserInfo;
import idr.extend.query.CommonDao;
import idr.extend.query.callback.AbstractRowStatusCallback;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class : 점검결과 목록 검색
 * Program Name : IN0501Service
 * Description : 점검결과 검색을 위한 @Service 클래스
 * Author : jit13
 * Created Date : 2019-07-19
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
public class IN0501Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0501Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 점검결과 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0501(Map<String, Object> data) {
		return dao.queryForMapList("in0501.retrieveIN0501", data);
	}

	/**
	 * 트리에서 점검결과 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0501ToTree(Map<String, Object> data) {
		return dao.queryForMapList("in0501.retrieveIN0501ToTree", data);
	}

	/**
	 * 점검결과 항목 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCmplYmdList(Map<String, Object> data) {
		return dao.queryForMapList("in0501.retrieveIN0501SubCmplYmd", data);
	}


	/**
	 * 점검결과 항목 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0501Sub(Map<String, Object> data) {
		List<Map<String, Object>> cmplYmdList = dao.queryForMapList("in0501.retrieveIN0501SubCmplYmd", data);

		data.put("cmplYmdList",  cmplYmdList);

		return dao.queryForMapList("in0501.retrieveIN0501Sub", data);

	}
}
