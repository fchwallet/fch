package com.xyz.fch_sp.app.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xyz.fch_sp.app.modular.system.dao.TxMapper;
import com.xyz.fch_sp.app.modular.system.model.Tx;
import com.xyz.fch_sp.app.modular.system.service.TxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TxServiceImpl extends ServiceImpl<TxMapper, Tx> implements TxService {

    @Resource
    private TxMapper txMapper;

    @Override
    public List<Tx> finAll() {
        return txMapper.finAll();
    }

    @Override
    public Tx findTxid(String txid) {
        return txMapper.findTxid(txid);
    }

    @Override
    public int updateTx(Tx tx) {
        return txMapper.updateTx(tx);
    }

    @Override
    public List<Tx> findStatus(Integer status) {
        return txMapper.findStatus(status);
    }

}
