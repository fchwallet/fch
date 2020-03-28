package com.xyz.fch_sp.app.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xyz.fch_sp.app.modular.api.vo.UtxoVo;
import com.xyz.fch_sp.app.modular.system.model.Utxo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UtxoMapper extends BaseMapper<Utxo> {

    List<Utxo> findByAddress(@Param("address") String[] address, @Param("coinbase") Integer coinbase, @Param("spent") Integer spent);

    List<UtxoVo> getTotal(@Param("address") String[] address, @Param("spent") Integer spent);

}
