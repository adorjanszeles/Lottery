package com.lottery.config;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Resource szerver konfigurációs osztály
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@PropertySource("classpath:authserver.properties")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "lottery_rest_api";
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceServerConfig.class);
    private Environment env;

    @Autowired
    public ResourceServerConfig(Environment env) {
        this.env = env;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(ResourceServerConfig.RESOURCE_ID)
                 .tokenServices(this.tokenServices())
                 .tokenStore(this.tokenStore());
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

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String publicKey = this.getPublicKey();
        converter.setVerifierKey(publicKey);

        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(this.accessTokenConverter());
    }

    @Bean
    public ResourceServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(this.tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(this.accessTokenConverter());
        return defaultTokenServices;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

    private String getPublicKey() {
        ResourceServerConfig.LOGGER.debug("Public Key lekérése az Auth szervertől");

        RestTemplate restTemplate = new RestTemplate();
        String authServerUrl = this.env.getProperty("authserver.token_key");
        ResponseEntity<String> response = restTemplate.getForEntity(authServerUrl, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            return root.path("value").getTextValue().replace("\\n", "");
        } catch (IOException e) {
            ResourceServerConfig.LOGGER.debug("Public key lekérése az Auth szervertől sikertelen");
            System.out.println("Public key lekérése sikertelen \n\n\n");
            throw new Error("Public key lekérése sikertelen");
        }
    }

}
