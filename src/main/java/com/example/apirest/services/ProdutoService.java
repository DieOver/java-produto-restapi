package com.example.apirest.services;

import java.util.List;

import com.example.apirest.models.ProdutoDTO;

import javassist.NotFoundException;

public interface ProdutoService {

    public abstract List<ProdutoDTO> index();
    public abstract ProdutoDTO find(Long id) throws NotFoundException;
    public abstract void store(ProdutoDTO produtoDTO);
    public abstract void update(ProdutoDTO produtoDTO) throws NotFoundException;
    public abstract void delete(ProdutoDTO produtoDTO) throws NotFoundException;

}
