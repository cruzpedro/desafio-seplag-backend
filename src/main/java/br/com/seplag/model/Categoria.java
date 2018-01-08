package br.com.seplag.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Classe responsável por representar uma categoria dentro do sistema.
 */
@Entity
@Table(name = "TB_CATEGORIA")
public class Categoria extends BaseEntity<Long> {

    @JsonProperty("descricao")
    @NotNull(message = "{categoria.descricao.notnull}")
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(descricao, categoria.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), descricao);
    }
}
