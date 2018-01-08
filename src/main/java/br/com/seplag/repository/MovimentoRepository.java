package br.com.seplag.repository;

import br.com.seplag.model.Movimento;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public abstract class MovimentoRepository extends AbstractEntityRepository<Movimento, Long> {
}
