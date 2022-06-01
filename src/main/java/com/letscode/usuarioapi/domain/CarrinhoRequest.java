package com.letscode.usuarioapi.domain;

import lombok.Data;

@Data
public class CarrinhoRequest {

    private String idUsuario;
    private Long idProduto;
}
