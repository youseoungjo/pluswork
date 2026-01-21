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
import idr.common.code.CodeCache;
import idr.extend.core.exception.BizException;
import idr.extend.core.mvc.context.WebContext;
import idr.extend.iam.UserInfo;
import idr.extend.query.CommonDao;
import idr.extend.query.callback.AbstractRowStatusCallback;
import idr.extend.util.SpringUtil;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class : 공통모듈
 * Program Name : CO0301DService
 * Description : 공통코드 등록/수정/삭제를 위한 @Service 클래스
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
public class CO0301Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(CO0301Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 공통코드 그룹 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0301MList(Map<String, Object> data) {
		return dao.queryForMapList("co0301.retrieveCO0301MList", data);
	}

	/**
	 * 공통코드 그룹 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0301CList(Map<String, Object> data) {
		return dao.queryForMapList("co0301.retrieveCO0301CList", data);
	}

	/**
	 * 공통코드 그룹 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0301JList(Map<String, Object> data) {
		return dao.queryForMapList("co0301.retrieveCO0301JList", data);
	}

	/**
	 * 공통코드 그룹 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0301PList(Map<String, Object> data) {
		return dao.queryForMapList("co0301.retrieveCO0301PList", data);
	}

	/**
	 * 공통코드 그룹 목록 추가/수정
	 *
	 * @return void
	 */
	public void saveCO0301M(GridData<HashMap>  gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0301.insertCO0301M", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0301.updateCO0301M", newRecord);
			}


			public void delete(HashMap record, int index) {
				dao.update("co0301.deleteCO0301M", record);
				dao.update("co0301.deleteCO0301MByCO0301C", record);
				dao.update("co0301.deleteCO0301MByCO0301J", record);
				dao.update("co0301.deleteCO0301MByCO0301P", record);
			}
		});
	}

	/**
	 * 공통코드 Code 목록 추가/수정/삭제
	 *
	 * @return void
	 */
	public void saveCO0501C(GridData<HashMap>  gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0301.insertCO0501C", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0301.updateCO0501C", newRecord);
			}


			public void delete(HashMap record, int index) {
				dao.update("co0301.deleteCO0501C", record);
			}
		});
	}

	/**
	 * 공통코드 Code 목록 추가/수정/삭제
	 *
	 * @return void
	 */
	public void saveCO0501J(GridData<HashMap>  gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0301.insertCO0501J", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0301.updateCO0501J", newRecord);
			}


			public void delete(HashMap record, int index) {
				dao.update("co0301.deleteCO0501J", record);
			}
		});
	}

	/**
	 * 공통코드 Code 목록 추가/수정/삭제
	 *
	 * @return void
	 */
	public void saveCO0501P(GridData<HashMap>  gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0301.insertCO0501P", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0301.updateCO0501P", newRecord);
			}


			public void delete(HashMap record, int index) {
				dao.update("co0301.deleteCO0501P", record);
			}
		});
	}

	/**
	 * 공통코드 그룹/Code 목록 추가/수정/삭제
	 *
	 * @return void
	 */
	public void saveCO0301(GridData<HashMap>  co0301MList, GridData<HashMap>  co0301CList, GridData<HashMap>  co0301JList, GridData<HashMap>  co0301PList) {
		saveCO0301M(co0301MList);
		saveCO0501C(co0301CList);
		saveCO0501J(co0301JList);
		saveCO0501P(co0301PList);
	}

	/**
	 * W/O에 저장된 작업표준-작업 리스트 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoJobList(Map<String, Object> data) {
		return dao.queryForMapList("co0301.retrieveWoJobList", data);
	}

	/**
	 * W/O에 저장된 작업표준-인건비 리스트 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoCraftList(Map<String, Object> data) {
		return dao.queryForMapList("co0301.retrieveWoCraftList", data);
	}

	/**
	 * W/O에 저장된 작업표준-자재비 리스트 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoMaterialList(Map<String, Object> data) {
		return dao.queryForMapList("co0301.retrieveWoMaterialList", data);
	}

	/**
	 * W/O에 저장된 작업표준
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoStdPopList(Map<String, Object> data) {
		return dao.queryForMapList("co0301.retrieveWoStdPopList", data);
	}
}
