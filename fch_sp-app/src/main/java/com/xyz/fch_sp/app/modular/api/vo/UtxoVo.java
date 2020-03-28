package com.xyz.fch_sp.app.modular.api.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UtxoVo {

    private String address;

    private BigDecimal amount;

}
