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
 * System Class : 종류관리
 * Program Name : EQ0201Service
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
public class EQ0201Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(EQ0201Service.class);

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
	public List<Map<String, Object>> retrieveEQ0201EqTree(Map<String, Object> data) {
		return dao.queryForMapList("eq0201.retrieveEQ0201EqTree", data);
	}

	/**
	 * 공정 트리 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0201PrcsTree(Map<String, Object> data) {
		return dao.queryForMapList("eq0201.retrieveEQ0201PrcsTree", data);
	}

	/**
	 * 종류 트리 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0201TpTree(Map<String, Object> data) {
		return dao.queryForMapList("eq0201.retrieveEQ0201TpTree", data);
	}

	/**
	 * 위치 트리 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0201LocTree(Map<String, Object> data) {
		return dao.queryForMapList("eq0201.retrieveEQ0201LocTree", data);
	}

	/**
	 * 종류 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0201List(Map<String, Object> data) {

		if("!".equals(data.get("clsCd"))) {
			data.put("clsCd", "");
		}

		return dao.queryForMapList("eq0201.retrieveEQ0201List", data);
	}

	/**
	 * 1. 상세정보 Save
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveEqProcessAll(
			GridData<HashMap> eq0201List, Map<String, Object> data) {

		saveEQ0201(eq0201List, data); //종류save
	}
	/**
	 * 종류 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveEQ0201(GridData<HashMap> gridData, Map<String, Object> data) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				record.put("clsRevNo", data.get("clsRevNo"));

				dao.update("eq0201.insertEQ0201", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("eq0201.updateEQ0201", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("eq0201.deleteEQ0201", record);
			}
		});
	}

	/**
	 * 설비종류 전력효율 등록/수정
	 *
	 * @return List<Map<String, Object>>
	 */
	public void savePowerEfficient(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());
				if("".equals(newRecord.get("powerEfficient"))) {
					newRecord.put("powerEfficient", 0);
				}

				dao.update("eq0201.updateEQ0202RPowerEfficient", newRecord);
				dao.update("eq0201.updateEQ0201MPowerEfficient", newRecord);
			}
		});
	}

	/**
	 * 종류 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveEQ0201Move(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("eq0201.updateEQ0201Move", record);
			}
		});
	}

	/**
	 * 설비분류 리비전 마스터 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0202MList(Map<String, Object> data) {
		return dao.queryForMapList("eq0201.retrieveEQ0202MList", data);
	}

	public void insertEQ0202(Map<String, Object> data) {
		Map<String, Object> newClsRevSeq = dao.queryForMap("eq0201.retrieveClsRevNoNextValue", null);
		data.put("fnlEditUserId", UserInfo.getUserId());
		data.put("fstRegUserId", UserInfo.getUserId());
		data.put("newClsRevNo", newClsRevSeq.get("clsRevNo"));
		dao.update("eq0201.insertEQ0202M", data);
		dao.update("eq0201.insertEQ0202R", data);
	}

	/**
	 * 공정 트리 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0201PrcsTreePop(Map<String, Object> data) {
		return dao.queryForMapList("eq0201.retrieveEQ0201PrcsTreePop", data);
	}

	/**
	 * 종류 트리 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0201TpTreePop(Map<String, Object> data) {
		return dao.queryForMapList("eq0201.retrieveEQ0201TpTreePop", data);
	}

	/**
	 * 위치 트리 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0201LocTreePop(Map<String, Object> data) {
		return dao.queryForMapList("eq0201.retrieveEQ0201LocTreePop", data);
	}

	public List<Map<String, Object>> retrieveLineCd(Map<String, Object> data) {
		data.put("companyCd", UserInfo.getCompanyCd());
		return dao.queryForMapList("eq0201.retrieveLineCd", data);
	}

}