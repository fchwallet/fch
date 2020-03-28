package com.xyz.fch_sp.app.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.xyz.fch_sp.app.modular.api.vo.UtxoVo;
import com.xyz.fch_sp.app.modular.system.model.Utxo;

import java.util.List;

public interface UtxoService extends IService<Utxo> {

    List<Utxo> findByAddress(String[] address, Integer coinbase, Integer spent);

    List<UtxoVo> getTotal(String[] address, Integer spent);

}
