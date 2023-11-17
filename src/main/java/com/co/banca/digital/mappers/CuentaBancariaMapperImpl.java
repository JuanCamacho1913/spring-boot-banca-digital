package com.co.banca.digital.mappers;

import com.co.banca.digital.dtos.ClienteDTO;
import com.co.banca.digital.models.Cliente;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CuentaBancariaMapperImpl {

    public ClienteDTO mapearDeCliente(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        BeanUtils.copyProperties(cliente, clienteDTO);
        return clienteDTO;
    }

    public Cliente mapearDeClienteDTO(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        return cliente;
    }

}
