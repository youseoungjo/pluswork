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
 * Program Name : CO0201DService
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
public class CO0201Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(CO0201Service.class);

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
	public List<Map<String, Object>> retrieveCO0201MList(Map<String, Object> data) {
		return dao.queryForMapList("co0201.retrieveCO0201MList", data);
	}

	/**
	 * 공통코드 그룹 목록 추가/수정
	 *
	 * @return void
	 */
	public void saveCO0201M(GridData<HashMap>  gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0201.insertCO0201M", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0201.updateCO0201M", newRecord);
			}


			public void delete(HashMap record, int index) {
				dao.update("co0201.deleteCO0201M", record);
				dao.update("co0201.deleteCO0201MByCO0201D", record);
			}
		});
	}

	/**
	 * 공통코드 Code 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveCO0201DList(Map<String, Object> data) {
		return dao.queryForMapList("co0201.retrieveCO0201DList", data);
	}

	/**
	 * 공통코드 Code 목록 추가/수정/삭제
	 *
	 * @return void
	 */
	public void saveCO0201D(GridData<HashMap>  gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0201.insertCO0201D", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("co0201.updateCO0201D", newRecord);
			}


			public void delete(HashMap record, int index) {
				dao.update("co0201.deleteCO0201D", record);
			}
		});
	}

	/**
	 * 공통코드 그룹/Code 목록 추가/수정/삭제
	 *
	 * @return void
	 */
	public void saveCO0201(GridData<HashMap>  co0201MList, GridData<HashMap>  co0201DList) {
		saveCO0201M(co0201MList);
		saveCO0201D(co0201DList);
	}
}
