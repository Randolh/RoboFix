package randolh.robofix.robot;

public class RobotMemento {

    private final int energia;
    private final ModoRobot modo;

    public RobotMemento(int energia, ModoRobot modo) {
        this.energia = energia;
        this.modo = modo;
    }

    public int getEnergia() {
        return energia;
    }

    public ModoRobot getModo() {
        return modo;
    }
}
