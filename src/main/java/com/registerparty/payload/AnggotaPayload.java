package com.registerparty.payload;

import com.registerparty.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnggotaPayload {

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

    private Integer idJenisKelamin;

    private Integer idPendidikan;

    private Integer idKecamatan;

    private Integer idStatusPerkawinan;

    private Integer idKelurahan;

    private Integer idPekerjaan;

    private String fotoCloseup;

    private String fotoKtp;

    private Date tanggalDaftar;
}
