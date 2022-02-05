package com.registerparty.controller.rest;

import com.registerparty.payload.Response;
import com.registerparty.repository.StatusPerkawinanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status-perkawinan")
public class StatusPerkawinanController {

    @Autowired
    StatusPerkawinanRepository statusPerkawinanRepository;

    @GetMapping("/find-all")
    public Response<Object> findAll() {
        Response<Object> response = new Response<>();
        response.setSuccess("Successfully read data!", statusPerkawinanRepository.findAll());
        return response;
    }
}
