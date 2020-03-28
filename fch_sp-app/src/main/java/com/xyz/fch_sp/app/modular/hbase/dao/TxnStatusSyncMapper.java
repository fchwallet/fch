package com.xyz.fch_sp.app.modular.hbase.dao;

//import com.baomidou.mybatisplus.annotations.DataSource;
//import com.baomidou.mybatisplus.annotations.DataSource;

import com.xyz.fch_sp.app.modular.hbase.model.BlockSync;
import com.xyz.fch_sp.app.modular.hbase.model.TxnStatusSync;

import java.util.List;

public interface TxnStatusSyncMapper {


    void deleteByHash(String hash);

    List<TxnStatusSync> selectAll();
}
