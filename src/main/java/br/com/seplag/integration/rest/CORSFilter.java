package br.com.seplag.integration.rest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Classe reponsável por prover HEADERS expecíficos ao tratamento do CORS.
 *
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

    /**
     * Método que adiciona os HEADERS pertinentes a liberação da requisição.
     *
     * @param requestContext  O contexto a qual pertence a requisição efetuada.
     * @param responseContext O contexto a qual pertence a resposta a ser enviada.
     * @throws IOException Uma excessão caso ocorra algum problema de I/O no servidor.
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
}
