package br.com.seplag.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

/**
 * Classe responsável por representar as configurações gerais do sistema.
 */
@Entity
@Table(name = "TB_GENERALSETUP")
public class GeneralSetup extends BaseEntity<Long> {

    @Enumerated(EnumType.STRING)
    @JsonProperty("key")
    @Column(name="DS_KEY")
    private GeneralSetupEnum generalSetupEnum;

    @JsonProperty("value")
    @Column(name = "DS_VALUE")
    private String value;

    public GeneralSetupEnum getGeneralSetupEnum() {
        return generalSetupEnum;
    }

    public void setGeneralSetupEnum(GeneralSetupEnum generalSetupEnum) {
        this.generalSetupEnum = generalSetupEnum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GeneralSetup that = (GeneralSetup) o;
        return generalSetupEnum == that.generalSetupEnum &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), generalSetupEnum, value);
    }
}
