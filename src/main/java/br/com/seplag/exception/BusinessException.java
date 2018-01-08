package br.com.seplag.exception;

/**
 * Excessão responsável por representar qualquer violação de regras de negócio dentro de aplicação.
 *
 */
public class BusinessException extends RuntimeException {

    /**
     * Construtor responsável por criar a representação da violação, já com a mensagem de erro apropriada.
     *
     * @param message A mensagem de erro sobre a violação da regra.
     */
    public BusinessException(String message) {
        super(message);
    }
}
