package randolh.robofix;

public class Taller {

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
