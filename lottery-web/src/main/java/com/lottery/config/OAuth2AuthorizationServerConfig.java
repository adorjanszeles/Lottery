package com.lottery.config;

import com.lottery.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Az applikációhoz tartozo websecurity konfigurációt tartalmazo osztály
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2AuthorizationServerConfig extends WebSecurityConfigurerAdapter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(OAuth2AuthorizationServerConfig.class);
    private UserDetailsServiceImpl userService;
    private ClientDetailsService clientDetailsService;

    @Autowired
    public OAuth2AuthorizationServerConfig(UserDetailsServiceImpl userService,
                                           ClientDetailsService clientDetailsService) {
        this.userService = userService;
        this.clientDetailsService = clientDetailsService;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        OAuth2AuthorizationServerConfig.LOGGER.debug("authentikáció elkezdődött");
        auth.userDetailsService(userService).passwordEncoder(encoder());
        OAuth2AuthorizationServerConfig.LOGGER.debug("authentikációnak vége");
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // provides the default AuthenticationManager as a Bean
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/oauth/token").permitAll();
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(this.clientDetailsService));
        handler.setClientDetailsService(this.clientDetailsService);
        return handler;
    }

    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

}