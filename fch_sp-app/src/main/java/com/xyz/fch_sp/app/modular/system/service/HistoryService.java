package com.xyz.fch_sp.app.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.xyz.fch_sp.app.modular.api.vo.HistoryVo;
import com.xyz.fch_sp.app.modular.system.model.History;

import java.util.List;
import java.util.Map;

public interface HistoryService extends IService<History> {

    List<HistoryVo> findByAddress(Map<String, Object> query);

    long findCount(String address);

}
