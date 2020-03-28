package com.xyz.fch_sp.app.modular.hbase.service.impl;

import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import com.xyz.fch_sp.app.core.common.constant.DatasourceEnum;
import com.xyz.fch_sp.app.modular.hbase.dao.BancorMapper;
import com.xyz.fch_sp.app.modular.hbase.model.Bancor;
import com.xyz.fch_sp.app.modular.hbase.service.BancorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancorServiceImpl implements BancorService {


    @Autowired
    private BancorMapper bancorMapper;

    @Override
    @DataSource(name= DatasourceEnum.DATA_SOURCE_BIZ)
    public Bancor selectByContract(String contract) {
        return bancorMapper.selectByContract(contract);
    }

    @Override
    @DataSource(name= DatasourceEnum.DATA_SOURCE_BIZ)
    public List<Bancor> selectBanCor(String name) {
        return bancorMapper.selectBanCor(name);
    }
}
