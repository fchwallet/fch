package com.xyz.fch_sp.app.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.xyz.fch_sp.app.modular.system.model.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataService extends IService<Data> {

    int insertData(Data data);

    List<Data> getCid(Map<String, Object> params);

    Long getCidCount(Map<String, Object> params);

    Long findByCidCount(String address);

    int updataData(Data data);

    int deleteAddress(String address);

    List<Data> findtest();

    List<Data> findByAddress(String[] address);

    Data findAddress(String address);

}
