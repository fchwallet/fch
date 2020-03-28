package com.xyz.fch_sp.app.modular.hbase.service;

import com.xyz.fch_sp.app.modular.hbase.model.AddressTransaction;

public interface AddressTransactionService {

    AddressTransaction selectByAddress(String address);

}
