package randolh.robofix.diagnostico;

import randolh.robofix.EscanerAntiguo;
import randolh.robofix.excepcion.DiagnosticoException;

public class AdaptadorEscaner implements ServicioDiagnostico {

    private final EscanerAntiguo escaner;

    public AdaptadorEscaner() {
        this.escaner = new EscanerAntiguo();
    }

    public AdaptadorEscaner(EscanerAntiguo escaner) {
        this.escaner = escaner;
    }

    @Override
    public ResultadoDiagnostico escanear(String codigoRobot) {
        if (codigoRobot == null || codigoRobot.trim().isEmpty()) {
            throw new DiagnosticoException("El codigo del robot no puede estar vacio para el escaneo.");
        }

        String resultado = escaner.ejecutarEscaneo(codigoRobot);

        if (resultado == null || resultado.trim().isEmpty()) {
            throw new DiagnosticoException("El escaner retorno una respuesta vacia.");
        }

        String[] datos = resultado.split("\\|");

        if (datos.length != 4) {
            throw new DiagnosticoException(
                "Formato de diagnostico invalido o incompleto. Se esperaban 4 campos y se recibieron: " + datos.length
            );
        }

        int consumo;
        try {
            consumo = Integer.parseInt(datos[3].trim());
        } catch (NumberFormatException e) {
            throw new DiagnosticoException(
                "El valor de consumo reportado no es numerico: '" + datos[3] + "'."
            );
        }

        return new ResultadoDiagnostico(
            datos[0].trim(),
            datos[1].trim(),
            datos[2].trim(),
            consumo
        );
    }
}
