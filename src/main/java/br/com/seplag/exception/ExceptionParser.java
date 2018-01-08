package br.com.seplag.exception;

import java.util.Map;

/**
 * Interface funcional responsável por definir a assinatura do método que é capaz
 * de extrair a informação de erro da forma mais edequada de acordo com a necessidade.
 *
 */
@FunctionalInterface
public interface ExceptionParser {

    /**
     * Método responsável por realizar a extração da mensagem de erro contida na excessão lançada.
     *
     * @param error A excessão root que foi lançada por alguma camada mais interna do sistema.
     * @return Uma mapa com a mensagem de erro esxpecífica gerada.
     */
    Map<String, String> parser(Throwable error);
}
