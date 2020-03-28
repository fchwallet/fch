package com.xyz.fch_sp.app.modular.api;


import com.google.common.collect.Maps;
import com.xyz.fch_sp.app.core.util.JsonResult;
import com.xyz.fch_sp.app.modular.system.model.Data;
import com.xyz.fch_sp.app.modular.system.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/cid")
@Slf4j
public class CidController {


    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/getcid", method = RequestMethod.POST)
    public JsonResult getcid(String cid, Integer page) {

        page = page - 1;
        Integer limit = 10;
        Integer offset = page * limit;

        Map<String,Object> params = Maps.newHashMap();
        params.put("offset",offset);
        params.put("limit",limit);
        if (cid != null && !"".equals(cid))
            params.put("cid", cid);


        long total = dataService.getCidCount(params);
        List<Data> dataList = dataService.getCid(params);

        long size;
        if (total % limit == 0){
            size = total/limit;
        } else {
            size = total/limit + 1;
        }

        return new JsonResult().addData("contracts",dataList).addData("total",String.valueOf(total)).addData("size",String.valueOf(size));
    }


    @RequestMapping(value = "/getByAddress", method = RequestMethod.POST)
    public JsonResult getByAddress(String[] address) {

        List<Data> dataList = dataService.findByAddress(address);

        return new JsonResult().addData("list",dataList);

    }

}
