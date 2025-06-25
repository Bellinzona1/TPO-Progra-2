package Testeo;

import Clases.Grafo;
import Clases.Nodo;
import Clases.AlgoritmoDijkstra;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        // Test: crear grafo simple
        Grafo grafo = new Grafo();
        grafo.agregarNodo(0, "Centro");
        grafo.agregarNodo(1, "A");
        grafo.agregarNodo(2, "B");
        grafo.agregarArista(0, 1, 5);
        grafo.agregarArista(1, 2, 7);
        grafo.agregarArista(0, 2, 20);
        System.out.println("Test 1: Mostrar grafo simple");
        grafo.mostrarGrafo();

        // Test: obtener peso de arista
        int peso = grafo.obtenerPesoArista(0, 1);
        System.out.println("Peso de la arista Centro -> A: " + peso);

        // Test: eliminar arista y nodo
        grafo.eliminarArista(0, 1);
        System.out.println("Tras eliminar arista Centro -> A:");
        grafo.mostrarGrafo();
        grafo.eliminarNodo(1);
        System.out.println("Tras eliminar nodo A:");
        grafo.mostrarGrafo();

        // Test: Dijkstra con camino
        grafo.agregarNodo(1, "A");
        grafo.agregarArista(0, 1, 5);
        grafo.agregarArista(1, 2, 7);
        AlgoritmoDijkstra.ResultadoDijkstra res = AlgoritmoDijkstra.dijkstraConCamino(grafo, 0);
        System.out.println("\nTest 2: Dijkstra con camino desde Centro");
        for (Integer destino : res.distancias.keySet()) {
            int distancia = res.distancias.get(destino);
            if (distancia == Integer.MAX_VALUE) {
                System.out.println("Destino " + grafo.getNombreNodo(destino) + ": Inalcanzable");
            } else {
                List<String> camino = new ArrayList<>();
                int actual = destino;
                while (actual != 0) {
                    camino.add(0, grafo.getNombreNodo(actual));
                    actual = res.predecesores.getOrDefault(actual, 0);
                    if (actual == 0) camino.add(0, grafo.getNombreNodo(0));
                }
                if (destino == 0) camino.add(grafo.getNombreNodo(0));
                System.out.println("Destino " + grafo.getNombreNodo(destino) + ": " + distancia + " min. Camino: " + String.join(" -> ", camino));
            }
        }

        // Test: setNombre y getNombre
        Nodo nodoB = (Nodo)grafo.getNodos().get(2);
        System.out.println("Nombre original de nodo 2: " + nodoB.getNombre());
        nodoB.setNombre("B modificado");
        System.out.println("Nombre modificado de nodo 2: " + nodoB.getNombre());
    }
}
