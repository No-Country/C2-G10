package com.appoinment.repository;

import com.appoinment.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    @Query("SELECT p FROM PatientEntity p WHERE p.email = :email")
    PatientEntity findByEmail(@Param("email") String email);
}
