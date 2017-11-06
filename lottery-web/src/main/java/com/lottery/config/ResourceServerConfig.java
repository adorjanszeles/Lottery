package com.lottery.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * Resource szerver konfigurációs osztály
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "lottery_rest_api";
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceServerConfig.class);

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(ResourceServerConfig.RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ResourceServerConfig.LOGGER.debug("httpSecurity inicializálás kezdése");
        http.anonymous()
            .disable()
            .authorizeRequests()
            .antMatchers("/lottery/**", "/user/users")
            .authenticated()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(new OAuth2AccessDeniedHandler());
        ResourceServerConfig.LOGGER.debug("httpSecurity inicializálva");

    }

}
