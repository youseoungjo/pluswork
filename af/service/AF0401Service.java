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
 * System Class : 종류관리
 * Program Name : AF0401Service
 * Description : 종류 등록/수정/삭제를 위한 @Service 클래스
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
public class AF0401Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(AF0401Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 종류 트리 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveAF0401Tree(Map<String, Object> data) {
		return dao.queryForMapList("af0401.retrieveAF0401Tree", data);
	}

	/**
	 * 종류 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveAF0401List(Map<String, Object> data) {
		return dao.queryForMapList("af0401.retrieveAF0401List", data);
	}

	/**
	 * 종류 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveAF0401Flow(Map<String, Object> data) {
//		data.put("userId", UserInfo.getUserId());
//		data.put("orgCd", UserInfo.getOrgCd());
		data.put("companyCd", UserInfo.getCompanyCd());
		return dao.queryForMapList("af0401.retrieveAF0401Flow", data);
	}

	/**
	 * 종류 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveAF0401Line(Map<String, Object> data) {
		data.put("userId", UserInfo.getUserId());
		data.put("orgCd", UserInfo.getOrgCd());
		data.put("companyCd", UserInfo.getCompanyCd());
		return dao.queryForMapList("af0401.retrieveAF0401Line", data);
	}

	/**
	 * 1. 상세정보 Save
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveEqProcessAll(
			GridData<HashMap> af0401Flow,GridData<HashMap> af0401Line) {

		saveAF0401Flow(af0401Flow); //종류save
		saveAF0401Line(af0401Line); //종류save
	}
	/**
	 * 종류 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveAF0401Flow(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("af0401.insertAF0401Flow", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("af0401.updateAF0401Flow", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("af0401.deleteAF0401Flow", record);
			}
		});
	}

	/**
	 * 종류 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveAF0401Line(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("af0401.insertAF0401Line", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("af0401.updateAF0401Line", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("af0401.deleteAF0401Line", record);
			}
		});
	}

}