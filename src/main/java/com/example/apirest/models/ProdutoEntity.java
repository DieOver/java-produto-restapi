package com.example.apirest.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "TB_PRODUTO")
public class ProdutoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Código do produto")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @ApiModelProperty(value = "Nome do produto")
    @NotBlank(message = "Nome é necessário")
    @NotNull
    private String nome;

    @ApiModelProperty(value = "Quantidade do produto")
    @NotBlank(message = "Quantidade é necessário")
    @NotNull
    private BigDecimal quantidade;

    @ApiModelProperty(value = "Valor do produto")
    @NotBlank(message = "Valor é necessário")
    @NotNull
    private BigDecimal valor;

    public ProdutoEntity() {
    }

    public ProdutoEntity(String nome, BigDecimal quantidade, BigDecimal valor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ProdutoEntity(long id, String nome, BigDecimal quantidade, BigDecimal valor) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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

    public ProdutoEntity id(long id) {
        this.id = id;
        return this;
    }

    public ProdutoEntity nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ProdutoEntity quantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public ProdutoEntity valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProdutoEntity)) {
            return false;
        }
        ProdutoEntity produto = (ProdutoEntity) o;
        return id == produto.id && Objects.equals(nome, produto.nome) && Objects.equals(quantidade, produto.quantidade) && Objects.equals(valor, produto.valor);
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