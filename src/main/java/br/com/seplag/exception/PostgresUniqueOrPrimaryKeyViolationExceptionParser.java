package br.com.seplag.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe responsável por tratar as mensagens de erro do banco quando as mesmas forem sobre violação de chave primária.
 *
 */
public class PostgresUniqueOrPrimaryKeyViolationExceptionParser implements ExceptionParser {

    private static final Pattern REGEX = Pattern.compile("\\(.*\\)=\\(.*\\)");
    private Map<String, String> errorMessageToConvert = new HashMap<>();

    /**
     * Método responsável por realizar a extração da mensagem de erro contida na excessão lançada.
     *
     * @param error A excessão root que foi lançada por alguma camada mais interna do sistema.
     * @return Uma mapa com a mensagem de erro esxpecífica gerada.
     */
    @Override
    public Map<String, String> parser(Throwable error) {
    	System.out.println(error.getMessage());
        Matcher matcher = REGEX.matcher(error.getMessage());

        if (matcher.find()) {
            String keyValue = matcher.group(0).replaceAll("\\(|\\)", "");
            errorMessageToConvert.put("error", String.format("O %s %s já foi cadastrado por outro usuário.", keyValue.split("=")[0], keyValue.split("=")[1]));
        }

        return errorMessageToConvert;
    }
}