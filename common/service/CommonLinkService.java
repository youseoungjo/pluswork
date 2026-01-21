package com.bplus.pw.common.service;

import idr.extend.iam.UserInfo;
import idr.extend.query.CommonDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by bequs on 2017-03-09.
 */
@Service
public class CommonLinkService {

    /**
     * DB처리를 위한 공통 dao
     */
    @Resource(name = "idrDao")
    private CommonDao dao;

    public List<Map<String, Object>> retrieveChkDiv(final Map<String, Object> data) {
		return dao.queryForMapList("commonLink.retrieveChkDiv", data);
    }
    public List<Map<String, Object>> retrieveScComm(final Map<String, Object> data) {
		return dao.queryForMapList("commonLink.retrieveScComm", data);
    }
    public List<Map<String, Object>> retrieveWoStd(final Map<String, Object> data) {
		return dao.queryForMapList("commonLink.retrieveWoStd", data);
    }

    public List<Map<String, Object>> retrieveStdJob(final Map<String, Object> data) {
		return dao.queryForMapList("commonLink.retrieveStdJob", data);
    }

    public List<Map<String, Object>> retrieveStdCraft(final Map<String, Object> data) {
		return dao.queryForMapList("commonLink.retrieveStdCraft", data);
    }

    public List<Map<String, Object>> retrieveStdPart(final Map<String, Object> data) {
		return dao.queryForMapList("commonLink.retrieveStdPart", data);
    }

    public List<Map<String, Object>> retrieveUserList(final Map<String, Object> data) {
		return dao.queryForMapList("commonLink.retrieveUserList", data);
    }

    public List<Map<String, Object>> retrievePrntUnitCdList(final Map<String, Object> data) {
		return dao.queryForMapList("commonLink.retrievePrntUnitCdList", data);
    }

    public List<Map<String, Object>> retrieveUnitCdList(final Map<String, Object> data) {
		return dao.queryForMapList("commonLink.retrieveUnitCdList", data);
    }

    public List<Map<String, Object>> retrieveIN0301CmplYmdList(final Map<String, Object> data) {
		return dao.queryForMapList("commonLink.retrieveIN0301CmplYmdList", data);
    }

}