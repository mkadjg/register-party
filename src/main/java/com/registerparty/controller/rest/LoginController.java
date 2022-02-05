package com.registerparty.controller.rest;

import com.registerparty.payload.LoginPayload;
import com.registerparty.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/")
    public Object login(@RequestBody LoginPayload payload) {
        return loginService.login(payload);
    }

}
