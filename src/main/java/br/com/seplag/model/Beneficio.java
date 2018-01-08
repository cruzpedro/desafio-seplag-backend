package br.com.seplag.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Classe responsável por representar um BeneficioRestEndPoint dentro do sistema.
 */
@Entity
@Table(name = "TB_BENEFICIO")
public class Beneficio extends BaseEntity<Long> {

    @ManyToOne
    @JsonProperty("tramitacao")
    @NotNull(message = "{beneficio.tramitacao.notnull}")
    private Tramitacao tramitacao;

    @ManyToOne
    @JsonProperty("categoria")
    @NotNull(message = "{beneficio.categoria.notnull}")
    private Categoria categoria;

    @ManyToOne
    @JsonProperty("usuario")
    @NotNull(message = "{beneficio.usuario.notnull}")
    private Usuario usuario;

    @JsonProperty("arquivo")
    @NotNull(message = "{beneficio.arquivo.notnull}")
    private String arquivo;

    public Tramitacao getTramitacao() {
        return tramitacao;
    }

    public void setTramitacao(Tramitacao tramitacao) {
        this.tramitacao = tramitacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Beneficio beneficio = (Beneficio) o;
        return Objects.equals(tramitacao, beneficio.tramitacao) &&
                Objects.equals(categoria, beneficio.categoria) &&
                Objects.equals(usuario, beneficio.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tramitacao, categoria, usuario);
    }
}
