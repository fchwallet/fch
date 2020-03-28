package com.xyz.fch_sp.app.modular.system.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xyz.fch_sp.app.core.util.JwtToken;
import com.xyz.fch_sp.app.modular.system.dao.MemberMapper;
import com.xyz.fch_sp.app.modular.system.model.Member;
import com.xyz.fch_sp.app.modular.system.service.MemberService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Member selectMembers(String username) {
        return memberMapper.selectMembers(username);
    }

    @Override
    public Member selectEmail(String email) {
        return memberMapper.selectEmail(email);
    }

    @Override
    public int insertMembers(Member Member) {
        return memberMapper.insertMembersInfo(Member);
    }

    @Override
    public int updateMembers(Member member) {
        return memberMapper.updateMembers(member);
    }

    @Override
    public Member selectMemberInfo() {

        try {

            Member member = memberMapper.selectMembers(JwtToken.getUsername((String) SecurityUtils.getSubject().getPrincipal()));
            return member;

        } catch (Exception e) {

            throw new UnknownAccountException("400401");

        }
    }

    @Override
    public Member findMember(String username, String password) {
        return memberMapper.findMember(username, password);
    }
}
