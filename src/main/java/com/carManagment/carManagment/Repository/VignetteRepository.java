package com.carManagment.carManagment.Repository;

import com.carManagment.carManagment.Entitys.Vignette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VignetteRepository extends JpaRepository<Vignette, Long> {
}