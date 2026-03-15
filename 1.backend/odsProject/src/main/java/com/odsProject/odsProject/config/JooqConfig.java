package com.odsProject.odsProject.config;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.SQLDialect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

    // ── DataSource Principal (ods_login) ──

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS01 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods01")
    public DataSource ods01DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS02 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods02")
    public DataSource ods02DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS03 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods03")
    public DataSource ods03DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS04 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods04")
    public DataSource ods04DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS05 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods05")
    public DataSource ods05DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS06 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods06")
    public DataSource ods06DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS07 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods07")
    public DataSource ods07DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS08 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods08")
    public DataSource ods08DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS09 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods09")
    public DataSource ods09DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS10 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods10")
    public DataSource ods10DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS11 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods11")
    public DataSource ods11DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS12 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods12")
    public DataSource ods12DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS13 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods13")
    public DataSource ods13DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS14 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods14")
    public DataSource ods14DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS15 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods15")
    public DataSource ods15DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS16 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods16")
    public DataSource ods16DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DataSource para ODS17 ──

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.ods17")
    public DataSource ods17DataSource() {
        return DataSourceBuilder.create().build();
    }

    // ── DSL Contexts ──

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
    @Qualifier("dslOds01")
    public DSLContext dslOds01(DataSource ods01DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods01DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds02")
    public DSLContext dslOds02(DataSource ods02DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods02DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds03")
    public DSLContext dslOds03(DataSource ods03DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods03DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds04")
    public DSLContext dslOds04(DataSource ods04DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods04DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds05")
    public DSLContext dslOds05(DataSource ods05DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods05DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds06")
    public DSLContext dslOds06(DataSource ods06DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods06DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds07")
    public DSLContext dslOds07(DataSource ods07DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods07DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds08")
    public DSLContext dslOds08(DataSource ods08DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods08DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds09")
    public DSLContext dslOds09(DataSource ods09DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods09DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds10")
    public DSLContext dslOds10(DataSource ods10DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods10DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds11")
    public DSLContext dslOds11(DataSource ods11DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods11DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds12")
    public DSLContext dslOds12(DataSource ods12DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods12DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds13")
    public DSLContext dslOds13(DataSource ods13DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods13DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds14")
    public DSLContext dslOds14(DataSource ods14DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods14DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds15")
    public DSLContext dslOds15(DataSource ods15DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods15DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds16")
    public DSLContext dslOds16(DataSource ods16DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods16DataSource), SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds17")
    public DSLContext dslOds17(DataSource ods17DataSource) {
        return DSL.using(new TransactionAwareDataSourceProxy(ods17DataSource), SQLDialect.MARIADB);
    }
}
