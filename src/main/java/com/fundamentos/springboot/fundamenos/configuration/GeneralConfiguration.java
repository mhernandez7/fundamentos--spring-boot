package com.fundamentos.springboot.fundamenos.configuration;

import com.fundamentos.springboot.fundamenos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamenos.bean.MyBeanWithPropertiesImplement;
import com.fundamentos.springboot.fundamenos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.validation.annotation.Validated;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:connection.properties")//conecta con connection.properties a la db
@EnableConfigurationProperties(UserPojo.class)//representa a la clase uerpojo como una dependencia
public class GeneralConfiguration {

    @Value("${value.name}")
    private String name;

    @Value("${value.apellido}")
    private String apellido;

    @Value("${value.random}")
    private String random;

    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${driver}")
    private String driver;
    @Value("${username}")
    private String userName;
    @Value("${password}")
    private String password;

    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImplement(name, apellido);
    }

    //Se establece la configuracion de nuestra base de datos directamente sobre la clase
    //y no desde application.properties
    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(userName);
        dataSourceBuilder.password(password);
        return  dataSourceBuilder.build();
    }
}
