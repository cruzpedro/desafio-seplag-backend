package br.com.seplag.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Classe responsável por representar uma tramitacao dentro do sistema.
 */
@Entity
@Table(name = "TB_TRAMITACAO")
public class Tramitacao extends BaseEntity<Long> {

    @JsonProperty("nome")
    @NotNull(message = "{tramitacao.nome.notnull}")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tramitacao that = (Tramitacao) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), nome);
    }
}
