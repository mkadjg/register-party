package com.registerparty.repository;

import com.registerparty.model.Kelurahan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KelurahanRepository extends JpaRepository<Kelurahan, Integer> {

    @Query("FROM Kelurahan where kecamatan.idKecamatan =:idKecamatan")
    List<Kelurahan> findAllByIdKecamatan(int idKecamatan);

}
