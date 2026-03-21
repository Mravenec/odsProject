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

    // ── DataSource Principal (ods_login) ──

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods_login")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS01 ──

    @Bean
    @Qualifier("ods01DataSource")
    public DataSource ods01DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods01")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS02 ──

    @Bean
    @Qualifier("ods02DataSource")
    public DataSource ods02DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods02")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS03 ──

    @Bean
    @Qualifier("ods03DataSource")
    public DataSource ods03DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods03")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS04 ──

    @Bean
    @Qualifier("ods04DataSource")
    public DataSource ods04DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods04")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS05 ──

    @Bean
    @Qualifier("ods05DataSource")
    public DataSource ods05DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods05")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS06 ──

    @Bean
    @Qualifier("ods06DataSource")
    public DataSource ods06DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods06")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS07 ──

    @Bean
    @Qualifier("ods07DataSource")
    public DataSource ods07DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods07")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS08 ──

    @Bean
    @Qualifier("ods08DataSource")
    public DataSource ods08DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods08")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS09 ──

    @Bean
    @Qualifier("ods09DataSource")
    public DataSource ods09DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods09")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS10 ──

    @Bean
    @Qualifier("ods10DataSource")
    public DataSource ods10DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods10")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS11 ──

    @Bean
    @Qualifier("ods11DataSource")
    public DataSource ods11DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods11")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS12 ──

    @Bean
    @Qualifier("ods12DataSource")
    public DataSource ods12DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods12")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS13 ──

    @Bean
    @Qualifier("ods13DataSource")
    public DataSource ods13DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods13")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS14 ──

    @Bean
    @Qualifier("ods14DataSource")
    public DataSource ods14DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods14")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS15 ──

    @Bean
    @Qualifier("ods15DataSource")
    public DataSource ods15DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods15")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS16 ──

    @Bean
    @Qualifier("ods16DataSource")
    public DataSource ods16DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods16")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }

    // ── DataSource para ODS17 ──

    @Bean
    @Qualifier("ods17DataSource")
    public DataSource ods17DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/ods17")
                .username("root")
                .password("123456")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
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
    public DSLContext dslOds01(@Qualifier("ods01DataSource") DataSource ods01DataSource) {
        return DSL.using(ods01DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds02")
    public DSLContext dslOds02(@Qualifier("ods02DataSource") DataSource ods02DataSource) {
        return DSL.using(ods02DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds03")
    public DSLContext dslOds03(@Qualifier("ods03DataSource") DataSource ods03DataSource) {
        return DSL.using(ods03DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds04")
    public DSLContext dslOds04(@Qualifier("ods04DataSource") DataSource ods04DataSource) {
        return DSL.using(ods04DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds05")
    public DSLContext dslOds05(@Qualifier("ods05DataSource") DataSource ods05DataSource) {
        return DSL.using(ods05DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds06")
    public DSLContext dslOds06(@Qualifier("ods06DataSource") DataSource ods06DataSource) {
        return DSL.using(ods06DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds07")
    public DSLContext dslOds07(@Qualifier("ods07DataSource") DataSource ods07DataSource) {
        return DSL.using(ods07DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds08")
    public DSLContext dslOds08(@Qualifier("ods08DataSource") DataSource ods08DataSource) {
        return DSL.using(ods08DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds09")
    public DSLContext dslOds09(@Qualifier("ods09DataSource") DataSource ods09DataSource) {
        return DSL.using(ods09DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds10")
    public DSLContext dslOds10(@Qualifier("ods10DataSource") DataSource ods10DataSource) {
        return DSL.using(ods10DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds11")
    public DSLContext dslOds11(@Qualifier("ods11DataSource") DataSource ods11DataSource) {
        return DSL.using(ods11DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds12")
    public DSLContext dslOds12(@Qualifier("ods12DataSource") DataSource ods12DataSource) {
        return DSL.using(ods12DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds13")
    public DSLContext dslOds13(@Qualifier("ods13DataSource") DataSource ods13DataSource) {
        return DSL.using(ods13DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds14")
    public DSLContext dslOds14(@Qualifier("ods14DataSource") DataSource ods14DataSource) {
        return DSL.using(ods14DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds15")
    public DSLContext dslOds15(@Qualifier("ods15DataSource") DataSource ods15DataSource) {
        return DSL.using(ods15DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds16")
    public DSLContext dslOds16(@Qualifier("ods16DataSource") DataSource ods16DataSource) {
        return DSL.using(ods16DataSource, SQLDialect.MARIADB);
    }

    @Bean
    @Qualifier("dslOds17")
    public DSLContext dslOds17(@Qualifier("ods17DataSource") DataSource ods17DataSource) {
        return DSL.using(ods17DataSource, SQLDialect.MARIADB);
    }
}
