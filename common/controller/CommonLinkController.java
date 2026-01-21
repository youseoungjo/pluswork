package com.bplus.pw.common.controller;

import idr.common.utils.RequestUtil;
import idr.extend.core.mvc.BqsRequest;
import idr.extend.core.mvc.BqsResponse;
import idr.extend.core.mvc.context.WebContext;
import com.bplus.pw.common.service.CommonLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/pw/common/*")
public class CommonLinkController {

    private final CommonLinkService commonLinkService;

    @Autowired
    public CommonLinkController(final CommonLinkService commonLinkService) {
        this.commonLinkService = commonLinkService;
    }

    @RequestMapping("retrieveChkDiv.*")
    public void retrieveChkDiv(final BqsRequest request, final BqsResponse response) {
        final Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
        final List<Map<String, Object>> ds_PopupResult = commonLinkService.retrieveChkDiv(map);

        response.setList("ds_PopupResult", ds_PopupResult);
    }

    @RequestMapping("retrieveWoStd.*")
    public void retrieveWoStd(final BqsRequest request, final BqsResponse response) {
        final Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
        final List<Map<String, Object>> ds_eqStdList = commonLinkService.retrieveWoStd(map);

        response.setList("ds_eqStdList", ds_eqStdList);
    }

    @RequestMapping("retrieveStdJob.*")
    public void retrieveStdJob(final BqsRequest request, final BqsResponse response) {
        final Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
        final List<Map<String, Object>> ds_eqStdJob = commonLinkService.retrieveStdJob(map);

        response.setList("ds_eqStdJob", ds_eqStdJob);
    }

    @RequestMapping("retrieveStdCraft.*")
    public void retrieveStdCraft(final BqsRequest request, final BqsResponse response) {
        final Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
        final List<Map<String, Object>> ds_eqStdCraft = commonLinkService.retrieveStdCraft(map);

        response.setList("ds_eqStdCraft", ds_eqStdCraft);
    }

    @RequestMapping("retrieveStdPart.*")
    public void retrieveStdPart(final BqsRequest request, final BqsResponse response) {
        final Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
        final List<Map<String, Object>> ds_eqStdPart = commonLinkService.retrieveStdPart(map);

        response.setList("ds_eqStdPart", ds_eqStdPart);
    }
    @RequestMapping("retrieveUserList.*")
    public void retrieveUserList(final BqsRequest request, final BqsResponse response) {
        final Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
        final List<Map<String, Object>> ds_PopupResult = commonLinkService.retrieveUserList(map);

        response.setList("ds_PopupResult", ds_PopupResult);
    }

    @RequestMapping("retrieveScComm.*")
    public void retrieveScComm(final BqsRequest request, final BqsResponse response) {
        final Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
        final List<Map<String, Object>> ds_PopupResult = commonLinkService.retrieveScComm(map);

        response.setList("ds_PopupResult", ds_PopupResult);
    }

    @RequestMapping("retrievePrntUnitCdList.*")
    public void retrievePrntUnitCdList(final BqsRequest request, final BqsResponse response) {
        final Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
        final List<Map<String, Object>> ds_PrntUnitCdList = commonLinkService.retrievePrntUnitCdList(map);

        response.setList("ds_PrntUnitCdList", ds_PrntUnitCdList);
    }

    @RequestMapping("retrieveUnitCdList.*")
    public void retrieveUnitCdList(final BqsRequest request, final BqsResponse response) {
        final Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
        final List<Map<String, Object>> ds_UnitCdList = commonLinkService.retrieveUnitCdList(map);

        response.setList("ds_UnitCdList", ds_UnitCdList);
    }

    @RequestMapping("retrieveIN0301CmplYmdList.*")
    public void retrieveIN0301CmplYmdList(final BqsRequest request, final BqsResponse response) {
        final Map<String, Object> map = RequestUtil.getParam(request, WebContext.getRequest());
        final List<Map<String, Object>> ds_PopupResult = commonLinkService.retrieveIN0301CmplYmdList(map);

        response.setList("ds_PopupResult", ds_PopupResult);
    }

}
