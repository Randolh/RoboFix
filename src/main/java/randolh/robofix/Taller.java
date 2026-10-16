package randolh.robofix;

import randolh.robofix.excepcion.InventarioException;
import randolh.robofix.inventario.Inventario;
import randolh.robofix.robot.Robot;

public class Taller {

    // Todos los talleres comparten la misma instancia de inventario (Singleton).
    private Inventario inventario = Inventario.getInstancia();

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

        try {
            if (robot.getEnergia() < 20) {
                System.out.println("No se pudo reparar el robot: energia insuficiente para el procedimiento (requiere minimo 20).");
                return;
            }
            inventario.descontar(repuesto, 1);
            robot.setEnergia(robot.getEnergia() - 20);
            System.out.println("Robot reparado correctamente.");
        } catch (InventarioException e) {
            System.out.println("No se pudo reparar el robot: " + e.getMessage());
        }
    }

    public void mostrarInventario() {
        inventario.mostrar();
    }
}
