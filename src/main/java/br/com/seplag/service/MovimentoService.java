package br.com.seplag.service;

import br.com.seplag.model.Movimento;
import br.com.seplag.repository.MovimentoRepository;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.inject.Inject;

@Transactional
public class MovimentoService extends BaseService<Movimento, Long> {

    @Inject
    private MovimentoRepository movimentoRepository;

    @Override
    protected AbstractEntityRepository<Movimento, Long> getRepository() {
        return movimentoRepository;
    }
}
