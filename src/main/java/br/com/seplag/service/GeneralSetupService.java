package br.com.seplag.service;

import br.com.seplag.model.GeneralSetup;
import br.com.seplag.model.GeneralSetupEnum;
import br.com.seplag.repository.GeneralSetupRepository;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Transactional
public class GeneralSetupService extends BaseService<GeneralSetup, Long>{

    @Inject
    private GeneralSetupRepository repository;

    public List<GeneralSetup> findByValue(String value) {
        return Optional.of(repository.findByValue(value)).orElseThrow(NoResultException::new);
    }

    public GeneralSetup findByKey(GeneralSetupEnum key) {
        return Optional.of(repository.findByGeneralSetupEnum(key)).orElseThrow(NoResultException::new);
    }

    @Override
    protected AbstractEntityRepository<GeneralSetup, Long> getRepository() {
        return repository;
    }
}
