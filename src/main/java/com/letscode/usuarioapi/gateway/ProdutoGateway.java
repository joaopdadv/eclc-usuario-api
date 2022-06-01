package com.letscode.usuarioapi.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProdutoGateway {

    private final ProdutoFeignClient produtoFeignClient;

    public ResponseEntity<String> getProduto(Long id){
        return produtoFeignClient.getProduto(id);
    }
}
