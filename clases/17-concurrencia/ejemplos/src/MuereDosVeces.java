// Archivo: MuereDosVeces.java
// Comprobar y despues actuar. Cuatro hilos atacan a cien mil enemigos a la vez y
// se cuenta cuantas veces murieron, primero con recibirDanio() sin proteger
// y despues con recibirDanioProtegido(), donde comprobar y restar son un
// solo paso.

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MuereDosVeces {

    static class Enemigo {
        private int vida = 10;
        private final AtomicInteger muertes = new AtomicInteger();

        public void recibirDanio(int danio) {
            if (this.vida > 0) {
                this.vida = this.vida - danio;
                if (this.vida <= 0) {
                    this.morir();
                }
            }
        }

        public void recibirDanioProtegido(int danio) {
            boolean murio;
            synchronized (this) {
                if (this.vida <= 0) {
                    return;
                }
                this.vida = this.vida - danio;
                murio = this.vida <= 0;
            }
            if (murio) {
                this.morir();
            }
        }

        private void morir() {
            this.muertes.incrementAndGet();
        }

        public int getMuertes() {
            return this.muertes.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("sin proteger: " + atacar(false) + " muertes de 100000 enemigos");
        System.out.println("protegido:    " + atacar(true) + " muertes de 100000 enemigos");
    }

    static int atacar(boolean protegido) throws InterruptedException {
        List<Enemigo> enemigos = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            enemigos.add(new Enemigo());
        }
        List<Thread> atacantes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            atacantes.add(new Thread(() -> {
                for (Enemigo enemigo : enemigos) {
                    for (int golpe = 0; golpe < 10; golpe++) {
                        if (protegido) {
                            enemigo.recibirDanioProtegido(1);
                        } else {
                            enemigo.recibirDanio(1);
                        }
                    }
                }
            }));
        }
        for (Thread atacante : atacantes) {
            atacante.start();
        }
        for (Thread atacante : atacantes) {
            atacante.join();
        }
        int muertes = 0;
        for (Enemigo enemigo : enemigos) {
            muertes += enemigo.getMuertes();
        }
        return muertes;
    }
}
