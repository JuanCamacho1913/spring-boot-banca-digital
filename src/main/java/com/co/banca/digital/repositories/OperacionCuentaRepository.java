package com.co.banca.digital.repositories;

import com.co.banca.digital.models.OperacionCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacionCuentaRepository extends JpaRepository<OperacionCuenta, Long> {
}
