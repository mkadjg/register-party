package com.registerparty.repository;

import com.registerparty.model.Kecamatan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KecamatanRepository extends JpaRepository<Kecamatan, Integer> {
}
