package com.bplus.pw.co.service;

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
 * System Class : 공통모듈
 * Program Name : CO0501Service
 * Description : 현상코드 등록/수정/삭제를 위한 @Service 클래스
 * Author : Jun.
 * Created Date : 2020-10-14
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
public class CO0501Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(CO0501Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 현상코드 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0501List(Map<String, Object> data) {
		List<Map<String, Object>> co0501List = dao.queryForMapList("co0501.retrieveCO0501List", data);
		return co0501List;
	}
	
	/**
	 * 원인코드 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0502List(Map<String, Object> data) {
		List<Map<String, Object>> co0502List = dao.queryForMapList("co0501.retrieveCO0502List", data);
		return co0502List;
	}
	
	/**
	 * 조치코드 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0503List(Map<String, Object> data) {
		List<Map<String, Object>> co0503List = dao.queryForMapList("co0501.retrieveCO0503List", data);
		return co0503List;
	}
	
	/**
	 * 현상코드 추가/수정
	 *
	 * @return void
	 */
	public void saveCO0501(GridData<HashMap>  gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0501.insertCO0501", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0501.updateCO0501", newRecord);
			}


			public void delete(HashMap record, int index) {
				dao.update("co0501.deleteCO0501", record);
			}
		});
	}
	
	/**
	 * 현상트리 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0502Tree(Map<String, Object> data) {
		List<Map<String, Object>> co0502Tree = dao.queryForMapList("co0501.retrieveCO0502Tree", data);
		return co0502Tree;
	}
	
	/**
	 * 원인트리 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0503Tree(Map<String, Object> data) {
		List<Map<String, Object>> co0503Tree = dao.queryForMapList("co0501.retrieveCO0503Tree", data);
		return co0503Tree;
	}
	
	/**
	 * 현상팝업 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0501PopupList(Map<String, Object> data) {
		List<Map<String, Object>> co0501List = dao.queryForMapList("co0501.retrieveCO0501PopupList", data);
		return co0501List;
	}
	
	/**
	 * 원인팝업 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0502PopupList(Map<String, Object> data) {
		List<Map<String, Object>> co0502List = dao.queryForMapList("co0501.retrieveCO0502PopupList", data);
		return co0502List;
	}
	
	/**
	 * 조치팝업 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0503PopupList(Map<String, Object> data) {
		List<Map<String, Object>> co0503List = dao.queryForMapList("co0501.retrieveCO0503PopupList", data);
		return co0503List;
	}

	
}
