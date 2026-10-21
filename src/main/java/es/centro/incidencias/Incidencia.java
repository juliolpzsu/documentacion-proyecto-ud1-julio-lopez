package es.centro.incidencias;

import java.util.Locale;

/**
 * Representa una incidencia de soporte del centro educativo.
 * Cada incidencia tiene un identificador, un título, una prioridad
 * y un estado que indica si sigue abierta. Los objetos de esta clase
 * son inmutables: sus datos no pueden modificarse una vez creados.
 *
 * @author Julio Lopez Suarez
 * @version 1.0.0
 * @since 1.0.0
 * @see GestorIncidencias
 */
public final class Incidencia {

    private final String id;
    private final String titulo;
    private final String prioridad;
    private final boolean abierta;

    /**
     * Crea una nueva incidencia con los datos indicados.
     *
     * @param id        identificador único de la incidencia, por ejemplo {@code "INC-001"}
     * @param titulo    descripción breve del problema
     * @param prioridad nivel de prioridad: {@code "alta"}, {@code "media"} o {@code "baja"}
     * @param abierta   {@code true} si la incidencia está abierta;
     *                  {@code false} si ya está cerrada
     */
    public Incidencia(String id, String titulo, String prioridad, boolean abierta) {
        this.id = id;
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.abierta = abierta;
    }

    /**
     * Devuelve el identificador de la incidencia.
     *
     * @return identificador de la incidencia
     */
    public String getId() {
        return id;
    }

    /**
     * Devuelve el título de la incidencia.
     *
     * @return título que describe el problema
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Devuelve la prioridad de la incidencia.
     *
     * @return prioridad de la incidencia ({@code "alta"}, {@code "media"} o {@code "baja"})
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * Indica si la incidencia sigue abierta.
     *
     * @return {@code true} si la incidencia está abierta;
     *         {@code false} si está cerrada
     * @see GestorIncidencias#listarAbiertas()
     */
    public boolean isAbierta() {
        return abierta;
    }

    /**
     * Comprueba si el identificador o el título de la incidencia contienen
     * el texto indicado, sin distinguir entre mayúsculas y minúsculas.
     *
     * @param texto texto que se desea buscar
     * @return {@code true} si el identificador o el título contienen el texto;
     *         {@code false} en caso contrario
     * @see GestorIncidencias#buscar(String)
     */
    public boolean coincideCon(String texto) {
        String criterio = texto.toLowerCase(Locale.ROOT);
        return id.toLowerCase(Locale.ROOT).contains(criterio)
                || titulo.toLowerCase(Locale.ROOT).contains(criterio);
    }

}
