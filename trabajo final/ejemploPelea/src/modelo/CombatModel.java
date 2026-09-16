package modelo;

import java.util.ArrayList;
import java.util.List;

public class CombatModel {
	private final FighterState fighter1;
    private final FighterState fighter2;
    private final List<ActionType> inputBuffer;
    private long lastInputTime;
    private final long comboTimeoutMs = 800;

    public CombatModel() {
        this.fighter1 = new FighterState("P1", 100, "idle");
        this.fighter2 = new FighterState("P2", 100, "idle");
        this.inputBuffer = new ArrayList<>();
        this.lastInputTime = 0;
    }

    public CombatResult registerInput(ActionType action, long timestamp) {
        if (timestamp - lastInputTime > comboTimeoutMs) {
            inputBuffer.clear();
        }

        inputBuffer.add(action);
        lastInputTime = timestamp;

        return evaluateCombos();
    }

    private CombatResult evaluateCombos() {
        int size = inputBuffer.size();

        // Combo 1: Golpe especial (PUNCH -> KICK -> SPECIAL)
        if (size >= 3 &&
            inputBuffer.get(size - 3) == ActionType.PUNCH &&
            inputBuffer.get(size - 2) == ActionType.KICK &&
            inputBuffer.get(size - 1) == ActionType.SPECIAL) {
            
            fighter1.setCurrentAnimation("fireball_cast");
            fighter2.setHp(fighter2.getHp() - 30);
            fighter2.setCurrentAnimation("knockdown");
            inputBuffer.clear();
            return new CombatResult(true, "Dragon Fury");
        }

        // Combo 2: Golpe doble (PUNCH -> PUNCH)
        if (size >= 2 &&
            inputBuffer.get(size - 2) == ActionType.PUNCH &&
            inputBuffer.get(size - 1) == ActionType.PUNCH) {
            
            fighter1.setCurrentAnimation("combo_punch_finisher");
            fighter2.setHp(fighter2.getHp() - 15);
            fighter2.setCurrentAnimation("hit_heavy");
            return new CombatResult(true, "Double Jab");
        }

        // Ataque simple
        ActionType last = inputBuffer.get(size - 1);
        fighter1.setCurrentAnimation(last == ActionType.PUNCH ? "punch_light" : "kick_light");
        fighter2.setHp(fighter2.getHp() - 5);
        fighter2.setCurrentAnimation("hit_light");

        return new CombatResult(true, null);
    }

    public void resetToIdle() {
        fighter1.setCurrentAnimation("idle");
        fighter2.setCurrentAnimation("idle");
    }

    public FighterState getFighter1() { return fighter1; }
    public FighterState getFighter2() { return fighter2; }
}
