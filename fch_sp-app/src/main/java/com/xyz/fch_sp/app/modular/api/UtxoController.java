package com.xyz.fch_sp.app.modular.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xyz.fch_sp.app.core.util.JsonResult;
import com.xyz.fch_sp.app.modular.api.vo.UtxoVo;
import com.xyz.fch_sp.app.modular.system.model.Member;
import com.xyz.fch_sp.app.modular.system.model.Utxo;
import com.xyz.fch_sp.app.modular.system.service.BlockService;
import com.xyz.fch_sp.app.modular.system.service.CompanyService;
import com.xyz.fch_sp.app.modular.system.service.MemberService;
import com.xyz.fch_sp.app.modular.system.service.UtxoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/utxo")
@Slf4j
public class UtxoController {

    @Autowired
    private UtxoService utxoService;

    @Autowired
    private BlockService blockService;

    @ResponseBody
    @RequestMapping(value="/findUtxoByAddress", method = RequestMethod.POST)
    public JsonResult findUtxoByAddress(String address[], Integer coinbase, Integer spent) {

        List<Utxo> utxos = utxoService.findByAddress(address, coinbase, spent);

        Long height = blockService.selectHeight();

        return new JsonResult().addData("utxos",utxos).addData("height", height);

    }

    @ResponseBody
    @RequestMapping(value="/getTotal", method = RequestMethod.POST)
    public JsonResult findUtxoByAddress(String address[]) {

        List<UtxoVo> utxos = utxoService.getTotal(address, 0);

        return new JsonResult().addData("utxos",utxos);

    }

}
