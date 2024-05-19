package com.carManagment.carManagment.Repository;
import com.carManagment.carManagment.Entitys.CASKO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CASKORepository extends JpaRepository<CASKO, Long> {
}
