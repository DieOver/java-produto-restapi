package com.example.apirest.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.apirest.config.ProdutoConfig;
import com.example.apirest.models.ProdutoDTO;
import com.example.apirest.models.ProdutoEntity;
import com.example.apirest.repository.ProdutoRepository;
import com.example.apirest.services.ProdutoService;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import javassist.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest")
public class ProdutoResource {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ProdutoService produtoService;

    private static final String INTERNAL_ERROR_MESSAGE = "Algum erro ocorreu.";
    private static final String BAD_REQUEST_MESSAGE = "Dados inválidos.";
    
    @ApiOperation(value = "Retorna uma lista de Produtos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma lista de produtos"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoDTO>> listaProdutos() {
        try {
            List<ProdutoDTO> produtosDTO = produtoService.index();
            return new ResponseEntity<>(produtosDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE + e.getMessage());
        }
    }

    @ApiOperation(value = "Retorna um Produto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna um produto"),
        @ApiResponse(code = 404, message = "Produto não encontrado"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/produto/{id}")
    public ResponseEntity<ProdutoDTO> getProduto(@PathVariable("id") long id) {
        try {
            ProdutoDTO produtoDTO = produtoService.find(id);
            return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE + e.getMessage());
        }
    }

    @ApiOperation(value = "Adiciona um Produto")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Adiciona um Produto"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/produto")
    public ResponseEntity<String> addProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        try {
            produtoService.store(produtoDTO);
            return new ResponseEntity<>("Adicionado com sucesso.", HttpStatus.OK);
        } catch (BadRequest e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE + e.getMessage());
        }
    }

    @ApiOperation(value = "Atualiza um Produto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Atualiza um produto"),
        @ApiResponse(code = 404, message = "Produto não encontrado"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/produto")
    public ResponseEntity<String> updateProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        try {
            produtoService.update(produtoDTO);
            return new ResponseEntity<>("Atualizado com sucesso.", HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (BadRequest e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE + e.getMessage());
        }
    }

    @ApiOperation(value = "Deleta um Produto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Deleta um produto"),
        @ApiResponse(code = 404, message = "Produto não encontrado"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/produto")
    public ResponseEntity<String> deleteProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        try {
            produtoService.delete(produtoDTO);
            return new ResponseEntity<>("Deletado com sucesso.", HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (BadRequest e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE + e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR_MESSAGE + e.getMessage());
        }
    }

}
