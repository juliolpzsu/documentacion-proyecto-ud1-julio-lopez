package es.centro.incidencias;

public class IncidenciaNoEncontradaException extends Exception {

    public IncidenciaNoEncontradaException(String id) {
        super("No existe ninguna incidencia con el identificador " + id + ".");
    }
}
