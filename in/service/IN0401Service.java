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
 * Program Name : IN0401Service
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
public class IN0401Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0401Service.class);

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
	public List<Map<String, Object>> retrieveIN0401(Map<String, Object> data) {
		return dao.queryForMapList("in0401.retrieveIN0401", data);
	}

	/**
	 * 점검결과 작성 상세목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0401Sub(Map<String, Object> data) {
		return dao.queryForMapList("in0401.retrieveIN0401Sub", data);
	}

	public void saveIN0401(GridData<HashMap> ds_IN0401Sub01List, Map<String, Object> data) {

		saveIN0401ListSub(ds_IN0401Sub01List, data);

		if("Y".equals(data.get("chkStsDiv"))) {
			dao.update("in0401.updateIN0401CompleteCheck", data);
//			dao.update("in0401.updateIN0401LastCheckYmd", data);
		}
	}


	/**
	 * 점검결과 작성 저장
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveIN0401ListSub(GridData<HashMap> gridData, Map<String, Object> data) {
		// 미완료점검 중 예방점검결과상세의 점검결과가 다 입력되있는 경우 완료여부 변경

		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				if ( "".equals(oldRecord.get("chkRsltNo")) ){
					if ("".equals(newRecord.get("chkUserId"))){
						newRecord.put("chkUserId", UserInfo.getUserId());
					}
					newRecord.put("fstRegUserId", UserInfo.getUserId());
					newRecord.put("fnlEditUserId", UserInfo.getUserId());

					dao.update("in0401.saveIN0401ListSub", newRecord);
				} else {
					newRecord.put("fnlEditUserId", UserInfo.getUserId());

					dao.update("in0401.updateIN0401ListSub", newRecord);
				}
			}
		});

	}


}
