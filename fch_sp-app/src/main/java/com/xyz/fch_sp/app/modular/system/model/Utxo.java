package com.xyz.fch_sp.app.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("utxo")
public class Utxo extends Model<Utxo> {

    private String address;

    private String txid;

    private Integer vout;

    private String amount;

    private Integer coinbase;

    private Integer spent;

    private Long height;

    public Integer getCoinbase() {
        return coinbase;
    }

    public void setCoinbase(Integer coinbase) {
        this.coinbase = coinbase;
    }

    public Integer getSpent() {
        return spent;
    }

    public void setSpent(Integer spent) {
        this.spent = spent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Integer getVout() {
        return vout;
    }

    public void setVout(Integer vout) {
        this.vout = vout;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
