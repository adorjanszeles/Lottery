package com.lottery.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Spring Security authorizációs szerver konfig osztálya
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationServerConfig.class);
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
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.realm(REALM + "/client");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        AuthorizationServerConfig.LOGGER.debug("Access, Refresh tokenek konfigurálása elkezdődött");
        clients.inMemory()
               .withClient("lottery-client")
               .authorizedGrantTypes("password", "refresh_token")
               .authorities("Role_admin")
               .scopes("read", "write", "trust")
               .authorities("Role_user")
               .scopes("read")
               .secret("secret")
               .accessTokenValiditySeconds(600000) // valid for 5 minutes
               .refreshTokenValiditySeconds(1200); // valid for 20 minutes
        AuthorizationServerConfig.LOGGER.debug("Access, Refresh tokenek konfigurálása befejeződött");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(this.tokenStore)
                 .userApprovalHandler(this.userApprovalHandler)
                 .authenticationManager(this.authenticationManager);
    }

}