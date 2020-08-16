package me.kalpha.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@PropertySource("persistence-kalpha.properties")
@PropertySource("persistence-library.properties")
public class DbConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("user"));
        dataSource.setPassword(env.getProperty("password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "me.kalpha.entity" });
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    final Properties additionalProperties() {
        final Properties jpaProperties = new Properties();
        if (env.getProperty("hibernate.dialect") != null) {
            jpaProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

        }
        if (env.getProperty("hibernate.show_sql") != null) {
            jpaProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        }
        if (env.getProperty("hibernate.format_sql") != null) {
            jpaProperties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        }
        return jpaProperties;
    }
}

@Configuration
@Profile("kalpha")
@EnableJpaRepositories(basePackages = "me.kalpha.book.repository")
@PropertySource("classpath:persistence-kalpha.properties")
class KalphaConfig {
}

@Configuration
@Profile("library")
@EnableJpaRepositories(basePackages = "me.kalpha.book.repository")
@PropertySource("classpath:persistence-library.properties")
class LibraryConfig {
}