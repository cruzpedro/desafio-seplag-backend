package br.com.seplag.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por converter o erro gerado quando houver alguma violação de regra de negócio,
 * em uma resposta JSON adequada com a mensagem de erro apropriada.
 * <p>
 * Em outras palavras, ela encapsula a mensagem de erro gerada ao tentar realizar alguma operação não permitida,
 * e gera um Respose com o seguinte formato:
 * <p>
 * <code>
 * {error: "mensagem de erro gerada "}
 * </code>
 *
 */
@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

    /**
     * Método responsável por gerar a resposta ao cliente chamador da API com a devida mensagem de erro.
     *
     * @param error A excessão que foi originada.
     * @return Uma resposta ao cliente no formato <strong>JSON</strong> contendo a mensagem de erro.
     */
    @Override
    public Response toResponse(BusinessException error) {
        Map<String, String> map = new HashMap<>();
        map.put("error", error.getMessage());

        return Response.status(Response.Status.BAD_REQUEST).type("application/json;charset=UTF-8").entity(map).build();
    }
}