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
 * System Class : 작업계획서관리
 * Program Name : WO0201Service
 * Description : 작업계획서 등록/수정/삭제를 위한 @Service 클래스
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
public class WO0201Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(WO0201Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 설비분류 트리 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0201Tree(Map<String, Object> data) {
		return dao.queryForMapList("wo0201.retrieveWO0201Tree", data);
	}

	/**
	 * 작업계획서 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0201List(Map<String, Object> data) {
		return dao.queryForMapList("wo0201.retrieveWO0201List", data);
	}

	/**
	 * 트리에서 작업계획서 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0201ListToTree(Map<String, Object> data) {
		return dao.queryForMapList("wo0201.retrieveWO0201ListToTree", data);
	}

	/**
	 * 작업 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoJobList(Map<String, Object> data) {
		return dao.queryForMapList("wo0201.retrieveWoJobList", data);
	}

	/**
	 * Craft 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoCraftList(Map<String, Object> data) {
		return dao.queryForMapList("wo0201.retrieveWoCraftList", data);
	}

	/**
	 * 경비 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0201PList(Map<String, Object> data) {
		return dao.queryForMapList("wo0201.retrieveWO0201PList", data);
	}

	/**
	 * W/O No별 자재 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoMaterialList(Map<String, Object> data) {
		return dao.queryForMapList("wo0201.retrieveWoMaterialList", data);
	}

	/**
	 * 1. 상세정보 Save
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveWoProcessAll(
			GridData<HashMap> wo0201List,
			GridData<HashMap> jobList,GridData<HashMap> craftList,GridData<HashMap> materialList) {


		saveWO0201(wo0201List); //설계상세save
		saveJob(jobList); //작업Save
		saveCraft(craftList); //작업Save
		saveMaterial(materialList); //작업Save

	}
	/**
	 * 작업계획서  추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveWO0201(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0201.insertWO0201", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0201.updateWO0201", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0201.deleteWO0201", record);
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
				dao.update("wo0201.insertJob", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0201.updateJob", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0201.deleteJob", record);
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
				dao.update("wo0201.insertCraft", record);

				dao.update("wo0201.updateTotalCraftCost", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0201.updateCraft", newRecord);
				dao.update("wo0201.updateTotalCraftCost", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0201.deleteCraft", record);
				dao.update("wo0201.updateTotalCraftCost", record);

			}
		});
	}

	/**
	 * 기타경비 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveMaterial(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				dao.update("wo0201.insertMaterial", record);
				dao.update("wo0201.updateWO0201Pt", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0201.updateMaterial", newRecord);
				dao.update("wo0201.updateWO0201Pt", newRecord);
			}

			public void delete(HashMap record, int index) {
				record.put("fnlEditUserId", UserInfo.getUserId());
				dao.update("wo0201.deleteMaterial", record);
				dao.update("wo0201.updateWO0201Pt", record);
			}
		});

	}


	/**
	 * 기타경비 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void updatePlanConfirmWO0201(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fnlEditUserId", UserInfo.getUserId());
				dao.update("wo0201.updatePlanConfirmWO0201", record);
			}
		});

	}

	/**
	 * 작업설계서 상세 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0201Detail(Map<String, Object> data) {
		return dao.queryForMapList("wo0201.retrieveWO0201Detail", data);
	}

	public List<Map<String, Object>> retrieveStNo2(Map<String, Object> data) {
		return dao.queryForMapList("wo0201.retrieveStNo2", data);
	}

	public List<Map<String, Object>> retrieveStNo3(Map<String, Object> data) {
		return dao.queryForMapList("wo0201.retrieveStNo3", data);
	}

	public List<Map<String, Object>> retrieveStNo4(Map<String, Object> data) {
		return dao.queryForMapList("wo0201.retrieveStNo4", data);
	}
}