package com.xyz.fch_sp.app.modular.api.app;

import com.google.common.collect.Maps;
import com.xyz.fch_sp.app.core.util.JsonResult;
import com.xyz.fch_sp.app.modular.api.vo.ContractAppVo;
import com.xyz.fch_sp.app.modular.system.model.Contract;
import com.xyz.fch_sp.app.modular.system.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/contract")
@Slf4j
public class APPContractController {

    @Autowired
    private ContractService contractService;


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult contractList(Integer page, Integer type, Integer status, String address) {

        page = page - 1;
        Integer limit = 20;
        Integer offset = page * limit;

        Map<String,Object> params = Maps.newHashMap();

        params.put("offset",offset);
        params.put("limit",limit);
        params.put("status", status);

        if (type == 1) {
            params.put("sender",address);
        } else if (type == 2) {
            params.put("recipient",address);
        } else {
            params.put("sender",address);
            params.put("recipient",address);
        }

        long total = contractService.pageCount(params);
        List<Contract> contractList = contractService.pageList(params);

        List<ContractAppVo> list = new ArrayList<>();

        for (Contract countract: contractList) {

            ContractAppVo contractVo = getContractAppVo(countract);

            list.add(contractVo);

        }

        long size;
        if (total % limit == 0){
            size = total/limit;
        } else {
            size = total/limit + 1;
        }

        return new JsonResult().addData("contracts",list).addData("total",String.valueOf(total)).addData("size",String.valueOf(size));
    }

    public ContractAppVo getContractAppVo(Contract contract) {

        ContractAppVo contractVo = new ContractAppVo(contract.getId(), contract.getTitle(), contract.getTxid(), contract.getStatus(), contract.getCrypt());

        return contractVo;
    }

    @ResponseBody
    @RequestMapping(value="/getTxInfo", method = RequestMethod.POST)
    public JsonResult sendHex(String txid) {

        Contract contract = contractService.findTxid(txid);
        ContractAppVo contractVo = new ContractAppVo(contract.getId(), contract.getTitle(), contract.getTxid(), contract.getStatus(), contract.getCrypt());

        return new JsonResult().addData("contract", contractVo);

    }

}
