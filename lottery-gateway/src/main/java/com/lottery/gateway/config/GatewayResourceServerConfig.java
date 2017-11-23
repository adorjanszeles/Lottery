package com.lottery.gateway.config;

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
 * GATEWAY Resource szerver konfigurációs osztály
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@PropertySource("classpath:authserver.properties")
public class GatewayResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "lottery_rest_api";
    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayResourceServerConfig.class);
    private Environment env;

    @Autowired
    public GatewayResourceServerConfig(Environment env) {
        this.env = env;
    }

    /**
     * Ahhoz, hogy a ResourceConfigurerAdapter tudja kezelni a beérkező tokent, ugyanazokkal a tulajdonságokkal kell
     * rendelkeznie mint az Auth szerver oldalán lévő AuthorizationServerConfigurerAdapter-nek. Tehát ugyanazt a
     * tokenStore-t és tokenServices-t kell neki beállítani
     *
     * @param resources default paraméter
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(GatewayResourceServerConfig.RESOURCE_ID)
                 .tokenServices(tokenServices())
                 .tokenStore(tokenStore());
    }

    /**
     * Http kéréseket tudunk kontrollálni. Beállíthatjuk, hogy mely végpontokra érkező http request-ekhez kérünk
     * előzetes authorizációt / authentikációt illetve mely végpontokat hagyunk publikusan elérhetőnek.
     *
     * @param http default paraméter
     * @throws Exception kivétel
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        GatewayResourceServerConfig.LOGGER.debug("httpSecurity inicializálás kezdése");
        http.anonymous()
            .disable()
            .authorizeRequests()
            .antMatchers("/gateway/**", "/user/users")
            .authenticated()
            .antMatchers("/auth/get-token")
            .permitAll()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(new OAuth2AccessDeniedHandler());
        GatewayResourceServerConfig.LOGGER.debug("httpSecurity inicializálva");

    }

    /**
     * Az Auth szervertől elkért rsa publikus kulccsal tudjuk ellenőrizni, hogy az access token-en lévő digitális
     * aláírás érvényes-e.
     *
     * @return JwtAccessTokenConverter bean, amely a publikus kulcs birtokában ellenőrzi a bejövő access / refresh
     * tokeneket
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        String publicKey = this.getPublicKey();
        converter.setVerifierKey(publicKey);

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
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * Access és Refresh token értékeket generáló bean amely random UUID értékek alapján dolgozik. A tokenek
     * perzisztálásának feladatát a tokenStore implementációja látja el, amennyiben erre képes tokenStore-t használunk.
     *
     * @return defaultTokenServices bean
     */
    @Bean
    public ResourceServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        return defaultTokenServices;
    }

    /**
     * Ennek a beannek a segítségével tudjuk titkosítani illetve dekódolni a titkosított jelszavakat. Jelenleg a BCrypt
     * hashing functiont használjuk amelynek paraméterként megadhatjuk, hogy milyen erősségű biztonságot szeretnénk
     * elérni. A Default érték 10. Ennek növelése exponenciálisan növeli a hashelés idejét.
     *
     * @return PasswordEncoder bean
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

    /**
     * RestTemplate használatával egy http kérést indítunk az auth szerver felé, amelynek eredménye képpen visszakapjuk
     * az access és refresh tokenek aláírásának ellenőrzéséhez elengedhetetlen rsa publikus kulcsot.
     *
     * @return String rsa publikus kulcs
     */
    public String getPublicKey() {
        GatewayResourceServerConfig.LOGGER.debug("Public Key lekérése az Auth szervertől");

        RestTemplate restTemplate = new RestTemplate();
        String authServerUrl = this.env.getProperty("authserver.token_key");
        ResponseEntity<String> response = restTemplate.getForEntity(authServerUrl, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            String publicKey = root.path("value").getTextValue().replace("\\n", "");
            return publicKey;
        } catch (IOException e) {
            GatewayResourceServerConfig.LOGGER.debug("Public key lekérése az Auth szervertől sikertelen");
            System.out.println("Public key lekérése sikertelen \n\n\n");
            throw new Error("Public key lekérése sikertelen");
        }
    }

}
