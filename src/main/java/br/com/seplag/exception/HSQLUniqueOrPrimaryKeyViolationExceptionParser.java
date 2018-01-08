package br.com.seplag.exception;

import javax.enterprise.inject.Alternative;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe alternativa responsável por processar as mensagens de erros gerada pelo driver do banco
 * de testes, HSQLDB.
 *
 */
@Alternative
public class HSQLUniqueOrPrimaryKeyViolationExceptionParser implements ExceptionParser {

    private static final String JSON_PATH_FOR_ERROR_MESSAGE = "error";

    private Map<String, String> errorMessageToConvert = new HashMap<>();

    /**
     * Método responsável por realizar a extração da mensagem de erro contida na excessão lançada.
     *
     * @param error A excessão root que foi lançada por alguma camada mais interna do sistema.
     * @return Uma mapa com a mensagem de erro esxpecífica gerada.
     */
    @Override
    public Map<String, String> parser(Throwable error) {
        if (error.getMessage().contains("Unique index or primary key violation")) {
            errorMessageToConvert.put(JSON_PATH_FOR_ERROR_MESSAGE, String.format("O %s %s já foi cadastrado por outro usuário.", "Grupo", "ADMIN"));
        }

        return errorMessageToConvert;
    }
}