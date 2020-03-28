package com.xyz.fch_sp.app.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

@TableName("company")
public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer memberId;

    private String name;

    private String code;

    private String address;

    private String contacts;

    private String phone;

    private Integer status;

    private String businessLicenseImg;

    private String positiveIDImg;

    private String backIDImg;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBusinessLicenseImg() {
        return businessLicenseImg;
    }

    public void setBusinessLicenseImg(String businessLicenseImg) {
        this.businessLicenseImg = businessLicenseImg;
    }

    public String getPositiveIDImg() {
        return positiveIDImg;
    }

    public void setPositiveIDImg(String positiveIDImg) {
        this.positiveIDImg = positiveIDImg;
    }

    public String getBackIDImg() {
        return backIDImg;
    }

    public void setBackIDImg(String backIDImg) {
        this.backIDImg = backIDImg;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
