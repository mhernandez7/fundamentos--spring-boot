package com.fundamentos.springboot.fundamenos.service;

import com.fundamentos.springboot.fundamenos.entity.User;
import com.fundamentos.springboot.fundamenos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional//si existe un error a nivel de este metodo
    //La anotacion nos va a permitir realizar un rollback a nivel de la base de datos
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user -> LOG.info("Usuario insertado: "+user))
                .forEach(user -> userRepository.save(user)); // = .forEach(userRepository::save)
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
