package com.waterleak.config.dev;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({"classpath:application-dev.yml"})
@EnableJpaRepositories(
    basePackages = "com.waterleak.dao.reporting",
    entityManagerFactoryRef = "reportingEntityManager",
    transactionManagerRef = "reportingTransactionManager"
)
@Profile("dev")
public class DevPersistenceReportingConfiguration {
  @Autowired
  private Environment env;

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean reportingEntityManager() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(userDataSource());
    em.setPackagesToScan(new String[]{"com.waterleak.model.reporting"});
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    em.setJpaPropertyMap(properties);
    return em;
  }

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource userDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean
  public PlatformTransactionManager reportingTransactionManager() {
    JpaTransactionManager transactionManager
        = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        reportingEntityManager().getObject());
    return transactionManager;
  }
}
