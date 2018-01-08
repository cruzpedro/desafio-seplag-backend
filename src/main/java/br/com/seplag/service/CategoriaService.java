package br.com.seplag.service;

import br.com.seplag.model.Categoria;
import br.com.seplag.repository.CategoriaRepository;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.inject.Inject;

@Transactional
public class CategoriaService extends BaseService<Categoria, Long> {

    @Inject
    private CategoriaRepository categoriaRepository;

    @Override
    protected AbstractEntityRepository<Categoria, Long> getRepository() {
        return categoriaRepository;
    }
}
