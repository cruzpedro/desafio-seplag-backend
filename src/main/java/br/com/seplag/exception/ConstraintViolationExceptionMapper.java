package br.com.seplag.exception;

import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por converter todas as excessões provenientes de erros de validações
 * na camada REST da aplicação, em mensagens mais amigáveis para o usuário final.
 *
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ResteasyViolationException> {

    /**
     * Método responsável por converter o erro proveninete da excessão gerada, na mensagem amigável.
     *
     * @param error A excessão que foi lançada durante a etapa de validação dos dados na camada REST.
     * @return Uma resposta HTTP contendo a mensagem de erro já tratada no seguinte fomamto:
     * <code>{error: "MSG DE ERRO"}</code>
     */
    @Override
    public Response toResponse(ResteasyViolationException error) {
        Map<String, String> map = new HashMap<>();
        String parameters = "";
        for(ResteasyConstraintViolation violation: error.getParameterViolations()){
        	parameters += violation.getMessage() + ", ";
        }
        map.put("error", "Ocorreu um erro no "+parameters+"no envio do formulário. Por favor, verificar.");

        return Response.status(Response.Status.BAD_REQUEST).type("application/json;charset=UTF-8").entity(map).build();
    }
}
