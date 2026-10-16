package randolh.robofix.diagnostico;

public class ResultadoDiagnostico {

    private final String codigoRobot;
    private final String componente;
    private final String estado;
    private final int consumo;

    public ResultadoDiagnostico(String codigoRobot, String componente, String estado, int consumo) {
        this.codigoRobot = codigoRobot;
        this.componente = componente;
        this.estado = estado;
        this.consumo = consumo;
    }

    public String getCodigoRobot() {
        return codigoRobot;
    }

    public String getComponente() {
        return componente;
    }

    public String getEstado() {
        return estado;
    }

    public int getConsumo() {
        return consumo;
    }
}
