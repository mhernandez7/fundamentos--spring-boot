package com.fundamentos.springboot.fundamenos.controller;

import com.fundamentos.springboot.fundamenos.caseuse.GetUser;
import com.fundamentos.springboot.fundamenos.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    //CRUD BAJO ARQUITECTURA REST
    //CREATE, READ, UPDATE, DELETE -> POST, GET, UPDATE, DELETE

    private GetUser getUser;

    public UserRestController(GetUser getUser) {
        this.getUser = getUser;
    }
    //R-> READ
    @GetMapping("/all")
    List<User> get(){
        return getUser.getAll();
    }
}
