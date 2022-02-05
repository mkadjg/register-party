package com.registerparty.controller.rest;

import com.registerparty.payload.Response;
import com.registerparty.repository.KecamatanRepository;
import com.registerparty.repository.PekerjaanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pekerjaan")
public class PekerjaanController {

    @Autowired
    PekerjaanRepository pekerjaanRepository;

    @GetMapping("/find-all")
    public Response<Object> findAll() {
        Response<Object> response = new Response<>();
        response.setSuccess("Successfully read data!", pekerjaanRepository.findAll());
        return response;
    }

}
