package com.registerparty.controller.rest;

import com.registerparty.payload.Response;
import com.registerparty.repository.KecamatanRepository;
import com.registerparty.repository.KelurahanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kelurahan")
public class KelurahanController {

    @Autowired
    KelurahanRepository kelurahanRepository;

    @GetMapping("/find-all/{idKecamatan}")
    public Response<Object> findAll(@PathVariable(value = "idKecamatan") int idKecamatan) {
        Response<Object> response = new Response<>();
        response.setSuccess("Successfully read data!", kelurahanRepository.findAllByIdKecamatan(idKecamatan));
        return response;
    }

}
