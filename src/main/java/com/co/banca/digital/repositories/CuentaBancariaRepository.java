package com.co.banca.digital.repositories;

import com.co.banca.digital.models.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, String> {
}
