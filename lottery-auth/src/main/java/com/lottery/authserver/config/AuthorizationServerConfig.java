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
    private static final Integer ACCESS_VALIDITY_IN_SECONDS = 600000;
    private static final Integer REFRESH_VALIDITY_IN_SECONDS = 600000;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationServerConfig(
            @Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Az rsa publikus kulcshoz való hozzáférést tudjuk beállítani, aminek a default értéke denyAll(), illetve csak
     * adminként vagy regisztrált userként tudjuk lellenőrizni a token érvényességét.
     *
     * @param oauthServer default paraméter
     * @throws Exception kivétel
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()");
        oauthServer.checkTokenAccess("hasAuthority('Role_admin') or hasAuthority(Role_user)");
    }

    /**
     * Különböző kliensekhez(pl Angular, mobilapp stb..) tudunk különböző konfigurációkat társítani.
     *
     * @param clients default paraméter
     * @throws Exception kivétel
     */
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

    /**
     * Az Auth szerver eléréséhez szükséges komponenseket tudjuk konfigurálni
     *
     * @param endpoints default paraméter
     * @throws Exception kivétel
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(this.tokenStore())
                 .tokenServices(this.tokenServices())
                 .authenticationManager(this.authenticationManager)
                 .accessTokenConverter(this.accessTokenConverter());
    }

    /**
     * Az itt generált rsa kulcspárt használva tudjuk majd digitálisan aláírni a kiadott tokeneket amelyek érvényességét
     * a többi MicroService a publikus kulccsal tudja ellenőrizni
     *
     * @return JwtAccessTokenConverter bean
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mykeys.jks"),
                                                                       "mypass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mykeys"));
        return converter;
    }

    /**
     * TokenStore implementáció ami a tokenekben érkező adatokat olvassa ki. A store csak a nevében jelenik meg,
     * valójában nem perzisztál adatot. Az implementációt le lehet cserélni akár jdbc vagy redis tokenStore bean-re.
     *
     * @return JwtTokenStore bean
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(this.accessTokenConverter());
    }

    /**
     * Access és Refresh token értékeket generáló bean amely random UUID értékek alapján dolgozik. A tokenek
     * perzisztálásának feladatát a tokenStore implementációja látja el, amennyiben erre képes tokenStore-t használunk.
     * Itt tudjuk beállítani a kiadott access és refresh tokenek érvényességének hosszát is.
     *
     * @return defaultTokenServices bean
     */
    @Bean
    // https://stackoverflow.com/a/29929270/6917248
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(this.tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(this.accessTokenConverter());
        defaultTokenServices.setAccessTokenValiditySeconds(AuthorizationServerConfig.ACCESS_VALIDITY_IN_SECONDS);
        defaultTokenServices.setRefreshTokenValiditySeconds(AuthorizationServerConfig.REFRESH_VALIDITY_IN_SECONDS);
        return defaultTokenServices;
    }
}