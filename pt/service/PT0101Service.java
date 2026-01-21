package com.bplus.pw.pt.service;

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
 * System Class : PT0101Service.java
 * Program Name : PT0101Service
 * Description : 자재마스터 등록/수정/삭제를 위한 controller 클래스
 * Author : sds
 * Created Date : 2022-12-06
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
public class PT0101Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(PT0101Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 자재마스터 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrievePT0101MList(Map<String, Object> data) {

		return dao.queryForMapList("pt0101.retrievePT0101MList", data);
	}

	/**
	 * 자재마스터 목록 추가/수정
	 *
	 * @return void
	 */
	public void savePT0101M(GridData<HashMap>  gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("pt0101.insertPT0101M", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());
				dao.update("pt0101.updatePT0101M", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("pt0101.deletePT0101M", record);
			}
		});
	}

	/**
	 * 자재팝업 : 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveMaterialListPopup(Map<String, Object> data) {
		return dao.queryForMapList("pt0101.retrieveMaterialListPopup", data);
	}

}
