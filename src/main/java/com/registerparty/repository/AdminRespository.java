package com.registerparty.repository;

import com.registerparty.model.Admin;
import com.registerparty.model.Anggota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRespository extends JpaRepository<Admin, Integer> {

    @Query("select a from Admin a where a.username =:username")
    Optional<Admin> findByUsername(String username);
}
