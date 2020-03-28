package com.xyz.fch_sp.app.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xyz.fch_sp.app.modular.system.dao.DataMapper;
import com.xyz.fch_sp.app.modular.system.model.Data;
import com.xyz.fch_sp.app.modular.system.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, Data> implements DataService {

    @Resource
    private DataMapper dataMapper;


    @Override
    public int insertData(Data data) {
        return dataMapper.insertData(data);
    }

    @Override
    public List<Data> getCid(Map<String, Object> params) {
        return dataMapper.getCid(params);
    }

    @Override
    public Long getCidCount(Map<String, Object> params) {
        return dataMapper.getCidCount(params);
    }

    @Override
    public Long findByCidCount(String address) {
        return dataMapper.findByCidCount(address);
    }

    @Override
    public int updataData(Data data) {
        return dataMapper.updataData(data);
    }

    @Override
    public int deleteAddress(String address) {
        return dataMapper.deleteAddress(address);
    }

    @Override
    public List<Data> findtest() {
        return dataMapper.findtest();
    }

    @Override
    public List<Data> findByAddress(String[] address) {
        return dataMapper.findByAddress(address);
    }

    @Override
    public Data findAddress(String address) {
        return dataMapper.findAddress(address);
    }

}
