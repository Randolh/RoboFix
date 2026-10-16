package randolh.robofix;

import java.util.Scanner;
import randolh.robofix.excepcion.ConfiguracionRobotException;
import randolh.robofix.robot.Robot;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        Robot robot = new Robot("RX-01", "Explorador", 80, "NORMAL");

        Taller taller1 = new Taller();
        Taller taller2 = new Taller();

        int opcion = 0;

        while (opcion != 7) {
            System.out.println("\n===== ROBOFIX =====");
            System.out.println("1. Ver robot");
            System.out.println("2. Diagnosticar robot");
            System.out.println("3. Reparar robot");
            System.out.println("4. Cambiar configuracion");
            System.out.println("5. Ver inventario");
            System.out.println("6. Comparar inventarios");
            System.out.println("7. Salir");
            System.out.print("Opcion: ");

            opcion = leerEntero(teclado, "Opcion invalida. Ingrese un numero: ");

            switch (opcion) {
                case 1:
                    robot.mostrar();
                    break;

                case 2:
                    taller1.diagnosticar(robot);
                    break;

                case 3:
                    taller1.reparar(robot);
                    break;

                case 4:
                    System.out.print("Nueva energia: ");
                    int energia = leerEntero(teclado, "Debe ingresar un numero entero para la energia: ");

                    System.out.print("Nuevo modo: ");
                    String modo = teclado.nextLine();

                    try {
                        robot.actualizarConfiguracion(energia, modo);
                        System.out.println("Configuracion actualizada.");
                    } catch (ConfiguracionRobotException e) {
                        System.out.println("Error: " + e.getMessage());
                        System.out.println("Se conserva la configuracion anterior.");
                    }
                    break;

                case 5:
                    taller1.mostrarInventario();
                    break;

                case 6:
                    System.out.println("Inventario taller 1:");
                    taller1.mostrarInventario();

                    System.out.println("Inventario taller 2:");
                    taller2.mostrarInventario();
                    break;

                case 7:
                    System.out.println("Cerrando RoboFix...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        }

        teclado.close();
    }

    private static int leerEntero(Scanner teclado, String mensajeReintento) {
        while (true) {
            String entrada = teclado.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print(mensajeReintento);
            }
        }
    }
}