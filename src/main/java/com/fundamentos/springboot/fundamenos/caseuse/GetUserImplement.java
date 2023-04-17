package com.fundamentos.springboot.fundamenos.caseuse;

import com.fundamentos.springboot.fundamenos.entity.User;
import com.fundamentos.springboot.fundamenos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser{

    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
