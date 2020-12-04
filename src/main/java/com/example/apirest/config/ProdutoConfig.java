package com.example.apirest.config;

import com.example.apirest.models.ProdutoDTO;
import com.example.apirest.models.ProdutoEntity;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class ProdutoConfig {

    private ProdutoConfig() {}

    @Bean
    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static ProdutoEntity convertToEntity(ProdutoDTO produtoDTO) {
        return modelMapper().map(produtoDTO, ProdutoEntity.class);
    }

    public static ProdutoDTO convertToDto(ProdutoEntity produtoEntity) {
        return modelMapper().map(produtoEntity, ProdutoDTO.class);
    }

}
