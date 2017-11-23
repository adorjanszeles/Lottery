package com.lottery.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * WebMvc konfigurációs osztály
 */
@Configuration
public class MessageConverterConfig extends WebMvcConfigurerAdapter {

    /**
     * Ez a metódus állítja be, hogy a restController végpontjainkon a @RequestBody annotációval ellátott metódusok ne
     * csak httpMessage-t hanem x-www-form-urlencoded paramatéreket is tudjanak fogadni
     *
     * @param converters default paraméter
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FormHttpMessageConverter converter = new FormHttpMessageConverter();
        MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        converters.add(converter);
        super.configureMessageConverters(converters);
    }

}
