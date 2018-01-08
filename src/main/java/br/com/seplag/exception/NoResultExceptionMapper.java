package br.com.seplag.exception;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por converter o erro gerado quando determinada entidade que foi solicitada
 * ao sistema não puder ser encontrada, em uma resposta JSON adequada com a mensagem de erro apropriada.
 * <p>
 * Em outras palavras, ela encapsula a mensagem de erro gerada ao tentar buscar a entidade, e gera um Respose
 * com o seguinte formato:
 * <p>
 * <code>
 * {error: "mensagem de erro gerada pelo sistema para a busca mal sucedida"}
 * </code>
 *
 */
@Provider
public class NoResultExceptionMapper implements ExceptionMapper<NoResultException> {

    /**
     * Método responsável por gerar a resposta ao cliente chamador da API com a devida mensagem de erro.
     *
     * @param error A excessão que foi originada durante a busca mal sucedida da entidade.
     * @return Uma resposta ao cliente no formato <strong>JSON</strong> contendo a mensagem de erro gerada.
     */
    @Override
    public Response toResponse(NoResultException error) {
        error.printStackTrace();
    	Map<String, String> map = new HashMap<>();
        map.put("error", "Nenhum resultado encontrado.");

        return Response.status(Response.Status.NOT_FOUND).type("application/json;charset=UTF-8").entity(map).build();
    }
}