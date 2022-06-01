package com.letscode.usuarioapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "usuario")
@Data
public class UsuarioEntity {

    @Id
    private String id;

    private String nome;
    private Double saldo = 0D;

    @Field
    private List<Long> carrinho = new ArrayList<>();
}
