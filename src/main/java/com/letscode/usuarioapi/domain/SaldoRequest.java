package com.letscode.usuarioapi.domain;

import lombok.Data;

@Data
public class SaldoRequest {

    private String idUsuario;
    private Double valor;
}
