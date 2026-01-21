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
 * Program Name : CO0101DService
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
public class CO0101Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(CO0101Service.class);

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
	public List<Map<String, Object>> retrieveCO0101MList(Map<String, Object> data) {
		return dao.queryForMapList("co0101.retrieveCO0101MList", data);
	}

	/**
	 * 공통코드 그룹 목록 추가/수정
	 *
	 * @return void
	 */
	public void saveCO0101M(GridData<HashMap>  gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0101.insertCO0101M", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0101.updateCO0101M", newRecord);
			}


			public void delete(HashMap record, int index) {
				dao.update("co0101.deleteCO0101M", record);
				dao.update("co0101.deleteCO0101MByCO0101D", record);
			}
		});
	}

	/**
	 * 공통코드 Code 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0101DList(Map<String, Object> data) {
		return dao.queryForMapList("co0101.retrieveCO0101DList", data);
	}

	/**
	 * 공통코드 Code 목록 추가/수정/삭제
	 *
	 * @return void
	 */
	public void saveCO0101D(GridData<HashMap>  gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0101.insertCO0101D", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0101.updateCO0101D", newRecord);
			}


			public void delete(HashMap record, int index) {
				dao.update("co0101.deleteCO0101D", record);
			}
		});
	}

	/**
	 * 공통코드 그룹/Code 목록 추가/수정/삭제
	 *
	 * @return void
	 */
	public void saveCO0101(GridData<HashMap>  co0101MList, GridData<HashMap>  co0101DList) {
		saveCO0101M(co0101MList);
		saveCO0101D(co0101DList);
	}

	/**
	 * SPEC 팝업 - 표준규격(SPEC) 데이터 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveStdSpecList(Map<String, Object> data) {
		return dao.queryForMapList("co0101.retrieveStdSpecList", data);
	}

	/**
	 * SPEC 팝업 - 규격항목 데이터 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveSpecItemList(Map<String, Object> data) {
		return dao.queryForMapList("co0101.retrieveSpecItemList", data);
	}
}
