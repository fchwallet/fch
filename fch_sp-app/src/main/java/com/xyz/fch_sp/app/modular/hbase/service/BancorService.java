package com.xyz.fch_sp.app.modular.hbase.service;

import com.xyz.fch_sp.app.modular.hbase.model.Bancor;

import java.util.List;

public interface BancorService {

    Bancor selectByContract(String Contract);

    List<Bancor> selectBanCor(String name);

}
