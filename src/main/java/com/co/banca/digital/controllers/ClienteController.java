package com.co.banca.digital.controllers;

import com.co.banca.digital.dtos.ClienteDTO;
import com.co.banca.digital.exceptions.ClienteNotFoundException;
import com.co.banca.digital.services.CuentaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/clientes")
public class ClienteController {

    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @GetMapping("")
    public List<ClienteDTO> listarClientes() {
        return cuentaBancariaService.listClientes();
    }

    @GetMapping("/{id}")
    public ClienteDTO listarDatosDelCliente(@PathVariable(name = "id") Long clienteId) throws ClienteNotFoundException {
        return cuentaBancariaService.getCliente(clienteId);
    }

    @PostMapping("")
    public ClienteDTO crearCliente(@RequestBody ClienteDTO clienteDTO) {
        return cuentaBancariaService.saveCliente(clienteDTO);
    }

    @PutMapping("/{clienteId}")
    public ClienteDTO actualizarCliente(@PathVariable Long clienteId, @RequestBody ClienteDTO clienteDTO) {
        clienteDTO.setId(clienteId);
        return cuentaBancariaService.updateCliente(clienteDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        cuentaBancariaService.deleteCliente(id);
    }

    @GetMapping("/search")
    public List<ClienteDTO> buscarClientes(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return cuentaBancariaService.searchClientes("%" + keyword + "%");
    }

}
