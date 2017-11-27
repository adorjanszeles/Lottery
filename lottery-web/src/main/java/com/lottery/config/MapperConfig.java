package com.lottery.config;

import com.lottery.service.UserDestinationMapperImpl;
import com.lottery.service.WeeklyDrawDestinationMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A Bean mapper osztályok implementációit szolgáltató osztály
 */
@Configuration
public class MapperConfig {

    @Bean
    public WeeklyDrawDestinationMapperImpl getWeeklyDrawDestinationMapperImpl() {
        return new WeeklyDrawDestinationMapperImpl();
    }

    @Bean
    public UserDestinationMapperImpl getUserDestinationMapperImpl() {
        return new UserDestinationMapperImpl();
    }
}
