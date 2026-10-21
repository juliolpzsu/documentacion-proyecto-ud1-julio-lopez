package es.centro.incidencias;

import java.util.List;

/**
 * Gestiona el listado de incidencias de soporte del centro educativo.
 * @author Julio Lopez Suarez
 * @version 1.0.0
 * @since 1.0.0
 * @see Incidencia
 */

public final class GestorIncidencias {

    private final List<Incidencia> incidencias;

    /**
     * Crea un gestor con un conjunto de incidencias de ejemplo precargadas.
     */
    public GestorIncidencias() {
        incidencias = List.of(
                new Incidencia("INC-001", "No se puede iniciar sesión", "alta", true),
                new Incidencia("INC-002", "Actualizar el navegador", "media", false),
                new Incidencia("INC-003", "Impresora sin conexión", "baja", true),
                new Incidencia("INC-004", "Fallo de red en el aula", "alta", true)
        );
    }

    /**
     * Devuelve todas las incidencias registradas.
     *
     * @return lista inmutable con todas las incidencias
     */
    public List<Incidencia> listar() {
        return incidencias;
    }

    /**
     * Devuelve solo las incidencias que siguen abiertas.
     *
     * @return lista de incidencias cuyo método {@link Incidencia#isAbierta()}
     *         devuelve {@code true}; puede estar vacía
     */
    public List<Incidencia> listarAbiertas() {
        return incidencias.stream()
                .filter(Incidencia::isAbierta)
                .toList();
    }

    /**
     * Busca incidencias cuyo identificador o título contengan el texto indicado,
     * sin distinguir mayúsculas de minúsculas.
     *
     * @param texto texto que se desea buscar
     * @return lista de incidencias que coinciden; vacía si no hay coincidencias
     * @see Incidencia#coincideCon(String)
     */
    public List<Incidencia> buscar(String texto) {
        return incidencias.stream()
                .filter(incidencia -> incidencia.coincideCon(texto))
                .toList();
    }

    /**
     * Obtiene la incidencia con el identificador indicado.
     *
     * @param id identificador de la incidencia, por ejemplo {@code "INC-001"}
     * @return la incidencia encontrada
     * @throws IncidenciaNoEncontradaException si no existe ninguna incidencia
     *         con ese identificador
     */
    public Incidencia buscarPorId(String id) throws IncidenciaNoEncontradaException {
        return incidencias.stream()
                .filter(incidencia -> incidencia.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new IncidenciaNoEncontradaException(id));
    }

    /**
     * Obtiene la incidencia con el identificador indicado.
     *
     * @param id identificador de la incidencia
     * @return la incidencia encontrada, o {@code null} si no existe
     * @deprecated No se recomienda porque devuelve {@code null} cuando la
     *             incidencia no existe, lo que oculta el error y puede provocar
     *             un {@link NullPointerException}. Utiliza
     *             {@link #buscarPorId(String)}, que lanza una excepción
     *             descriptiva, o {@link #buscar(String)} para búsquedas parciales.
     */
    @Deprecated(since = "1.0.0", forRemoval = false)
    public Incidencia obtenerIncidencia(String id) {
        try {
            return buscarPorId(id);
        } catch (IncidenciaNoEncontradaException excepcion) {
            return null;
        }
    }
}
