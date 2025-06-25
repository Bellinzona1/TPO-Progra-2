package Clases;

import interfaces.InterfazNodo;
import java.util.HashMap;
import java.util.Map;

public class Nodo implements InterfazNodo {
    private int id;
    private String nombre;
    private Map<InterfazNodo, Integer> vecinos;

    public Nodo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.vecinos = new HashMap<>();
    }

    public Nodo(int id) {
        this(id, "Nodo " + id);
    }

    @Override
    public void agregarVecino(InterfazNodo nodo, int peso) {
        vecinos.put(nodo, peso);
    }

    @Override
    public void eliminarVecino(InterfazNodo nodo) {
        vecinos.remove(nodo);
    }

    @Override
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<InterfazNodo, Integer> getVecinos() {
        return vecinos;
    }
}
