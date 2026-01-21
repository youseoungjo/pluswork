package com.bplus.pw.wo.service;

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
 * System Class : 작업결과서
 * Program Name : WO0301Service
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
public class WO0301Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(WO0301Service.class);

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
	public List<Map<String, Object>> retrieveWO0301Tree(Map<String, Object> data) {
		data.put("userId", UserInfo.getUserId());
		return dao.queryForMapList("wo0301.retrieveWO0301Tree", data);
	}

	/**
	 * 작업결과서 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0301List(Map<String, Object> data) {
		return dao.queryForMapList("wo0301.retrieveWO0301List", data);
	}

	/**
	 * 트리에서 작업결과서 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0301ListToTree(Map<String, Object> data) {
		return dao.queryForMapList("wo0301.retrieveWO0301ListToTree", data);
	}

	/**
	 * 작업 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoRJobList(Map<String, Object> data) {
		return dao.queryForMapList("wo0301.retrieveWoRJobList", data);
	}

	/**
	 * Craft 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoRCraftList(Map<String, Object> data) {
		return dao.queryForMapList("wo0301.retrieveWoRCraftList", data);
	}

	/**
	 * 자재 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoRMaterialList(Map<String, Object> data) {
		return dao.queryForMapList("wo0301.retrieveWoRMaterialList", data);
	}

	/**
	 * 현상 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoSymList(Map<String, Object> data) {
		return dao.queryForMapList("wo0301.retrieveWoSymList", data);
	}

	/**
	 * 조치 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoResultList(Map<String, Object> data) {
		return dao.queryForMapList("wo0301.retrieveWoResultList", data);
	}

	/**
	 * 1. 상세정보 Save
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveWoProcessAll(
			GridData<HashMap> wo0301List,
			GridData<HashMap> jobList,GridData<HashMap> craftList,GridData<HashMap> materialList,
			GridData<HashMap> symList,GridData<HashMap> resultList) {

		saveWO0301(wo0301List); //설계상세save
		saveJob(jobList); //작업 Save
		saveCraft(craftList); //인건비 Save

		Map<String, String> param = new HashMap<String, String>() {
		    {
		    	put("seqNm", "RETURN_CD_SEQ_01");
		    }
		};
		saveMaterial(materialList); //자재 Save

		saveSym(symList); //현상Save
		saveResult(resultList); //조치Save

		Map<String, Object> woNoData = new HashMap<String, Object>();

		if(resultList.size() > 0) {
			woNoData.put("woNo", resultList.get(0).get("woNo"));
			List<Map<String, Object>> resultCmmntList = dao.queryForMapList("wo0301.retrieveWoResultList", woNoData);
			String workDesc = "";
			int row = 0;
			for(int i = 0; i < resultCmmntList.size(); i++) {
				row = i+1;
				workDesc = workDesc + "작업부위"+row+" : " + resultCmmntList.get(i).get("workRegionCd") + System.lineSeparator();
				workDesc = workDesc + "현상"+row+" : " + resultCmmntList.get(i).get("symptmCmmnt") + System.lineSeparator();
				workDesc = workDesc + "원인"+row+" : " + resultCmmntList.get(i).get("failCmmnt") + System.lineSeparator();
				workDesc = workDesc + "조치"+row+" : " + resultCmmntList.get(i).get("reprCmmnt") + System.lineSeparator() + System.lineSeparator();
			}
			woNoData.put("workDesc", workDesc);

			dao.update("wo0301.updateWO0301WorkDesc", woNoData);
		}

	}
	/**
	 * 작업결과서 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveWO0301(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
                // 작업결과서 생성
                dao.update("wo0301.insertWO0301", record);

			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());
				dao.update("wo0301.updateWO0301", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0301.deleteWO0301", record);
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
				dao.update("wo0301.insertJob", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0301.updateJob", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0301.deleteJob", record);
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
				dao.update("wo0301.insertCraft", record);
				if("1".equals(record.get("inOutDiv"))) {
					dao.update("wo0301.updateLaborCost", record);
				} else if("2".equals(record.get("inOutDiv"))) {
					dao.update("wo0301.updateEtcCost", record);
				}

				dao.update("wo0301.updateWO0301Craft", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0301.updateCraft", newRecord);
				if("1".equals(newRecord.get("inOutDiv"))) {
					dao.update("wo0301.updateLaborCost", newRecord);
				} else if("2".equals(newRecord.get("inOutDiv"))) {
					dao.update("wo0301.updateEtcCost", newRecord);
				}

				dao.update("wo0301.updateWO0301Craft", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0301.deleteCraft", record);
				if("1".equals(record.get("inOutDiv"))) {
					dao.update("wo0301.updateLaborCost", record);
				} else if("2".equals(record.get("inOutDiv"))) {
					dao.update("wo0301.updateEtcCost", record);
				}

				dao.update("wo0301.updateWO0301Craft", record);

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

				dao.update("wo0301.insertMaterial", record);
				dao.update("wo0301.updateWO0301Pt", record);
				dao.update("wo0301.updatePtCost", record);

			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0301.updateMaterial", newRecord);
				dao.update("wo0301.updateWO0301Pt", newRecord);
				dao.update("wo0301.updatePtCost", newRecord);

			}

			public void delete(HashMap record, int index) {
				record.put("fnlEditUserId", UserInfo.getUserId());
				dao.update("wo0301.deleteMaterial", record);
				dao.update("wo0301.updateWO0301Pt", record);
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
				record.put("woDiv", "WO0301");
				dao.update("wo0301.insertSym", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());
				newRecord.put("woDiv", "WO0301");

				dao.update("wo0301.updateSym", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0301.deleteSym", record);
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
				dao.update("wo0301.insertResult", record);

			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0301.updateResult", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0301.deleteResult", record);
			}
		});

	}


	/**
	 * 작업취소/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveWoCancelWO0301(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fnlEditUserId", UserInfo.getUserId());
				/*
				 * 작업취소시 불출된 자재는 자동반납
				 * 1. 취소할 W/O의 불출된 자재 조회
				 * 2. 불출된 자재가 있는지 체크. 있다면 로직 실행, 없으면 결과서 상태만 UPDATE(wo0301.updateWoCancelWO0301)
				 * 3. wo0301P 테이블의 USE_QTY(사용수량) = 0, RETURN_QTY(반납수량) = DISPEND_QTY(불출수량) 으로 수정
				 * 4. 자재반납마스터 생성 상태값은 반납완료(30), 비고란='작업취소로 인한 자동반납' 고정값
				 * 5. 자재반납상세 생성(작업결과서 불출 자재만큼 생성), 상태값은 반납완료상태(30)
				 * 6. 재고 증가
				 */
				List<Map<String, Object>> wo0301PList = new ArrayList<Map<String,Object>>();
				// 1. 취소할 W/O의 불출된 자재 조회
				wo0301PList = dao.queryForMapList("wo0301.retrieveWo0301PList", record);
				// 2. 불출된 자재가 있는지 체크. 있다면 로직 실행, 없으면 결과서 상태만 UPDATE(wo0301.updateWoCancelWO0301)
				if(wo0301PList.size() > 0) {
					Map<String, String> param = new HashMap<String, String>() {
					    {
					    	put("seqNm", "RETURN_CD_SEQ_01");
					    }
					};
					List<Map<String, Object>> returnCdListMap = dao.queryForMapList("util.retrieveSequence", param);
					record.put("returnCd", returnCdListMap.get(0).get("seqNum"));


					// 3.wo0301P 테이블의 USE_QTY(사용수량) = 0, RETURN_QTY(반납수량) = DISPEND_QTY(불출수량) 으로 수정
					dao.update("wo0301.updateWo0301PCancel", record);

				}
				dao.update("wo0301.updateWoCancelWO0301", record);


			}
		});

	}

	/**
	 * 작업결과서 상세 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0301Detail(Map<String, Object> data) {
		return dao.queryForMapList("wo0301.retrieveWO0301Detail", data);
	}

}
