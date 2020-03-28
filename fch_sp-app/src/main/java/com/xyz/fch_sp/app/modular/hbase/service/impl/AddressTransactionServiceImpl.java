package com.xyz.fch_sp.app.modular.hbase.service.impl;

import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import com.xyz.fch_sp.app.core.common.constant.DatasourceEnum;
import com.xyz.fch_sp.app.modular.hbase.dao.AddressTransactionMapper;
import com.xyz.fch_sp.app.modular.hbase.model.AddressTransaction;
import com.xyz.fch_sp.app.modular.hbase.service.AddressTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressTransactionServiceImpl implements AddressTransactionService {

    @Autowired
    private AddressTransactionMapper addressTransactionMapper;

    @Override
    @DataSource(name= DatasourceEnum.DATA_SOURCE_BIZ)
    public AddressTransaction selectByAddress(String address) {
        return addressTransactionMapper.selectByAddress(address);
    }
}
