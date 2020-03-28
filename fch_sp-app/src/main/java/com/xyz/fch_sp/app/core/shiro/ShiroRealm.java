/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xyz.fch_sp.app.core.shiro;

import com.xyz.fch_sp.app.core.shiro.service.UserAuthService;
import com.xyz.fch_sp.app.core.shiro.service.impl.UserAuthServiceServiceImpl;
import com.xyz.fch_sp.app.core.util.JwtToken;
import com.xyz.fch_sp.app.modular.system.model.Member;
import com.xyz.fch_sp.app.modular.system.model.User;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.xyz.fch_sp.app.modular.system.service.MemberService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component(value = "shiroRealm")
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private MemberService memberService;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {

        String token = (String)authcToken.getCredentials();

        String username = JwtToken.getUsername(token);

        Member member = memberService.selectMembers(username);

        // 用户不会空
        if (member != null) {

            // 密码验证
            if (!JwtToken.verify(token, username, member.getPassword())) {
                throw new UnknownAccountException("400401");
            }

            return new SimpleAuthenticationInfo(token, token, "realm");

        } else {
            throw new UnknownAccountException("400401");
        }

    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserAuthService shiroFactory = UserAuthServiceServiceImpl.me();
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<Integer> roleList = shiroUser.getRoleList();

        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();

        for (Integer roleId : roleList) {
            List<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
            if (permissions != null) {
                for (String permission : permissions) {
                    if (ToolUtil.isNotEmpty(permission)) {
                        permissionSet.add(permission);
                    }
                }
            }
            String roleName = shiroFactory.findRoleNameByRoleId(roleId);
            roleNameSet.add(roleName);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionSet);
        info.addRoles(roleNameSet);
        return info;
    }

  /*  *//**
     * 设置认证加密方式
     *//*
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }*/
}
