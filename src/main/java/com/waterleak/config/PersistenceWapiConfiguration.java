package com.waterleak.config;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({"classpath:application-${spring.profiles.active}.yml"})
@EnableJpaRepositories(
    basePackages = "com.waterleak.dao.wapi",
    entityManagerFactoryRef = "wapiEntityManager",
    transactionManagerRef = "wapiTransactionManager"
)
@Profile("!dev")
public class PersistenceWapiConfiguration {
  @Autowired
  private Environment env;

  @Bean
  public LocalContainerEntityManagerFactoryBean wapiEntityManager() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(productDataSource());
    em.setPackagesToScan(new String[]{"com.waterleak.model.wapi"});
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    em.setJpaPropertyMap(properties);
    return em;
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.second-datasource")
  public DataSource productDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public PlatformTransactionManager wapiTransactionManager() {
    JpaTransactionManager transactionManager
        = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        wapiEntityManager().getObject());
    return transactionManager;
  }
}
