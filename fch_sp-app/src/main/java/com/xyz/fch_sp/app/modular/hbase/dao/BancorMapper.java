package com.xyz.fch_sp.app.modular.hbase.dao;

import com.xyz.fch_sp.app.modular.hbase.model.Bancor;

import java.util.List;

public interface BancorMapper {

    Bancor selectByContract(String Contract);

    List<Bancor> selectBanCor(String name);

}
