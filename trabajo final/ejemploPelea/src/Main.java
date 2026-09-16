import javax.swing.SwingUtilities;

import controlador.FightController;
import modelo.CombatModel;
import vista.FightSwingView;
;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            CombatModel model = new CombatModel();
            FightSwingView view = new FightSwingView();
            new FightController(model, view);
        });

	}

}
