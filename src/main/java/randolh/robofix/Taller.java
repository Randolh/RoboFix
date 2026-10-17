package randolh.robofix;

import randolh.robofix.diagnostico.AdaptadorEscaner;
import randolh.robofix.diagnostico.ResultadoDiagnostico;
import randolh.robofix.diagnostico.ServicioDiagnostico;
import randolh.robofix.excepcion.ConfiguracionRobotException;
import randolh.robofix.excepcion.DiagnosticoException;
import randolh.robofix.excepcion.InventarioException;
import randolh.robofix.inventario.Inventario;
import randolh.robofix.robot.Robot;

public class Taller {

    // Todos los talleres comparten la misma instancia de inventario (Singleton).
    private Inventario inventario = Inventario.getInstancia();

    // Diagnostico moderno a traves del adaptador (Adapter).
    private ServicioDiagnostico servicioDiagnostico = new AdaptadorEscaner();

    public void diagnosticar(Robot robot) {
        try {
            ResultadoDiagnostico datos = servicioDiagnostico.escanear(robot.getCodigo());

            System.out.println("--- DIAGNOSTICO ---");
            System.out.println("Robot: " + datos.getCodigoRobot());
            System.out.println("Componente: " + datos.getComponente());
            System.out.println("Estado: " + datos.getEstado());
            System.out.println("Consumo reportado: " + datos.getConsumo());
        } catch (DiagnosticoException e) {
            System.out.println("Error en el diagnostico: " + e.getMessage());
        }
    }

    public void reparar(Robot robot) {
        try {
            ResultadoDiagnostico datos = servicioDiagnostico.escanear(robot.getCodigo());
            String repuesto = datos.getComponente();

            if (robot.getEnergia() < 20) {
                System.out.println("No se pudo reparar el robot: energia insuficiente para el procedimiento (requiere minimo 20).");
                return;
            }

            inventario.descontar(repuesto, 1);
            robot.setEnergia(robot.getEnergia() - 20);
            System.out.println("Robot reparado correctamente.");
        } catch (DiagnosticoException e) {
            System.out.println("No se pudo reparar el robot: " + e.getMessage());
        } catch (InventarioException e) {
            System.out.println("No se pudo reparar el robot: " + e.getMessage());
        } catch (ConfiguracionRobotException e) {
            System.out.println("No se pudo reparar el robot: " + e.getMessage());
        }
    }

    public void mostrarInventario() {
        inventario.mostrar();
    }
}
