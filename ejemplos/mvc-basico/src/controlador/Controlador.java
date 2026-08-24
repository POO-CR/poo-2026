package controlador;

import modelo.LoQueQuieran;
import vista.Vista;

public class Controlador {

    private Vista vista;
    private LoQueQuieran modelo;

    public Controlador() {
        this.vista = new Vista();
        this.modelo = new LoQueQuieran();

        this.vista.setVisible(true);

        this.vista.getButton().addActionListener(evento -> {
            String texto = modelo.getLeyenda();
            this.vista.setLabel(texto);
        });
    }
}
