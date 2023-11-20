package com.co.banca.digital.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistorialCuentaDTO {

    private String cuentaId;

    private double balance;

    private int currentPage;                                            //Pagina actual en la que nos encontramos.

    private int totalPages;

    private int pageSize;                                               //Cantidad de elementos que puede tener cada pagina.

    private List<OperacionCuentaDTO> operacionesCuentaDTOS;

}
