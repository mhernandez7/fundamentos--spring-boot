package com.fundamentos.springboot.fundamenos.component;

//Estereotipo de spring Boot que nos indicca que @Component hace parte de spring
import org.springframework.stereotype.Component;

//Se crea clase que va a ser el componente 2
@Component// se denota esta anotacion la cual es muy generica e indica que esta es una clase componente
public class ComponentTwoImplement implements ComponentDependency{
    @Override // se implementan los metodos de la nuestra dependencia "ComponentDependency"
    public void saludar() {
        System.out.println("Hola mundo desde el componene 2");
    }
}
