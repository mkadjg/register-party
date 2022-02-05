package com.registerparty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kelurahan")
public class Kelurahan {

    public Kelurahan(int idKelurahan) {
        this.idKelurahan = idKelurahan;
    }

    @Id
    @Column(name = "id_kelurahan", nullable = false, unique = true)
    public int idKelurahan;

    @Column(name = "nama_kelurahan")
    public String namaKelurahan;

    @ManyToOne
    @JoinColumn(name = "id_kecamatan", referencedColumnName = "id_kecamatan")
    private Kecamatan kecamatan;

}
