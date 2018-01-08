package br.com.seplag.service;

import br.com.seplag.model.Tramitacao;
import br.com.seplag.repository.TramitacaoRepository;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.inject.Inject;

@Transactional
public class TramitacaoService extends BaseService<Tramitacao, Long>{

    @Inject
    private TramitacaoRepository tramitacaoRepository;

    @Override
    protected AbstractEntityRepository<Tramitacao, Long> getRepository() {
        return tramitacaoRepository;
    }
}
