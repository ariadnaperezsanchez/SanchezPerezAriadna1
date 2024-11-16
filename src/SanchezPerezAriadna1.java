import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SanchezPerezAriadna1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pide el número de filas y columnas
        int filas = 0, columnas = 0;

        // Validación de la entrada para filas
        while (filas <= 0) {
            System.out.print("Introduce el número de filas, debe ser mayor que 0: ");
            if (scanner.hasNextInt()) {
                filas = scanner.nextInt();
                if (filas <= 0) {
                    System.out.println("El número de filas debe ser mayor que 0.");
                }
            } else {
                System.out.println("Escribe un número entero.");
                scanner.next();
            }
        }

        // Validación de la entrada para columnas
        while (columnas <= 0) {
            System.out.print("Introduce el número de columnas, debe ser mayor que 0: ");
            if (scanner.hasNextInt()) {
                columnas = scanner.nextInt();
                if (columnas <= 0) {
                    System.out.println("El número de columnas debe ser mayor que 0.");
                }
            } else {
                System.out.println("Escribe un número entero.");
                scanner.next();
            }
        }

        // Crear la matriz y llenarla con números aleatorios
        Random random = new Random();
        int[][] matriz = new int[filas][columnas];

        // Llenar la matriz con números aleatorios entre 0 y 9
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(10);
            }
        }

        // Crear una lista para almacenar los valores de las explosiones
        List<Integer> explosiones = new ArrayList<>();

        // Menú de opciones
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú:");
            System.out.println("[1] Mostrar matriz");
            System.out.println("[2] Poner bomba");
            System.out.println("[3] Poner todos los valores de la matriz a 0");
            System.out.println("[4] Ranking");
            System.out.println("[0] Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Mostrar la matriz
                    System.out.println("Matriz actual:");
                    for (int i = 0; i < matriz.length; i++) {
                        for (int j = 0; j < matriz[i].length; j++) {
                            if (matriz[i][j] == -1) {
                                System.out.print(" B ");
                            } else {
                                System.out.print(matriz[i][j] + " ");
                            }
                        }
                        System.out.println();
                    }
                    break;

                case 2:
                    // Poner bomba
                    System.out.print("Introduce la coordenada x (0 a " + (filas - 1) + "): ");
                    int coordenadax = scanner.nextInt();
                    System.out.print("Introduce la coordenada y (0 a " + (columnas - 1) + "): ");
                    int coordenaday = scanner.nextInt();

                    // Validar las coordenadas
                    if (coordenadax >= 0 && coordenadax < filas && coordenaday >= 0 && coordenaday < columnas) {
                        if (matriz[coordenadax][coordenaday] == 0) {
                            System.out.println("Intenta en otro lugar, ya existe una bomba entre estas coordenadas.");
                        } else {
                            // Calcular el valor de la explosión
                            int suma = 0;
                            for (int i = coordenadax - 1; i <= coordenadax + 1; i++) {
                                for (int j = coordenaday - 1; j <= coordenaday + 1; j++) {
                                    if (i >= 0 && i < matriz.length && j >= 0 && j < matriz[i].length) {
                                        suma += matriz[i][j];
                                    }
                                }
                            }

                            explosiones.add(suma);  // Añadir el valor de la explosión al ranking
                            System.out.println("¡Bomba puesta en las coordenadas (" + coordenadax + ", " + coordenaday + ")");
                            System.out.println("Valor de la explosión: " + suma);

                            // Convertir a 0 toda la fila coordenadax
                            for (int j = 0; j < columnas; j++) {
                                matriz[coordenadax][j] = 0;
                            }

                            // Convertir a 0 toda la columna coordenaday
                            for (int i = 0; i < filas; i++) {
                                matriz[i][coordenaday] = 0;
                            }
                        }
                    } else {
                        System.out.println("Coordenadas no validas.Por favor, inténtalo de nuevo.");
                    }
                    break;

                case 3:
                    // Poner todos los valores de la matriz a 0
                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            matriz[i][j] = 0;
                        }
                    }
                    System.out.println("Todos los valores de la matriz han sido puestos a 0.");
                    break;

                case 4:
                    // Ranking de explosiones
                    if (explosiones.isEmpty()) {
                        System.out.println("Todavía no ha explotado nada.");
                    } else {
                        System.out.println("Ranking de explosiones:");
                        for (int i = 0; i < explosiones.size(); i++) {
                            System.out.println("Explosión " + (i + 1) + ": " + explosiones.get(i));
                        }
                    }
                    break;

                case 0:
                    // Salir
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;

                default:
                    System.out.println("Esta opción no es válida. Por favor, elige otra.");
            }
        }

        scanner.close();
    }
}
