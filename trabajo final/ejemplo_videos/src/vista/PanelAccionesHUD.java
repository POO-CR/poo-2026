package vista;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import modelo.Personaje;

public class PanelAccionesHUD extends JPanel {

    private final JLabel lblFaseRonda;
    private final JLabel lblNombreHeroe;
    private final JLabel lblUbicacionHeroe;
    private final JLabel lblAccionesRestantes;
    private final JLabel lblRetratoHeroe;

    private final JButton btnViajar;
    private final JButton btnReclutar;
    private final JButton btnAtacar;
    private final JButton btnPasarTurno;

    private static final int ANCHO_IMG = 160;
    private static final int ALTO_IMG = 150;

    public PanelAccionesHUD(Runnable alViajar, Runnable alReclutar, Runnable alAtacar, Runnable alPasarTurno) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(310, 0));
        setBackground(new Color(18, 20, 26, 240));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(212, 175, 55)),
                BorderFactory.createEmptyBorder(20, 18, 20, 18)
        ));

        // Título de Fase y Ronda
        lblFaseRonda = new JLabel("Ronda 1 - Fase 2");
        lblFaseRonda.setFont(new Font("Serif", Font.BOLD, 18));
        lblFaseRonda.setForeground(new Color(212, 175, 55));
        lblFaseRonda.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblFaseRonda);

        add(Box.createVerticalStrut(15));

        // Retrato del Héroe Activo
        lblRetratoHeroe = new JLabel();
        lblRetratoHeroe.setPreferredSize(new Dimension(ANCHO_IMG, ALTO_IMG));
        lblRetratoHeroe.setMaximumSize(new Dimension(ANCHO_IMG, ALTO_IMG));
        lblRetratoHeroe.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRetratoHeroe.setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 2));
        add(lblRetratoHeroe);

        add(Box.createVerticalStrut(10));

        // Datos del Héroe
        lblNombreHeroe = new JLabel("Héroe Activo");
        lblNombreHeroe.setFont(new Font("Serif", Font.BOLD, 20));
        lblNombreHeroe.setForeground(new Color(245, 235, 205));
        lblNombreHeroe.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblNombreHeroe);

        lblUbicacionHeroe = new JLabel("En: Rivendel");
        lblUbicacionHeroe.setFont(new Font("Serif", Font.ITALIC, 14));
        lblUbicacionHeroe.setForeground(new Color(180, 175, 160));
        lblUbicacionHeroe.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblUbicacionHeroe);

        add(Box.createVerticalStrut(8));

        lblAccionesRestantes = new JLabel("Acciones restantes: 2");
        lblAccionesRestantes.setFont(new Font("Serif", Font.BOLD, 14));
        lblAccionesRestantes.setForeground(new Color(255, 215, 0));
        lblAccionesRestantes.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblAccionesRestantes);

        add(Box.createVerticalStrut(25));

        // Botones de Acción
        btnViajar = crearBotonAccion("VIAJAR", alViajar);
        btnReclutar = crearBotonAccion("RECLUTAR", alReclutar);
        btnAtacar = crearBotonAccion("ATACAR", alAtacar);
        btnPasarTurno = crearBotonAccion("TERMINAR TURNO", alPasarTurno);

        add(btnViajar);
        add(Box.createVerticalStrut(10));
        add(btnReclutar);
        add(Box.createVerticalStrut(10));
        add(btnAtacar);
        add(Box.createVerticalStrut(20));
        add(btnPasarTurno);
    }

    private JButton crearBotonAccion(String texto, Runnable accion) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Serif", Font.BOLD, 15));
        btn.setForeground(new Color(235, 225, 195));
        btn.setBackground(new Color(32, 35, 44));
        btn.setFocusPainted(false);
        btn.setMaximumSize(new Dimension(250, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> { if (accion != null) accion.run(); });
        return btn;
    }

    public void actualizarHUD(GestorTurno gestor) {
        lblFaseRonda.setText(String.format("Ronda %d - %s", gestor.getNumeroRonda(), gestor.getFaseActual().getNombre()));

        Personaje heroe = gestor.getHeroeActivo();
        if (heroe != null) {
            lblNombreHeroe.setText(heroe.getNombre());
            lblUbicacionHeroe.setText("En: " + heroe.getUbicacionActual());
            lblAccionesRestantes.setText("Acciones restantes: " + gestor.getAccionesRestantesHeroe());

            // Actualizar la foto del personaje
            if (heroe.getRutaImagen() != null) {
                File f = new File(heroe.getRutaImagen());
                if (f.exists()) {
                    ImageIcon icono = new ImageIcon(
                            new ImageIcon(f.getAbsolutePath()).getImage().getScaledInstance(ANCHO_IMG, ALTO_IMG, Image.SCALE_SMOOTH)
                    );
                    lblRetratoHeroe.setIcon(icono);
                } else {
                    lblRetratoHeroe.setIcon(null);
                }
            }
        }

        boolean habilitar = gestor.heroeActivoTieneAcciones() && gestor.getFaseActual() == modelo.FaseJuego.FASE_2_ACCIONES;
        btnViajar.setEnabled(habilitar);
        btnReclutar.setEnabled(habilitar);
        btnAtacar.setEnabled(habilitar);
    }
}