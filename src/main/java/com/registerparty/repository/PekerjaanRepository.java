package com.registerparty.repository;

import com.registerparty.model.Pekerjaan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PekerjaanRepository extends JpaRepository<Pekerjaan, Integer> {
}
