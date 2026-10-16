package randolh.robofix.robot;

import randolh.robofix.excepcion.ConfiguracionRobotException;

public class Robot {

    public static final int ENERGIA_MINIMA = 0;
    public static final int ENERGIA_MAXIMA = 100;

    private String codigo;
    private String nombre;
    private int energia;
    private ModoRobot modo;

    public Robot(String codigo, String nombre, int energia, String modo) {
        this.codigo = codigo;
        this.nombre = nombre;
        actualizarConfiguracion(energia, modo);
    }

    public Robot(String codigo, String nombre, int energia, ModoRobot modo) {
        this.codigo = codigo;
        this.nombre = nombre;
        actualizarConfiguracion(energia, modo);
    }

    public void mostrar() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Energia: " + energia);
        System.out.println("Modo: " + modo);
    }

    public static void validarEnergia(int energia) {
        if (energia < ENERGIA_MINIMA || energia > ENERGIA_MAXIMA) {
            throw new ConfiguracionRobotException(
                "La energia debe estar en el intervalo de " + ENERGIA_MINIMA + " a " + ENERGIA_MAXIMA + ". Valor recibido: " + energia
            );
        }
    }

    public static void validarModo(ModoRobot modo) {
        if (modo == null) {
            throw new ConfiguracionRobotException("El modo no puede ser nulo.");
        }
    }

    public void actualizarConfiguracion(int nuevaEnergia, String nuevoModoTexto) {
        validarEnergia(nuevaEnergia);
        ModoRobot nuevoModo = ModoRobot.desdeTexto(nuevoModoTexto);
        this.energia = nuevaEnergia;
        this.modo = nuevoModo;
    }

    public void actualizarConfiguracion(int nuevaEnergia, ModoRobot nuevoModo) {
        validarEnergia(nuevaEnergia);
        validarModo(nuevoModo);
        this.energia = nuevaEnergia;
        this.modo = nuevoModo;
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
        validarEnergia(energia);
        this.energia = energia;
    }

    public ModoRobot getModo() {
        return modo;
    }

    public void setModo(ModoRobot modo) {
        validarModo(modo);
        this.modo = modo;
    }

    public void setModo(String modo) {
        this.modo = ModoRobot.desdeTexto(modo);
    }
}
