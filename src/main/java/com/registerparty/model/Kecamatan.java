package com.registerparty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kecamatan")
public class Kecamatan {

    public Kecamatan(int idKecamatan) {
        this.idKecamatan = idKecamatan;
    }

    @Id
    @Column(name = "id_kecamatan", nullable = false, unique = true)
    public int idKecamatan;

    @Column(name = "nama_kecamatan")
    public String namaKecamatan;

}
