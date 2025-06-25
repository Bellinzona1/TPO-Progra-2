package Clases;

import interfaces.InterfazGrafo;
import interfaces.InterfazNodo;
import java.util.HashMap;
import java.util.Map;

public class Grafo implements InterfazGrafo {
    private Map<Integer, InterfazNodo> nodos;

    public Grafo() {
        this.nodos = new HashMap<>();
    }

    @Override
    public void agregarNodo(int id) {
        if (!nodos.containsKey(id)) {
            nodos.put(id, new Nodo(id));
        }
    }

    public void agregarNodo(int id, String nombre) {
        if (!nodos.containsKey(id)) {
            nodos.put(id, new Nodo(id, nombre));
        }
    }

    @Override
    public void eliminarNodo(int id) {
        InterfazNodo nodo = nodos.remove(id);
        if (nodo != null) {
            for (InterfazNodo n : nodos.values()) {
                n.eliminarVecino(nodo);
            }
        }
    }

    @Override
    public void agregarArista(int id1, int id2, int peso) {
        InterfazNodo nodo1 = nodos.get(id1);
        InterfazNodo nodo2 = nodos.get(id2);
        if (nodo1 != null && nodo2 != null) {
            nodo1.agregarVecino(nodo2, peso);
        }
    }

    @Override
    public void eliminarArista(int id1, int id2) {
        InterfazNodo nodo1 = nodos.get(id1);
        InterfazNodo nodo2 = nodos.get(id2);
        if (nodo1 != null && nodo2 != null) {
            nodo1.eliminarVecino(nodo2);
        }
    }

    @Override
    public void mostrarGrafo() {
        for (InterfazNodo nodo : nodos.values()) {
            System.out.print("Nodo " + nodo.getId() + " -> ");
            for (Map.Entry<InterfazNodo, Integer> entry : ((Nodo)nodo).getVecinos().entrySet()) {
                System.out.print("[Destino: " + entry.getKey().getId() + ", Tiempo: " + entry.getValue() + "] ");
            }
            System.out.println();
        }
    }

    @Override
    public int obtenerPesoArista(int id1, int id2) {
        InterfazNodo nodo1 = nodos.get(id1);
        InterfazNodo nodo2 = nodos.get(id2);
        if (nodo1 != null && nodo2 != null) {
            Integer peso = ((Nodo)nodo1).getVecinos().get(nodo2);
            return peso != null ? peso : -1;
        }
        return -1;
    }

    public String getNombreNodo(int id) {
        InterfazNodo nodo = nodos.get(id);
        if (nodo != null && nodo instanceof Nodo) {
            return ((Nodo)nodo).getNombre();
        }
        return null;
    }

    public Map<Integer, InterfazNodo> getNodos() {
        return nodos;
    }
}
