package br.com.seplag.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe responsável por representar um movimento dentro do sistema.
 */
@Entity
@Table(name = "TB_MOVIMENTO")
public class Movimento extends BaseEntity<Long> {

    @JsonProperty("data")
    @NotNull(message = "{movimento.data.notnull}")
    private LocalDateTime data;

    @ManyToOne
    @JsonProperty("tramitacaoOrigem")
    @NotNull(message = "{movimento.tramitacaoOrigem.notnull}")
    private Tramitacao tramitacaoOrigem;

    @ManyToOne
    @JsonProperty("tramitacaoDestino")
    @NotNull(message = "{movimento.tramitacaoDestino.notnull}")
    private Tramitacao tramitacaoDestino;

    @ManyToOne
    @JsonProperty("beneficio")
    @NotNull(message = "{movimento.beneficio.notnull}")
    private Beneficio beneficio;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Tramitacao getTramitacaoOrigem() {
        return tramitacaoOrigem;
    }

    public void setTramitacaoOrigem(Tramitacao tramitacaoOrigem) {
        this.tramitacaoOrigem = tramitacaoOrigem;
    }

    public Tramitacao getTramitacaoDestino() {
        return tramitacaoDestino;
    }

    public void setTramitacaoDestino(Tramitacao tramitacaoDestino) {
        this.tramitacaoDestino = tramitacaoDestino;
    }

    public Beneficio getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Beneficio beneficio) {
        this.beneficio = beneficio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Movimento movimento = (Movimento) o;
        return Objects.equals(data, movimento.data) &&
                Objects.equals(tramitacaoOrigem, movimento.tramitacaoOrigem) &&
                Objects.equals(tramitacaoDestino, movimento.tramitacaoDestino) &&
                Objects.equals(beneficio, movimento.beneficio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data, tramitacaoOrigem, tramitacaoDestino, beneficio);
    }
}
