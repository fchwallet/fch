package com.xyz.fch_sp.app.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xyz.fch_sp.app.modular.api.vo.UtxoVo;
import com.xyz.fch_sp.app.modular.system.dao.UtxoMapper;
import com.xyz.fch_sp.app.modular.system.model.Utxo;
import com.xyz.fch_sp.app.modular.system.service.UtxoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UtxoServiceImpl extends ServiceImpl<UtxoMapper, Utxo> implements UtxoService {

    @Resource
    private UtxoMapper utxoMapper;

    @Override
    public List<Utxo> findByAddress(String[] address, Integer coinbase, Integer spent) {
        return utxoMapper.findByAddress(address, coinbase, spent);
    }

    @Override
    public List<UtxoVo> getTotal(String[] address, Integer spent) {
        return utxoMapper.getTotal(address, spent);
    }

}
