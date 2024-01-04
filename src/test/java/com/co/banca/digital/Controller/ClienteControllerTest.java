package com.co.banca.digital.Controller;

import com.co.banca.digital.controllers.ClienteController;
import com.co.banca.digital.dtos.ClienteDTO;
import com.co.banca.digital.services.CuentaBancariaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CuentaBancariaService cuentaBancariaService;

    ClienteDTO clientDto01 = new ClienteDTO();

    @Test
    @DisplayName("Listado de clientes")
    void testListClientes() throws Exception {
        List<ClienteDTO> clienteDTOS = new ArrayList<>(Arrays.asList(clientDto01));
        clientDto01.setNombre("jose");
        clientDto01.setId(123l);
        clientDto01.setGmail("jose.com");
        Mockito.when(cuentaBancariaService.listClientes()).thenReturn(clienteDTOS);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))).andReturn();
    }


    @Test
    @DisplayName("Listado de un solo cliente")
    void testListClientesPorId() throws Exception {
        clientDto01.setNombre("juan");
        clientDto01.setId(1l);
        clientDto01.setGmail("juan.com");
        Mockito.when(cuentaBancariaService.getCliente(clientDto01.getId())).thenReturn(clientDto01);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.nombre", is("juan")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.gmail", is("juan.com")))
                .andReturn();
    }

    @Test
    @DisplayName("Crear un cliente")
    void testSaveCliente() throws Exception {
        clientDto01.setId(2l);
        clientDto01.setNombre("samuel");
        clientDto01.setGmail("samuel.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(clientDto01);
        Mockito.when(cuentaBancariaService.saveCliente(clientDto01)).thenReturn(clientDto01);
        MvcResult a = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                 .andReturn();
        System.out.println(a.getResponse().getContentAsString());
        }

    @Test
    @DisplayName("Eliminar cliente")
    void testEliminarConExito() throws Exception{
        Mockito.when(cuentaBancariaService.getCliente(clientDto01.getId())).thenReturn(clientDto01);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/clientes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}


