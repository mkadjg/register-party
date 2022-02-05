package com.registerparty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "anggota")
public class Anggota {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_anggota")
    private String idAnggota;

    @Column(name = "nomor_urut_anggota")
    private Integer nomorUrutAnggota;

    @Column(name = "nomor_induk_anggota")
    private String nomorIndukAnggota;

    @Column(name = "nama_anggota")
    private String namaAnggota;

    @Column(name = "nik")
    private String nik;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "no_handphone")
    private String noHandphone;

    @Column(name = "tempat_lahir")
    private String tempatLahir;

    @Column(name = "tanggal_lahir")
    private Date tanggalLahir;

    @Column(name = "alamat")
    private String alamat;

    @ManyToOne
    @JoinColumn(name = "id_jenis_kelamin", referencedColumnName = "id_jenis_kelamin")
    private JenisKelamin jenisKelamin;

    @ManyToOne
    @JoinColumn(name = "id_pendidikan", referencedColumnName = "id_pendidikan")
    private Pendidikan pendidikan;

    @ManyToOne
    @JoinColumn(name = "id_kecamatan", referencedColumnName = "id_kecamatan")
    private Kecamatan kecamatan;

    @ManyToOne
    @JoinColumn(name = "id_status_perkawinan", referencedColumnName = "id_status_perkawinan")
    private StatusPerkawinan statusPerkawinan;

    @ManyToOne
    @JoinColumn(name = "id_kelurahan", referencedColumnName = "id_kelurahan")
    private Kelurahan kelurahan;

    @ManyToOne
    @JoinColumn(name = "id_pekerjaan", referencedColumnName = "id_pekerjaan")
    private Pekerjaan pekerjaan;

    @Column(name = "foto_closeup")
    private Blob fotoCloseup;

    @Column(name = "foto_ktp")
    private Blob fotoKtp;

    @Column(name = "tanggal_daftar")
    private Date tanggalDaftar;

}
