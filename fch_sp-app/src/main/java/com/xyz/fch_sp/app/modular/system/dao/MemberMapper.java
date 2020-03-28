package com.xyz.fch_sp.app.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xyz.fch_sp.app.modular.system.model.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper extends BaseMapper<Member>  {

    Member selectMembers(String username);

    Member selectEmail(String email);

    int insertMembersInfo(Member Member);

    int updateMembers(Member member);

    Member findMember(@Param("username") String username, @Param("password") String password);

}
