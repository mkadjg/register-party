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
@Table(name = "pekerjaan")
public class Pekerjaan {

    public Pekerjaan(int idPekerjaan) {
        this.idPekerjaan = idPekerjaan;
    }

    @Id
    @Column(name = "id_pekerjaan", nullable = false, unique = true)
    public int idPekerjaan;

    @Column(name = "nama_pekerjaan")
    public String namaPekerjaan;

}
