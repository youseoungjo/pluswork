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
 * System Class : W/O 현황
 * Program Name : ST1401Service
 * Description :
 * Author : SDS
 * Created Date : 2021-11-18
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
public class ST1401Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(ST1401Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	public List<Map<String, Object>> retrieveST1401Chart1(Map<String, Object> data) {
		return dao.queryForMapList("st1401.retrieveST1401Chart1", data);
	}

	public List<Map<String, Object>> retrieveST1401Chart2(Map<String, Object> data) {
		return dao.queryForMapList("st1401.retrieveST1401Chart2", data);
	}

	public List<Map<String, Object>> retrieveST1401Chart3(Map<String, Object> data) {
		return dao.queryForMapList("st1401.retrieveST1401Chart3", data);
	}
	public List<Map<String, Object>> retrieveST1401List3(Map<String, Object> data) {
		return dao.queryForMapList("st1401.retrieveST1401List3", data);
	}

}
