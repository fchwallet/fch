package com.xyz.fch_sp.app.modular.system.service;


import com.baomidou.mybatisplus.service.IService;
import com.xyz.fch_sp.app.modular.system.model.Tx;

import java.util.List;

public interface TxService extends IService<Tx> {

    List<Tx> finAll();

    Tx findTxid(String txid);

    int updateTx(Tx tx);

    List<Tx> findStatus(Integer status);


}
