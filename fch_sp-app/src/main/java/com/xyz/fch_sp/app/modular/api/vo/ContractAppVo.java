package com.xyz.fch_sp.app.modular.api.vo;

public class ContractAppVo {

    private Integer id;

    private String title;

    private String txid;

    private Integer status;

    private Integer crypt;

    public ContractAppVo(){

    }

    public ContractAppVo(Integer id, String title, String txid, Integer status, Integer crypt) {

        this.id = id;
        this.title = title;
        this.txid = txid;
        this.status = status;
        this.crypt = crypt;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCrypt() {
        return crypt;
    }

    public void setCrypt(Integer crypt) {
        this.crypt = crypt;
    }
}
