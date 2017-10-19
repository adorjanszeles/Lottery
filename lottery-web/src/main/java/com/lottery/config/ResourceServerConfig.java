package com.lottery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "lottery_rest_api";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(ResourceServerConfig.RESOURCE_ID).stateless(false); // TODO: or true?
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous()
            .disable()
            .requestMatchers()
            .antMatchers("/lottery**")
            .and()
            .authorizeRequests()
            .antMatchers("/lottery**")
            .access("hasRole('ADMIN')")
            .and()
            .exceptionHandling()
            .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
