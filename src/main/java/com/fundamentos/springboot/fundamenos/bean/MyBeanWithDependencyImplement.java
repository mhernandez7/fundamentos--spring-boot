package com.fundamentos.springboot.fundamenos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

//clase que implementa nuestra interfaz
public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    //permite raelizar capturas sobre los metodos
    private Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
    //A su vez se inyecta nuestra otra DEpendencia (MyOperation)
   private MyOperation myOperation;

   //Como inyectamos otra dependencia tenemos que generar el constructor de esa interfaz para poder
    //instanciar sus objetos y hacer uso de sus metodos
    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    //dentro del metodo de la interfaz hacemos uso de su metodo de impresion
    //adicional hacemos uso de la dependencia que inyectamos en nuestra dependencia principal
    //la cual trae una suma que viene instanciada desde la interfaz MyOperation
    @Override
    public void printWithDependency() {
        LOGGER.info("hemos ingresado al metodo printWtihDependency");
        int numero = 1;
        LOGGER.debug("el numero de la dependencia es: "+numero);
        System.out.println(myOperation.sum(numero));
        System.out.println("hola desde la ipmlementacin de un bean con dependencia");
    }
}
