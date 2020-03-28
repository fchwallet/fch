/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
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
package com.xyz.fch_sp.app.config.web;

import com.xyz.fch_sp.app.config.filter.JWTFilter;
import com.xyz.fch_sp.app.core.shiro.ShiroRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.*;

/**
 * shiro权限管理的配置
 *
 * @author fengshuonan
 * @date 2016年11月14日 下午3:03:44
 */
@Configuration
public class ShiroConfig {

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        // 关闭自带session
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(evaluator);

        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());

        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();

        filterChainDefinitionManager.put("/message/**", "anon");

        filterChainDefinitionManager.put("/api/member/login", "anon");

        filterChainDefinitionManager.put("/api/member/register", "anon");

        filterChainDefinitionManager.put("/api/member/sendSms", "anon");

        filterChainDefinitionManager.put("/api/utxo/findUtxoByAddress", "anon");

        filterChainDefinitionManager.put("/api/contract/getHex", "anon");

        filterChainDefinitionManager.put("/app/contract/list", "anon");

        filterChainDefinitionManager.put("/app/contract/getTxInfo", "anon");

        filterChainDefinitionManager.put("/api/cid/getcid", "anon");

        filterChainDefinitionManager.put("/api/utxo/getTotal", "anon");

        filterChainDefinitionManager.put("/api/VerifyMessage", "anon");

        // 其他请求全部验证
        filterChainDefinitionManager.put("/**", "jwt");

        factoryBean.setLoginUrl("/api/member/noLogin");//没有登录的用户请求需要登录的资源时自动跳转到该路径

        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

        return factoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
