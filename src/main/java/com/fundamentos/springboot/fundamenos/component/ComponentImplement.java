package com.fundamentos.springboot.fundamenos.component;

import org.springframework.stereotype.Component;

@Component// se denota esta anotacion la cual es muy generica e indica que esta es una clase componente
public class ComponentImplement implements  ComponentDependency{
    @Override // se implementan los metodos de la nuestra dependencia "ComponentDependency"
    public void saludar() {
        System.out.println("Holii");
    }
}
