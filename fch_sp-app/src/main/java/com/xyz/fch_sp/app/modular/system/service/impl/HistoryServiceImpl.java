package com.xyz.fch_sp.app.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xyz.fch_sp.app.modular.api.vo.HistoryVo;
import com.xyz.fch_sp.app.modular.system.dao.HistoryMapper;
import com.xyz.fch_sp.app.modular.system.model.History;
import com.xyz.fch_sp.app.modular.system.service.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {

    @Resource
    private HistoryMapper historyMapper;

    @Override
    public List<HistoryVo> findByAddress(Map<String, Object> query) {
        return historyMapper.findByAddress(query);
    }

    @Override
    public long findCount(String address) {
        return historyMapper.findCount(address);
    }

}
