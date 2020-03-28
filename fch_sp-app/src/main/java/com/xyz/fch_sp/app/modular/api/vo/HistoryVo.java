package com.xyz.fch_sp.app.modular.api.vo;

import lombok.Data;

@Data
public class HistoryVo {

    private String txid;

    private Long time;

    private Long height;

    private String value;

    private Integer status;

}
