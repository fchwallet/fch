package com.xyz.fch_sp.app.modular.system.service.impl;

import com.xyz.fch_sp.app.modular.system.model.RankingAsset;
import com.xyz.fch_sp.app.modular.system.dao.RankingAssetMapper;
import com.xyz.fch_sp.app.modular.system.service.IRankingAssetService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-10
 */
@Service
public class RankingAssetServiceImpl extends ServiceImpl<RankingAssetMapper, RankingAsset> implements IRankingAssetService {

    @Autowired
    private RankingAssetMapper rankingAssetMapper;

    @Override
    public int selectRankByAddress(String address) {
        return rankingAssetMapper.selectRankByAddress(address);
    }

    @Override
    public String selectCirculation() {
        return rankingAssetMapper.selectCirculation();
    }
}
