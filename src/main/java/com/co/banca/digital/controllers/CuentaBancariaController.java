package com.co.banca.digital.controllers;

import com.co.banca.digital.dtos.CuentaBancariaDTO;
import com.co.banca.digital.dtos.HistorialCuentaDTO;
import com.co.banca.digital.dtos.OperacionCuentaDTO;
import com.co.banca.digital.exceptions.CuentaBancariaNotFoundException;
import com.co.banca.digital.services.CuentaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/vi/cuentas")
public class CuentaBancariaController {

    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @GetMapping("/{cuentaId}")
    public CuentaBancariaDTO listarDatosDeUnaCuentaBancaria(@PathVariable String cuentaId) throws CuentaBancariaNotFoundException {
        return cuentaBancariaService.getCuentaBancaria(cuentaId);
    }

    @GetMapping("")
    public List<CuentaBancariaDTO> listarCuentasBancarias(){
        return cuentaBancariaService.listCuentasBancarias();
    }

    @GetMapping("/{cuentaId}/operaciones")
    public List<OperacionCuentaDTO> listHistorialDeCuentas(@PathVariable String cuentaId){
        return cuentaBancariaService.listHistorialDeCuentas(cuentaId);
    }

    @GetMapping("/{cuentaId}/pageOperaciones")
    public HistorialCuentaDTO listHistorialDeLaCuentaPaginado(@PathVariable String cuentaId, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) throws CuentaBancariaNotFoundException {
        return cuentaBancariaService.getHistorialCuenta(cuentaId, page, size);
    }

}
