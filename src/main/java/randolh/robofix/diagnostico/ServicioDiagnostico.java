package randolh.robofix.diagnostico;

import randolh.robofix.excepcion.DiagnosticoException;

public interface ServicioDiagnostico {

    ResultadoDiagnostico escanear(String codigoRobot) throws DiagnosticoException;
}
