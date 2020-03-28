package com.xyz.fch_sp.app.modular.system.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xyz.fch_sp.app.modular.system.model.Block;

public interface BlockMapper extends BaseMapper<Block> {

    Long selectHeight();

}
