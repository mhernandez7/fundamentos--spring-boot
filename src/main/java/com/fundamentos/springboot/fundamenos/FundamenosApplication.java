package com.fundamentos.springboot.fundamenos;

import com.fundamentos.springboot.fundamenos.bean.MyBean;
import com.fundamentos.springboot.fundamenos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamenos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//implementamos la dependencia CommandLineRunner la cual nos va a permitir ejecutar nuestros metodos
@SpringBootApplication
public class FundamenosApplication implements CommandLineRunner {

	//como creamos nuestra propia dependencia inyectamos nuestro atributo para hacer uso de sus metodos
	private ComponentDependency componentDependency;
	//se inyecta nuestra dependencia
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;


	//Se crea el constructor para hacer uso de nuestras dependencias
	//@Qualifer nos permite definr que dependencia vamos a utilizar
	public FundamenosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency
			, MyBean myBean, MyBeanWithDependency myBeanWithDependency){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamenosApplication.class, args);
	}

	//Metodo que se implementa al heredar de CommandLineRunner
	//Ejecutara la dependencia que definimos que iba a utilizar nuestro programa
	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
	}
}
