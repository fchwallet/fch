package com.xyz.fch_sp.app.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xyz.fch_sp.app.modular.system.model.Tx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TxMapper extends BaseMapper<Tx> {

    List<Tx> finAll();

    Tx findTxid(String txid);

    int updateTx(Tx tx);

    List<Tx> findStatus(Integer status);

}
