package com.lottery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static String REALM = "my_oauth_realm";

    private TokenStore tokenStore;
    private UserApprovalHandler userApprovalHandler;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationServerConfig(TokenStore tokenStore,
                                     UserApprovalHandler userApprovalHandler,
                                     @Qualifier("authenticationManagerBean")
                                             AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore;
        this.userApprovalHandler = userApprovalHandler;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
               .withClient("my-trusted-client")
               .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
               .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
               .scopes("read", "write", "trust")
               .secret("secret")
               .accessTokenValiditySeconds(120) // valid for 2 minutes
               .refreshTokenValiditySeconds(600); // valid for 10 minutes

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(this.tokenStore)
                 .userApprovalHandler(this.userApprovalHandler)
                 .authenticationManager(this.authenticationManager);

    }

}
