package com.xyz.fch_sp.app.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xyz.fch_sp.app.modular.api.vo.HistoryVo;
import com.xyz.fch_sp.app.modular.system.model.History;

import java.util.List;
import java.util.Map;


public interface HistoryMapper extends BaseMapper<History> {

    List<HistoryVo> findByAddress(Map<String, Object> query);

    long findCount(String address);

}
