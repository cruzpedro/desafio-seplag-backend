package br.com.seplag.service;

import br.com.seplag.model.Usuario;
import br.com.seplag.repository.UsuarioRepository;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.inject.Inject;

@Transactional
public class UsuarioService extends BaseService<Usuario, Long>{

    @Inject
    private UsuarioRepository usuarioRepository;

    @Override
    protected AbstractEntityRepository<Usuario, Long> getRepository() {
        return usuarioRepository;
    }
}
