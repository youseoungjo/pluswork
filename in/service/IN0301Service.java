package com.bplus.pw.in.service;

import java.util.ArrayList;
import java.util.Calendar;
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
import idr.extend.util.StringUtil;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class : 점검일정 생성
 * Program Name : IN0301Service
 * Description : 점검일정 생성을 위한 @Service 클래스
 * Author : jit13
 * Created Date : 2019-07-29
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
public class IN0301Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(IN0301Service.class);

	/**
	 * DB처리를 위한 공통 dao
	 */
	@Autowired
	@Qualifier("mainDB")
	private CommonDao dao;

	/**
	 * 설비별 점검데이터 목록 조회
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0301(Map<String, Object> data) {
		Map<String, Object> retrieveActlYmdData = new HashMap<String, Object>();

		List<Map<String, Object>> selectYmdList = dao.queryForMapList("in0301.retrieveIN0301Ymd", data);

		String startDate = data.get("fStartDate").toString().replace("-", "");
		String endDate = data.get("fEndDate").toString().replace("-", "");

		String [] monthArray = getArrayBetweenMonth(startDate, endDate);

		data.put("selectYmdList",  selectYmdList);

		List<Map<String, Object>> retrieveIN0301List = dao.queryForMapList("in0301.retrieveIN0301", data);

		/*
		retrieveActlYmdData.put("fStartDate", data.get("fStartDate"));
		retrieveActlYmdData.put("fEndDate", data.get("fEndDate"));

		for(int i=0; i< retrieveIN0301List.size(); i++){
			retrieveActlYmdData.put("chkNo", retrieveIN0301List.get(i).get("chkNo"));
			String [] actualDateView = stringToArray("", monthArray.length);

			List<Map<String, Object>> retrieveIN0301ListActlYmd = dao.queryForMapList("in0301.retrieveIN0301ActlYmd", retrieveActlYmdData);

			for(int k=0; k < retrieveIN0301ListActlYmd.size(); k++){
	            String actualDate = retrieveIN0301ListActlYmd.get(k).get("actlYmd").toString().replace("-", "");  // 계획일자
	            String isComplete = retrieveIN0301ListActlYmd.get(k).get("cmpltYn").toString();  // 완료여부
	            String conUncheck = retrieveIN0301ListActlYmd.get(k).get("unchkYn").toString();  // 미점검확인

	            if (actualDate == null || actualDate.length() != 8) continue;

	            String year  = actualDate.substring(0, 4);  // 년
	            String month = actualDate.substring(4, 6);  // 월
	            String day   = actualDate.substring(6);     // 일

	            // 점검완료가 되어있다면 일자앞에 _를 붙인다.(점검 구분)
	            if ("Y".equals(isComplete)) day = "_" + day;
	            else if ("Y".equals(conUncheck)) day = "+" + day;

	            for (int j=0; j<monthArray.length; j++){
	                // 시작년월과 계획년월이 같다면
	                if (monthArray[j].equals(year+month)){
	                    // 셋팅된 일자가 있다면 ,를 붙인다.
	                    if ("".equals(actualDateView[j])){
	                        actualDateView[j] = day;
	                        break;
	                    }
	                    else{
	                        actualDateView[j] = actualDateView[j] + "," + day;
	                        break;
	                    }
	                }
	            }

			}

	        for (int l=0; l<monthArray.length; l++){
	        	retrieveIN0301List.get(i).put(monthArray[l], actualDateView[l]);
	        }
  	   }
  	   */
	   return retrieveIN0301List;
	}

	/**
	 * 점검일정 합계
	 *
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0301Total(Map<String, Object> data) {
		Map<String, Object> retrieveActlYmdData = new HashMap<String, Object>();

		List<Map<String, Object>> selectYmdList = dao.queryForMapList("in0301.retrieveIN0301Ymd", data);

		String startDate = data.get("fStartDate").toString().replace("-", "");
		String endDate = data.get("fEndDate").toString().replace("-", "");

		String [] monthArray = getArrayBetweenMonth(startDate, endDate);

		data.put("selectYmdList",  selectYmdList);
		data.put("selectYmdSubList",  selectYmdList);

		List<Map<String, Object>> retrieveIN0301List = dao.queryForMapList("in0301.retrieveIN0301Total", data);
	   return retrieveIN0301List;
	}

	/**
	 *
	 * 담당자별 점검일정 조회
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> retrieveIN0301Sub(Map<String, Object> data) {
		Map<String, Object> retrieveActlYmdData = new HashMap<String, Object>();

		List<Map<String, Object>> selectYmdList = dao.queryForMapList("in0301.retrieveIN0301Ymd", data);

		String startDate = data.get("fStartDate").toString().replace("-", "");
		String endDate = data.get("fEndDate").toString().replace("-", "");

		String [] monthArray = getArrayBetweenMonth(startDate, endDate);

		data.put("selectYmdList",  selectYmdList);
		data.put("selectYmdSubList",  selectYmdList);

		List<Map<String, Object>> retrieveIN0301SubList = dao.queryForMapList("in0301.retrieveIN0301Sub", data);

		return retrieveIN0301SubList;
	}

	public void saveIN0301ListActlYmd(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("in0301.saveIN0301ListActlYmd", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("in0301.updateIN0301ListActlYmd", newRecord);
			}

			public void delete(HashMap record, int index) {
				dao.update("in0301.deleteIN0301ListActlYmd", record);
			}
		});
	}

    public static String [] getArrayBetweenMonth(String startDate, String endDate)
    {
        ArrayList arrayDate = new ArrayList();

        int startYear = Integer.parseInt(startDate.substring(0, 4));
        int startMonth = Integer.parseInt(startDate.substring(4, 6));

        int endYear = Integer.parseInt(endDate.substring(0, 4));
        int endMonth = Integer.parseInt(endDate.substring(4, 6));

        for(int year=startYear, month=startMonth; (year==endYear && month<=endMonth) || (year<endYear) ; month++)
        {
            arrayDate.add(year+""+(month<10?"0"+month:month));

            // 12월이 되었다면 년도를 증가한다.
            if (month == 12)
            {
                year ++;
                month = 0;
            }
        }

        return (String [])arrayDate.toArray(new String[arrayDate.size()]);
    }

    public static String [] stringToArray(String origin, int length)
    {
        String [] resultArray = new String[length];

        for (int i=0; i<length; i++)
        {
            resultArray[i] = origin;
        }

        return resultArray;
    }

    //전체일정 생성
    public List<Map<String, Object>> createIN0301List(Map<String, Object> data) {

    	// 일정시작일~종료일까지의 년월 조회
    	List<Map<String, Object>> selectYmdList = dao.queryForMapList("mt0201.retrieveMT0201Ymd", data);

    	/*
    	 *  시작일과 종료일의 - 문자 제거(2021-01-01 => 202101-01)
    	 *  replaceAll 할 경우 -문자 모두 제거 가능
    	 */
		String startDate = data.get("fStartDate").toString().replace("-", "");
		String endDate = data.get("fEndDate").toString().replace("-", "");

		data.put("selectYmdList",  selectYmdList);

		/*
		 *  점검번호별 생성된 월별 일정 건수 조회
		 *  EX) EA00000001, C1XX BJ 조립라인 ,200446	, PI 예방점검 TEST 1203, G, General insp.(일반점검), 000328, 설비개선팀, 1, M, 1, month, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 3
		 */
		List<Map<String, Object>> retrieveIN0301List = dao.queryForMapList("in0301.retrieveIN0301", data);  //조회된 데이터가져오기
		Map<String, Object> findCheckData = new HashMap<String, Object>();

		findCheckData.put("fStartDate", data.get("fStartDate"));
		findCheckData.put("fEndDate", data.get("fEndDate"));

		for (int i=0; i < retrieveIN0301List.size(); i++){
			findCheckData.put("chkNo", retrieveIN0301List.get(i).get("chkNo"));  //점검번호 세팅
			/*
			 *  조회된 점검번호의 속성 조회(주기,휴일지정달력코드, 점검요일, 점검기준일)
			 *  EX) 200446, G, 1, M, 10013, 04-06
			 */
			List<Map<String, Object>> findCheckInfoList = dao.queryForMapList("in0301.findCheckInfoIN0301", findCheckData);

			for(int j=0; j < findCheckInfoList.size(); j++){
				String calCd      = "";
				String cycleCd    = "";
				String cycleTp    = "";
				String lastChkYmd = "";
				String dayWeek    = "";
				String chkStdMd   = "";

				if(findCheckInfoList.size() > 0){
					if (findCheckInfoList.get(j).get("calCd") != null){
					   calCd      = findCheckInfoList.get(j).get("calCd").toString(); // 달력종류
					}
					if (findCheckInfoList.get(j).get("cycleCd") != null){
					   cycleCd    = findCheckInfoList.get(j).get("cycleCd").toString(); // 주기코드
					}
					if (findCheckInfoList.get(j).get("cycleTp") != null){
					   cycleTp    = findCheckInfoList.get(j).get("cycleTp").toString(); // 주기유형
					}
					if (findCheckInfoList.get(j).get("lastChkYmd") != null ){
					   lastChkYmd = findCheckInfoList.get(j).get("lastChkYmd").toString(); // 최종점검일자
					}
					if (findCheckInfoList.get(j).get("dayWeek") != null ){
					   dayWeek    = findCheckInfoList.get(j).get("dayWeek").toString(); // 요일
					}
					if (findCheckInfoList.get(j).get("chkStdMd") != null ){
						 chkStdMd = data.get("fYear") + "-" + findCheckInfoList.get(j).get("chkStdMd").toString(); // 점검기준일
					}

					int checkCount = 0;

					chkStdMd = chkStdMd.toString().replace("-", "");

					// 해당 주기에 따른 다음 일자를 계산한다.
					String checkDate = chkStdMd ;

					/*
					 * checkDate 가 휴무인지 이닌지 체크한다.
					 * 휴무일 이라면 이후 첫번째 평일로 날짜를 보정한다.
					 * 보정일자가 현재 년도가 아니면 일정 생성 멈춘다.
					 */
//					if(!"".equals(calCd) && checkCount == 0) {
//						/*
//						 * 달력이 있고 첫번쨰 지정일자가 아닌경우만 휴일 제외 (첫 시작지정일은 휴일이라도 생성 되어야 함) >> 김철욱 상무
//						 * 2021.12.08 첫 지정일자가 휴일과 겹치는 경우 다음일자로 계획생성 >> PI 정제호
//						 */
//						checkDate = getCheckDate(calCd, checkDate, cycleCd, cycleTp, checkCount, dayWeek, checkDate);
//						checkDate = checkDate.toString().replace("-", "");
//					}

					checkDate = getCheckDate(calCd, checkDate, cycleCd, cycleTp, checkCount, dayWeek, checkDate);
//					checkDate = nextCheckDate(calCd, checkDate, cycleCd, cycleTp, checkCount, dayWeek, checkDate);

					// 계산된 점검계획일자와  현재 생성 년도사이의 점검안된값들은 모두 삭제한다.(다시 생성된다.)
					dao.update("in0301.createBeforeDeleteIN0301List", findCheckData);

					// 바로전 생성 점검일자
					String beforeCheckDate = checkDate;

					// 점검년도가 현재 점검To일자 보다 같거나 작을때까지
					while (compareDate(checkDate, endDate)){
						findCheckData.put("checkDate", checkDate);

						// 생성하려는 일자에 점검 일자가 있거나, 점검생성시작일 보다 작다면 생성하지 않는다.
						String checkActualDate = dao.queryForObject("in0301.checkActualDate",findCheckData, String.class);

						if (checkActualDate == null && compareDate(startDate, checkDate)){
						   Map<String, Object> insertDate = new HashMap<String, Object>();
						   insertDate.put("chkNo", retrieveIN0301List.get(i).get("chkNo"));
						   insertDate.put("actlYmd", checkDate);

						   insertDate.put("fstRegUserId", UserInfo.getUserId());
						   insertDate.put("fnlEditUserId", UserInfo.getUserId());

						   //데이터 생성
						   dao.update("in0301.saveIN0301AllListActlYmd", insertDate);
						}

						// 다음 일자를 계산하기 위해서.. 처리
						checkCount++;
						// 해당 주기에 따른 다음 점검 일자를 계산한다.
						checkDate = nextCheckDate(calCd, checkDate, cycleCd, cycleTp, checkCount, dayWeek, beforeCheckDate);

						beforeCheckDate = checkDate;
					} //while문 종료
				}//if문종료
			}//for문 종료
		}//for문 종료
		return retrieveIN0301List;
	}

    private String nextCheckDate(String calCd, String chkStdMd, String cycleCd, String cycleTp, int checkCount, String dayWeek, String beforeCheckDate)
    {
        if (chkStdMd == null || cycleCd == null || cycleTp == null ||
            "".equals(chkStdMd) || "".equals(cycleCd) || "".equals(cycleTp))
        {
            return null;
        }

        // 점검일자
        String checkDate = "";
        /*
         * 주기 는 4개가 있다.
         * D:일, W:주, M:월, Y:년
         */
        if ("D".equals(cycleTp))
        {
//            int interDate = Integer.parseInt(cycleCd) * checkCount;
            int interDate = Integer.parseInt(cycleCd);
            checkDate = getAfterDays(chkStdMd, interDate);
        }
        else if("W".equals(cycleTp))
        {
//            int interDate = Integer.parseInt(cycleCd) * 7 * checkCount;
            int interDate = Integer.parseInt(cycleCd) * 7 ;
            checkDate = getAfterDays(chkStdMd, interDate);
        }
        else if("M".equals(cycleTp))
        {
//            int interMonth = Integer.parseInt(cycleCd) * checkCount;
            int interMonth = Integer.parseInt(cycleCd) ;
            checkDate = getAfterMonth(chkStdMd, interMonth);
        }
        else if("Y".equals(cycleTp))
        {
//            int interMonth = Integer.parseInt(cycleCd) * 12 * checkCount;
            int interMonth = Integer.parseInt(cycleCd) * 12 ;
            checkDate = getAfterMonth(chkStdMd, interMonth);
        }

        // 요일이 있다면 lastCheckDate를 요일을 보정한 값으로 출발시킨다.
        if (!"".equals(dayWeek)) checkDate = checkLastCheckDate(checkDate, dayWeek);

        /*
         * checkDate 가 휴무인지 이닌지 체크한다.
         * 휴무일 이라면 이후 첫번째 평일로 날짜를 보정한다.
         * 보정일자가 현재 년도가 아니면 일정 생성 멈춘다.
         */
       	checkDate = getCheckDate(calCd, checkDate, cycleCd, cycleTp, checkCount, dayWeek, beforeCheckDate);

        return checkDate;
    }

    public static String getAfterDays(String originDate, int days)
    {
        Calendar tempCal = Calendar.getInstance();

        tempCal.set(Integer.parseInt(originDate.substring(0, 4)),
                    Integer.parseInt(originDate.substring(4, 6)) - 1,
                    Integer.parseInt(originDate.substring(6, 8))
                    );

        tempCal.add(Calendar.DAY_OF_MONTH, days);

        int iYear = tempCal.get(Calendar.YEAR);
        // month는 0 base 이다.
        int iMonth = tempCal.get(Calendar.MONTH)+1;
        int iDay = tempCal.get(Calendar.DAY_OF_MONTH);

        return iYear + (iMonth<10?"0"+iMonth:iMonth+"") + (iDay<10?"0"+iDay:iDay+"");
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

    public static int getDayOfWeek(String yyyyMMdd)
    {
        if (yyyyMMdd == null || yyyyMMdd.length() != 8)
        {
            return 0;
        }

        // 년, 월, 일로 분리한다.
        String year  = yyyyMMdd.substring(0, 4);
        String month = yyyyMMdd.substring(4, 6);
        String day   = yyyyMMdd.substring(6);


        int iYear = 0;
        int iMonth = 0;
        int iDay = 0;

        try
        {
            iYear  = Integer.parseInt(year);
            iMonth = Integer.parseInt(month);
            iDay   = Integer.parseInt(day);
        }
        catch(Exception e)
        {
            return 0;
        }

        Calendar tempCal = Calendar.getInstance();

        tempCal.set(iYear, iMonth-1, iDay);

        return tempCal.get(Calendar.DAY_OF_WEEK);
    }


    private String checkLastCheckDate(String originDate, String dayWeek)
    {
        // 1:일, 2:월, 3:화, 4:수, 5:목, 6:금, 7:토
        int iDayWeek = 0;
        if ("SUN".equals(dayWeek)) iDayWeek = 1;
        else if ("MON".equals(dayWeek)) iDayWeek = 2;
        else if ("TUE".equals(dayWeek)) iDayWeek = 3;
        else if ("WED".equals(dayWeek)) iDayWeek = 4;
        else if ("THU".equals(dayWeek)) iDayWeek = 5;
        else if ("FRI".equals(dayWeek)) iDayWeek = 6;
        else if ("SAT".equals(dayWeek)) iDayWeek = 7;
        else return originDate;

        // 1:일, 2:월, 3:화, 4:수, 5:목, 6:금, 7:토
        int oringDayWeek = getDayOfWeek(originDate);

        int corWeek = iDayWeek - oringDayWeek;
        if (corWeek < 0) corWeek = 7 + corWeek;

        return getAfterDays(originDate, corWeek);
    }

    private String getCheckDate(String calCd, String checkDate, String cycleCd, String cycleTp, int checkCount, String dayWeek, String beforeCheckDate)
    {
        /*
         * 보전칼렌더 유효성 체크
         * 보전칼렌더가 생성안되어 있거나 유효하지 않다면 기본 칼렌더 적용
         */
        // String checkDate = pmiYearCksDAO.checkCalType(calCd, _checkDate);

    	String holyDayDate = "";
    	String holyCheckDate = "";
    	String nextDay = "NOT OK";
    	int oringDayWeek = 9 ;

        /*
         * 계산된 점검 일자가 last_check_date 와 같거나 작으면
         * 다음 평일일자를 check 일자로 잡는다.
         * (연휴가 길고 정비 주기가 짧다면 계속 해서 정비일자가 휴일이라면
         *  이전 정비가능일자로 정비일자를 잡기때문에 같은일자로 정비일자가 계산될수 있다.)
         */
//        if (compareDate(checkDate, lastChkYmd)) {
            // 가장 최근의 Work 일자를 리턴한다.
        	Map<String, Object> data = new HashMap<String, Object>();

        	data.put("calCd", calCd);
        	data.put("checkDate", checkDate);
            List<Map<String, Object>> retrieveMT0301MList = dao.queryForMapList("in0301.retrieveMT0301M", data);  //휴일관리 Master MT0301M(요일을 가져오기 떄문에 한번만 가져와서 일자별 비교만 해준다.
            do {
                nextDay = "NOT OK" ;
            	oringDayWeek = getDayOfWeek(checkDate);		//정비일자의 요일을 계산한다
            	String oringDayWeekStr =  Integer.toString(oringDayWeek) ;
            	data.put("oringDayWeekStr", oringDayWeekStr);

         	   for(int j=0; j < retrieveMT0301MList.size(); j++){

         		   if (oringDayWeekStr.equals(retrieveMT0301MList.get(j).get("colName").toString())) {
//         			  if ("".equals(dayWeek)) {
	                       checkDate = getAfterDays(checkDate, 1);		//
	                       nextDay = "OK" ;
	                       break ;
//         			  } else {
//         				 checkDate = nextCheckDate(calCd, checkDate, cycleCd, cycleTp, checkCount, dayWeek, beforeCheckDate);
//         			  }
         	       }
         	   }
               if(!"OK".equals(nextDay)) { //이미 휴일 판정이 났으므로 일별 휴일 자료를 조회할 필요 없음
	           	   holyCheckDate = dao.queryForObject("in0301.findWorkingDay",data, String.class);		//휴일 Detail MT0301D의  checkDate 쉬는날인지 비교한다
	           	   holyCheckDate = StringUtil.getText(holyCheckDate) ;
	               if (!"".equals(holyCheckDate)) {
	                   checkDate = getAfterDays(checkDate, 1);		//
	               	   nextDay = "OK" ;
	           	   }
               }
               if(!"OK".equals(nextDay)) { //이미 휴일 판정이 났으므로 일별 휴일 자료를 조회할 필요 없음
	               List<Map<String, Object>> retrieveWeekChk = dao.queryForMapList("in0301.retrieveWeekChk", data);  //휴일관리 Master MT0301M(요일을 가져오기 떄문에 한번만 가져와서 일자별 비교만 해준다.
	       		   if(retrieveWeekChk.size() > 0) {
	       			   checkDate = getAfterDays(checkDate, 1);		//
	       			   nextDay = "OK" ;
	       		   }
               }

               if(!"OK".equals(nextDay)) { //이미 휴일 판정이 났으므로 일별 휴일 자료를 조회할 필요 없음
	       		   if (!"".equals(dayWeek)) {
	       			   String dayWeekNum = "";
	        		   if("SUN".equals(dayWeek)) {
	        			   dayWeekNum = "1";
	        		   } else if("MON".equals(dayWeek)) {
	        			   dayWeekNum = "2";
	        		   } else if("TUE".equals(dayWeek)) {
	        			   dayWeekNum = "3";
	        		   } else if("WED".equals(dayWeek)) {
	        			   dayWeekNum = "4";
	        		   } else if("THU".equals(dayWeek)) {
	        			   dayWeekNum = "5";
	        		   } else if("FRI".equals(dayWeek)) {
	        			   dayWeekNum = "6";
	        		   } else if("SAT".equals(dayWeek)) {
	        			   dayWeekNum = "7";
	        		   }
	        		   if(!dayWeekNum.equals(oringDayWeekStr)) {
	 				   checkDate = checkLastCheckDate(checkDate, dayWeek);
	 				   nextDay = "OK" ;
	        		   }
	       		   }
               }

               data.put("checkDate", checkDate);
            } while("OK".equals(nextDay));		//정비일자가 휴일이 아닐때 까지 돌린다

//        }

        return checkDate;
    }

    /**
     * 두 날짜를 비교하여 fromDate 가 작거나 같으면 true
     * 크면 false를 리턴한다.
     *
     * @param fromDate
     * @param toDate
     * @return
     * @throws Exception
     */
    public static boolean compareDate(String fromDate, String toDate)
    {
        int iFromdate = Integer.parseInt(fromDate);
        int iToDate = Integer.parseInt(toDate);

        if (iFromdate <= iToDate)
        {
            return true;
        }

        return false;
    }

    /* 계획일 하루전으로 변경 W/O 미생성 대상만 적용 */
	public void moveDayMinusIN0301(Map<String, Object> data) {
		data.put("fstRegUserId", UserInfo.getUserId());
		data.put("fnlEditUserId", UserInfo.getUserId());

		dao.update("in0301.moveDayMinusIN0301", data);
	}

	/* 계획일 하루후로 변경 W/O 미생성 대상만 적용 */
	public void moveDayPlusIN0301(Map<String, Object> data) {
		data.put("fstRegUserId", UserInfo.getUserId());
		data.put("fnlEditUserId", UserInfo.getUserId());

		dao.update("in0301.moveDayPlusIN0301", data);
	}

	//예방정비일정 생성
    public List<Map<String, Object>> createScheduleList(Map<String, Object> data) {
       Map<String, Object> findCheckData = new HashMap<String, Object>();
       String targetYear = (String) data.get("targetYear");
       String targetYmd = targetYear + "-12-31" ;									//작업년도의 마지막날
       findCheckData.put("targetYear", targetYear);									//예방점검 년도
       findCheckData.put("targetYmd", targetYmd);									//마지막 년월
       findCheckData.put("companyCd", UserInfo.getCompanyCd());
       findCheckData.put("fstRegUserId", UserInfo.getUserId());
       findCheckData.put("fnlEditUserId", UserInfo.getUserId());
       findCheckData.put("chkNoS", data.get("chkNoS"));									//마지막 년월
       //점검항목 및 공구 리스트 작업 요건을 조회 해 옮
       List<Map<String, Object>> selectIN0201MList = dao.queryForMapList("in0301.selectIN0201MList", findCheckData);
       if(selectIN0201MList.size() > 0) {												//TBM 작업이 있을 시만 작업함 (UBM 작업이 기존 생성된 자료 삭제되면 안됨

	       for (int i=0; i < selectIN0201MList.size(); i++){
	    	   findCheckData.put("chkNo", selectIN0201MList.get(i).get("chkNo"));
	    	   findCheckData.put("eqNo", selectIN0201MList.get(i).get("eqNo"));
	    	   findCheckData.put("cycleTp", selectIN0201MList.get(i).get("cycleTp"));	//점검주기  D:일간, W:주간, M:월간, Y1:년1회, Y2:년2회, Y2:년3회, Y2:년4회
	    	   findCheckData.put("cycleCd", selectIN0201MList.get(i).get("cycleCd"));		//년간 주차
	    	   findCheckData.put("dayWeek", selectIN0201MList.get(i).get("dayWeek"));		//년간 요일
	    	   findCheckData.put("calCd", selectIN0201MList.get(i).get("calCd"));		// 휴일지정코드
	    	   findCheckData.put("chkStdMd", selectIN0201MList.get(i).get("chkStdMd"));		// 휴일지정코드
	    	   String chkStdMd = (String) selectIN0201MList.get(i).get("chkStdMd");

	    	   dao.update("in0101.createBeforeDeleteIN0401M", findCheckData);				//작업일자 이전 완료되지 않은 예방정비 자료 삭제

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

    public void saveProcess(GridData<HashMap> ds_IN0101Info, GridData<HashMap> ds_UserList) {
    	saveIN0101Cycle(ds_IN0101Info); // 점검주기 저장
		saveIN0201PList(ds_UserList); //담당자 정보 저장
    }



    public void saveIN0101Cycle(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void normal(HashMap record, int index) {
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("in0301.updateIN0101Cycle", record);
			}
		});

	}

    public void saveIN0201PList(GridData<HashMap> gridData) {
    	gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

    		public void insert(HashMap record, int index) {
    			record.put("fnlRegUserId", UserInfo.getUserId());

    			dao.update("in0301.insertIN0201P", record);
    		}
			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
    			newRecord.put("fnlEditUserId", UserInfo.getUserId());

    			dao.update("in0301.updateIN0201P", newRecord);
    		}
    		public void delete(HashMap record, int index) {

    			dao.update("in0301.deleteIN0201P", record);
    		}
    	});

    }

}
