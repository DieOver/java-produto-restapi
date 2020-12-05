package com.example.apirest.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.apirest.config.ProdutoConfig;
import com.example.apirest.models.ProdutoDTO;
import com.example.apirest.models.ProdutoEntity;
import com.example.apirest.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

import javassist.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
public class ProdutoResource {

    @Autowired
    ProdutoRepository produtoRepository;

    private static final String INTERNAL_ERROR_MESSAGE = "Algum erro ocorreu.";
    private static final String NOT_FOUND_MESSAGE = "Não encontrado.";
    private static final String BAD_REQUEST_MESSAGE = "Dados inválidos.";

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoDTO>> listaProdutos() {
        try {
            List<ProdutoEntity> produtosEntity = produtoRepository.findAll();
            List<ProdutoDTO> produtosDTO = ProdutoConfig.toDTOs(produtosEntity);
            return new ResponseEntity<>(produtosDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE + e.getMessage());
        }
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<ProdutoDTO> getProduto(@PathVariable("id") long id) {
        try {
            Optional<ProdutoEntity> produtoEntity = produtoRepository.findById(id);
            if (!produtoEntity.isPresent()) {
                throw new NotFoundException(NOT_FOUND_MESSAGE);
            }
            ProdutoDTO produtoDTO = ProdutoConfig.toDTO(produtoEntity.get());
            return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE + e.getMessage());
        }
    }

    @PostMapping("/produto")
    public ResponseEntity<String> addProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        try {
            ProdutoEntity produtoEntity = ProdutoConfig.toEntity(produtoDTO);
            produtoRepository.save(produtoEntity);
            return new ResponseEntity<>("Adicionado com sucesso.", HttpStatus.OK);
        } catch (BadRequest e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE + e.getMessage());
        }
    }

    @DeleteMapping("/produto")
    public ResponseEntity<String> deleteProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        try {
            Optional<ProdutoEntity> checkProdutoEntity = produtoRepository.findById(produtoDTO.getId());
            if (!checkProdutoEntity.isPresent()) {
                throw new NotFoundException(NOT_FOUND_MESSAGE);
            }
            ProdutoEntity produtoEntity = ProdutoConfig.toEntity(produtoDTO);
            produtoRepository.delete(produtoEntity);
            return new ResponseEntity<>("Deletado com sucesso.", HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (BadRequest e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE + e.getMessage());
        }
    }

    @PutMapping("/produto")
    public ResponseEntity<String> updateProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        try {
            Optional<ProdutoEntity> checkProdutoEntity = produtoRepository.findById(produtoDTO.getId());
            if (!checkProdutoEntity.isPresent()) {
                throw new NotFoundException(NOT_FOUND_MESSAGE);
            }
            ProdutoEntity produtoEntity = ProdutoConfig.toEntity(produtoDTO);
            produtoRepository.save(produtoEntity);
            return new ResponseEntity<>("Atualizado com sucesso.", HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (BadRequest e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE + e.getMessage());
        }
    }

}
