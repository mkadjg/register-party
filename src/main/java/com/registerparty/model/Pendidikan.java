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
@Table(name = "pendidikan")
public class Pendidikan {

    public Pendidikan(int idPendidikan) {
        this.idPendidikan = idPendidikan;
    }

    @Id
    @Column(name = "id_pendidikan", nullable = false, unique = true)
    public int idPendidikan;

    @Column(name = "nama_pendidikan")
    public String namaPendidikan;
}
