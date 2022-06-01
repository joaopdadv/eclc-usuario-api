package com.letscode.usuarioapi.controller;

import com.letscode.usuarioapi.domain.CarrinhoRequest;
import com.letscode.usuarioapi.domain.SaldoRequest;
import com.letscode.usuarioapi.domain.UsuarioEntity;
import com.letscode.usuarioapi.domain.UsuarioRequest;
import com.letscode.usuarioapi.gateway.ProdutoGateway;
import com.letscode.usuarioapi.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ProdutoGateway produtoGateway;

    public UsuarioController(UsuarioService usuarioService, ProdutoGateway produtoGateway) {
        this.usuarioService = usuarioService;
        this.produtoGateway = produtoGateway;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> getAll(){
        List<UsuarioEntity> list = usuarioService.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getById(
            @PathVariable String id
    ){
        UsuarioEntity entity = usuarioService.getById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entity);
    }

    @GetMapping("/carrinho/{id}")
    public ResponseEntity<List<Long>> getCarrinhoById(
        @PathVariable String id
    ){
        UsuarioEntity entity = usuarioService.getById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entity.getCarrinho());
    }

    @PostMapping("/add")
    public @ResponseBody String createUsuario(
            @RequestBody UsuarioRequest request
    ){
        usuarioService.createUsuario(request);

        return "salvo";
    }

    @PutMapping("/carrinho-add")
    public ResponseEntity<UsuarioEntity> addProdutoToCarrinho(
            @RequestBody CarrinhoRequest request
    ){
        Optional<UsuarioEntity> entityOptional = usuarioService.getByIdCarrinho(request);

        if (entityOptional.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .build();
        } else {

            ResponseEntity<String> produto = produtoGateway.getProduto(request.getIdProduto());

            if (!produto.getStatusCode().equals(HttpStatus.OK)){
                return ResponseEntity
                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .build();
            } else {
                List<Long> ids = entityOptional.get().getCarrinho();
                ids.add(request.getIdProduto());
                entityOptional.get().setCarrinho(ids);

                usuarioService.saveRepository(entityOptional.get());

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(entityOptional.get());
            }
        }
    }

    @PatchMapping("/saldo")
    public ResponseEntity<UsuarioEntity> updateSaldo(
            @RequestBody SaldoRequest request
    ){
        Optional<UsuarioEntity> usuarioEntity = usuarioService.getByIdSaldo(request);

        if (usuarioEntity.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .build();
        } else {

            Double saldo = usuarioEntity.get().getSaldo();
            saldo = saldo + request.getValor();
            usuarioEntity.get().setSaldo(saldo);

            usuarioService.saveRepository(usuarioEntity.get());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(usuarioEntity.get());
        }
    }
}
