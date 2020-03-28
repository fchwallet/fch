package com.xyz.fch_sp.app.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xyz.fch_sp.app.modular.system.model.Holders;

import java.util.List;
import java.util.Map;

public interface HoldersMapper extends BaseMapper<Holders> {

    List<Holders> pageList(Map<String,Object> params);

    long pageCount(Map<String, Object> params);

}
