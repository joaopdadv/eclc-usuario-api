package com.letscode.usuarioapi.service;

import com.letscode.usuarioapi.domain.CarrinhoRequest;
import com.letscode.usuarioapi.domain.SaldoRequest;
import com.letscode.usuarioapi.domain.UsuarioEntity;
import com.letscode.usuarioapi.domain.UsuarioRequest;
import com.letscode.usuarioapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioEntity> getAll(){
        return usuarioRepository.findAll();
    }

    public UsuarioEntity getById(String id){
        return usuarioRepository.findById(id).get();
    }

    public void createUsuario(UsuarioRequest request){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNome(request.getNome());

        usuarioRepository.save(entity);
    }

    public Optional<UsuarioEntity> getByIdCarrinho(CarrinhoRequest request){
        return usuarioRepository.findById(request.getIdUsuario());
    }

    public Optional<UsuarioEntity> getByIdSaldo(SaldoRequest request){
        return usuarioRepository.findById(request.getIdUsuario());
    }

    public void saveRepository(UsuarioEntity entity){
        usuarioRepository.save(entity);
    }

}
