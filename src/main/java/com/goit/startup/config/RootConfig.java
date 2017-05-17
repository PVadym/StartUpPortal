package com.goit.startup.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * The class is a configuration class for basic settings of Spring Data
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.goit.startup.repository")
@ComponentScan(basePackages = {"com.goit.startup.entity", "com.goit.startup.validator"})
@PropertySource(value = "classpath:database.properties")
public class RootConfig {

    /**
     * jdbc driver class
     */
    @Value("${jdbc.driver}")
    private String jdbcDriver;

    /**
     * jdbc driver url
     */
    @Value("${jdbc.driver.url}")
    private String jdbcDriverUrl;

    /**
     * Database host ip address
     */
    @Value("${database.host.ip}")
    private String hostIp;

    /**
     * Database host port number
     */
    @Value("${database.host.port}")
    private String hostPort;

    /**
     * Database name
     */
    @Value("${database.name}")
    private String databaseName;

    /**
     * Database username
     */
    @Value("${database.username}")
    private String databaseUsername;

    /**
     * Database password
     */
    @Value("${database.password}")
    private String databasePassword;

    /**
     * The parameter shows whether SSL enabled or disabled
     */
    @Value("${database.use-ssl}")
    private String useSsl;

    /**
     * The parameter shows whether Unicode enabled or disabled
     */
    @Value("${database.use-unicode}")
    private String useUnicode;

    /**
     * The parameter shows character encoding type
     */
    @Value("${database.character-encoding}")
    private String characterEncoding;

    /**
     * The parameter shows whether JdbcCompliantTimezoneShift
     * enabled or disabled
     */
    @Value("${database.use-jdbc-compliant-timezone-shift}")
    private boolean useJdbcCompliantTimezoneShift;

    /**
     * The parameter shows whether Legacy Datetime Code is used or not
     */
    @Value("${database.use-legacy-datetime-code}")
    private boolean useLegacyDatetimeCode;

    /**
     * The parameter shows what kind of server time zone is used
     */
    @Value("${database.server-timezone}")
    private String serverTimezone;

    /**
     * The parameter shows the initial size of connection pool
     */
    @Value("${database.initial-size}")
    private int initialSize;

    /**
     * The parameter shows maximal amount of active connections
     */
    @Value("${database.max-active}")
    private int maxActive;

    /**
     * If true, connections will be validated before being returned
     * from the pool. If the validation fails, the connection is destroyed,
     * and a new connection will be retrieved from the pool (and validated).
     */
    @Value("${database.test-on-borrow}")
    private boolean testOnBorrow;

    /**
     * The SQL query that will be used to validate connections
     * from this poolbefore returning them to the caller.
     * If specified, this query MUST be an SQL SELECT statement
     * that returns at least one row.
     */
    @Value("${database.validation-query}")
    private String validationQuery;

    /**
     * The parameter defines SQL dialect
     */
    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    /**
     * The parameter defines whether to show generated
     * by Hibernate SQLs or not
     */
    @Value("${hibernate.show-sql}")
    private boolean isShowSql;

    /**
     * The parameter defines whether to generate DDL after
     * the EntityManagerFactory has been initialized,
     * creating/updating all relevant tables.
     */
    @Value("${hibernate.generate-ddl}")
    private boolean isGenerateDdl;

    /**
     * the parameter defines packages to be scanned for Entities
     */
    @Value("${hibernate.entity-packages}")
    private String entityPackages;

    /**
     * The method returns {@link PropertySourcesPlaceholderConfigurer}
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * The method returns datasource
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.jdbcDriver);
        dataSource.setUrl(getDataSourceUrl());
        dataSource.setConnectionProperties(createDatabaseConnectionProperties());
        dataSource.setUsername(this.databaseUsername);
        dataSource.setPassword(this.databasePassword);
        dataSource.setInitialSize(this.initialSize);
        dataSource.setMaxActive(this.maxActive);
        dataSource.setTestOnBorrow(this.testOnBorrow);
        dataSource.setValidationQuery(this.validationQuery);
        return dataSource;
    }

    /**
     * The method returns configured {@link HibernateJpaVendorAdapter}
     */
    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(this.isShowSql);
        hibernateJpaVendorAdapter.setGenerateDdl(this.isGenerateDdl);
        hibernateJpaVendorAdapter.setDatabasePlatform(this.hibernateDialect);
        return hibernateJpaVendorAdapter;
    }

    /**
     * The method returns  configured {@link EntityManagerFactory}
     *
     * @param dataSource                accepts related {@link DataSource}
     * @param hibernateJpaVendorAdapter accepts related {@link HibernateJpaVendorAdapter}
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            HibernateJpaVendorAdapter hibernateJpaVendorAdapter
    ) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        emf.setPackagesToScan(this.entityPackages);
        return emf;
    }

    /**
     * The method returns {@link JpaTransactionManager}
     *
     * @param emf accepts related {@link EntityManagerFactory}
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    /**
     * The method returns {@link BeanPostProcessor}
     */
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * The method returns datasource url
     */
    private String getDataSourceUrl() {
        return this.jdbcDriverUrl + "://" + this.hostIp + ":" + this.hostPort + "/" + this.databaseName;
    }

    /**
     * The method returns database connection properties
     */
    private String createDatabaseConnectionProperties() {
        return "useSSL=" + this.useSsl + ";"
                + "useUnicode=" + this.useUnicode + ";"
                + "characterEncoding=" + this.characterEncoding + ";"
                + "useJDBCCompliantTimezoneShift=" + this.useJdbcCompliantTimezoneShift + ";"
                + "useLegacyDatetimeCode=" + this.useLegacyDatetimeCode + ";"
                + "serverTimezone=" + this.serverTimezone;
    }
}
