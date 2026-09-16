package modelo;

public class CombatResult {
	private final boolean hitLanded;
    private final String comboName;

    public CombatResult(boolean hitLanded, String comboName) {
        this.hitLanded = hitLanded;
        this.comboName = comboName;
    }

    public boolean isHitLanded() { return hitLanded; }
    public String getComboName() { return comboName; }
}
