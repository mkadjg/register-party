package com.registerparty.dto;

import com.registerparty.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Blob;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnggotaDto {
    private String idAnggota;

    private Integer nomorUrutAnggota;

    private String nomorIndukAnggota;

    private String namaAnggota;

    private String nik;

    private String email;

    private String password;

    private String noHandphone;

    private String tempatLahir;

    private Date tanggalLahir;

    private String alamat;

    private JenisKelamin jenisKelamin;

    private Pendidikan pendidikan;

    private Kecamatan kecamatan;

    private StatusPerkawinan statusPerkawinan;

    private Kelurahan kelurahan;

    private Pekerjaan pekerjaan;

    private byte[] fotoCloseup;

    private byte[] fotoKtp;

    private Date tanggalDaftar;
}
