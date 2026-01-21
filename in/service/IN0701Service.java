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
 * System Class : 미점검 리스트 검색
 * Program Name : IN0701Service
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
public class IN0701Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0701Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 미점검 리스트 검색
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0701(Map<String, Object> data) {
		return dao.queryForMapList("in0701.retrieveIN0701", data);
	}

	/**
	 * 트리에서 미점검 리스트 검색
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0701ToTree(Map<String, Object> data) {
		return dao.queryForMapList("in0701.retrieveIN0701ToTree", data);
	}

	/**
	 * 점검계획일자 재지정
	 *
	 * @return List<Map<String, Object>>
	 */
	public void updateIN0701List(Map<String, Object> data) {
		data.put("fstRegUserId", UserInfo.getUserId());
		data.put("fnlEditUserId", UserInfo.getUserId());

		dao.update("in0701.updateIN0701List", data);

	}

	/**
	 * 미점검확인
	 *
	 * @return List<Map<String, Object>>
	 */
	public void updateIN0701List2(Map<String, Object> data) {
		data.put("fstRegUserId", UserInfo.getUserId());
		data.put("fnlEditUserId", UserInfo.getUserId());

		dao.update("in0701.updateIN0701List2", data);

	}


}
