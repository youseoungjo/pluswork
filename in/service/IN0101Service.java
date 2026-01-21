package com.bplus.pw.in.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
 * Program Name : IN0101Service
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
public class IN0101Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0101Service.class);

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
	public List<Map<String, Object>> retrieveIN0101(Map<String, Object> data) {
		return dao.queryForMapList("in0101.retrieveIN0101", data);
	}

	public List<Map<String, Object>> retrieveIN0101Cnt(Map<String, Object> data) {
		return dao.queryForMapList("in0101.retrieveIN0101Cnt", data);
	}

	/**
	 * 트리에서 점검작성 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0101ToTree(Map<String, Object> data) {
		return dao.queryForMapList("in0101.retrieveIN0101ToTree", data);
	}

	/**
	 * 점검항목 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0101Sub(Map<String, Object> data) {
		return dao.queryForMapList("in0101.retrieveIN0101Sub", data);
	}

	/**
	 * 점검일정 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0101Sub2(Map<String, Object> data) {
		return dao.queryForMapList("in0101.retrieveIN0101Sub2", data);
	}

	/**
	 * 점검목록 추가/수정/삭제
	 *
	 * @return List<Map<String, Object>>
	 */
	public void saveIN0101List(GridData<HashMap> IN0101List,GridData<HashMap> IN0101ListSub,GridData<HashMap> in0201PList, Map<String, Object> map) {

		if( !"".equals(map.get("saveDiv")) ) {
			// 점검번호 시퀀스 생성
			Map<String, Object> chkRevSeq = dao.queryForMap("in0101.retrieveChkRevSeqNextValue", null);
			Map<String, Object> chkRevNo = dao.queryForMap("in0101.retrieveChkRevNoNextValue", null);

			// 점검마스터 생성
			saveIN0101(IN0101List, chkRevSeq, chkRevNo);


			if( "Y".equals(map.get("detailUpdate")) ) {
				if( "C".equals(map.get("saveDiv")) ) {
					saveIN0201DList(IN0101ListSub, chkRevSeq);

				} else if( "U".equals(map.get("saveDiv")) ) {
					saveIN0201DRList(IN0101ListSub, chkRevSeq);
				}
			} else if( "N".equals(map.get("detailUpdate")) ) {
				if( "Y".equals(map.get("revisionCreate")) ) {
					if( "C".equals(map.get("saveDiv")) ) {
						saveIN0201DList(IN0101ListSub, chkRevSeq);
					} else if( "U".equals(map.get("saveDiv")) ) {
						saveIN0201DRList(IN0101ListSub, chkRevSeq);
					}
				}



			}
		}

		// 점검마스터 담당자 지정 저장 프로세스
		saveIN0201P(in0201PList);
	}

	public void saveIN0101(GridData<HashMap> gridData, Map<String, Object> chkRevSeq, Map<String, Object> chkRevNo) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				record.put("chkRevSeq", chkRevSeq.get("chkRevSeq"));
				record.put("chkRevNo", chkRevNo.get("chkRevNo"));

				//TBM인지 UBM인지 구분해서 처리
				if ("T".equals(record.get("pmTp"))){
					dao.update("in0101.saveIN0101RListTbm", record);
					dao.update("in0101.saveIN0101ListTbm", record);
				} else {
					dao.update("in0101.saveIN0101RListUbm", record);
					dao.update("in0101.saveIN0101ListUbm", record);
				}
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());
				newRecord.put("chkRevSts", "10");
				newRecord.put("chkRevYmd", null);
				newRecord.put("chkRevSeq", chkRevSeq.get("chkRevSeq"));
				newRecord.put("chkRevNo", null);

				if ("T".equals(newRecord.get("pmTp"))){
					if(!newRecord.get("chkNm").equals(oldRecord.get("chkNm")) || !newRecord.get("actYn").equals(oldRecord.get("actYn")) || !newRecord.get("eqNo").equals(oldRecord.get("eqNo"))
							|| !newRecord.get("calCd").equals(oldRecord.get("calCd")) || !newRecord.get("dayYn").equals(oldRecord.get("dayYn")) || !newRecord.get("orgCd").equals(oldRecord.get("orgCd")) ) {
						dao.update("in0101.saveIN0101RListTbm", newRecord);
					}
					dao.update("in0101.updateCycle", newRecord);



				} else {
					dao.update("in0101.saveIN0101RListUbm", newRecord);
				}


			}

			public void delete(HashMap record, int index) {
				dao.update("in0101.deleteIN0101List", record);
			}
		});

	}

	/**
	 * 점검항목 추가
	 *
	 * @return
	 */
	public void saveIN0201DList(GridData<HashMap> gridData, Map<String, Object> chkRevSeq) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void normal(HashMap record, int index) {
				Map<String, Object> chkDtlNo = dao.queryForMap("in0101.retrieveChkDtlNoSeqNextValue", null);
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());
				record.put("chkRevSeq", chkRevSeq.get("chkRevSeq"));
				record.put("chkDtlNo", chkDtlNo.get("chkDtlNo"));


				dao.update("in0101.saveIN0201DRList", record);
				dao.update("in0101.saveIN0201DList", record);
			}
		});

	}
//	public void saveIN0201DList(GridData<HashMap> gridData, Map<String, Object> chkRevSeq) {
//		for(int i = 0; i < gridData.size(); i++) {
//
//			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
//			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
//			gridData.get(i).put("chkRevSeq", chkRevSeq.get("chkRevSeq"));
//
//			dao.update("in0101.saveIN0201DRList", gridData.get(i));
//			dao.update("in0101.saveIN0201DList", gridData.get(i));
//		}
//	}

	/**
	 * 점검항목 수정
	 *
	 * @return
	 */
	public void saveIN0201DRList(GridData<HashMap> gridData, Map<String, Object> chkRevSeq) {
		for(int i = 0; i < gridData.size(); i++) {
			Map<String, Object> chkDtlNo = dao.queryForMap("in0101.retrieveChkDtlNoSeqNextValue", null);
			gridData.get(i).put("fstRegUserId", UserInfo.getUserId());
			gridData.get(i).put("fnlEditUserId", UserInfo.getUserId());
			gridData.get(i).put("chkRevSeq", chkRevSeq.get("chkRevSeq"));
			gridData.get(i).put("chkDtlNo", chkDtlNo.get("chkDtlNo"));

			dao.update("in0101.saveIN0201MRList", gridData.get(i));
			dao.update("in0101.saveIN0201DRList", gridData.get(i));
		}
	}

	/**
	 * 예방점검 리비전 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0101RList(Map<String, Object> data) {
		return dao.queryForMapList("in0101.retrieveIN0101RList", data);
	}

	/**
	 * 점검항목리비전 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0201DRList(Map<String, Object> data) {
		return dao.queryForMapList("in0101.retrieveIN0201DRList", data);
	}

	/**
	 * 점검부서 리스트 조회(점검마스터 항목 중 점검부서로 지정된 부서리스트 조회)
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveChkOrgList(Map<String, Object> data) {
		data.put("companyCd", UserInfo.getCompanyCd());
		return dao.queryForMapList("in0101.retrieveChkOrgList", data);
	}


	public void saveIN0101RListCmmnt(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {


			public void insert(HashMap record, int index) {
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());
				dao.update("in0101.updateIN0101RListCmmnt", newRecord);
			}

			public void delete(HashMap record, int index) {
			}
		});

	}

	/**
	 * 점검마스터 담당자 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0201PList(Map<String, Object> data) {
		return dao.queryForMapList("in0101.retrieveIN0201PList", data);
	}

	public void saveIN0201P(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("in0101.insertIN0201PList", record);
			}


			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("in0101.updateIN0201PList", newRecord);

			}

			public void delete(HashMap record, int index) {
				dao.update("in0101.deleteIN0201PList", record);
			}
		});

	}

	//예방정비일정 생성
    public List<Map<String, Object>> createSchedule(Map<String, Object> data) {
       Map<String, Object> findCheckData = new HashMap<String, Object>();
	   findCheckData.put("plantCode", data.get("plantCode"));
	   findCheckData.put("factoryCode", data.get("factoryCode"));
	   String chkStdMd = (String) data.get("chkStdMd");
       String targetYear = (String) data.get("targetYear");
       String targetYmd = targetYear + "-12-31" ;									//작업년도의 마지막날
       findCheckData.put("chkNo", data.get("chkNo"));
       findCheckData.put("chkStdMd", chkStdMd);									//예방점검 년도
       findCheckData.put("targetYear", targetYear);									//예방점검 년도
       findCheckData.put("targetYmd", targetYmd);									//마지막 년월
       findCheckData.put("companyCd", UserInfo.getCompanyCd());
       findCheckData.put("fstRegUserId", UserInfo.getUserId());
       findCheckData.put("fnlEditUserId", UserInfo.getUserId());
       //점검항목 및 공구 리스트 작업 요건을 조회 해 옮
       List<Map<String, Object>> selectIN0201MList = dao.queryForMapList("in0101.selectIN0201MList", findCheckData);
       if(selectIN0201MList.size() > 0) {												//TBM 작업이 있을 시만 작업함 (UBM 작업이 기존 생성된 자료 삭제되면 안됨

    	   dao.update("in0101.createBeforeDeleteIN0401M", findCheckData);				//작업일자 이전 완료되지 않은 예방정비 자료 삭제
	       for (int i=0; i < selectIN0201MList.size(); i++){
	    	   findCheckData.put("chkNo", selectIN0201MList.get(i).get("chkNo"));
	    	   findCheckData.put("eqNo", selectIN0201MList.get(i).get("eqNo"));
	    	   findCheckData.put("cycleTp", selectIN0201MList.get(i).get("cycleTp"));	//점검주기  D:일간, W:주간, M:월간, Y1:년1회, Y2:년2회, Y2:년3회, Y2:년4회
	    	   findCheckData.put("cycleCd", selectIN0201MList.get(i).get("cycleCd"));		//년간 주차
	    	   findCheckData.put("dayWeek", selectIN0201MList.get(i).get("dayWeek"));		//년간 요일
	    	   findCheckData.put("calCd", selectIN0201MList.get(i).get("calCd"));		// 휴일지정코드

	    	   if (selectIN0201MList.get(i).get("cycleTp").equals("Y1")) {		//년1회
	    		   Integer addMonthI = Integer.parseInt(chkStdMd.substring(5,7));
	    	       findCheckData.put("chkMM", addMonthI);
	    		   dao.update("in0101.insertIN0401WY", findCheckData);
	    	   } else if (selectIN0201MList.get(i).get("cycleTp").equals("Y2")) {		//년2회
	    		   Integer addMonthI = Integer.parseInt(chkStdMd.substring(5,7));
	    		   String addMonth = "" ;
	    	       findCheckData.put("chkMM", addMonthI);
	    		   dao.update("in0101.insertIN0401WY", findCheckData);

	    		   addMonth = getAfterMonth(chkStdMd.replaceAll("-",  ""), 6);								//두번이기에 6개월을 더해서 작업함
	    		   if(addMonth.substring(0,4).equals(chkStdMd.substring(0,4))) {
		    		   addMonthI = Integer.parseInt(addMonth.substring(4,6));
		    	       findCheckData.put("chkMM", addMonthI);							//예방점검 작업시작일자
		    		   dao.update("in0101.insertIN0401WY", findCheckData);
	    		   }
	    	   } else if (selectIN0201MList.get(i).get("cycleTp").equals("Y3")) {		//년3회
	    		   Integer addMonthI = Integer.parseInt(chkStdMd.substring(5,7));
	    		   String addMonth = "" ;
	    	       findCheckData.put("chkMM", addMonthI);
	    		   dao.update("in0101.insertIN0401WY", findCheckData);

	    		   addMonth = getAfterMonth(chkStdMd.replaceAll("-",  ""), 4);								//두번이기에 4개월을 더해서 작업함
	    		   if(addMonth.substring(0,4).equals(chkStdMd.substring(0,4))) {
		    		   addMonthI = Integer.parseInt(addMonth.substring(4,6));
		    	       findCheckData.put("chkMM", addMonthI);							//예방점검 작업시작일자
		    		   dao.update("in0101.insertIN0401WY", findCheckData);
	    		   }

	    		   addMonth = getAfterMonth(addMonth.replaceAll("-",  ""), 4);								//두번이기에 4개월을 더해서 작업함
	    		   if(addMonth.substring(0,4).equals(chkStdMd.substring(0,4))) {
		    		   addMonthI = Integer.parseInt(addMonth.substring(4,6));
		    	       findCheckData.put("chkMM", addMonthI);							//예방점검 작업시작일자
		    		   dao.update("in0101.insertIN0401WY", findCheckData);
	    		   }
	    	   } else if (selectIN0201MList.get(i).get("cycleTp").equals("Y4")) {		//년4회
	    		   Integer addMonthI = Integer.parseInt(chkStdMd.substring(5,7));
	    		   String addMonth = "" ;
	    	       findCheckData.put("chkMM", addMonthI);
	    		   dao.update("in0101.insertIN0401WY", findCheckData);

	    		   addMonth = getAfterMonth(chkStdMd.replaceAll("-",  ""), 3);								//두번이기에 3개월을 더해서 작업함
	    		   if(addMonth.substring(0,4).equals(chkStdMd.substring(0,4))) {
		    		   addMonthI = Integer.parseInt(addMonth.substring(4,6));
		    	       findCheckData.put("chkMM", addMonthI);							//예방점검 작업시작일자
		    		   dao.update("in0101.insertIN0401WY", findCheckData);
	    		   }

	    		   addMonth = getAfterMonth(addMonth.replaceAll("-",  ""), 3);								//두번이기에 3개월을 더해서 작업함
	    		   if(addMonth.substring(0,4).equals(chkStdMd.substring(0,4))) {
		    		   addMonthI = Integer.parseInt(addMonth.substring(4,6));
		    	       findCheckData.put("chkMM", addMonthI);							//예방점검 작업시작일자
		    		   dao.update("in0101.insertIN0401WY", findCheckData);
	    		   }

	    		   addMonth = getAfterMonth(addMonth.replaceAll("-",  ""), 3);								//두번이기에 3개월을 더해서 작업함
	    		   if(addMonth.substring(0,4).equals(chkStdMd.substring(0,4))) {
		    		   addMonthI = Integer.parseInt(addMonth.substring(4,6));
		    	       findCheckData.put("chkMM", addMonthI);							//예방점검 작업시작일자
		    		   dao.update("in0101.insertIN0401WY", findCheckData);
	    		   }
	    	   }

	    	   // 지정한 휴일 날짜인 경우 다음날로 update
	    	   if(!"".equals(findCheckData.get("calCd"))) {
	    		   boolean holidayChk = true;


	    		   while(holidayChk) {
	    			   List<Map<String, Object>> selectMT0301DList = dao.queryForMapList("in0101.selectMT0301DList", findCheckData);
		    		   if(selectMT0301DList.size() > 0) {
		    			   for(int j = 0; j < selectMT0301DList.size(); j++) {
		    				   String exportYmd = (String) selectMT0301DList.get(j).get("exportYmd");
		    				   findCheckData.put("exportYmd", exportYmd);
		    				   dao.update("in0101.updateActlYmd", findCheckData);
		    			   }
		    		   } else {
		    			   holidayChk = false;
		    		   }
	    		   }

	    	   }

	       }
       }
       return selectIN0201MList ;
    }

    public static String getAfterMonth(String originDate, int month)
    {
        Calendar tempCal = Calendar.getInstance();

        tempCal.set(Integer.parseInt(originDate.substring(0, 4)),
                    Integer.parseInt(originDate.substring(4, 6)) - 1,
                    Integer.parseInt(originDate.substring(6, 8))
                    );

        tempCal.add(Calendar.MONTH, month);

        int iYear = tempCal.get(Calendar.YEAR);
        // month는 0 base 이다.
        int iMonth = tempCal.get(Calendar.MONTH)+1;
        int iDay = tempCal.get(Calendar.DAY_OF_MONTH);

        return iYear + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
    }

}
