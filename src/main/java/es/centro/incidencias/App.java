package es.centro.incidencias;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Punto de entrada de la aplicación de gestión de incidencias de soporte
 * del centro educativo.
 * <p>
 * La aplicación puede utilizarse de dos formas:
 * <ul>
 *   <li>Sin argumentos: se abre un menú interactivo en la consola.</li>
 *   <li>Con argumentos: se ejecuta directamente la operación indicada
 *       ({@code listar}, {@code abiertas}, {@code buscar}, {@code detalle}
 *       o {@code ayuda}).</li>
 * </ul>
 * Esta clase no puede instanciarse.
 *
 * @author Tu Nombre Apellidos
 * @version 1.0.0
 * @since 1.0.0
 * @see GestorIncidencias
 * @see Incidencia
 */
public final class App {

    /**
     * Constructor privado para impedir que se creen instancias de esta clase.
     */
    private App() {
    }

    /**
     * Inicia la aplicación.
     * <p>
     * Si no se reciben argumentos, se muestra el menú interactivo.
     * En caso contrario, el primer argumento indica la operación:
     * <ul>
     *   <li>{@code ayuda}: muestra las operaciones disponibles.</li>
     *   <li>{@code listar}: muestra todas las incidencias.</li>
     *   <li>{@code abiertas}: muestra solo las incidencias abiertas.</li>
     *   <li>{@code buscar <texto>}: busca incidencias por identificador o título.</li>
     *   <li>{@code detalle <id>}: muestra la incidencia con ese identificador.</li>
     * </ul>
     * Las operaciones no distinguen entre mayúsculas y minúsculas.
     *
     * @param args argumentos de la línea de comandos; el primero es la operación
     *             y los siguientes, si los hay, sus datos
     * @see GestorIncidencias#listar()
     * @see GestorIncidencias#listarAbiertas()
     * @see GestorIncidencias#buscar(String)
     * @see GestorIncidencias#buscarPorId(String)
     */
    public static void main(String[] args) {
        GestorIncidencias gestor = new GestorIncidencias();

        if (args.length == 0) {
            ejecutarMenuInteractivo(gestor);
            return;
        }

        if ("ayuda".equalsIgnoreCase(args[0])) {
            mostrarAyuda();
            return;
        }

        String operacion = args[0].toLowerCase();
        switch (operacion) {
            case "listar" -> mostrar(gestor.listar());
            case "abiertas" -> mostrar(gestor.listarAbiertas());
            case "buscar" -> buscar(args, gestor);
            case "detalle" -> mostrarDetalle(args, gestor);
            default -> {
                System.out.println("Operación desconocida: " + args[0]);
                mostrarAyuda();
            }
        }
    }

    /**
     * Muestra el menú interactivo y procesa las opciones del usuario
     * hasta que elige salir.
     *
     * @param gestor gestor de incidencias que se consulta
     */
    private static void ejecutarMenuInteractivo(GestorIncidencias gestor) {
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("GESTOR DE INCIDENCIAS");
        System.out.println("Aplicación de consulta de avisos de soporte del centro.");

        while (continuar) {
            mostrarMenu();
            System.out.print("Selecciona una opción: ");

            if (!entrada.hasNextLine()) {
                return;
            }

            String opcion = entrada.nextLine().trim();
            System.out.println();

            switch (opcion) {
                case "1" -> mostrar(gestor.listar());
                case "2" -> mostrar(gestor.listarAbiertas());
                case "3" -> buscarDesdeMenu(entrada, gestor);
                case "4" -> mostrarDetalleDesdeMenu(entrada, gestor);
                case "0" -> {
                    continuar = false;
                    System.out.println("Aplicación finalizada.");
                }
                default -> System.out.println("La opción indicada no es válida.");
            }

            System.out.println();
        }
    }

    /**
     * Muestra por consola las opciones del menú interactivo.
     */
    private static void mostrarMenu() {
        System.out.println("----------------------------------------");
        System.out.println("1. Mostrar todas las incidencias");
        System.out.println("2. Mostrar las incidencias abiertas");
        System.out.println("3. Buscar incidencias");
        System.out.println("4. Consultar una incidencia por su ID");
        System.out.println("0. Salir");
        System.out.println("----------------------------------------");
    }

    /**
     * Pide al usuario un texto y muestra las incidencias que coinciden con él.
     *
     * @param entrada escáner del que se lee el texto
     * @param gestor  gestor de incidencias que se consulta
     */
    private static void buscarDesdeMenu(Scanner entrada, GestorIncidencias gestor) {
        System.out.print("Texto que deseas buscar: ");
        String texto = entrada.hasNextLine() ? entrada.nextLine().trim() : "";

        if (texto.isBlank()) {
            System.out.println("Debes introducir algún texto.");
            return;
        }

        mostrar(gestor.buscar(texto));
    }

    /**
     * Pide al usuario un identificador y muestra la incidencia correspondiente.
     *
     * @param entrada escáner del que se lee el identificador
     * @param gestor  gestor de incidencias que se consulta
     */
    private static void mostrarDetalleDesdeMenu(Scanner entrada, GestorIncidencias gestor) {
        System.out.print("Identificador de la incidencia: ");
        String id = entrada.hasNextLine() ? entrada.nextLine().trim() : "";

        if (id.isBlank()) {
            System.out.println("Debes introducir un identificador.");
            return;
        }

        mostrarDetalle(id, gestor);
    }

    /**
     * Busca incidencias con el texto formado por los argumentos
     * que siguen a la operación {@code buscar}.
     *
     * @param args   argumentos de la línea de comandos
     * @param gestor gestor de incidencias que se consulta
     */
    private static void buscar(String[] args, GestorIncidencias gestor) {
        if (args.length < 2) {
            System.out.println("Debes indicar el texto que deseas buscar.");
            return;
        }

        String texto = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        mostrar(gestor.buscar(texto));
    }

    /**
     * Muestra la incidencia cuyo identificador se indica en el segundo argumento.
     *
     * @param args   argumentos de la línea de comandos
     * @param gestor gestor de incidencias que se consulta
     */
    private static void mostrarDetalle(String[] args, GestorIncidencias gestor) {
        if (args.length < 2) {
            System.out.println("Debes indicar el identificador de la incidencia.");
            return;
        }

        mostrarDetalle(args[1], gestor);
    }

    /**
     * Muestra la incidencia con el identificador indicado o, si no existe,
     * el mensaje de error correspondiente.
     *
     * @param id     identificador de la incidencia
     * @param gestor gestor de incidencias que se consulta
     */
    private static void mostrarDetalle(String id, GestorIncidencias gestor) {
        try {
            System.out.println(gestor.buscarPorId(id));
        } catch (IncidenciaNoEncontradaException excepcion) {
            System.out.println(excepcion.getMessage());
        }
    }

    /**
     * Muestra por consola una lista de incidencias, o un aviso si está vacía.
     *
     * @param incidencias lista de incidencias que se muestra
     */
    private static void mostrar(List<Incidencia> incidencias) {
        if (incidencias.isEmpty()) {
            System.out.println("No se han encontrado incidencias.");
            return;
        }
        incidencias.forEach(System.out::println);
    }

    /**
     * Muestra por consola las operaciones disponibles por línea de comandos.
     */
    private static void mostrarAyuda() {
        System.out.println("Gestor de incidencias");
        System.out.println("  ayuda              Muestra esta ayuda");
        System.out.println("  listar              Muestra todas las incidencias");
        System.out.println("  abiertas            Muestra las incidencias abiertas");
        System.out.println("  buscar <texto>      Busca por identificador o título");
        System.out.println("  detalle <id>        Muestra una incidencia");
    }
}
