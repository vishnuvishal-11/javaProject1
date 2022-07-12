//package com.example.dbproject.liquibase;
//
//import liquibase.integration.spring.SpringLiquibase;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.BeanNameAware;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
//import org.springframework.context.ResourceLoaderAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.core.io.ResourceLoader;
//
//import static org.springframework.orm.hibernate5.SessionFactoryUtils.getDataSource;
//
//public class CustomSpringLiquibase implements InitializingBean, BeanNameAware, ResourceLoaderAware {
//
//
//    private SpringLiquibase springLiquibase;
//
//    public CustomSpringLiquibase(SpringLiquibase springLiquibase) {
//        this.springLiquibase = springLiquibase;
//    }
//
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//        springLiquibase.afterPropertiesSet();
//    }
//
//    @Override
//    public void setBeanName(String s) {
//        springLiquibase.setBeanName(s);
//    }
//
//
//
//    @Override
//    public void setResourceLoader(ResourceLoader resourceLoader) {
//        springLiquibase.setResourceLoader(resourceLoader);
//
//    }
//
//    @Bean
//    @DependsOn(value = "entityManagerFactory")
//    public CustomSpringLiquibase liquibase() {
//        LiquibaseProperties liquibaseProperties = liquibaseProperties();
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog(liquibaseProperties.getChangeLog());
//        liquibase.setContexts(liquibaseProperties.getContexts());
//        liquibase.setDataSource(getDataSource((SessionFactory) liquibaseProperties));
//        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
//        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
//        liquibase.setShouldRun(true);
//        liquibase.setLabels(liquibaseProperties.getLabels());
//        liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
//        return new CustomSpringLiquibase(liquibase);
//    }
//
//    @Bean
//    public LiquibaseProperties liquibaseProperties() {
//        return new LiquibaseProperties();
//    }
//}
