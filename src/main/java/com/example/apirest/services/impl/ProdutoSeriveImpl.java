package com.example.apirest.services.impl;

import java.util.List;
import java.util.Optional;

import com.example.apirest.config.ProdutoConfig;
import com.example.apirest.models.ProdutoDTO;
import com.example.apirest.models.ProdutoEntity;
import com.example.apirest.repository.ProdutoRepository;
import com.example.apirest.services.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class ProdutoSeriveImpl implements ProdutoService {

    private static final String NOT_FOUND_MESSAGE = "Não encontrado.";

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoDTO> index() {
        List<ProdutoEntity> produtosEntity = produtoRepository.findAll();
        return ProdutoConfig.toDTOs(produtosEntity);
    }

    @Override
    public ProdutoDTO find(Long id) throws NotFoundException {
        Optional<ProdutoEntity> produtoEntity = produtoRepository.findById(id);
        if (!produtoEntity.isPresent()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }
        return ProdutoConfig.toDTO(produtoEntity.get());
    }

    @Override
    public void store(ProdutoDTO produtoDTO) {
        ProdutoEntity produtoEntity = ProdutoConfig.toEntity(produtoDTO);
        produtoRepository.save(produtoEntity);
    }

    @Override
    public void update(ProdutoDTO produtoDTO) throws NotFoundException {
        Optional<ProdutoEntity> checkProdutoEntity = produtoRepository.findById(produtoDTO.getId());
        if (!checkProdutoEntity.isPresent()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }
        ProdutoEntity produtoEntity = ProdutoConfig.toEntity(produtoDTO);
        produtoRepository.save(produtoEntity);
    }

    @Override
    public void delete(ProdutoDTO produtoDTO) throws NotFoundException {
        Optional<ProdutoEntity> checkProdutoEntity = produtoRepository.findById(produtoDTO.getId());
        if (!checkProdutoEntity.isPresent()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }
        ProdutoEntity produtoEntity = ProdutoConfig.toEntity(produtoDTO);
        produtoRepository.delete(produtoEntity);
    }
    
}
