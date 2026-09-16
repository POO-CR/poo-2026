package model;

public class TrollEspecial extends Troll {
    private boolean modoFuria;

    public TrollEspecial(int x, int y) {
        super(x, y);
        this.setVelocidad(7);
        this.modoFuria = false;
    }

    public boolean isModoFuria() {
        return modoFuria;
    }

    public void setModoFuria(boolean modoFuria) {
        this.modoFuria = modoFuria;
    }
}