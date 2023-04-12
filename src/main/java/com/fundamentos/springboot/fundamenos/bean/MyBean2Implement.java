package com.fundamentos.springboot.fundamenos.bean;

//Se crea clase para implementar los metodos de la interfaz de nuestro componente MyBean
public class MyBean2Implement implements  MyBean{

    //
    @Override
    public void print() {
        System.out.println("hola desde mi propia implementacion 2");
    }
}
