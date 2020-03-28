package com.xyz.fch_sp.app.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xyz.fch_sp.app.modular.system.model.Company;

import java.util.List;


public interface CompanyMapper extends BaseMapper<Company> {

   String findAddressById(Integer memberId);

   Company findByMemberId(Integer MemberId);

   Company findByAddress(String address);

   List<String> findByName(String name);


}
