package br.com.seplag.repository;

import br.com.seplag.model.Categoria;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public abstract class CategoriaRepository extends AbstractEntityRepository<Categoria, Long> {
}
