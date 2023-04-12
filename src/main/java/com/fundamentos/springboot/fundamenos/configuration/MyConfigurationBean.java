package com.fundamentos.springboot.fundamenos.configuration;

import com.fundamentos.springboot.fundamenos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration//Anotacion para indicarle a spring que esta clase va a ser la configuracion de nuestro componente
public class MyConfigurationBean {


    @Bean // produce un objeto y lo pone a disposición de otras partes de la aplicación que dependen de él
    public MyBean beanOperation(){
        // retorna la instancia de nuestra clase que implementa MyBean
        return new MyBean2Implement();
    }
    // si nosotros tenemos otra version de esta nueva implementacion
    // en la que estemos agregando una nueva funcionalidad simplemente la llamamos en nuestro metodo
    //public MyBean beanOperation() para este caso se va a sobreescribir
    /*
     public MyBean beanOperation(){
        // retorna la instancia de nuestra clase que implementa MyBean
        return new MyBeanImplement();
    }
     */
    @Bean
    public MyOperation beanOperationOperation(){

        return new MyOperationImplement();
    }
    //Se realiza la configuracion de nuestra dependencia
    //dentro llamamos la otra dependencia que inyectamos Myoperation
    @Bean
    public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation){

        return new MyBeanWithDependencyImplement(myOperation);
    }

}
