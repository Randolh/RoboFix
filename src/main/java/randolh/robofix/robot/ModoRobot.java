package randolh.robofix.robot;

import randolh.robofix.excepcion.ConfiguracionRobotException;

public enum ModoRobot {
    NORMAL,
    AHORRO,
    MANTENIMIENTO;

    public static ModoRobot desdeTexto(String texto) throws ConfiguracionRobotException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new ConfiguracionRobotException("El modo de operacion no puede estar vacio.");
        }

        try {
            return ModoRobot.valueOf(texto.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ConfiguracionRobotException(
                "Modo no reconocido: '" + texto + "'. Modos permitidos: NORMAL, AHORRO, MANTENIMIENTO."
            );
        }
    }
}
