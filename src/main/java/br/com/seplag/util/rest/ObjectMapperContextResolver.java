package br.com.seplag.util.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Classe reponsável por prover uma instancia configurada o ObjectMapper necessário ao
 * JAXRS para a configuração do mesmo.
 *
 */
@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    @Inject
    private ObjectMapper mapper;

    /**
     * Método responsável por retornar a instancia configurada para ser usada pelo JAXRS.
     *
     * @param type O Tipo do contexto onde será usado a instância.
     * @return A instancia do ObjectMapper já configurada.
     */
    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }

}
