package com.registerparty.service;

import com.registerparty.model.Anggota;
import com.registerparty.payload.AnggotaPayload;
import com.registerparty.payload.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface AnggotaService {
    Response<Object> save(AnggotaPayload payload);
    Response<Object> savePhoto(MultipartFile file, Anggota anggota, int idPhoto);
    Response<Object> findAllDatatable(String namaLengkap, String nik,
                                      String nomorIndukAnggota, String email,
                                      int idKecamatan, int idKelurahan);
}
