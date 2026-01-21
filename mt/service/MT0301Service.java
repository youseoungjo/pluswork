package com.bplus.pw.mt.service;

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
import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class : 연간휴일지정
 * Program Name : MT0301Service
 * Description : 연간 휴일 지정 등록/수정/삭제를 위한 @Service 클래스
 * Author : jit13
 * Created Date : 2020-04-01
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
public class MT0301Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(MT0301Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 연간휴일지정 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveMT0301(Map<String, Object> data) {
		return dao.queryForMapList("mt0301.retrieveMT0301", data);
	}

	public List<Map<String, Object>> retrieveMT0301Sub(Map<String, Object> data) {
		return dao.queryForMapList("mt0301.retrieveMT0301Sub", data);
	}

	public void saveMT0301Main(GridData<HashMap> ds_MT0301List, GridData<HashMap> ds_MT0301Sub01List ) {
		saveMT0301List(ds_MT0301List);
		saveMT0301ListSub(ds_MT0301Sub01List);
	}

	public void saveMT0301List(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("mt0301.saveMT0301List", record);

			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("mt0301.updateMT0301List", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("mt0301.deleteMT0301List", record);
			}

		});
	}

	public void saveMT0301ListSub(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("mt0301.saveMT0301ListSub", record);

			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("mt0301.updateMT0301ListSub", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("mt0301.deleteMT0301ListSub", record);
			}

		});
	}


	public List<Map<String, Object>> createMonMT0301List(Map<String, Object> data) {
		List<Map<String, Object>> retrieveMT0301CalList = dao.queryForMapList("mt0301.retrieveMT0301CalList", data);
		Map<String, Object> createCalList = new HashMap<String, Object>();

		if ( retrieveMT0301CalList.size() >= 1){
			dao.update("mt0301.delCalDate", data);
		};

		for(int i=0; i<retrieveMT0301CalList.size(); i++){
			createCalList.put("calCd", retrieveMT0301CalList.get(i).get("calcd").toString());
			createCalList.put("inDays", retrieveMT0301CalList.get(i).get("indays").toString());
			createCalList.put("fnlEditUserId", UserInfo.getUserId());
			createCalList.put("fstRegUserId", UserInfo.getUserId());
			createCalList.put("companyCd", UserInfo.getCompanyCd());

			dao.update("mt0301.insertCalDate", createCalList);

		}

		return dao.queryForMapList("mt0301.retrieveMT0301Sub", data);
	}
}
