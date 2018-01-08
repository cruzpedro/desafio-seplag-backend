package br.com.seplag.repository;

import br.com.seplag.model.GeneralSetup;
import br.com.seplag.model.GeneralSetupEnum;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

/**
 * Interface responsável por definir os métodos do repositório para a entidade que representa uma Localização de Via de trânsito.
 */
@Repository
public abstract class GeneralSetupRepository extends AbstractEntityRepository<GeneralSetup, Long> {

    public abstract List<GeneralSetup> findByValue(String value);

    public abstract GeneralSetup findByGeneralSetupEnum(GeneralSetupEnum generalSetupEnum);
}

