package com.odsProject.odsProject.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

/**
 * Configuración de DSLContext JOOQ — uno por schema.
 * CADA contexto apunta a su DataSource dedicado.
 * Los DataSources están definidos en DataSourceConfig.
 *
 * IMPORTANTE: Las consultas cross-schema (ods01 → ods_master, ods01 → ods_login)
 * funcionan porque MariaDB resuelve referencias por nombre de esquema en SQL.
 * El DSL del ODS específico sólo necesita acceso a su propia BD;
 * las FKs cross-DB son resueltas por MariaDB en server-side.
 */
@Configuration
public class JooqConfig {

    private DSLContext buildDsl(DataSource ds) {
        return DSL.using(new TransactionAwareDataSourceProxy(ds), SQLDialect.MARIADB);
    }

    // ── ods_login ──────────────────────────────────────────────────
    @Primary
    @Bean(name = {"dsl", "dslLogin", "dslOdsLogin"})
    public DSLContext dslLogin(@Qualifier("dataSourceLogin") DataSource ds) {
        return buildDsl(ds);
    }

    // ── ods_master ──────────────────────────────────────────────────
    @Bean(name = {"dslMaster", "dslOdsMaster"})
    public DSLContext dslMaster(@Qualifier("dataSourceMaster") DataSource ds) {
        return buildDsl(ds);
    }

    // ── ODS 01 – 17 ─────────────────────────────────────────────────
    @Bean("dslOds01")
    public DSLContext dslOds01(@Qualifier("dataSourceOds01") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds02")
    public DSLContext dslOds02(@Qualifier("dataSourceOds02") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds03")
    public DSLContext dslOds03(@Qualifier("dataSourceOds03") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds04")
    public DSLContext dslOds04(@Qualifier("dataSourceOds04") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds05")
    public DSLContext dslOds05(@Qualifier("dataSourceOds05") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds06")
    public DSLContext dslOds06(@Qualifier("dataSourceOds06") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds07")
    public DSLContext dslOds07(@Qualifier("dataSourceOds07") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds08")
    public DSLContext dslOds08(@Qualifier("dataSourceOds08") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds09")
    public DSLContext dslOds09(@Qualifier("dataSourceOds09") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds10")
    public DSLContext dslOds10(@Qualifier("dataSourceOds10") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds11")
    public DSLContext dslOds11(@Qualifier("dataSourceOds11") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds12")
    public DSLContext dslOds12(@Qualifier("dataSourceOds12") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds13")
    public DSLContext dslOds13(@Qualifier("dataSourceOds13") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds14")
    public DSLContext dslOds14(@Qualifier("dataSourceOds14") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds15")
    public DSLContext dslOds15(@Qualifier("dataSourceOds15") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds16")
    public DSLContext dslOds16(@Qualifier("dataSourceOds16") DataSource ds) { return buildDsl(ds); }

    @Bean("dslOds17")
    public DSLContext dslOds17(@Qualifier("dataSourceOds17") DataSource ds) { return buildDsl(ds); }
}
