package com.bplus.pw.st.service;

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
 * System Class : 점검데이터 작성
 * Program Name : ST0101Service
 * Description : 점검데이터 등록/수정/삭제를 위한 @Service 클래스
 * Author : jit13
 * Created Date : 2019-07-12
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
public class ST1001Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(ST1001Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 점검작성 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveST1001List(Map<String, Object> data) {
		return dao.queryForMapList("st1001.retrieveST1001List", data);
	}

	public List<Map<String, Object>> retrieveST1001Sub01List(Map<String, Object> data) {
		return dao.queryForMapList("st1001.retrieveST1001Sub01List", data);
	}

	public void saveST1001List(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("st1001.insertST1001", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("st1001.updateST1001", newRecord);
			}


		});

	}

	public void saveMonthlyChkCnt(Map<String, Object> params) {
//		String fStartDate = "";
//		String fEndDate = "";
//		fStartDate = params.get("fYearDate") + "-01-01";
//		fEndDate = params.get("fYearDate") + "-12-01";
//		params.put("fStartDate", fStartDate);
//		params.put("fEndDate", fEndDate);
//
//		List<Map<String, Object>> selectYmdList = dao.queryForMapList("st1001.retrieveMonthlyDate", params);
//		params.put("selectYmdList",  selectYmdList);
//		params.put("selectYmdSubList",  selectYmdList);

		params.put("01", params.get("fYearDate")+"01");
		params.put("02", params.get("fYearDate")+"02");
		params.put("03", params.get("fYearDate")+"03");
		params.put("04", params.get("fYearDate")+"04");
		params.put("05", params.get("fYearDate")+"05");
		params.put("06", params.get("fYearDate")+"06");
		params.put("07", params.get("fYearDate")+"07");
		params.put("08", params.get("fYearDate")+"08");
		params.put("09", params.get("fYearDate")+"09");
		params.put("10", params.get("fYearDate")+"10");
		params.put("11", params.get("fYearDate")+"11");
		params.put("12", params.get("fYearDate")+"12");



		dao.update("st1001.updateST1001PM", params);



	}

}
