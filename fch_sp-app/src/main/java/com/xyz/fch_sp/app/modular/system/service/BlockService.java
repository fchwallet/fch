package com.xyz.fch_sp.app.modular.system.service;


import com.baomidou.mybatisplus.service.IService;
import com.xyz.fch_sp.app.modular.system.model.Block;

public interface BlockService extends IService<Block> {

    Long selectHeight();

}
