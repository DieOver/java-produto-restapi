package com.example.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apirest.models.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>{

}
