package com.odsProject.odsProject.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Sprint 2 — Configuración de PlatformTransactionManager por DataSource.
 *
 * El sistema usa 19 DataSources distintos (ods_login, ods_master, ods01..ods17).
 * Spring Boot solo auto-configura un TransactionManager para la DS primaria
 * (ods_login). Para que {@code @Transactional} funcione correctamente al hacer
 * dos inserts atómicos en una BD ODS específica (medición + valores de parámetros),
 * cada DS necesita su propio PlatformTransactionManager.
 *
 * Convención de qualifiers:
 *   - txManagerLogin   → ods_login   (también es @Primary)
 *   - txManagerMaster  → ods_master
 *   - txManagerOds01   → ods01
 *   - txManagerOds02   → ods02
 *   - ...
 *   - txManagerOds17   → ods17
 *
 * Uso típico desde un servicio ODS:
 *   {@code @Transactional("txManagerOds01")}
 */
@Configuration
public class TransactionManagerConfig {

    @Primary
    @Bean("txManagerLogin")
    public PlatformTransactionManager txManagerLogin(@Qualifier("dataSourceLogin") DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean("txManagerMaster")
    public PlatformTransactionManager txManagerMaster(@Qualifier("dataSourceMaster") DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean("txManagerOds01")
    public PlatformTransactionManager txManagerOds01(@Qualifier("dataSourceOds01") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds02")
    public PlatformTransactionManager txManagerOds02(@Qualifier("dataSourceOds02") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds03")
    public PlatformTransactionManager txManagerOds03(@Qualifier("dataSourceOds03") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds04")
    public PlatformTransactionManager txManagerOds04(@Qualifier("dataSourceOds04") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds05")
    public PlatformTransactionManager txManagerOds05(@Qualifier("dataSourceOds05") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds06")
    public PlatformTransactionManager txManagerOds06(@Qualifier("dataSourceOds06") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds07")
    public PlatformTransactionManager txManagerOds07(@Qualifier("dataSourceOds07") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds08")
    public PlatformTransactionManager txManagerOds08(@Qualifier("dataSourceOds08") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds09")
    public PlatformTransactionManager txManagerOds09(@Qualifier("dataSourceOds09") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds10")
    public PlatformTransactionManager txManagerOds10(@Qualifier("dataSourceOds10") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds11")
    public PlatformTransactionManager txManagerOds11(@Qualifier("dataSourceOds11") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds12")
    public PlatformTransactionManager txManagerOds12(@Qualifier("dataSourceOds12") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds13")
    public PlatformTransactionManager txManagerOds13(@Qualifier("dataSourceOds13") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds14")
    public PlatformTransactionManager txManagerOds14(@Qualifier("dataSourceOds14") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds15")
    public PlatformTransactionManager txManagerOds15(@Qualifier("dataSourceOds15") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds16")
    public PlatformTransactionManager txManagerOds16(@Qualifier("dataSourceOds16") DataSource ds) { return new DataSourceTransactionManager(ds); }
    @Bean("txManagerOds17")
    public PlatformTransactionManager txManagerOds17(@Qualifier("dataSourceOds17") DataSource ds) { return new DataSourceTransactionManager(ds); }
}
