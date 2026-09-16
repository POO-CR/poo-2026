package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

import modelo.ActionType;
import modelo.CombatModel;
import modelo.CombatResult;
import modelo.IFightView;

public class FightController {
    private final CombatModel model;
    private final IFightView view;

    public FightController(CombatModel model, IFightView view) {
        this.model = model;
        this.view = view;

        this.view.addInputListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_J -> handlePlayerAction(ActionType.PUNCH);
                    case KeyEvent.VK_K -> handlePlayerAction(ActionType.KICK);
                    case KeyEvent.VK_L -> handlePlayerAction(ActionType.SPECIAL);
                }
            }
        });
    }

    private void handlePlayerAction(ActionType action) {
        long now = System.currentTimeMillis();
        CombatResult result = model.registerInput(action, now);

        // Sincroniza estados visuales
        view.updateFighterState("P1", model.getFighter1().getCurrentAnimation());
        view.updateFighterState("P2", model.getFighter2().getCurrentAnimation());

        // Sincroniza barras de salud
        view.updateHealthBar("P1", model.getFighter1().getHp());
        view.updateHealthBar("P2", model.getFighter2().getHp());

        if (result.getComboName() != null) {
            view.showComboText(result.getComboName());
        }

        // Restablecer a reposo (idle)
        Timer timer = new Timer(400, e -> {
            model.resetToIdle();
            view.updateFighterState("P1", "idle");
            view.updateFighterState("P2", "idle");
            ((Timer) e.getSource()).stop();
        });
        timer.setRepeats(false);
        timer.start();
    }
}