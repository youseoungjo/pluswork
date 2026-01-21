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
 * System Class : 예방정비UBM 작성
 * Program Name : MT0401Service
 * Description : 예방정비UBM 등록/수정/삭제를 위한 @Service 클래스
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
public class MT0401Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(MT0401Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 예방정비UBM 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveMT0401(Map<String, Object> data) {
		return dao.queryForMapList("mt0401.retrieveMT0401", data);
	}

	/**
	 * 트리에서 예방정비UBM 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveMT0401ToTree(Map<String, Object> data) {
		return dao.queryForMapList("mt0401.retrieveMT0401ToTree", data);
	}

	public List<Map<String, Object>> retrieveMT0401Sub(Map<String, Object> data) {
		return dao.queryForMapList("mt0401.retrieveMT0401Sub", data);
	}

	public List<Map<String, Object>> retrieveMT0401Sub2(Map<String, Object> data) {
		return dao.queryForMapList("mt0401.retrieveMT0401Sub2", data);
	}


	public List<Map<String, Object>> retrieveMT0401ListPop(Map<String, Object> data) {
		if ("Y".equals(data.get("grpYn"))){
			return dao.queryForMapList("mt0401.retrieveMT0401ListPopGroupY", data);
		} else {
			return dao.queryForMapList("mt0401.retrieveMT0401ListPopGroupN", data);
		}

	}

	//public List<Map<String, Object>> saveMT0401Pop(Map<String, Object> data) {
	public void saveMT0401Pop(Map<String, Object> data) {
		List<Map<String, Object>> saveMT0401Pop = dao.queryForMapList("mt0401.retrieveMT0401Sub", data);
		Map<String, Object> inseertMT0401ListPop = new HashMap<String, Object>();
		String ubmNo = "";

		for(int i=0; i < saveMT0401Pop.size(); i++){
			Map<String, Object> findSequenceNo = new HashMap<String, Object>();

     	    findSequenceNo.put("seqNm", "SQMT_UBM_NO");
 		    findSequenceNo.put("instcCd", "");

 		    ubmNo = Integer.toString(dao.queryForInt("util.retrieveSequence",findSequenceNo));

			inseertMT0401ListPop.put("pmNo", saveMT0401Pop.get(i).get("pmNo").toString());
			inseertMT0401ListPop.put("useYmd", data.get("useYmd"));
			inseertMT0401ListPop.put("totSum", data.get("totSum"));
			inseertMT0401ListPop.put("ubmNo", ubmNo);
			inseertMT0401ListPop.put("eqSts", data.get("eqSts"));
			inseertMT0401ListPop.put("fstRegUserId", UserInfo.getUserId());
      	    inseertMT0401ListPop.put("fnlEditUserId", UserInfo.getUserId());

      	    dao.update("mt0401.insertMT0401ListPop", inseertMT0401ListPop);
      	    dao.update("mt0401.updateMT0401ListPop", inseertMT0401ListPop);

        	//ubmNo = String.valueOf(dao.queryForObject("mt0401.retrieveMT0401WoYn", inseertMT0401ListPop, int.class));

        	ubmNo = dao.queryForObject("mt0401.retrieveMT0401WoYn", inseertMT0401ListPop, String.class);


        	if (ubmNo != null && !"".equals(ubmNo)){
        		createWoNo(inseertMT0401ListPop);
        	}
		}

		//return null;
	}


	//public List<Map<String, Object>> updateMT0401Pop(Map<String, Object> data) {
	public void updateMT0401Pop(Map<String, Object> data) {
		List<Map<String, Object>> updateMT0401Pop;

		if("Y".equals(data.get("grpYn"))){
			updateMT0401Pop = dao.queryForMapList("mt0401.retrieveMT0401UpdateListPopGroupY", data);
		}else{
			updateMT0401Pop = dao.queryForMapList("mt0401.retrieveMT0401UpdateListPopGroupN", data);
		};

		Map<String, Object> updateMT0401ListPop = new HashMap<String, Object>();
		String ubmNo = "";

		for(int i=0; i < updateMT0401Pop.size(); i++){

 		    updateMT0401ListPop.put("useYmd", data.get("useYmd"));
 		    updateMT0401ListPop.put("totSum", data.get("totSum"));
 		    updateMT0401ListPop.put("eqSts", data.get("eqSts"));
 		    updateMT0401ListPop.put("ubmNo", updateMT0401Pop.get(i).get("ubmNo").toString());
 		    updateMT0401ListPop.put("fstRegUserId", UserInfo.getUserId());
 		    updateMT0401ListPop.put("fnlEditUserId", UserInfo.getUserId());

      	    dao.update("mt0401.updateMT0401ListPop2", updateMT0401ListPop);
      	    dao.update("mt0401.updateMT0401ListPop", updateMT0401ListPop);

        	ubmNo = dao.queryForObject("mt0401.retrieveMT0401WoYn", updateMT0401ListPop, String.class);


        	if (ubmNo != null && !"".equals(ubmNo)){
        		createWoNo(updateMT0401ListPop);
        	}
		}

		//return null;
	}


	public void createWoNo(Map<String, Object> inseertMT0401ListPop){
		Map<String, Object> findSequenceNo = new HashMap<String, Object>();
		String woNo = "";

		findSequenceNo.put("seqNm", "SQWO_NO_01");
		findSequenceNo.put("instcCd", "");

		woNo = Integer.toString(dao.queryForInt("util.retrieveSequence",findSequenceNo));

		inseertMT0401ListPop.put("woNo", woNo);
		inseertMT0401ListPop.put("fstRegUserId", UserInfo.getUserId());
		inseertMT0401ListPop.put("fnlEditUserId", UserInfo.getUserId());

		dao.update("mt0401.insertMT0401Wo", inseertMT0401ListPop);
		dao.update("mt0401.insertMT0401Parts", inseertMT0401ListPop);
		dao.update("mt0401.insertMT0401Craft", inseertMT0401ListPop);
		dao.update("mt0401.insertMT0401WorkJob", inseertMT0401ListPop);

		dao.update("mt0401.updateMT0401WoUbmNo", inseertMT0401ListPop);


		dao.update("mt0401.insertMT0201M", inseertMT0401ListPop);


	}

}

