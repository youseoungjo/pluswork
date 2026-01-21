package com.bplus.pw.co.service;

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
 * System Class : 공통모듈
 * Program Name : CO0401Service
 * Description : 거래처 등록/수정/삭제를 위한 @Service 클래스
 * Author : Jun.
 * Created Date : 2020-09-07
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
public class CO0401Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(CO0401Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 공통코드 그룹 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveTradeList(Map<String, Object> data) {
		List<Map<String, Object>> tradeList = dao.queryForMapList("co0401.retrieveTradeList", data);
		return tradeList;
	}

	/**
	 * 공통코드 그룹 목록 추가/수정
	 *
	 * @return void
	 */
	public void saveTrade(GridData<HashMap>  gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				int tradeCnt = dao.queryForInt("co0401.checkTradeNm", record);

				if( tradeCnt == 0 ) {
					dao.update("co0401.insertCO0401", record);
				}
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				int tradeCnt = dao.queryForInt("co0401.checkTradeNm", newRecord);

				if( tradeCnt == 0 ) {
					dao.update("co0401.updateCO0401", newRecord);
				}

			}


			public void delete(HashMap record, int index) {
				dao.update("co0401.deleteCO0401", record);
			}
		});
	}

	/**
	 * 거래처 팝업 : 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveTradeListPopup(Map<String, Object> data) {
		List<Map<String, Object>> tradeList = dao.queryForMapList("co0401.retrieveTradeListPopup", data);
		return tradeList;
	}
}
