package com.xyz.fch_sp.app.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xyz.fch_sp.app.modular.system.dao.CompanyMapper;
import com.xyz.fch_sp.app.modular.system.model.Company;
import com.xyz.fch_sp.app.modular.system.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public String findAddressById(Integer memberId) {
        return companyMapper.findAddressById(memberId);
    }

    @Override
    public Company findByMemberId(Integer memberId) {
        return companyMapper.findByMemberId(memberId);
    }

    @Override
    public Company findByAddress(String address) {
        return companyMapper.findByAddress(address);
    }

}
