package com.co.banca.digital.repositories;

import com.co.banca.digital.models.OperacionCuenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacionCuentaRepository extends JpaRepository<OperacionCuenta, Long> {

    List<OperacionCuenta> findByCuentaBancariaId(String cuentaId);                                      //Vamos a buscar todas las operaciones que hay en una cuenta bancaria.

    Page<OperacionCuenta> findByCuentaBancariaId(String cuentaId, Pageable pageable);
}
