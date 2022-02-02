package com.example.apirest.config;

import java.lang.reflect.Type;
import java.util.List;
import com.example.apirest.models.ProdutoDTO;
import com.example.apirest.models.ProdutoEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Bean;

public class ProdutoConfig {

    public static final String NOT_FOUND_MESSAGE = "Não encontrado.";
    public static final String INTERNAL_ERROR_MESSAGE = "Algum erro ocorreu.";
    public static final String BAD_REQUEST_MESSAGE = "Dados inválidos.";

    private ProdutoConfig() {}

    @Bean
    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static ProdutoEntity toEntity(ProdutoDTO produtoDTO) {
        return modelMapper().map(produtoDTO, ProdutoEntity.class);
    }

    public static List<ProdutoEntity> toEntities(List<ProdutoDTO> produtosEntity) {
        Type listType = new TypeToken<List<ProdutoEntity>>(){}.getType();
        return modelMapper().map(produtosEntity, listType);
    }

    public static ProdutoDTO toDTO(ProdutoEntity produtoEntity) {
        return modelMapper().map(produtoEntity, ProdutoDTO.class);
    }

    public static List<ProdutoDTO> toDTOs(List<ProdutoEntity> produtosEntity) {
        Type listType = new TypeToken<List<ProdutoDTO>>(){}.getType();
        return modelMapper().map(produtosEntity, listType);
    }

}
