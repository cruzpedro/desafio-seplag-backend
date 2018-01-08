package br.com.seplag.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Classe responsável por representar um usuário dentro do sistema.
 */
@Entity
@Table(name = "TB_USUARIO")
public class Usuario extends BaseEntity<Long> {

    @JsonProperty("nome")
    @NotNull(message = "{usuario.nome.notnull}")
    private String nome;

    @JsonProperty("cpf")
    @NotNull(message = "{usuario.cpf.notnull}")
    private String cpf;

    @JsonProperty("orgao")
    @NotNull(message = "{usuario.orgao.notnull}")
    private String orgao;

    @JsonProperty("matricula")
    @NotNull(message = "{usuario.matricula.notnull}")
    private Long matricula;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nome, usuario.nome) &&
                Objects.equals(cpf, usuario.cpf) &&
                Objects.equals(matricula, usuario.matricula);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), nome, cpf, matricula);
    }
}
