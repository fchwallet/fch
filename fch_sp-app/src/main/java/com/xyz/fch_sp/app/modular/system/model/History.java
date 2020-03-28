package com.xyz.fch_sp.app.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("history")
public class History extends Model<History> {

    private String txid;

    private Long height;

    private Integer idx;

    private Integer txSize;

    private Long time;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Integer getTxSize() {
        return txSize;
    }

    public void setTxSize(Integer txSize) {
        this.txSize = txSize;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
