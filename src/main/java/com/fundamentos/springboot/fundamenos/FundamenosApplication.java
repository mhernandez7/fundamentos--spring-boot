package com.fundamentos.springboot.fundamenos;

import com.fundamentos.springboot.fundamenos.bean.MyBean;
import com.fundamentos.springboot.fundamenos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamenos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamenos.component.ComponentDependency;
import com.fundamentos.springboot.fundamenos.entity.User;
import com.fundamentos.springboot.fundamenos.pojo.UserPojo;
import com.fundamentos.springboot.fundamenos.repository.UserRepository;
import com.fundamentos.springboot.fundamenos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

//implementamos la dependencia CommandLineRunner la cual nos va a permitir ejecutar nuestros metodos
@SpringBootApplication
public class FundamenosApplication implements CommandLineRunner {

	// se crea instancia la cual nos va a permitir la captura de errores
	private final Log LOGGER = LogFactory.getLog(FundamenosApplication.class);

	//como creamos nuestra propia dependencia inyectamos nuestro atributo para hacer uso de sus metodos
	private ComponentDependency componentDependency;
	//se inyecta nuestra dependencia
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;


	//Se crea el constructor para hacer uso de nuestras dependencias
	//@Qualifer nos permite definr que dependencia vamos a utilizar
	public FundamenosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency
			, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
								 UserPojo userPojo, UserRepository userRepository, UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamenosApplication.class, args);
	}

	//Metodo que se implementa al heredar de CommandLineRunner
	//Ejecutara la dependencia que definimos que iba a utilizar nuestro programa
	//Corre nuestro programa en consola y viene de la implementacion  implements CommandLineRunner
	@Override
	public void run(String... args) throws Exception {

		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional(){

		User test1 = new User("Test1Transactional1", "TestTransactional1@domain.com",LocalDate.now());
		User test2 = new User("Test2Transactional1", "Test2Transactional1@domain.com",LocalDate.now());
		User test3 = new User("Test3Transactional1", "Test3Transactional1@domain.com",LocalDate.now());
		User test4 = new User("Test4Transactional1", "TestT4ransactional1@domain.com",LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Exeption dentro del metodo transaccional ");
		}


		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Ususario dentro de transactional: "+user));
	}

	private void getInformationJpqlFromUser(){
		//Obtiene usuario por email
		LOGGER.info("El usuario con el metododo get by email es: "+userRepository.findByUserEmail("daniela@domain.com")
				.orElseThrow(() -> new RuntimeException("no se encuentra email")));

		//obtiene usuarios por nombre y ordena por id con las propiedades de "Sort"
		userRepository.findAndSort("user", Sort.by("id").descending()).stream()
				.forEach(user -> LOGGER.info("Ususario con metodo Sort"+user));

		userRepository.findByName("John").stream()
				.forEach(user -> LOGGER.info("Ususario con query method "+user));

		LOGGER.info("Ususario con querimethod " + userRepository.findByEmailAndName("daniela@domain.com","daniela")
				.orElseThrow(() -> new RuntimeException("usuario no encontrado")));

		//Busqueda de lista por query method de LIKE
		userRepository.findByNameLike("%user%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con LIKE"+ user));

		//Busqueda de lista por query method de Or
		userRepository.findByNameOrEmail(null, "daniela@domain.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con Busqueda Or "+ user));

		userRepository.findByBirthDateBetween(
				LocalDate.of(2021,3,1), LocalDate.of(2021, 07, 29))
				.stream().forEach(user -> LOGGER.info("Busqueda por fecha: "+user));

		userRepository.findByNameLikeOrderByIdDesc("%user%")
				.stream().forEach(user -> LOGGER.info("usuario por name y orden por id "+user));

	}


	private  void saveUsersInDataBase(){
		User user1 = new User("John", "jhon@domain.com", LocalDate.of(2021,3,20));
		User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2021,5,21));
		User user3 = new User("daniela", "daniela@domain.com", LocalDate.of(2021,7,4));
		User user4 = new User("user4", "user4@domain.com", LocalDate.of(2021,7,22));
		User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021,11,24));
		User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021,2,12));
		User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021,3,11));
		User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021,5,10));
		User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021,8,24));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021,1,6));
		List<User> list = Arrays.asList(user1, user2,user3,user4,user5,user6,user7,user8,user9,user10);
		list.stream().forEach(userRepository::save);


	}
	public void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+ "-" +userPojo.getPassword()+"-"+ userPojo.getAge());
		try {
			//error
			int value = 10/0;
			LOGGER.debug("mi valor:" +value);
		}catch (Exception e){
			LOGGER.error("Captura de error al dividir por 0"+ e.getMessage());
		}

	}
}
