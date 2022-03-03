package com.registerparty.controller.rest;

import com.registerparty.dto.AnggotaDto;
import com.registerparty.model.Anggota;
import com.registerparty.payload.AnggotaPayload;
import com.registerparty.payload.Response;
import com.registerparty.repository.AnggotaRepository;
import com.registerparty.service.AnggotaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/anggota")
public class AnggotaController {

    @Autowired
    AnggotaService anggotaService;

    @Autowired
    AnggotaRepository anggotaRepository;
    
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/find-all")
    public Object findAll(@RequestParam("nama") String nama,
                          @RequestParam("nik") String nik,
                          @RequestParam("nomorIndukAnggota") String nomorIndukAnggota,
                          @RequestParam("email") String email,
                          @RequestParam("idKecamatan") int idKecamatan,
                          @RequestParam("idKelurahan") int idKelurahan
                          ) {
        Response<Object> response;
        response = anggotaService.findAllDatatable(nama, nik, nomorIndukAnggota, email, idKecamatan, idKelurahan);
        if (response.getRc().equals("00")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/detail")
    public Object detail(@RequestParam("nik") String nik) throws SQLException {
        Response<Object> response = new Response<>();
        Optional<Anggota> anggota = anggotaRepository.findByNik(nik);
        if (anggota.isPresent()) {
            AnggotaDto anggotaDto = modelMapper.map(anggota.get(), AnggotaDto.class);
            if (anggota.get().getFotoCloseup() != null) {
                anggotaDto.setFotoCloseup(anggota.get().getFotoCloseup().getBytes(1l, (int)anggota.get().getFotoCloseup().length()));
            }
            if (anggota.get().getFotoKtp() != null) {
                anggotaDto.setFotoKtp(anggota.get().getFotoKtp().getBytes(1l, (int)anggota.get().getFotoKtp().length()));
            }
            response.setSuccess("Berhasil", anggotaDto);
        } else {
            response.setError("99", "NIK belum terdaftar sebagai anggota");
        }
        return response;
    }

    @PostMapping("/save")
    public Object save(@RequestBody AnggotaPayload payload) {
        Response<Object> response = anggotaService.save(payload);
        if (response.getRc().equals("00")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/upload-ktp/{id}")
    public Object uploadKtp(@RequestParam("foto") MultipartFile file,
                            @PathVariable("id") String idAnggota) {
        Response response = new Response();
        Anggota anggota = anggotaRepository.findById(idAnggota).orElse(null);
        if (anggota != null) {
            response = anggotaService.savePhoto(file, anggota, 1);
            if (response.getRc().equals("00")) {
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } else {
            response.setError("Id Anggota tidak ditemukan!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/upload-closeup/{id}")
    public Object uploadCloseup(@RequestParam("foto") MultipartFile file,
                                @PathVariable("id") String idAnggota) {
        Response response = new Response();
        Anggota anggota = anggotaRepository.findById(idAnggota).orElse(null);
        if (anggota != null) {
            response = anggotaService.savePhoto(file, anggota, 2);
            if (response.getRc().equals("00")) {
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } else {
            response.setError("Id Anggota tidak ditemukan!");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
