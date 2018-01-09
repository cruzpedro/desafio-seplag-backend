package br.com.seplag.service;

import br.com.seplag.model.Beneficio;
import br.com.seplag.model.Movimento;
import br.com.seplag.repository.MovimentoRepository;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Transactional
public class MovimentoService extends BaseService<Movimento, Long> {

    @Inject
    private MovimentoRepository movimentoRepository;

    public List<Movimento> buscarPorBeneficio(Beneficio beneficio) {
        return Optional.of(movimentoRepository.findByBeneficio(beneficio)).orElseThrow(NoResultException::new);
    }

    @Override
    protected AbstractEntityRepository<Movimento, Long> getRepository() {
        return movimentoRepository;
    }
}
