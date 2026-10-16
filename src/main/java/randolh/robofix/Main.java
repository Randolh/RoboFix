package randolh.robofix;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

            opcion = teclado.nextInt();
            teclado.nextLine();

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
                    int energia = teclado.nextInt();
                    teclado.nextLine();

                    System.out.print("Nuevo modo: ");
                    String modo = teclado.nextLine();

                    robot.setEnergia(energia);
                    robot.setModo(modo);
                    System.out.println("Configuracion actualizada.");
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
}

class Robot {

    private String codigo;
    private String nombre;
    private int energia;
    private String modo;

    public Robot(String codigo, String nombre, int energia, String modo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.energia = energia;
        this.modo = modo;
    }

    public void mostrar() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Energia: " + energia);
        System.out.println("Modo: " + modo);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }
}

// Sistema antiguo de diagnostico.
// No se permite modificar esta clase: simula una biblioteca externa.
class EscanerAntiguo {

    public String ejecutarEscaneo(String codigoRobot) {
        if (codigoRobot.equals("RX-01")) {
            return "RX-01|BATERIA|CRITICO|15";
        }

        return codigoRobot + "|SISTEMA|NORMAL|0";
    }
}

class Inventario {

    private Map<String, Integer> repuestos = new HashMap<>();

    public Inventario() {
        repuestos.put("BATERIA", 2);
        repuestos.put("MOTOR", 3);
        repuestos.put("SENSOR", 4);
    }

    public void mostrar() {
        for (String repuesto : repuestos.keySet()) {
            System.out.println(
                repuesto + ": " + repuestos.get(repuesto)
            );
        }
    }

    public void descontar(String repuesto, int cantidad) {
        int actual = repuestos.get(repuesto);
        repuestos.put(repuesto, actual - cantidad);
    }

    public int consultar(String repuesto) {
        return repuestos.get(repuesto);
    }
}

class Taller {

    // Cada taller crea su propio inventario.
    private Inventario inventario = new Inventario();

    private EscanerAntiguo escaner = new EscanerAntiguo();

    public void diagnosticar(Robot robot) {
        String resultado =
            escaner.ejecutarEscaneo(robot.getCodigo());

        String[] datos = resultado.split("\\|");

        System.out.println("\n--- DIAGNOSTICO ---");
        System.out.println("Robot: " + datos[0]);
        System.out.println("Componente: " + datos[1]);
        System.out.println("Estado: " + datos[2]);
        System.out.println("Consumo reportado: " + datos[3]);
    }

    public void reparar(Robot robot) {
        String resultado =
            escaner.ejecutarEscaneo(robot.getCodigo());

        String[] datos = resultado.split("\\|");
        String repuesto = datos[1];

        inventario.descontar(repuesto, 1);

        robot.setEnergia(robot.getEnergia() - 20);

        System.out.println("Robot reparado correctamente.");
    }

    public void mostrarInventario() {
        inventario.mostrar();
    }
}