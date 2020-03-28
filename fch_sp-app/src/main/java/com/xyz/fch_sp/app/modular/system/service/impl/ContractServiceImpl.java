package com.xyz.fch_sp.app.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xyz.fch_sp.app.modular.system.dao.ContractMapper;
import com.xyz.fch_sp.app.modular.system.model.Contract;
import com.xyz.fch_sp.app.modular.system.service.ContractService;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements ContractService {

    @Resource
    private ContractMapper contractMapper;

    @Override
    public List<Contract> pageList(Map<String, Object> params) {
        return contractMapper.pageList(params);
    }

    @Override
    public long pageCount(Map<String, Object> params) {
        return contractMapper.pageCount(params);
    }

    @Override
    public int updateContract(Contract contract) {
        return contractMapper.updateContract(contract);
    }

    @Override
    public int insertContractInfo(Contract contract) {
        return contractMapper.insertContractInfo(contract);
    }

    @Override
    public List<Integer> findTxLink(String txid) {
        return contractMapper.findTxLink(txid);
    }

    @Override
    public Contract findTxid(String txid) {
        return contractMapper.findTxid(txid);
    }

    @Override
    public List<Contract> findContractList() {
        return contractMapper.findContractList();
    }


}
