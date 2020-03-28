package com.xyz.fch_sp.app.modular.hbase.dao;

//import com.baomidou.mybatisplus.annotations.DataSource;
//import com.baomidou.mybatisplus.annotations.DataSource;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xyz.fch_sp.app.modular.hbase.model.Block;
import com.xyz.fch_sp.app.modular.hbase.model.BlockSync;

import java.util.List;

public interface BlockSyncMapper{


    void deleteByHash(String hash);

    List<BlockSync> selectAll();
}
