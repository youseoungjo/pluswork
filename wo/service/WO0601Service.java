package com.bplus.pw.wo.service;

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
 * System Class : 기술문서관리
 * Program Name : WO0601Service
 * Description : 기술문서 등록/수정/삭제를 위한 @Service 클래스
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
public class WO0601Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(WO0601Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 기술문서 트리 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0601Tree(Map<String, Object> data) {
		return dao.queryForMapList("wo0601.retrieveWO0601Tree", data);
	}

	/**
	 * 기술문서 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0601List(Map<String, Object> data) {
		return dao.queryForMapList("wo0601.retrieveWO0601List", data);
	}

	/**
	 * 작업 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoRJobList(Map<String, Object> data) {
		return dao.queryForMapList("wo0601.retrieveWoRJobList", data);
	}

	/**
	 * Craft 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoRCraftList(Map<String, Object> data) {
		return dao.queryForMapList("wo0601.retrieveWoRCraftList", data);
	}


	/**
	 * 현상 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoSymList(Map<String, Object> data) {
		return dao.queryForMapList("wo0601.retrieveWoSymList", data);
	}

	/**
	 * 조치 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoResultList(Map<String, Object> data) {
		return dao.queryForMapList("wo0601.retrieveWoResultList", data);
	}

	/**
	 * 1. 상세정보 Save
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveWoProcessAll(
			GridData<HashMap> wo0601List,
			GridData<HashMap> jobList,GridData<HashMap> craftList,
			GridData<HashMap> symList,GridData<HashMap> resultList) {


		saveWO0601(wo0601List); //설계상세save
		saveJob(jobList); //작업Save
		saveCraft(craftList); //작업Save

		saveSym(symList); //현상Save
		saveResult(resultList); //조치Save

	}
	/**
	 * 기술문서 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveWO0601(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0601.insertWO0601", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0601.updateWO0601", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0601.deleteWO0601", record);
			}
		});
	}





	/**
	 * 작업 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveJob(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				record.put("orgCd", UserInfo.getOrgCd());
				dao.update("wo0601.insertJob", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0601.updateJob", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0601.deleteJob", record);
			}
		});
	}

	/**
	 * 인건비 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveCraft(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				record.put("orgCd", UserInfo.getOrgCd());
				dao.update("wo0601.insertCraft", record);

				dao.update("wo0601.updateWO0601Craft", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0601.updateCraft", newRecord);
				dao.update("wo0601.updateWO0601Craft", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0601.deleteCraft", record);
				dao.update("wo0601.updateWO0601Craft", record);

			}
		});
	}


	/**
	 * 현상 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveSym(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				record.put("orgCd", UserInfo.getOrgCd());
				record.put("woDiv", "WO0601");
				dao.update("wo0601.insertSym", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());
				newRecord.put("woDiv", "WO0601");

				dao.update("wo0601.updateSym", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0601.deleteSym", record);
			}
		});

	}

	/**
	 * 조치 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveResult(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				record.put("orgCd", UserInfo.getOrgCd());
				dao.update("wo0601.insertResult", record);

			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0601.updateResult", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0601.deleteResult", record);
			}
		});

	}


	/**
	 * 작업취소/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveWoCancelWO0601(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fnlEditUserId", UserInfo.getUserId());
				dao.update("wo0601.updateWoCancelWO0601", record);
			}
		});

	}

	/**
	 * 작업결과서 상세 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0601Detail(Map<String, Object> data) {
		return dao.queryForMapList("wo0601.retrieveWO0601Detail", data);
	}
}