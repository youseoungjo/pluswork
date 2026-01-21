package com.bplus.pw.mt.service;

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
 * Program Name : MT0101Service
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
public class MT0101Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(MT0101Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 예방정비 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveMT0101(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveMT0101", data);
	}

	/**
	 * 트리에서 예방정비 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveMT0101ToTree(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveMT0101ToTree", data);
	}

	public List<Map<String, Object>> retrieveMT0101Sub(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveMT0101Sub", data);
	}

	public List<Map<String, Object>> retrieveMT0101SubJob(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveMT0101SubJobList", data);
	}

	public List<Map<String, Object>> retrieveMT0101SubCraft(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveMT0101SubCraftList", data);
	}

	public List<Map<String, Object>> retrieveMT0101SubParts(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveMT0101SubPartsList", data);
	}

	/**
	 * 점검일정 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveMT0101Sub2(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveMT0101Sub2", data);
	}

	public void saveMainMT0101List(GridData<HashMap> ds_MT0101List, GridData<HashMap> ds_MT0101SubJobList
			,  GridData<HashMap> ds_MT0101SubCraftList, GridData<HashMap> ds_MT0101SubPartsList, Map<String, Object> map) {

		Map<String, Object> pmRevSeq = dao.queryForMap("mt0101.retrievePmRevSeqNextValue", null);
		Map<String, Object> pmRevNo = dao.queryForMap("mt0101.retrievePmRevNoNextValue", null);

		saveMT0101List(ds_MT0101List, pmRevSeq, pmRevNo);

		if( "C".equals(map.get("saveDiv")) ) {
			saveMT0102J(ds_MT0101SubJobList, pmRevSeq);
			saveMT0102C(ds_MT0101SubCraftList, pmRevSeq);
			saveMT0102P(ds_MT0101SubPartsList, pmRevSeq);
		} else if( "U".equals(map.get("saveDiv")) ) {
			saveMT0102JR(ds_MT0101SubJobList, pmRevSeq);
			saveMT0102CR(ds_MT0101SubCraftList, pmRevSeq);
			saveMT0102PR(ds_MT0101SubPartsList, pmRevSeq);
		}
	}

	/**
	 * 점검목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveMT0101List(GridData<HashMap> gridData, Map<String, Object> pmRevSeq, Map<String, Object> pmRevNo) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				record.put("pmRevSeq", pmRevSeq.get("pmRevSeq"));
				record.put("pmRevNo", pmRevNo.get("pmRevNo"));
				record.put("cycleChangeYn", "N");

				//TBM인지 UBM인지 구분해서 처리
				if ("T".equals(record.get("pmTp"))){
					dao.update("mt0101.saveMT0101ListTbm", record);
					dao.update("mt0101.saveMT0101RListTbm", record);
				} else {
					if(!"Y".equals(record.get("grpYn"))) {
						record.put("grpYn", "N");
					}
					dao.update("mt0101.saveMT0101ListUbm", record);
					dao.update("mt0101.saveMT0101RListUbm", record);
				}

			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fstRegUserId", UserInfo.getUserId());
				newRecord.put("fnlEditUserId", UserInfo.getUserId());
				newRecord.put("pmRevSts", "10");
				newRecord.put("pmRevYmd", null);
				newRecord.put("pmRevSeq", pmRevSeq.get("pmRevSeq"));
				newRecord.put("pmRevNo", null);
				newRecord.put("cycleChangeYn", "N");

				/* PM구분, 주기, 요일 변경 시 주기변경여부 값 변경
				 * CYCLE_CHANGE_YN = "Y" 인 경우 정비일정을 재생성 하라는 alert창 띄움
				 */
				if(newRecord.get("pmTp") != oldRecord.get("pmTp") || newRecord.get("cycleCd") != oldRecord.get("cycleCd")
						|| newRecord.get("cycleTp") != oldRecord.get("cycleTp") || newRecord.get("dayWeek") != oldRecord.get("dayWeek")) {
					newRecord.put("cycleChangeYn", "Y");
				}

				if ("T".equals(newRecord.get("pmTp"))){
					dao.update("mt0101.saveMT0101RListTbm", newRecord);
				} else {
					if(!"Y".equals(newRecord.get("grpYn"))) {
						newRecord.put("grpYn", "N");
					}
					dao.update("mt0101.saveMT0101RListUbm", newRecord);
				}
			}

		});
	}

	/**
	 * 작업 항목 정보 저장
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveMT0102J(GridData<HashMap> gridData, Map<String, Object> pmRevSeq) {
		for(int i = 0; i < gridData.size(); i++) {
			Map<String, Object> pmJobNo = dao.queryForMap("mt0101.retrievePmJobNoSeqNextValue", null);

			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
			gridData.get(i).put("pmRevSeq", pmRevSeq.get("pmRevSeq"));
			gridData.get(i).put("pmJobNo", pmJobNo.get("pmJobNo"));

			dao.update("mt0101.insertMT0102JR", gridData.get(i));
			dao.update("mt0101.insertMT0102J", gridData.get(i));
		}
	}

	public void saveMT0102C(GridData<HashMap> gridData, Map<String, Object> pmRevSeq) {
		for(int i = 0; i < gridData.size(); i++) {
			Map<String, Object> pmOccptNo = dao.queryForMap("mt0101.retrievePmOccptNoSeqNextValue", null);

			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
			gridData.get(i).put("pmRevSeq", pmRevSeq.get("pmRevSeq"));
			gridData.get(i).put("pmOccptNo", pmOccptNo.get("pmOccptNo"));

			dao.update("mt0101.insertMT0102CR", gridData.get(i));
			dao.update("mt0101.insertMT0102C", gridData.get(i));
		}
	}

	public void saveMT0102P(GridData<HashMap> gridData, Map<String, Object> pmRevSeq) {
		for(int i = 0; i < gridData.size(); i++) {
			Map<String, Object> pmPartNo = dao.queryForMap("mt0101.retrievePmPartNoSeqNextValue", null);

			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
			gridData.get(i).put("pmRevSeq", pmRevSeq.get("pmRevSeq"));
			gridData.get(i).put("pmPartNo", pmPartNo.get("pmPartNo"));

			dao.update("mt0101.insertMT0102PR", gridData.get(i));
			dao.update("mt0101.insertMT0102P", gridData.get(i));
		}
	}


	/**
	 * 작업 항목 정보 저장
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveMT0102JR(GridData<HashMap> gridData, Map<String, Object> pmRevSeq) {
		for(int i = 0; i < gridData.size(); i++) {
			Map<String, Object> pmJobNo = dao.queryForMap("mt0101.retrievePmJobNoSeqNextValue", null);

			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
			gridData.get(i).put("pmRevSeq", pmRevSeq.get("pmRevSeq"));
			gridData.get(i).put("pmJobNo", pmJobNo.get("pmJobNo"));

			dao.update("mt0101.saveMT0101MRList", gridData.get(i));
			dao.update("mt0101.insertMT0102JR", gridData.get(i));
		}
	}

	public void saveMT0102CR(GridData<HashMap> gridData, Map<String, Object> pmRevSeq) {
		for(int i = 0; i < gridData.size(); i++) {
			Map<String, Object> pmOccptNo = dao.queryForMap("mt0101.retrievePmOccptNoSeqNextValue", null);

			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
			gridData.get(i).put("pmRevSeq", pmRevSeq.get("pmRevSeq"));
			gridData.get(i).put("pmOccptNo", pmOccptNo.get("pmOccptNo"));


			dao.update("mt0101.saveMT0101MRList", gridData.get(i));
			dao.update("mt0101.insertMT0102CR", gridData.get(i));
		}
	}

	public void saveMT0102PR(GridData<HashMap> gridData, Map<String, Object> pmRevSeq) {
		for(int i = 0; i < gridData.size(); i++) {
			Map<String, Object> pmPartNo = dao.queryForMap("mt0101.retrievePmPartNoSeqNextValue", null);

			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
			gridData.get(i).put("pmRevSeq", pmRevSeq.get("pmRevSeq"));
			gridData.get(i).put("pmPartNo", pmPartNo.get("pmPartNo"));

			dao.update("mt0101.saveMT0101MRList", gridData.get(i));
			dao.update("mt0101.insertMT0102PR", gridData.get(i));
		}
	}

	public List<Map<String, Object>> retrieveStNo1(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveStNo1", data);
	}

	public List<Map<String, Object>> retrieveStNo2(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveStNo2", data);
	}

	public List<Map<String, Object>> retrieveStNo3(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveStNo3", data);
	}

	public List<Map<String, Object>> retrieveStNo4(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveStNo4", data);
	}

	public List<Map<String, Object>> retrieveCalender(Map<String, Object> data) {
		if(data.get("companyCd") == null || "".equals(data.get("companyCd"))) {
			data.put("companyCd", UserInfo.getCompanyCd());
		}
		return dao.queryForMapList("mt0101.retrieveCalender", data);
	}

	public List<Map<String, Object>> retrieveMT0101RList(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveMT0101RList", data);
	}

	public List<Map<String, Object>> retrieveMT0101SubJobRList(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveMT0101SubJobRList", data);
	}

	public List<Map<String, Object>> retrieveMT0101SubCraftRList(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveMT0101SubCraftRList", data);
	}

	public List<Map<String, Object>> retrieveMT0101SubPartsRList(Map<String, Object> data) {
		return dao.queryForMapList("mt0101.retrieveMT0101SubPartsRList", data);
	}



}
