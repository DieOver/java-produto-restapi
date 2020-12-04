package com.example.apirest.models;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoDTO {

    private Long id;

    private String nome;

    private BigDecimal quantidade;

    private BigDecimal valor;

    public ProdutoDTO() {
    }

    public ProdutoDTO(String nome, BigDecimal quantidade, BigDecimal valor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ProdutoDTO(Long id, String nome, BigDecimal quantidade, BigDecimal valor) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ProdutoDTO id(Long id) {
        this.id = id;
        return this;
    }

    public ProdutoDTO nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ProdutoDTO quantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public ProdutoDTO valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProdutoDTO)) {
            return false;
        }
        ProdutoDTO produtoDTO = (ProdutoDTO) o;
        return Objects.equals(id, produtoDTO.id) && Objects.equals(nome, produtoDTO.nome) && Objects.equals(quantidade, produtoDTO.quantidade) && Objects.equals(valor, produtoDTO.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, quantidade, valor);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            ", valor='" + getValor() + "'" +
            "}";
    }

}