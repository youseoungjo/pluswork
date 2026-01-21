package com.bplus.pw.st.service;

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
import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class : 점검데이터 작성
 * Program Name : ST0101Service
 * Description : 점검데이터 등록/수정/삭제를 위한 @Service 클래스
 * Author : jit13
 * Created Date : 2019-07-12
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
public class ST0101Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(ST0101Service.class);

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
	public List<Map<String, Object>> retrieveST0101Chart(Map<String, Object> data) {
		return dao.queryForMapList("st0101.retrieveST0101Chart", data);
	}

	public List<Map<String, Object>> retrieveST0101List(Map<String, Object> data) {
		return dao.queryForMapList("st0101.retrieveST0101List", data);
	}

	public List<Map<String, Object>> retrieveST0101SubList(Map<String, Object> data) {
		return dao.queryForMapList("st0101.retrieveST0101SubList", data);
	}
}
