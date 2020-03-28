package com.xyz.fch_sp.app.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xyz.fch_sp.app.modular.system.dao.BlockMapper;
import com.xyz.fch_sp.app.modular.system.model.Block;
import com.xyz.fch_sp.app.modular.system.service.BlockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BlockServiceImpl extends ServiceImpl<BlockMapper, Block> implements BlockService {

    @Resource
    private BlockMapper blockMapper;

    @Override
    public Long selectHeight() {
        return blockMapper.selectHeight();
    }
}
