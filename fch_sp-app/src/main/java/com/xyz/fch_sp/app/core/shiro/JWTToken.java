package com.xyz.fch_sp.app.core.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {

	private String token;

	public JWTToken(String token) {
        this.token = token;
    }

	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}

}
