package com.letscode.usuarioapi.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produto-service")
public interface ProdutoFeignClient {

    @GetMapping(value = "/produto/{produtoId}")
    ResponseEntity<String> getProduto(@PathVariable("produtoId") Long produtoId);

}
