package com.odsProject.odsProject.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Configuración de los 19 DataSources del sistema ODS.
 * Cada DataSource apunta a una base de datos MariaDB distinta.
 *
 * Orden de dependencias:
 *   ods_login → todos los demás (usuarios, catálogos)
 *   ods_master → proyectos centralizados
 *   ods01-17 → indicadores, mediciones por ODS
 */
@Configuration
public class DataSourceConfig {

    // ─────────────────────────────────────────────────────────────
    // PRIMARY: ods_login (usado por Spring Security y auto-config)
    // ─────────────────────────────────────────────────────────────

    @Primary
    @Bean(name = "dataSourceLogin")
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSourceLogin() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(5);
        ds.setMinimumIdle(2);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Login");
        return ds;
    }

    // ─────────────────────────────────────────────────────────────
    // ods_master
    // ─────────────────────────────────────────────────────────────

    @Bean(name = "dataSourceMaster")
    @ConfigurationProperties("app.datasource.ods-master")
    public DataSource dataSourceMaster() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(5);
        ds.setMinimumIdle(2);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Master");
        return ds;
    }

    // ─────────────────────────────────────────────────────────────
    // ODS 01 – 17
    // ─────────────────────────────────────────────────────────────

    @Bean(name = "dataSourceOds01")
    @ConfigurationProperties("app.datasource.ods01")
    public DataSource dataSourceOds01() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods01");
        return ds;
    }

    @Bean(name = "dataSourceOds02")
    @ConfigurationProperties("app.datasource.ods02")
    public DataSource dataSourceOds02() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods02");
        return ds;
    }

    @Bean(name = "dataSourceOds03")
    @ConfigurationProperties("app.datasource.ods03")
    public DataSource dataSourceOds03() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods03");
        return ds;
    }

    @Bean(name = "dataSourceOds04")
    @ConfigurationProperties("app.datasource.ods04")
    public DataSource dataSourceOds04() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods04");
        return ds;
    }

    @Bean(name = "dataSourceOds05")
    @ConfigurationProperties("app.datasource.ods05")
    public DataSource dataSourceOds05() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods05");
        return ds;
    }

    @Bean(name = "dataSourceOds06")
    @ConfigurationProperties("app.datasource.ods06")
    public DataSource dataSourceOds06() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods06");
        return ds;
    }

    @Bean(name = "dataSourceOds07")
    @ConfigurationProperties("app.datasource.ods07")
    public DataSource dataSourceOds07() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods07");
        return ds;
    }

    @Bean(name = "dataSourceOds08")
    @ConfigurationProperties("app.datasource.ods08")
    public DataSource dataSourceOds08() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods08");
        return ds;
    }

    @Bean(name = "dataSourceOds09")
    @ConfigurationProperties("app.datasource.ods09")
    public DataSource dataSourceOds09() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods09");
        return ds;
    }

    @Bean(name = "dataSourceOds10")
    @ConfigurationProperties("app.datasource.ods10")
    public DataSource dataSourceOds10() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods10");
        return ds;
    }

    @Bean(name = "dataSourceOds11")
    @ConfigurationProperties("app.datasource.ods11")
    public DataSource dataSourceOds11() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods11");
        return ds;
    }

    @Bean(name = "dataSourceOds12")
    @ConfigurationProperties("app.datasource.ods12")
    public DataSource dataSourceOds12() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods12");
        return ds;
    }

    @Bean(name = "dataSourceOds13")
    @ConfigurationProperties("app.datasource.ods13")
    public DataSource dataSourceOds13() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods13");
        return ds;
    }

    @Bean(name = "dataSourceOds14")
    @ConfigurationProperties("app.datasource.ods14")
    public DataSource dataSourceOds14() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods14");
        return ds;
    }

    @Bean(name = "dataSourceOds15")
    @ConfigurationProperties("app.datasource.ods15")
    public DataSource dataSourceOds15() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods15");
        return ds;
    }

    @Bean(name = "dataSourceOds16")
    @ConfigurationProperties("app.datasource.ods16")
    public DataSource dataSourceOds16() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods16");
        return ds;
    }

    @Bean(name = "dataSourceOds17")
    @ConfigurationProperties("app.datasource.ods17")
    public DataSource dataSourceOds17() {
        HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(2);
        ds.setMinimumIdle(1);
        ds.setConnectionTimeout(20000);
        ds.setPoolName("HikariPool-Ods17");
        return ds;
    }
}
