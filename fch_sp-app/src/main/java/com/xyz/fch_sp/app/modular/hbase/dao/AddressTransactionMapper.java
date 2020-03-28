package com.xyz.fch_sp.app.modular.hbase.dao;

import com.xyz.fch_sp.app.modular.hbase.model.AddressTransaction;

public interface AddressTransactionMapper {

    AddressTransaction selectByAddress(String address);

}
