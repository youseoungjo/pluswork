package com.bplus.pw.wo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idr.basis.data.GridData;
import idr.extend.iam.UserInfo;
import idr.extend.query.CommonDao;
import idr.extend.query.callback.AbstractRowStatusCallback;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class : 작업요청관리
 * Program Name : WO0101Service
 * Description : 작업요청 등록/수정/삭제를 위한 @Service 클래스
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
public class WO0101Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(WO0101Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;



	/**
	 * 작업요청 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0101List(Map<String, Object> data) {
		return dao.queryForMapList("wo0101.retrieveWO0101List", data);
	}

	/**
	 * 트리에서 작업요청 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0101ListToTree(Map<String, Object> data) {
		return dao.queryForMapList("wo0101.retrieveWO0101ListToTree", data);
	}

	/**
	 * 현상 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveSymptmList(Map<String, Object> data) {
		return dao.queryForMapList("wo0101.retrieveSymptmList", data);
	}

	/**
	 * 1. 상세정보 Save
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveWoProcessAll(
			GridData<HashMap> wo0101List,
			GridData<HashMap> symList) {

		saveWO0101(wo0101List); //작업요청save
		saveSym(symList); //스펙Save
	}

	/**
	 * W/O 생성
	 * param.get('woType') == "RESULT" 이면 결과서로 생성
	 * param.get('woType') == "PLAN" 이면 계획서로 생성
	 *
	 * @return List<Map<String, Object>>
	 */
	public void createWorkOrder(GridData<HashMap> wo0201List, Map<String, Object> param) {

		createWO0201(wo0201List, param); //작업결과save
	}

	/**
	 * 작업요청접수
	 *
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public int updateRequestAccep(Map<String, Object> map) throws Exception {
		map.put("receiptUserId", UserInfo.getUserId());
		map.put("fnlEditUserId", UserInfo.getUserId());

		int rstlCnt = 0;
		// 접수자, 요청상태 수정
		rstlCnt = dao.update("wo0101.updateReqAccep", map);

		if(rstlCnt > 0) {
			// 설비운영상태 Repair(수리)상태로 변경
			dao.update("wo0101.updateEqSts", map);
		}

		return rstlCnt;
	}

	/**
	 * 작업요청 취소
	 *
	 * @return
	 */
	public int saveReqCancelWO0101(Map<String, Object> map) {
		map.put("fnlEditUserId", UserInfo.getUserId());

		int rstlCnt = 0;
		/* 요청상태를 작업요청취소로 변경(작업요청(AA) -> 작업요청취소(AX)
		 * 취소자, 취소사유 update
		 * 요청자만 작업요청 취소 가능
		*/
		POMCancelSendMsg(map);

		rstlCnt = dao.update("wo0101.updateReqCancelWO0101", map);

		if(rstlCnt > 0) {
			// 설비운영상태 운전중 상태로 변경(협의 필요)
			dao.update("wo0101.updateEqSts", map);
		}

		return rstlCnt;

	}

	/**
	 * 작업요청 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveWO0101(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			@SuppressWarnings("unchecked")
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				record.put("woReqSts", "AA");
				dao.update("wo0101.insertWO0101", record);
			}

			@SuppressWarnings("unchecked")
			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				// 작업요청서 수정시 요청상태(WO_REQ_STS)는 update하지 않음.
				dao.update("wo0101.updateWO0101", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0101.deleteWO0101", record);
			}
		});
	}



	/**
	 * 작업요청 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveSym(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			@SuppressWarnings("unchecked")
			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				record.put("orgCd", UserInfo.getOrgCd());
				dao.update("wo0101.insertSymList", record);

			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("wo0101.updateSymList", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("wo0101.deleteSymList", record);
			}
		});
	}


	/**
	 * 작업요청 상세 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveWO0101Detail(Map<String, Object> data) {
		return dao.queryForMapList("wo0101.retrieveWO0101Detail", data);
	}


	/**
	 * 작업요청 목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void createWO0201(GridData<HashMap> gridData, Map<String, Object> param) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {
			@SuppressWarnings("unchecked")
			public void normal(HashMap record, int index) {
				Map<String, Object> createCnt = dao.queryForMap("wo0101.chkCreateWorkOrder", record);
				if((int)createCnt.get("createCnt") == 0 ) {
					Map<String, Object> woNoSeq = dao.queryForMap("wo0101.retreieveNextWoNoSeq", null);
					record.put("woNo", woNoSeq.get("woNo"));
					record.put("fstRegUserId", UserInfo.getUserId());
					record.put("fnlEditUserId", UserInfo.getUserId());
					record.put("woDiv", "WO0101");
//					record.put("workDesc", record.get("reqDesc"));
					if("RESULT".equals(param.get("woType"))) {
						record.put("woSts", "CA");
						record.put("planUserId", "");
						record.put("workUserId", UserInfo.getUserId());
					} else {
						record.put("woSts", "BA");
						record.put("orgCd", "");
						record.put("planUserId", UserInfo.getUserId());
						record.put("workUserId", "");
					}

					dao.update("wo0101.insertWO0201", record);

					if("RESULT".equals(param.get("woType"))) {
						dao.update("wo0101.createWO0302C", record);
						dao.update("wo0101.createWO0303S", record);
					}
					record.put("woReqSts", "AZ");
					dao.update("wo0101.updateWoReqSts", record);
				}
			}
		});
	}

	/**
	 * 요청자확인로직
	 *
	 * @return List<Map<String, Object>>
	 * @throws Exception
	 */
	public int reqUserConfirm(Map<String, Object> map) throws Exception {
		map.put("fnlEditUserId", UserInfo.getUserId());
		// 첫생산품확인자 = 로그인자
		map.put("fstProdChkUserId", UserInfo.getUserId());

		/* 첫생산품확인코드[CHK_RSLT]
		 * 1 : 정상
		 * 2 : 불량
		 */
		map.put("fstProdChk", "1");


		int rstlCnt = 0;
		// 작업결과서 상태 및 첫생산품 확인정보 변경
		rstlCnt = dao.update("wo0101.reqUserConfirm", map);

		return rstlCnt;
	}

	public void POMCancelSendMsg(Map<String, Object> map){
		try {
			HttpURLConnection conn = null;
			OutputStream writer = null;
			BufferedReader reader = null;
			InputStreamReader isr = null;
			URL url = null;

			JSONObject header = new JSONObject();
			JSONObject body = new JSONObject();
			JSONObject reply = new JSONObject();
			JSONObject jsonObject = new JSONObject();
			//JSONObject bodyData = new JSONObject();
			JSONObject bodySubData = new JSONObject();
			JSONArray bodyData = new JSONArray();

			bodySubData.put("EMSBMREQNO",map.get("reqNo"));
			bodySubData.put("ACTIONDETAIL",map.get("reqNm"));
			bodyData.put(bodySubData);


			url = new URL("http://momtest.ctr.co.kr/kr01/mom/pom/uix/EmsEquipmentRepair/EquipmentRepairCanceled");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);


			header.put("MessageName",  "EmsEquipmentRepair/EquipmentRepairCanceled");
			header.put("CommunicationType", "SendRequest");
			header.put("FactoryId",  "KR01");
			header.put("UserId", map.get("returnUserId"));
			header.put("SourceSystem",  "MOM.WEB");
			header.put("TargetSystem",  "MOM.POM.UIX");
			header.put("SourceUrl",  "");
			header.put("SourceTopic",  "CTR.KR01.MOM.JIYOON.POM.ECS");
			header.put("TargetTopic",  "CTR.KR01.MOM.JIYOON.POM.UIX");
			header.put("EventUser",  map.get("returnUserId"));
			header.put("EventTime", "");
			header.put("Language",  "ko");
			header.put("Encoding",  "UTF-8");
			header.put("SecretToken",  "");
			header.put("Tid", "EMS");
			header.put("TimeStamp", map.get("timeStamp"));
			body.put("EVENTUSER",  map.get("returnUserId"));
			body.put("EVENTCOMMENT",  "EMSCancelInput");

			body.put("EMSLIST",  bodyData);

			jsonObject.put("Header", header);
			jsonObject.put("Body", body);
			jsonObject.put("Reply", reply);

			String sendData = jsonObject.toString();
			System.out.println(sendData);

			OutputStream os = conn.getOutputStream();

			OutputStreamWriter wr = new OutputStreamWriter(os);
			wr.write(sendData);
			wr.flush();
			wr.close();

			int responseCode = conn.getResponseCode();

			isr = new InputStreamReader(conn.getInputStream());
			reader = new BufferedReader(isr);
			final StringBuffer buffer = new StringBuffer();
			String line;
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String responseParam = buffer.toString();
			System.out.println(responseParam);
		}catch(Exception e) {

		}
	}

}