package com.bplus.pw.in.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import idr.extend.iam.UserInfo;
import idr.extend.query.CommonDao;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class : 점검데이터 작성
 * Program Name : IN0901Service
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
public class IN0901Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0901Service.class);

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
	public List<Map<String, Object>> retrieveIN0901(Map<String, Object> data) {
		return dao.queryForMapList("in0901.retrieveIN0901", data);
	}

	public List<Map<String, Object>> retrieveIN0901Sub(Map<String, Object> data) {
		return dao.queryForMapList("in0901.retrieveIN0901Sub", data);
	}

	public List<Map<String, Object>> retrieveIN0901Sub2(Map<String, Object> data) {
		return dao.queryForMapList("in0901.retrieveIN0901Sub2", data);
	}


	public List<Map<String, Object>> retrieveIN0901ListPop(Map<String, Object> data) {
		if ("Y".equals(data.get("grpYn"))){
			return dao.queryForMapList("in0901.retrieveIN0901ListPopGroupY", data);
		} else {
			return dao.queryForMapList("in0901.retrieveIN0901ListPopGroupN", data);
		}

	}

	//public List<Map<String, Object>> saveIN0901Pop(Map<String, Object> data) {
	public void saveIN0901Pop(Map<String, Object> data) {
		List<Map<String, Object>> saveIN0901Pop = dao.queryForMapList("in0901.retrieveIN0901Sub", data);
		Map<String, Object> insertIN0901ListPop = new HashMap<String, Object>();
		String ubmNo = "";

		for(int i=0; i < saveIN0901Pop.size(); i++){
			Map<String, Object> findSequenceNo = new HashMap<String, Object>();

     	    findSequenceNo.put("seqNm", "SQMT_UBM_NO");
 		    findSequenceNo.put("instcCd", "");

 		    ubmNo = Integer.toString(dao.queryForInt("util.retrieveSequence",findSequenceNo));

			insertIN0901ListPop.put("chkNo", saveIN0901Pop.get(i).get("chkNo").toString());
			insertIN0901ListPop.put("useYmd", data.get("useYmd"));
			insertIN0901ListPop.put("totSum", data.get("totSum"));
			insertIN0901ListPop.put("ubmNo", ubmNo);
			insertIN0901ListPop.put("eqSts", data.get("eqSts"));
			insertIN0901ListPop.put("fstRegUserId", UserInfo.getUserId());
      	    insertIN0901ListPop.put("fnlEditUserId", UserInfo.getUserId());

      	    dao.update("in0901.insertIN0901ListPop", insertIN0901ListPop);
      	    dao.update("in0901.updateIN0901ListPop", insertIN0901ListPop);

        	ubmNo = "";


        	if (ubmNo != null && !"".equals(ubmNo)){
        		createIN0901Chk(insertIN0901ListPop);
        	}
		}

		//return null;
	}


	//public List<Map<String, Object>> updateIN0901Pop(Map<String, Object> data) {
	public void updateIN0901Pop(Map<String, Object> data) {
		List<Map<String, Object>> updateIN0901Pop;

		if("Y".equals(data.get("grpYn"))){
			updateIN0901Pop = dao.queryForMapList("in0901.retrieveIN0901UpdateListPopGroupY", data);
		}else{
			updateIN0901Pop = dao.queryForMapList("in0901.retrieveIN0901UpdateListPopGroupN", data);
		};

		Map<String, Object> updateIN0901ListPop = new HashMap<String, Object>();
		String ubmNo = "";

		for(int i=0; i < updateIN0901Pop.size(); i++){

 		    updateIN0901ListPop.put("useYmd", data.get("useYmd"));
 		    updateIN0901ListPop.put("totSum", data.get("totSum"));
 		    updateIN0901ListPop.put("eqSts", data.get("eqSts"));
 		    updateIN0901ListPop.put("ubmNo", updateIN0901Pop.get(i).get("ubmNo").toString());
 		    updateIN0901ListPop.put("fstRegUserId", UserInfo.getUserId());
 		    updateIN0901ListPop.put("fnlEditUserId", UserInfo.getUserId());

      	    dao.update("in0901.updateIN0901ListPop2", updateIN0901ListPop);
      	    dao.update("in0901.updateIN0901ListPop", updateIN0901ListPop);

        	ubmNo = "" ;


        	if (ubmNo != null && !"".equals(ubmNo)){
        		createIN0901Chk(updateIN0901ListPop);
        	}
		}
		//return null;
	}


	public void createIN0901Chk(Map<String, Object> insertIN0901ListPop){
		Map<String, Object> findSequenceNo = new HashMap<String, Object>();
		Map<String, Object> chkSchdNo = new HashMap<String, Object>();

		findSequenceNo.put("seqNm", "SQIN_CHK_SCHD_NO");
		findSequenceNo.put("instcCd", "");

		chkSchdNo = dao.queryForMap("util.retrieveSequence",findSequenceNo);

		insertIN0901ListPop.put("chkSchdNo", chkSchdNo.get("seqNum"));
		insertIN0901ListPop.put("fstRegUserId", UserInfo.getUserId());
		insertIN0901ListPop.put("fnlEditUserId", UserInfo.getUserId());

		dao.update("in0901.updateIN0901ChkSchdNo",insertIN0901ListPop );
		dao.update("in0901.insertIN0401", insertIN0901ListPop);
	}

}

