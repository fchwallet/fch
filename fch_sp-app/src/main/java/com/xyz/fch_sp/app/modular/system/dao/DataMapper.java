package com.xyz.fch_sp.app.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xyz.fch_sp.app.modular.system.model.Data;
import com.xyz.fch_sp.app.modular.system.model.Tx;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataMapper extends BaseMapper<Data> {

    int insertData(Data data);

    List<Data> getCid(Map<String, Object> params);

    Long getCidCount(Map<String, Object> params);

    Long findByCidCount(String address);

    int updataData(Data data);

    int deleteAddress(String address);

    List<Data> findtest();

    List<Data> findByAddress(@Param("address") String[] address);

    Data findAddress(String address);

}
