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
 * System Class : 기술문서관리
 * Program Name : EQ0301Service
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
public class EQ0301Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(EQ0301Service.class);

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
	public List<Map<String, Object>> retrieveEQ0301Tree(Map<String, Object> data) {
		return dao.queryForMapList("eq0301.retrieveEQ0301Tree", data);
	}

	/**
	 * 기술문서 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0301List(Map<String, Object> data) {
		return dao.queryForMapList("eq0301.retrieveEQ0301List", data);
	}
	/**
	 * 기술문서 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEQ0301ListToTree(Map<String, Object> data) {
		return dao.queryForMapList("eq0301.retrieveEQ0301ListToTree", data);
	}

	/**
	 * 트리에서 기술문서 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEqDocListToTree(Map<String, Object> data) {
		return dao.queryForMapList("eq0301.retrieveEqDocListToTree", data);
	}

	/**
	 * 기술문서문서 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveEqDocList(Map<String, Object> data) {
		return dao.queryForMapList("eq0301.retrieveEqDocList", data);
	}

	/**
	 * 1. 상세정보 Save
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveEqProcessAll(
			GridData<HashMap> eq0301List,
			GridData<HashMap> eqList) {

		saveEQ0301(eq0301List); //기술문서save
		saveEq(eqList); //스펙Save
	}
	/**
	 * 기술문서 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveEQ0301(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("eq0301.insertEQ0301", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("eq0301.updateEQ0301", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("eq0301.deleteEQ0301", record);
			}
		});
	}



	/**
	 * 기술문서 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveEq(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				record.put("orgCd", UserInfo.getOrgCd());
				dao.update("eq0301.insertEqDoc", record);
				//기술문서마스터의 첨부파일정보 변경
				//String fileAtchId = dao.queryForObject("eq0301.retrieveEqAtchId", record, String.class);
				dao.update("eq0301.updateEq", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("eq0301.updateEqDoc", newRecord);
				//기술문서마스터의 첨부파일정보 변경
				dao.update("eq0301.updateEq", newRecord);
			}

			public void delete(HashMap record, int index) {
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("eq0301.deleteEqDoc", record);
				//기술문서마스터의 첨부파일정보 변경
				dao.update("eq0301.deleteEq", record);
			}
		});
	}

}