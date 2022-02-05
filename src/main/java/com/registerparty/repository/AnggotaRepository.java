package com.registerparty.repository;

import com.registerparty.model.Anggota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnggotaRepository extends JpaRepository<Anggota, String> {

    @Query("select max(nomorUrutAnggota) from Anggota")
    Integer getNoUrut();

    @Query("select a from Anggota a where a.nik =:nik")
    Optional<Anggota> findByNik(String nik);

}
