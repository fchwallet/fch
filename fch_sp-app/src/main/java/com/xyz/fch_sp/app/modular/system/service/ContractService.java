package com.xyz.fch_sp.app.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.xyz.fch_sp.app.modular.api.vo.ContractInfoVo;
import com.xyz.fch_sp.app.modular.api.vo.ContractSearchVo;
import com.xyz.fch_sp.app.modular.system.model.Contract;

import java.util.List;
import java.util.Map;


public interface ContractService extends IService<Contract> {

    List<Contract> pageList(Map<String, Object> params);

    long pageCount(Map<String, Object> params);

    int updateContract(Contract contract);

    int insertContractInfo(Contract contract);

    List<Integer> findTxLink(String txid);

    Contract findTxid(String txid);

    List<Contract> findContractList();

}
