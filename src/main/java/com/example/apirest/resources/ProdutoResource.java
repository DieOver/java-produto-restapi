package com.example.apirest.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.apirest.models.ProdutoDTO;
import com.example.apirest.services.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import javassist.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "Produtos")
public class ProdutoResource {

    @Autowired
    ProdutoService produtoService;
    
    @ApiOperation(value = "Retorna uma lista de Produtos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma lista de produtos"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoDTO>> listaProdutos() {
        try {
            List<ProdutoDTO> produtosDTO = produtoService.index();
            return ResponseEntity.ok().body(produtosDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
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
            return ResponseEntity.ok().body(produtoDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
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
            return ResponseEntity.ok().body("Adicionado com sucesso.");
        } catch (BadRequest e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @ApiOperation(value = "Atualiza um Produto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Atualiza um produto"),
        @ApiResponse(code = 404, message = "Produto não encontrado"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/produto")
    public ResponseEntity<String> updateProduto(@RequestBody @Validated ProdutoDTO produtoDTO) {
        try {
            produtoService.update(produtoDTO);
            return ResponseEntity.ok().body("Atualizado com sucesso.");
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BadRequest e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @ApiOperation(value = "Deleta um Produto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Deleta um produto"),
        @ApiResponse(code = 404, message = "Produto não encontrado"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/produto")
    public ResponseEntity<String> deleteProduto(@RequestBody @Validated ProdutoDTO produtoDTO) {
        try {
            produtoService.delete(produtoDTO);
            return ResponseEntity.ok().body("Deletado com sucesso.");
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BadRequest e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
