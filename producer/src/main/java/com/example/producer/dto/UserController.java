package com.example.producer.dto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.example.producer.config.RabbitMqSender;

@RestController
@RequestMapping(value = "/api/v1/")
public class UserController {

	
	    private RabbitMqSender rabbitMqSender;
	    @Autowired
	    public UserController(RabbitMqSender rabbitMqSender) {
	        this.rabbitMqSender = rabbitMqSender;
	    }
	    @Value("${app.message}")
	    private String message;
	    
	    
	    @PostMapping(value = "user")
	    public String publishUserDetails(@RequestBody com.example.producer.dto.User user) {
	        rabbitMqSender.send(user);
	        return message;
	    }
	}
	
