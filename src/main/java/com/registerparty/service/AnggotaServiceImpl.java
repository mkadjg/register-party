package com.registerparty.service;

import com.registerparty.model.*;
import com.registerparty.payload.AnggotaPayload;
import com.registerparty.payload.Response;
import com.registerparty.repository.AnggotaRepository;
import com.registerparty.utility.HashUtil;
import com.registerparty.utility.IdAnggotaGenerator;
import com.registerparty.utility.NomorIndukAnggotaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AnggotaServiceImpl implements AnggotaService {

    AtomicInteger noUrut = new AtomicInteger();

    @Autowired
    AnggotaRepository anggotaRepository;

    @Override
    public Response<Object> save(AnggotaPayload payload) {
        Response<Object> response = new Response<>();
        try {
            Anggota anggota = new Anggota();
            anggota.setIdAnggota(IdAnggotaGenerator.generate());
            anggota.setNamaAnggota(payload.getNamaAnggota());
            anggota.setAlamat(payload.getAlamat());
            anggota.setEmail(payload.getEmail());
            anggota.setNik(payload.getNik());
            anggota.setNoHandphone(payload.getNoHandphone());
            anggota.setTempatLahir(payload.getTempatLahir());
            Integer noUrut = anggotaRepository.getNoUrut();
            anggota.setNomorUrutAnggota(noUrut == null ? 1 : noUrut + 1);
            anggota.setTanggalLahir(payload.getTanggalLahir());
            anggota.setTanggalDaftar(payload.getTanggalDaftar());
            anggota.setPassword(HashUtil.SHA_256.digestAsHex(payload.getPassword()));
            anggota.setTanggalDaftar(new Date());
            anggota.setNomorIndukAnggota(NomorIndukAnggotaGenerator.generate(noUrut == null ? 1 : noUrut + 1, payload.getTanggalLahir()));

            anggota.setJenisKelamin(new JenisKelamin(payload.getIdJenisKelamin()));
            anggota.setStatusPerkawinan(new StatusPerkawinan(payload.getIdStatusPerkawinan()));
            anggota.setKecamatan(new Kecamatan(payload.getIdKecamatan()));
            anggota.setKelurahan(new Kelurahan(payload.getIdKelurahan()));
            anggota.setPendidikan(new Pendidikan(payload.getIdPendidikan()));
            anggota.setPekerjaan(new Pekerjaan(payload.getIdPekerjaan()));

            Anggota result = anggotaRepository.save(anggota);
            response.setSuccess("Berhasil", result);
        } catch (Exception e) {
            e.printStackTrace();
            response.setError("Internal Server Error!");
        }
        return response;
    }

    @Override
    public Response<Object> savePhoto(MultipartFile file, Anggota anggota, int idPhoto) {
        Response<Object> response = new Response<>();
        try {
            byte[] fileByte = file.getBytes();
            Blob blob = new javax.sql.rowset.serial.SerialBlob(fileByte);
            switch (idPhoto) {
                case 1 : {
                    anggota.setFotoKtp(blob);
                    break;
                }
                case 2 : {
                    anggota.setFotoCloseup(blob);
                }
            }
            anggotaRepository.save(anggota);
            response.setSuccess("Upload foto berhasil!", null);
        } catch (Exception e) {
            e.printStackTrace();
            response.setError("Internal Service Error!");
        }
        return response;
    }

}
