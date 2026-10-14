package es.centro.incidencias;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public final class App {

    private App() {
    }

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

    private static void mostrarMenu() {
        System.out.println("----------------------------------------");
        System.out.println("1. Mostrar todas las incidencias");
        System.out.println("2. Mostrar las incidencias abiertas");
        System.out.println("3. Buscar incidencias");
        System.out.println("4. Consultar una incidencia por su ID");
        System.out.println("0. Salir");
        System.out.println("----------------------------------------");
    }

    private static void buscarDesdeMenu(Scanner entrada, GestorIncidencias gestor) {
        System.out.print("Texto que deseas buscar: ");
        String texto = entrada.hasNextLine() ? entrada.nextLine().trim() : "";

        if (texto.isBlank()) {
            System.out.println("Debes introducir algún texto.");
            return;
        }

        mostrar(gestor.buscar(texto));
    }

    private static void mostrarDetalleDesdeMenu(Scanner entrada, GestorIncidencias gestor) {
        System.out.print("Identificador de la incidencia: ");
        String id = entrada.hasNextLine() ? entrada.nextLine().trim() : "";

        if (id.isBlank()) {
            System.out.println("Debes introducir un identificador.");
            return;
        }

        mostrarDetalle(id, gestor);
    }

    private static void buscar(String[] args, GestorIncidencias gestor) {
        if (args.length < 2) {
            System.out.println("Debes indicar el texto que deseas buscar.");
            return;
        }

        String texto = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        mostrar(gestor.buscar(texto));
    }

    private static void mostrarDetalle(String[] args, GestorIncidencias gestor) {
        if (args.length < 2) {
            System.out.println("Debes indicar el identificador de la incidencia.");
            return;
        }

        mostrarDetalle(args[1], gestor);
    }

    private static void mostrarDetalle(String id, GestorIncidencias gestor) {
        try {
            System.out.println(gestor.buscarPorId(id));
        } catch (IncidenciaNoEncontradaException excepcion) {
            System.out.println(excepcion.getMessage());
        }
    }

    private static void mostrar(List<Incidencia> incidencias) {
        if (incidencias.isEmpty()) {
            System.out.println("No se han encontrado incidencias.");
            return;
        }
        incidencias.forEach(System.out::println);
    }

    private static void mostrarAyuda() {
        System.out.println("Gestor de incidencias");
        System.out.println("  ayuda              Muestra esta ayuda");
        System.out.println("  listar              Muestra todas las incidencias");
        System.out.println("  abiertas            Muestra las incidencias abiertas");
        System.out.println("  buscar <texto>      Busca por identificador o título");
        System.out.println("  detalle <id>        Muestra una incidencia");
    }
}
