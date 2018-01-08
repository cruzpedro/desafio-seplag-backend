package br.com.seplag.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Classe responsável por produzir instancias de logs para serem usados na aplicação.
 *
 */
@ApplicationScoped
public class LoggerProducer {

    private LoggerProducer() {
    }

    /**
     * Método responsável por produzir uma instancia de log, usando o nome do ponto de injeção
     * como parametro para a criação do log.
     *
     * @param injectionPoint O local onde a instancia produzida será injetado dentro da aplicação.
     * @return Uma instancia de log devidamente configurada com o nome da classe onde a mesma será injetada.
     * @throws IOException Uma excessão caso ocorra algum problema de I/O no servidor.
     */
    @Default
    @Produces
    public static Logger createLogger(InjectionPoint injectionPoint) throws IOException {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
