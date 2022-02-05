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
@Table(name = "jenis_kelamin")
public class JenisKelamin {

    public JenisKelamin(int idJenisKelamin) {
        this.idJenisKelamin = idJenisKelamin;
    }

    @Id
    @Column(name = "id_jenis_kelamin", nullable = false, unique = true)
    public int idJenisKelamin;

    @Column(name = "nama_jenis_kelamin")
    public String namaJenisKelamin;

}
