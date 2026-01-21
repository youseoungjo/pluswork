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
 * System Class : 점검결과 작성 검색
 * Program Name : IN0801Service
 * Description : 설비별 점검데이터 검색을 위한 @Service 클래스
 * Author : jit13
 * Created Date : 2019-07-24
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
public class IN0801Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0801Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 점검결과 작성 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0801(Map<String, Object> data) {
		return dao.queryForMapList("in0801.retrieveIN0801", data);
	}

	/**
	 * 점검결과 작성 저장
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveIN0801List(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				// 점검결과번호가 없는 경우
				if ( "".equals(oldRecord.get("chkRsltNo")) ){
					// 점검자값 없을때 로그인자를 점검자로 등록
					if ("".equals(newRecord.get("chkUserId"))){
						newRecord.put("chkUserId", UserInfo.getUserId());
					}
					newRecord.put("fstRegUserId", UserInfo.getUserId());
					newRecord.put("fnlEditUserId", UserInfo.getUserId());

					dao.update("in0801.saveIN0801List", newRecord);
				} else {
					newRecord.put("fnlEditUserId", UserInfo.getUserId());

					dao.update("in0801.updateIN0801List", newRecord);
				}
				dao.update("in0801.updateIN0801CompleteCheck", newRecord);
//				dao.update("in0801.updateIN0801LastCheckYmd", newRecord);
			}

		});
	}


}
