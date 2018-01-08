package br.com.seplag.exception;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Classe responsável por mapear os erros gerado pelo banco de dados, para uma resposta em JSON.
 *
 */
@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

    @Inject
    private PostgresUniqueOrPrimaryKeyViolationExceptionParser exceptionParser;

    /**
     * Método responsável por converter a mensagem da excessão em uma mensagem JSON.
     *
     * @param error O erro que foi lançado na camada de persistência.
     * @return Uma resposta de erro no formato JSON.
     */
    @Override
    public Response toResponse(PersistenceException error) {
        return Response.status(Response.Status.BAD_REQUEST).type("application/json;charset=UTF-8").entity(exceptionParser.parser(extractRootCause(error))).build();
    }

    private Throwable extractRootCause(Throwable error) {
        if (error.getCause() != null) {
            return extractRootCause(error.getCause());
        } else {
            return error;
        }
    }

}