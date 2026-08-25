package modelo;

import java.util.Random;

public abstract class Personaje{
    
    private String nombre;
    private Integer vida;
    private Integer nivelAtaque;
    private Integer nivelDefensa;


    public Personaje(String nombre){
        this.nombre = nombre;
        this.vida = 100;
        this.nivelAtaque = new Random().nextInt(100)+1;
        this.nivelDefensa = new Random().nextInt(100)+1;
    }

    public Integer atacar(){
        return this.nivelAtaque;
    }

    public void defender(Integer danio){
        //Consistir el valor del daño
        this.vida -= (danio - this.nivelDefensa);
    }

}
