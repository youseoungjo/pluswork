package com.bplus.pw.in.service;

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
 * System Class : 이상점검결과 검색
 * Program Name : IN0601Service
 * Description : 이상점검결과 검색을 위한 @Service 클래스
 * Author : jit13
 * Created Date : 2019-07-23
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
public class IN0601Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0601Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 미점검 리스트 검색
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0601(Map<String, Object> data) {
		return dao.queryForMapList("in0601.retrieveIN0601", data);
	}

	/**
	 * 트리에서 미점검 리스트 검색
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0601ToTree(Map<String, Object> data) {
		return dao.queryForMapList("in0601.retrieveIN0601ToTree", data);
	}

	/**
	 * 작업계획 W/O 발행
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveIN0601ListWo(Map<String, Object> data) {
		data.put("fstRegUserId", UserInfo.getUserId());
		data.put("fnlEditUserId", UserInfo.getUserId());
		data.put("planOrgCd", UserInfo.getOrgCd());
		data.put("planUserId", UserInfo.getUserId());

  	    dao.update("in0601.saveIN0601List", data);
		dao.update("in0601.updateIN0601ListWo", data);
	}

	/**
	 * 작업결과 W/O 발행
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveIN0601ListResult(Map<String, Object> data) {
		data.put("fstRegUserId", UserInfo.getUserId());
		data.put("fnlEditUserId", UserInfo.getUserId());
		data.put("orgCd", UserInfo.getOrgCd());
		data.put("workUserId", UserInfo.getUserId());

		dao.update("in0601.saveIN0601Result", data);
		dao.update("in0601.updateIN0601ListWo", data);
	}


	public void saveIN0601List(GridData<HashMap> gridData) {

		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void insert(HashMap record, int index) {
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
   			   newRecord.put("fstRegUserId", UserInfo.getUserId());
			   newRecord.put("fnlEditUserId", UserInfo.getUserId());

			   dao.update("in0601.updateIN0601List", newRecord);
			}

			public void delete(HashMap record, int index) {
			}
		});

	}


}
