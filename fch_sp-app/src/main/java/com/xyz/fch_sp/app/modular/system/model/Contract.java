package com.xyz.fch_sp.app.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

@TableName("contract")
public class Contract extends Model<Contract> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String txid;                    // 该合同的txid

    private String title;                   // 标题

    private Integer status;                 // 状态

    private String sendDate;                // 发送日期

    private String endDate;                 // 合同到期日期

    private String overdueDate;             // 签约截止时间

    private String completeDate;            // 签约完成时间

    private String sender;                  // 发送的公司地址

    private String recipient;               // 接收的公司地址

    private String mark;                    // 备注

    private Integer crypt;                  // 是否加密

    private String senderCompany;

    private String recipientCompany;

    public String getSenderCompany() {
        return senderCompany;
    }

    public void setSenderCompany(String senderCompany) {
        this.senderCompany = senderCompany;
    }

    public String getRecipientCompany() {
        return recipientCompany;
    }

    public void setRecipientCompany(String recipientCompany) {
        this.recipientCompany = recipientCompany;
    }

    public Integer getCrypt() {
        return crypt;
    }

    public void setCrypt(Integer crypt) {
        this.crypt = crypt;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
