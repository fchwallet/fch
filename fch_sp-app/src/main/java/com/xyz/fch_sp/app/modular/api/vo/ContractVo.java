package com.xyz.fch_sp.app.modular.api.vo;

public class ContractVo {

    private Integer id;

    private String title;

    private String company;

    private Integer status;

    private String sendDate;

    private String endDate;

    private String overdueDate;

    private String completeDate;

    private String mark;

    private String txid;

    private String companyAddress;

    public ContractVo() {

    }

    public ContractVo(Integer id, String title, String company, Integer status, String sendDate, String endDate, String overdueDate, String completeDate, String mark, String txid, String companyAddress) {

        this.id = id;
        this.title = title;
        this.company = company;
        this.status = status;
        this.sendDate = sendDate;
        this.endDate = endDate;
        this.overdueDate = overdueDate;
        this.completeDate = completeDate;
        this.mark = mark;
        this.txid = txid;
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOverdueDate() {
        return overdueDate;
    }

    public void setOverdueDate(String overdueDate) {
        this.overdueDate = overdueDate;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
