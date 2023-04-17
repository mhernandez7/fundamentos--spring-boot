package com.fundamentos.springboot.fundamenos.configuration;

import com.fundamentos.springboot.fundamenos.caseuse.GetUser;
import com.fundamentos.springboot.fundamenos.caseuse.GetUserImplement;
import com.fundamentos.springboot.fundamenos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return  new GetUserImplement(userService);
    }
}
