package com.spring.oauth2;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


@Service
public class AAAUserAuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = 2530887739703542181L;
	
	private final Object principal;
    private Object credentials;
 
    public AAAUserAuthenticationToken(Object principal, Object credentials, 
    		Collection<? extends GrantedAuthority> authorities) {
    	
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }
 
    public Object getCredentials() {
        return this.credentials;
    }
 
    public Object getPrincipal() {
        return this.principal;
    }
}
