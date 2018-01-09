package br.com.seplag.repository;

import br.com.seplag.model.Beneficio;
import br.com.seplag.model.Movimento;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public abstract class MovimentoRepository extends AbstractEntityRepository<Movimento, Long> {

    public abstract List<Movimento> findByBeneficio(Beneficio beneficio);
}
