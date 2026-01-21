package com.bplus.pw.eq.service;

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
 * System Class : 설비관리
 * Program Name : EQ0101Service
 * Description : 설비 등록/수정/삭제를 위한 @Service 클래스
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
public class EQ0101Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(EQ0101Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 설비 트리 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0101Tree(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveEQ0101Tree", data);
	}

	/**
	 * 설비 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0101List(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveEQ0101List", data);
	}

	/**
	 * 설비트리에서 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0101ListToTree(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveEQ0101ListToTree", data);
	}

	/**
	 * 스펙 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveSpecList(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveSpecList", data);
	}

	/**
	 * 작업이력 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWoHist(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveWoHist", data);
	}

	/**
	 * 수기이력 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEqHist(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveEqHist", data);
	}

	/**
	 * 작업표준 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEqStd(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveEqStd", data);
	}
	/**
	 * 1. 상세정보 Save
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveEqProcessAll(
			GridData<HashMap> eq0101List,
			GridData<HashMap> specList,
			GridData<HashMap> eqStd,
			GridData<HashMap> bomList,
			GridData<HashMap> eq0108MList,
			Map<String, Object> map) {



		Map<String, Object> eqRevSeq = dao.queryForMap("eq0101.retrieveEqRevSeqNextValue", null);
		Map<String, Object> eqRevNo = dao.queryForMap("eq0101.retrieveEqRevNoNextValue", null);

		saveEQ0101(eq0101List,eqRevSeq, eqRevNo); //설비save
		if( "C".equals(map.get("saveDiv")) ) {
			saveSpec(specList, eqRevSeq); //스펙Save
			eq0108MSave(eq0108MList, eqRevSeq);
		} else if( "U".equals(map.get("saveDiv")) ) {
			specRevSave(specList, eqRevSeq); //스펙Save
			eq0108RSave(eq0108MList, eqRevSeq);
		}

//		saveLocHist(eq0101List); //위치이력Save
		saveEqStd(eqStd); //설비이력Save
		saveBom(bomList); //BOM Save
	}
	/**
	 * 설비 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveEQ0101(GridData<HashMap> gridData, Map<String, Object> eqRevSeq, Map<String, Object> eqRevNo) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				record.put("eqRevSeq", eqRevSeq.get("eqRevSeq"));
				record.put("eqRevNo", eqRevNo.get("eqRevNo"));

				dao.update("eq0101.insertEQ0101", record);
				dao.update("eq0101.insertEQ0101R", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fstRegUserId", UserInfo.getUserId());
				newRecord.put("fnlEditUserId", UserInfo.getUserId());
//				newRecord.put("prevEqPrcs", oldRecord.get("eqPrcs"));
//				dao.update("eq0101.updateEQ0101", newRecord);
				newRecord.put("eqRevSts", "10");
				newRecord.put("eqRevStartYmd", null);
				newRecord.put("eqRevSeq", eqRevSeq.get("eqRevSeq"));
				newRecord.put("eqRevNo", null);
				dao.update("eq0101.insertEQ0101R", newRecord);

//				if(!oldRecord.get("eqPrcs").equals(newRecord.get("eqPrcs"))) {
//					dao.update("eq0101.insertLocHist", newRecord);
//				}
			}

			public void delete(HashMap record, int index) {
				dao.update("eq0101.deleteEQ0101", record);
			}
		});
	}

	/**
	 * 설비이동이력/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveLocHist(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fstRegUserId", UserInfo.getUserId());
				newRecord.put("fnlEditUserId", UserInfo.getUserId());
				newRecord.put("fstRegUserId", UserInfo.getUserId());
				newRecord.put("prevEqPrcs", oldRecord.get("eqPrcs"));

				dao.update("eq0101.insertLocHist", newRecord);
			}
		});
	}

	/**
	 * 스펙 목록 추가
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveSpec(GridData<HashMap> gridData, Map<String, Object> eqRevSeq) {
		for(int i = 0; i < gridData.size(); i++) {
			Map<String, Object> eqSpecNo = dao.queryForMap("eq0101.retrieveEqSpecNoNextValue", null);

			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
			gridData.get(i).put("eqRevSeq", eqRevSeq.get("eqRevSeq"));
			gridData.get(i).put("eqSpecNo", eqSpecNo.get("eqSpecNo"));

			dao.update("eq0101.insertEQ0102R", gridData.get(i));
			dao.update("eq0101.insertSpec", gridData.get(i));
		}

	}

	/**
	 * 리비전 스펙 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void specRevSave(GridData<HashMap> gridData, Map<String, Object> eqRevSeq) {
		for(int i = 0; i < gridData.size(); i++) {
			Map<String, Object> eqSpecNo = dao.queryForMap("eq0101.retrieveEqSpecNoNextValue", null);

			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
			gridData.get(i).put("eqRevSeq", eqRevSeq.get("eqRevSeq"));
			gridData.get(i).put("eqSpecNo", eqSpecNo.get("eqSpecNo"));

			dao.update("eq0101.insertEQ0102R", gridData.get(i));
		}
	}

	/**
	 * 작업표준 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveEqStd(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				dao.update("eq0101.insertEqStd", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("eq0101.updateEqStd", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("eq0101.deleteEqStd", record);
			}
		});
	}

	/**
	 * 점검이력 Tab 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveInspectionHistory(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveInspectionHistory", data);
	}

	/**
	 * 점검이력 Tab 마스터조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveInspection(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveInspection", data);
	}

	/**
	 * 설비마스터 - BOM 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveBomList(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveBomList", data);
	}

	public List<Map<String, Object>> retrieveBomListN(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveBomListN", data);
	}

	/**
	 * BOM 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveBom(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("eq0101.insertBom", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("eq0101.updateBom", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("eq0101.deleteBom", record);
			}
		});
	}

	/**
	 * Revision 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0101RList(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveEQ0101RList", data);
	}

	/**
	 * Revision 스펙 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0102RList(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveEQ0102RList", data);
	}

	/**
	 * 설비 공통규격 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0108MList(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveEQ0108MList", data);
	}

	/**
	 * 설비 공통규격 리비전 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0108RList(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveEQ0108RList", data);
	}

	/**
	 * 설비 공통규격 추가
	 *
	 * @return List<Map<String, Object>>
	 */
	public void eq0108MSave(GridData<HashMap> gridData, Map<String, Object> eqRevSeq) {
		for(int i = 0; i < gridData.size(); i++) {

			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
			gridData.get(i).put("eqRevSeq", eqRevSeq.get("eqRevSeq"));

			dao.update("eq0101.insertEQ0108R", gridData.get(i));
			dao.update("eq0101.insertEQ0108M", gridData.get(i));
		}

	}

	/**
	 * 리비전 설비 공통규격 저장
	 *
	 * @return List<Map<String, Object>>
	 */
	public void eq0108RSave(GridData<HashMap> gridData, Map<String, Object> eqRevSeq) {
		for(int i = 0; i < gridData.size(); i++) {

			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
			gridData.get(i).put("eqRevSeq", eqRevSeq.get("eqRevSeq"));

			dao.update("eq0101.insertEQ0108R", gridData.get(i));
		}
	}

	/**
	 * 표준규격 적용 시 규격항목 데이터 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveSpecItemList(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveSpecItemList", data);
	}

	public List<Map<String, Object>> retrieveEQ0101Detail(Map<String, Object> data) {
		return dao.queryForMapList("eq0101.retrieveEQ0101Detail", data);
	}

	public void deleteEq0101R(Map<String, Object> data) {
		dao.update("eq0101.deleteEQ0101R", data);
		dao.update("eq0101.deleteEQ0102R", data);
		dao.update("eq0101.deleteEQ0108R", data);
//		int cnt = dao.update("eq0101.deleteEQ0101R", data);
//			dao.update("eq0101.deleteEQ0102R", data);
//			dao.update("eq0101.deleteEQ0108R", data);
//		}
//
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("deleteCnt", cnt);
//		List<Map<String, Object>> deleteCnt = new ArrayList<Map<String, Object>>();
//		deleteCnt.add(resultMap);
//
//		return deleteCnt;
	}

}
