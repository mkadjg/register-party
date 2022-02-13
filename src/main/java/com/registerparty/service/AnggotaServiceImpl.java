package com.registerparty.service;

import com.registerparty.model.*;
import com.registerparty.payload.AnggotaPayload;
import com.registerparty.payload.Response;
import com.registerparty.repository.*;
import com.registerparty.utility.HashUtil;
import com.registerparty.utility.IdAnggotaGenerator;
import com.registerparty.utility.NomorIndukAnggotaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AnggotaServiceImpl implements AnggotaService {

    AtomicInteger noUrut = new AtomicInteger();

    @Autowired
    AnggotaRepository anggotaRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    PekerjaanRepository pekerjaanRepository;

    @Autowired
    PendidikanRepository pendidikanRepository;

    @Autowired
    KecamatanRepository kecamatanRepository;

    @Autowired
    KelurahanRepository kelurahanRepository;


    @Override
    public Response<Object> save(AnggotaPayload payload) {
        Response<Object> response = new Response<>();
        try {
            Anggota anggota = new Anggota();
            if (payload.getIdAnggota() != null && payload.getIdAnggota() != null) {
                anggota = anggotaRepository.findById(payload.getIdAnggota()).orElse(null);
            } else {
                anggota.setIdAnggota(IdAnggotaGenerator.generate());
            }
            assert anggota != null;
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

    @Override
    public Response<Object> findAllDatatable(String namaLengkap, String nik, String nomorIndukAnggota, String email, int idKecamatan, int idKelurahan) {
        Response<Object> response = new Response<>();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Anggota> query = builder.createQuery(Anggota.class);
            Root<Anggota> root = query.from(Anggota.class);

            List<Predicate> predicates = new ArrayList<>();
            if (namaLengkap != null && !namaLengkap.equals("")) {
                predicates.add(builder.equal(root.get("namaAnggota"), namaLengkap));
            }

            if (nik != null && !nik.equals("")) {
                predicates.add(builder.equal(root.get("nik"), nik));
            }

            if (nomorIndukAnggota != null && !nomorIndukAnggota.equals("")) {
                predicates.add(builder.equal(root.get("nomorIndukAnggota"), nomorIndukAnggota));
            }

            if (email != null && !email.equals("")) {
                predicates.add(builder.equal(root.get("email"), email));
            }

            Kecamatan kecamatan = kecamatanRepository.findById(idKecamatan).orElse(null);
            if (kecamatan != null) {
                predicates.add(builder.equal(root.get("kecamatan"), kecamatan));
                kelurahanRepository.findById(idKelurahan).ifPresent(kelurahan -> predicates.add(builder.equal(root.get("kelurahan"), kelurahan)));
            }

            query.where(predicates.toArray(new Predicate[0]));
            response.setSuccess(entityManager.createQuery(query.select(root)).getResultList());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.setError("Internal Service Error!");
        }
        return null;
    }

}
