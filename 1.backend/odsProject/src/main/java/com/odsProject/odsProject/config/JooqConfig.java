package com.odsProject.odsProject.config;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.SQLDialect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

/**
 * Configuración de jOOQ para múltiples bases de datos ODS
 * Provee DSLContext para cada base de datos individual
 */
@Configuration
public class JooqConfig {

    // ── DataSource Único y Centralizado (Compartido por todos los ODS) ──

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods_login") // URL base para el pool
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DSL Contexts (Todos comparten el mismo pool de conexiones) ──

    @Bean
    @Primary
    public DSLContext dsl(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOdsLogin")
    public DSLContext dslOdsLogin(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOdsMaster")
    public DSLContext dslOdsMaster(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds01")
    public DSLContext dslOds01(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds02")
    public DSLContext dslOds02(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds03")
    public DSLContext dslOds03(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds04")
    public DSLContext dslOds04(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds05")
    public DSLContext dslOds05(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds06")
    public DSLContext dslOds06(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds07")
    public DSLContext dslOds07(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds08")
    public DSLContext dslOds08(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds09")
    public DSLContext dslOds09(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds10")
    public DSLContext dslOds10(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds11")
    public DSLContext dslOds11(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds12")
    public DSLContext dslOds12(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds13")
    public DSLContext dslOds13(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds14")
    public DSLContext dslOds14(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds15")
    public DSLContext dslOds15(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds16")
    public DSLContext dslOds16(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds17")
    public DSLContext dslOds17(DataSource dataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(dataSource), SQLDialect.MARIADB);
    }
}
