package com.bplus.pw.af.service;

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
 * System Class : 작업요청관리
 * Program Name : AF0201Service
 * Description : 작업요청 등록/수정/삭제를 위한 @Service 클래스
 * Author : Jun.
 * Created Date : 2013-12-10
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
public class AF0201Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(AF0201Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 작업요청 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveAF0201List(Map<String, Object> data) {
		return dao.queryForMapList("af0201.retrieveAF0201List", data);
	}

	public List<Map<String, Object>> retrieveAF0201SubList(Map<String, Object> data) {
		return dao.queryForMapList("af0201.retrieveAF0201SubList", data);
	}

	/**
	 * 결재상세정보-예방정비 리비전 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveMT0101Detail(Map<String, Object> data) {
		return dao.queryForMapList("af0201.retrieveMT0101Detail", data);
	}
}