package com.fundamentos.springboot.fundamenos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @RequestMapping("/saludo")
    @ResponseBody
    public ResponseEntity<String> function(){
        return new ResponseEntity<>(" hola desde controlador", HttpStatus.OK);
    }


}
