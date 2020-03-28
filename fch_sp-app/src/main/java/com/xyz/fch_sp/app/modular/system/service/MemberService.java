package com.xyz.fch_sp.app.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.xyz.fch_sp.app.modular.system.model.Member;

public interface MemberService extends IService<Member> {

    Member selectMembers(String username);

    Member selectEmail(String email);

    int insertMembers(Member Member);

    int updateMembers(Member member);

    Member selectMemberInfo();

    Member findMember(String username, String password);

}
