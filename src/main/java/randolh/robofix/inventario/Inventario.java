package randolh.robofix.inventario;

import java.util.HashMap;
import java.util.Map;
import randolh.robofix.excepcion.InventarioException;

public class Inventario {

    private static final Inventario instancia = new Inventario();

    private final Map<String, Integer> repuestos = new HashMap<>();

    private Inventario() {
        repuestos.put("BATERIA", 2);
        repuestos.put("MOTOR", 3);
        repuestos.put("SENSOR", 4);
    }

    public static Inventario getInstancia() {
        return instancia;
    }

    public void mostrar() {
        for (String repuesto : repuestos.keySet()) {
            System.out.println(repuesto + ": " + repuestos.get(repuesto));
        }
    }

    public void descontar(String repuesto, int cantidad) {
        if (repuesto == null || !repuestos.containsKey(repuesto)) {
            throw new InventarioException("El repuesto '" + repuesto + "' no existe en el inventario.");
        }

        if (cantidad <= 0) {
            throw new InventarioException("La cantidad a descontar debe ser mayor a cero. Valor recibido: " + cantidad);
        }

        int actual = repuestos.get(repuesto);
        if (cantidad > actual) {
            throw new InventarioException(
                "Stock insuficiente de '" + repuesto + "'. Disponible: " + actual + ", requerido: " + cantidad
            );
        }

        repuestos.put(repuesto, actual - cantidad);
    }

    public int consultar(String repuesto) {
        if (repuesto == null || !repuestos.containsKey(repuesto)) {
            return 0;
        }
        return repuestos.get(repuesto);
    }
}
