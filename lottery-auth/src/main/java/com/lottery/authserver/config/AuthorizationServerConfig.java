package com.lottery.authserver.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * Spring Security Oauth2 authorizációs szerver konfig osztálya
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationServerConfig.class);
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationServerConfig(
            @Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()");
        oauthServer.checkTokenAccess("hasAuthority('Role_admin') or hasAuthority(Role_user)");
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
               .secret("secret");
        AuthorizationServerConfig.LOGGER.debug("Access, Refresh tokenek konfigurálása befejeződött");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                 .tokenServices(tokenServices())
                 .authenticationManager(this.authenticationManager)
                 .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mykeys.jks"),
                                                                       "mypass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mykeys"));
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    // https://stackoverflow.com/a/29929270/6917248
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        defaultTokenServices.setAccessTokenValiditySeconds(60);
        defaultTokenServices.setRefreshTokenValiditySeconds(1200);
        return defaultTokenServices;
    }
}