package com.xyz.fch_sp.app.modular.api;

import com.google.common.collect.Maps;
import com.xyz.fch_sp.app.core.util.JsonResult;
import com.xyz.fch_sp.app.modular.api.vo.HistoryVo;
import com.xyz.fch_sp.app.modular.system.model.Data;
import com.xyz.fch_sp.app.modular.system.model.History;
import com.xyz.fch_sp.app.modular.system.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/history")
@Slf4j
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult contractList(Integer page, String address) {

        page = page - 1;
        Integer limit = 10;
        Integer offset = page * limit;

        Map<String,Object> params = Maps.newHashMap();
        params.put("offset",offset);
        params.put("limit",limit);
        params.put("address", address);

        long total = historyService.findCount(address);
        List<HistoryVo> dataList = historyService.findByAddress(params);

        long size;
        if (total % limit == 0){
            size = total/limit;
        } else {
            size = total/limit + 1;
        }

        return new JsonResult().addData("list",dataList).addData("total",String.valueOf(total)).addData("size",String.valueOf(size));

    }


}
