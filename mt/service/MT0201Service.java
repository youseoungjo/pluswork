package com.bplus.pw.mt.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import idr.basis.data.GridData;
import idr.common.utils.StringUtil;
import idr.extend.iam.UserInfo;
import idr.extend.query.CommonDao;
import idr.extend.query.callback.AbstractRowStatusCallback;

/**
 * <pre>
 * ---------------------------------------------------------------
 * System Class : 점검일정 생성
 * Program Name : MT0201Service
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
public class MT0201Service {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(MT0201Service.class);

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
	public List<Map<String, Object>> retrieveMT0201(Map<String, Object> data) {
		Map<String, Object> retrievePlanData = new HashMap<String, Object>();

		List<Map<String, Object>> selectYmdList = dao.queryForMapList("mt0201.retrieveMT0201Ymd", data);

		String startDate = data.get("fStartDate").toString().replace("-", "");
		String endDate = data.get("fEndDate").toString().replace("-", "");

		String [] monthArray = getArrayBetweenMonth(startDate, endDate);

		data.put("selectYmdList",  selectYmdList);

		List<Map<String, Object>> retrieveMT0201List = dao.queryForMapList("mt0201.retrieveMT0201", data);

		/*
		retrievePlanData.put("fStartDate", data.get("fStartDate"));
		retrievePlanData.put("fEndDate", data.get("fEndDate"));

		for(int i=0; i< retrieveMT0201List.size(); i++){
			retrievePlanData.put("pmNo", retrieveMT0201List.get(i).get("pmNo"));
			String [] planDateView = stringToArray("", monthArray.length);

			List<Map<String, Object>> retrieveMT0201ListPlan = dao.queryForMapList("mt0201.retrieveMT0201ListPlan", retrievePlanData);

			for(int k=0; k < retrieveMT0201ListPlan.size(); k++){
				String planStartDate = "";
				String planEndDate = "";
				String woStatus = "";

				if ( retrieveMT0201ListPlan.get(k).get("planStartYmd") != null ){
					planStartDate = retrieveMT0201ListPlan.get(k).get("planStartYmd").toString();  // 계획일자 시작
				}

				if ( retrieveMT0201ListPlan.get(k).get("planEndYmd") != null ){
					planEndDate = retrieveMT0201ListPlan.get(k).get("planEndYmd").toString();  // 계획일자 시작
				}

				if ( retrieveMT0201ListPlan.get(k).get("woSts") != null ){
					woStatus = retrieveMT0201ListPlan.get(k).get("woSts").toString();  // 작업상태
				}

				planStartDate = planStartDate.toString().replace("-", "");
				planEndDate   = planStartDate.toString().replace("-", "");

				String planStartYear  = planStartDate.substring(0, 4);  // 년
	            String planStartMonth = planStartDate.substring(4, 6);  // 월
	            String planStartDay   = planStartDate.substring(6);     // 일

	            String planEndDay   = planEndDate.substring(6);     // 일

	            for (int j=0; j<monthArray.length; j++){
	                // 시작년월과 계획년월이 같다면
	                if (monthArray[j].equals(planStartYear+planStartMonth)){
	                    // 셋팅된 일자가 있다면 ,를 붙인다.
	                    if ("".equals(planDateView[j])){
	                    	planDateView[j] = planStartDay+"("+planEndDay+")_"+woStatus;
	                        break;
	                    }
	                    else{
	                    	planDateView[j] = planDateView[j] + "," + planStartDay+"("+planEndDay+")_"+woStatus;
	                        break;
	                    }
	                }
	            }
			}

	        for (int l=0; l<monthArray.length; l++){
	        	retrieveMT0201List.get(i).put(monthArray[l], planDateView[l]);
	        }
  	   }
  	   */
	   return retrieveMT0201List;
	}

	public void saveMT0201ListPlanYmd(GridData<HashMap> gridData) {
		gridData.forEachRow(new AbstractRowStatusCallback<HashMap>() {

			public void insert(HashMap record, int index) {
				record.put("fstRegUserId", UserInfo.getUserId());
				record.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("mt0201.saveMT0201ListPlanYmd", record);
			}

			public void update(HashMap newRecord, HashMap oldRecord, int arg2) {
				newRecord.put("fnlEditUserId", UserInfo.getUserId());

				dao.update("mt0201.updateMT0201ListPlanYmd", newRecord);
				if(!"".equals(newRecord.get("woNo"))){
				   dao.update("mt0201.updateMT0201ListWoPlanYmd", newRecord);
				};
			}

			public void delete(HashMap record, int index) {
				dao.update("mt0201.delMT0201ListPlanYmd", record);
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

    // 선택된 예방정비일정 생성
    public List<Map<String, Object>> createMT0201List(Map<String, Object> data) {
    	List<Map<String, Object>> selectYmdList = dao.queryForMapList("mt0201.retrieveMT0201Ymd", data);	// 조회조건의 start월 부터 end월까지의  리스트 출력 (ex. 202101, 202102,..., 202112)

		String startDate = data.get("fStartDate").toString().replace("-", "");
		String endDate = data.get("fEndDate").toString().replace("-", "");

		data.put("selectYmdList",  selectYmdList);

       List<Map<String, Object>> retrieveMT0201List = dao.queryForMapList("mt0201.retrieveMT0201", data);  //조회된 데이터가져오기
       Map<String, Object> findCheckData = new HashMap<String, Object>();

//	   String startDate = data.get("fStartDate").toString().replace("-", "");
//	   String endDate = data.get("fEndDate").toString().replace("-", "");

	   findCheckData.put("fStartDate", data.get("fStartDate"));
	   findCheckData.put("fEndDate", data.get("fEndDate"));
	   findCheckData.put("companyCd", data.get("companyCd"));

       for (int i=0; i < retrieveMT0201List.size(); i++){
    	   findCheckData.put("pmNo", retrieveMT0201List.get(i).get("pmNo"));  //pmNo 세팅
    	   List<Map<String, Object>> findCheckInfoList = dao.queryForMapList("mt0201.findCheckInfoMT0201", findCheckData);

    	   for(int j=0; j < findCheckInfoList.size(); j++){
 		      String calCd      = "";
 		      String cycleCd    = "";
 		      String cycleTp    = "";
 		      String lastPmYmd  = "";
 		      String dayWeek    = "";
 		      String pmHour     = "";
 		      String pmNo       = "";

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
    		     if (findCheckInfoList.get(j).get("lastPmYmd") != null ){
    		    	 lastPmYmd = findCheckInfoList.get(j).get("lastPmYmd").toString(); // 최종점검일자
     		     }
    		     if (findCheckInfoList.get(j).get("dayWeek") != null ){
    			    dayWeek    = findCheckInfoList.get(j).get("dayWeek").toString(); // 요일
     		     }
    		     if (findCheckInfoList.get(j).get("pmHour") != null ){
    		    	 pmHour    = findCheckInfoList.get(j).get("pmHour").toString(); // 소요시간
     		     }
    		     if (findCheckInfoList.get(j).get("pmNo") != null ){
    		    	 pmNo    = findCheckInfoList.get(j).get("pmNo").toString(); // 소요시간
     		     }

    		     int ipmHour = (int) Float.parseFloat(pmHour);
    		     //시간을 24로 나눈다
    		     int resultPmHour = ipmHour/24;

    		     int iNeedMhr;

    	         if(resultPmHour < 1){
    	            iNeedMhr = 1;
    	         } else {
    	            iNeedMhr = resultPmHour;
    	         }

    	         if ("".equals(cycleCd)|| "".equals(cycleTp)) continue;


                 // 계산된 정비계획일자와  현재 생성 년도사이의 W/O발행 안된 값은 모두 삭제한다.(다시 생성된다.)
    	         dao.update("mt0201.deleteWoSubData", findCheckData);	// 계획서의 작업, 인건비, 자재 테이블 데이터 삭제
    	         dao.update("mt0201.deleteWO0201M", findCheckData);		// 작업계획서 삭제
                 dao.update("mt0201.createBeforeDeleteMT0201List", findCheckData);	// 계획일정 삭제

    		     int checkCount = 1;

    		     lastPmYmd = lastPmYmd.toString().replace("-", "");

                 // 해당 주기에 따른 다음 일자를 계산한다.
                 String pmDate = nextCheckDate(lastPmYmd, cycleCd, cycleTp, checkCount, dayWeek);

                 /*
                  * checkDate 가 휴무인지 이닌지 체크한다.
                  * 휴무일 이라면 이후 첫번째 평일로 날짜를 보정한다.
                  * 보정일자가 현재 년도가 아니면 일정 생성 멈춘다.
                  */
                 pmDate = getCheckDate(calCd, pmDate, lastPmYmd);

                 boolean isFirstPlan = true;

                 // 바로전 생성 점검일자
                 String beforeCheckDate = pmDate;

                 // 점검년도가 현재 점검To일자 보다 같거나 작을때까지
                 while (compareDate(pmDate, endDate)){
                   String pmSchdlNo = "";
                   Map<String, Object> insertDate = new HashMap<String, Object>();
                   insertDate.put("fStartDate", pmDate);
                   insertDate.put("companyCd", UserInfo.getCompanyCd());

             	   // 소요일수는 1을 빼준다. 소요일수가 1일이라면 시작일자와 완료일이 같다.
            	   String planEndDate = getAfterDays(pmDate, iNeedMhr-1);
            	   insertDate.put("fEndDate", planEndDate);

            	   insertDate.put("woNo", null);

            	   insertDate.put("pmNo", pmNo);
                   // 생성하려는 일자에 점검 일자가 있거나, 점검생성시작일 보다 작다면 생성하지 않는다.
            	   String checkPlanDate = dao.queryForObject("mt0201.checkPlanDate",insertDate, String.class);

                   if (checkPlanDate == null && compareDate(startDate, pmDate)){

                	   Map<String, Object> findSequenceNo = new HashMap<String, Object>();

                	   findSequenceNo.put("seqNm", "SQMT_PM_SCHEDULE_NO");
            		   findSequenceNo.put("instcCd", "");

            		   pmSchdlNo = Integer.toString(dao.queryForInt("util.retrieveSequence", findSequenceNo));

            		   insertDate.put("pmSchdlNo", pmSchdlNo);
                	   insertDate.put("fstRegUserId", UserInfo.getUserId());
                	   insertDate.put("fnlEditUserId", UserInfo.getUserId());

                	   //데이터 생성
                	   dao.update("mt0201.saveMT0201AllListplan", insertDate);

                   }

                   // 다음 일자를 계산하기 위해서.. 처리
                   checkCount++;

                   // 해당 주기에 따른 다음 점검 일자를 계산한다.
                   pmDate = nextCheckDate(lastPmYmd, cycleCd, cycleTp, checkCount, dayWeek);

                   /*
                    * checkDate 가 휴무인지 이닌지 체크한다.
                    * 휴무일 이라면 이후 첫번째 평일로 날짜를 보정한다.
                    * 보정일자가 현재 년도가 아니면 일정 생성 멈춘다.
                    */
                   pmDate = getCheckDate(calCd, pmDate, beforeCheckDate);

                   beforeCheckDate = pmDate;
                } //while문 종료
    	     }//if문종료
          }//for문 종료
       }//for문 종료

       dao.update("mt0201.updateCycleChangeYn", data);

       // 점검일정 2주 이내의 W/O 생성
       createMT0201MakeWoList(data);

       return retrieveMT0201List;
    }

    private String nextCheckDate(String lastChkYmd, String cycleCd, String cycleTp, int checkCount, String dayWeek)
    {
        if (lastChkYmd == null || cycleCd == null || cycleTp == null ||
            "".equals(lastChkYmd) || "".equals(cycleCd) || "".equals(cycleTp))
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
            int interDate = Integer.parseInt(cycleCd) * checkCount;
            checkDate = getAfterDays(lastChkYmd, interDate);
        }
        else if("W".equals(cycleTp))
        {
            int interDate = Integer.parseInt(cycleCd) * 7 * checkCount;
            checkDate = getAfterDays(lastChkYmd, interDate);
        }
        else if("M".equals(cycleTp))
        {
            int interMonth = Integer.parseInt(cycleCd) * checkCount;
            checkDate = getAfterMonth(lastChkYmd, interMonth);
        }
        else if("Y".equals(cycleTp))
        {
            int interMonth = Integer.parseInt(cycleCd) * 12 * checkCount;
            checkDate = getAfterMonth(lastChkYmd, interMonth);
        }

        // 요일이 있다면 lastCheckDate를 요일을 보정한 값으로 출발시킨다.
        if (!"".equals(dayWeek)) checkDate = checkLastCheckDate(checkDate, dayWeek);

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

    private String getCheckDate(String calCd, String _checkDate, String lastChkYmd)
    {
        /*
         * 보전칼렌더 유효성 체크
         * 보전칼렌더가 생성안되어 있거나 유효하지 않다면 기본 칼렌더 적용
         */
        // String checkDate = pmiYearCksDAO.checkCalType(calCd, _checkDate);

    	String checkDate = "";

        // 정비는 보전 칼렌더가 없다면 그일자로 생성한다.
        if ("".equals(checkDate))
        {
            checkDate =  _checkDate;
        }

        /*
         * 계산된 점검 일자가 last_check_date 와 같거나 작으면
         * 다음 평일일자를 check 일자로 잡는다.
         * (연휴가 길고 정비 주기가 짧다면 계속 해서 정비일자가 휴일이라면
         *  이전 정비가능일자로 정비일자를 잡기때문에 같은일자로 정비일자가 계산될수 있다.)
         */
        if (compareDate(checkDate, lastChkYmd))
        {
            // 가장 최근의 Work 일자를 리턴한다.
        	Map<String, Object> data = new HashMap<String, Object>();

        	data.put("calCd", calCd);
        	data.put("checkDate", checkDate);
        	data.put("companyCd", UserInfo.getCompanyCd());

            checkDate = dao.queryForObject("in0301.findWorkingDay",data, String.class);
        }

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
    	if(StringUtils.isNotBlank(fromDate)) {
    		int iFromdate = Integer.parseInt(fromDate);
            int iToDate = Integer.parseInt(toDate);

            if (iFromdate <= iToDate)
            {
                return true;
            } else {
            	return false;
            }
    	} else {
    		return false;
    	}


    }

	public List<Map<String, Object>> retrieveMT0201ListPlan(Map<String, Object> data) {
		return dao.queryForMapList("mt0201.retrieveMT0201ListPlan", data);
	}

	//수동으로 W/O 생성
    public List<Map<String, Object>> createMT0201WoNo(Map<String, Object> data) {
    	List<Map<String, Object>> pmSchdInfo = dao.queryForMapList("mt0201.findPmScheduleInfo", data);

    	makeWoList(pmSchdInfo);

    	return dao.queryForMapList("mt0201.retrieveMT0201ListPlan", data);
    }

    //현재일 기준 2주후 정비일정에 대해서 W/O 생성(미생성분만 생성)
    public void createMT0201MakeWoList(Map<String, Object> data) {
    	List<Map<String, Object>> pmSchdInfo = dao.queryForMapList("mt0201.retrieveMT0201MakeWoList", data);

    	makeWoList(pmSchdInfo);

    	//return dao.queryForMapList("mt0201.retrieveMT0201ListPlan", data);
    }

    //현재일 기준 2주후 정비일정에 대해서 W/O 생성(미생성분만 생성) 새벽3시에 스케쥴 통해서 생성
    @Scheduled(cron = "0 0 3 * * *") // seconds(0~59), minutes(0~59), hours(0~23), day of month(1~31), month(1~12), day of week(1~7 : 1-Sunday, 7-Saturday), years(optional)
    public void scheduledCreateMT0201MakeWoList() {
    	Map<String, Object> data = new HashMap<String, Object>();
    	List<Map<String, Object>> pmSchdInfo = dao.queryForMapList("mt0201.retrieveMT0201MakeWoList", data);

    	makeWoList(pmSchdInfo);

    	//return dao.queryForMapList("mt0201.retrieveMT0201ListPlan", data);
    }


    public void makeWoList(List<Map<String, Object>> pmSchdInfo){
    	for(int i=0; i<pmSchdInfo.size(); i++){
    		if (pmSchdInfo.get(i).get("pmSchdlNo") == null ) continue;
    		if (pmSchdInfo.get(i).get("woNo") != null ) continue;

    		String pmNo = "";
    		String planStartYmd = "";
    		String planEndYmd = "";
    		String woNo = "";
    		String pmSchdlNo = "";
    		String eqSts = "";

    		if (pmSchdInfo.get(i).get("pmNo") != null ){
    			pmNo = pmSchdInfo.get(i).get("pmNo").toString();
    		}
    		if (pmSchdInfo.get(i).get("planStartYmd") != null ){
    			planStartYmd = pmSchdInfo.get(i).get("planStartYmd").toString();
    		}
    		if (pmSchdInfo.get(i).get("planEndYmd") != null ){
    			planEndYmd = pmSchdInfo.get(i).get("planEndYmd").toString();
    		}
    		if (pmSchdInfo.get(i).get("woNo") != null ){
    			woNo = pmSchdInfo.get(i).get("woNo").toString();
    		}
    		if (pmSchdInfo.get(i).get("pmSchdlNo") != null ){
    			pmSchdlNo = pmSchdInfo.get(i).get("pmSchdlNo").toString();
    		}
    		if (pmSchdInfo.get(i).get("eqSts") != null ){
    			eqSts = pmSchdInfo.get(i).get("eqSts").toString();
    		}

    		ArrayList queryList = new ArrayList();

    		Map<String, Object> findSequenceNo = new HashMap<String, Object>();

    		findSequenceNo.put("seqNm", "SQWO_NO_01");
    		findSequenceNo.put("instcCd", "");

    		woNo = Integer.toString(dao.queryForInt("util.retrieveSequence",findSequenceNo));

    		Map<String, Object> inserDate = new HashMap<String, Object>();

    		inserDate.put("woNo", woNo);
    		inserDate.put("pmNo", pmNo);
    		inserDate.put("eqSts", eqSts);
    		inserDate.put("pmSchdlNo", pmSchdlNo);
    		inserDate.put("planStartYmd", planStartYmd);
    		inserDate.put("planEndYmd", planEndYmd);
    		inserDate.put("fstRegUserId", UserInfo.getUserId());
    		inserDate.put("fnlEditUserId", UserInfo.getUserId());
    		inserDate.put("companyCd", UserInfo.getCompanyCd());

    		// WO0201M 입력
    		dao.update("mt0201.insertWorkOrder", inserDate);

    		// 정비계획에 따른 소요자재 계획으로 입력
    		dao.update("mt0201.insertWorkParts", inserDate);

    		// 정비계획에 따른 소요작업 계획으로 입력
    		dao.update("mt0201.insertWorkJob", inserDate);

            // 정비계획에 따른 소요인력 계획으로 입력
    		dao.update("mt0201.insertWorkCraft", inserDate);

    		List<Map<String, Object>> lastPmYmd = dao.queryForMapList("mt0201.retrieveLastPmYmd", inserDate);
    		inserDate.put("lastPmYmd", lastPmYmd.get(0).get("lastPmYmd"));
    		// PM 예정일 T2PREVNET_SCHEDULE 에 셋팅
    		dao.update("mt0201.updatePrePmDate", inserDate);

    		// 정비일정에 생성된 woNo 셋팅
    		dao.update("mt0201.updatePmSchdWoNo", inserDate);
    	}

    }

    /* 계획일 하루전으로 변경 W/O 미생성 대상만 적용 */
	public void moveDayMT0201(Map<String, Object> data) {
		data.put("fstRegUserId", UserInfo.getUserId());
		data.put("fnlEditUserId", UserInfo.getUserId());

		dao.update("mt0201.moveDayMT0201", data);
	}

    /* 계획일 하루전으로 변경 W/O 미생성 대상만 적용 */
	public void moveDayMinusMT0201(Map<String, Object> data) {
		data.put("fstRegUserId", UserInfo.getUserId());
		data.put("fnlEditUserId", UserInfo.getUserId());

		dao.update("mt0201.moveDayMinusMT0201", data);
	}

	/* 계획일 하루후로 변경 W/O 미생성 대상만 적용 */
	public void moveDayPlusMT0201(Map<String, Object> data) {
		data.put("fstRegUserId", UserInfo.getUserId());
		data.put("fnlEditUserId", UserInfo.getUserId());

		dao.update("mt0201.moveDayPlusMT0201", data);
	}

}
