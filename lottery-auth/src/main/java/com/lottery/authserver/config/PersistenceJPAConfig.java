package com.lottery.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Az adatbázissal való kommunikációt és a package-ben található Entity-ket menedzselő bean-eket előállító osztály.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.lottery", entityManagerFactoryRef = "entityManagerFactory")
@PropertySource("classpath:database.properties")
public class PersistenceJPAConfig {
    @Autowired
    private Environment env;

    /**
     * Az EntityManagerFactory bean előállításáért és konfigurálásáért felelős függvény
     *
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(getJpaVendorAdapter());
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPersistenceUnitName("myJpaPersistenceUnit");
        entityManagerFactory.setPackagesToScan("com.lottery");
        entityManagerFactory.setJpaProperties(jpaProperties());
        return entityManagerFactory;
    }

    /**
     * A JPAVendort szolgáltató bean-t előállító függvény
     *
     * @return JpaVendorAdapter instance
     */
    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }

    /**
     * Az adatbázis hozzáféréshez szükséges adatokat gyűjti be a database.properties fájlból
     *
     * @return DataSource instance
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.env.getProperty("database.driverClassName"));
        dataSource.setUrl(this.env.getProperty("database.url"));
        dataSource.setUsername(this.env.getProperty("database.username"));
        dataSource.setPassword(this.env.getProperty("database.password"));
        return dataSource;
    }

    /**
     * Az adartbázissal való kommunikáció szabályait állítja össze.
     *
     * @return Properties instance
     */
    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", this.env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", this.env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", this.env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", this.env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    /**
     * A tranzakciókezeléshez szükséges bean-t állítja elő.
     *
     * @param entityManagerFactory a project-ben található Entity-ket kezelő objektum
     * @return JpaTransactionManager instance
     */
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}