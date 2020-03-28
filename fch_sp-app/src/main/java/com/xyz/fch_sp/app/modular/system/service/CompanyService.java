package com.xyz.fch_sp.app.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.xyz.fch_sp.app.modular.system.model.Company;

public interface CompanyService extends IService<Company> {

    String findAddressById(Integer memberId);

    Company findByMemberId(Integer MemberId);

    Company findByAddress(String address);

}
