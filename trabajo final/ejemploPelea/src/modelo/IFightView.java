package modelo;

import java.awt.event.KeyListener;

public interface IFightView {
    void addInputListener(KeyListener listener);
    void updateFighterState(String fighterId, String animationKey);
    void updateHealthBar(String fighterId, double healthPercentage);
    void showComboText(String message);
}