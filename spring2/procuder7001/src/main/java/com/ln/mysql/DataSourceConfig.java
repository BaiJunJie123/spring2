/*
package com.ln.mysql;

import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * @author 白俊杰
 * @Date 2019/12/9
 * @Description
 **//*

@Configuration
@ConfigurationProperties(prefix = "")
public class DataSourceConfig  extends AbstractRoutingDataSource {
    @Autowired
    private  DataSourcesType dataSourcesType;
    @ConfigurationProperties(prefix = "spring.datasource.update")
    @Primary
    @Bean
    public DataSource oneDataSource(){
        return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.datasource.select")
    @Bean
    public DataSource twoDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Override
    public void afterPropertiesSet(){
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("select",twoDataSource());
        targetDataSources.put("update",oneDataSource());
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourcesType.getDataType() == null ? "select" : dataSourcesType.getDataType();
    }
}
*/
