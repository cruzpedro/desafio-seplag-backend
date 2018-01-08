package br.com.seplag.repository;

import br.com.seplag.model.Beneficio;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public abstract class BeneficioRepository extends AbstractEntityRepository<Beneficio, Long> {
}
