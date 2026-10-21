package es.centro.incidencias;

/**
 * Excepción que se lanza cuando no existe ninguna incidencia
 * con el identificador buscado.
 * Es una excepción comprobada (hereda de {@link Exception}), por lo que
 * quien llame a un metodo que la lance debe capturarla o declararla.
 *
 * @author Julio Lopez Suarez
 * @version 1.0.0
 * @since 1.0.0
 * @see GestorIncidencias#buscarPorId(String)
 */
public class IncidenciaNoEncontradaException extends Exception {

    /**
     * Crea la excepción con un mensaje que incluye el identificador no encontrado.
     *
     * @param id identificador de la incidencia que no se ha encontrado
     */
    public IncidenciaNoEncontradaException(String id) {
        super("No existe ninguna incidencia con el identificador " + id + ".");
    }
}
