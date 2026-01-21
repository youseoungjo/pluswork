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
 * System Class : 설비별 점검데이터 검색
 * Program Name : IN0201Service
 * Description : 설비별 점검데이터 검색을 위한 @Service 클래스
 * Author : jit13
 * Created Date : 2019-07-18
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
public class IN0201Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0201Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 점검데이터 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0201(Map<String, Object> data) {
		return dao.queryForMapList("in0201.retrieveIN0201", data);
	}

	/**
	 * 트리에서 점검데이터 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0201ToTree(Map<String, Object> data) {
		return dao.queryForMapList("in0201.retrieveIN0201ToTree", data);
	}

	/**
	 * 설비별 점검데이터 상세목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0201Sub(Map<String, Object> data) {
		return dao.queryForMapList("in0201.retrieveIN0201Sub", data);
	}
}
