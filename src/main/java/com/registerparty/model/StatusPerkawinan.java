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
@Table(name = "status_perkawinan")
public class StatusPerkawinan {

    public StatusPerkawinan(int idStatusPerkawinan) {
        this.idStatusPerkawinan = idStatusPerkawinan;
    }

    @Id
    @Column(name = "id_status_perkawinan", nullable = false, unique = true)
    public int idStatusPerkawinan;

    @Column(name = "nama_status_perkawinan")
    public String namaStatusPerkawinan;



}
