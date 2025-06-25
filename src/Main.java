import Clases.Grafo;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Crear nodos con nombres descriptivos
        grafo.agregarNodo(0, "Centro logístico");
        grafo.agregarNodo(1, "Sucursal A");
        grafo.agregarNodo(2, "Sucursal B");
        grafo.agregarNodo(3, "Cliente C");
        grafo.agregarNodo(4, "Cliente D");
        grafo.agregarNodo(5, "Cliente E");
        grafo.agregarNodo(6, "Cliente F");
        grafo.agregarNodo(7, "Sucursal G");


        // Rutas entre los puntos (aristas con tiempo en minutos)
        grafo.agregarArista(0, 1, 10); // Centro -> Sucursal A (10 min)
        grafo.agregarArista(0, 2, 15); // Centro -> Sucursal B (15 min)
        grafo.agregarArista(1, 3, 12); // Sucursal A -> Cliente C (12 min)
        grafo.agregarArista(2, 3, 10); // Sucursal B -> Cliente C (10 min)
        grafo.agregarArista(1, 2, 5);  // Sucursal A -> Sucursal B (5 min)
        grafo.agregarArista(3, 2, 20); // Cliente C -> Sucursal B (20 min)
        grafo.agregarArista(0, 4, 30); // Centro -> Cliente D (30 min)
        grafo.agregarArista(1, 4, 8);   // Sucursal A -> Cliente D (8 min)
        grafo.agregarArista(4, 3, 6);   // Cliente D -> Cliente C (6 min)
        grafo.agregarArista(2, 5, 7);   // Sucursal B -> Cliente E (7 min)
        grafo.agregarArista(5, 6, 4);   // Cliente E -> Cliente F (4 min)
        grafo.agregarArista(6, 3, 9);   // Cliente F -> Cliente C (9 min)
        grafo.agregarArista(1, 7, 11);  // Sucursal A -> Sucursal G (11 min)
        grafo.agregarArista(7, 3, 5);   // Sucursal G -> Cliente C (5 min)
        grafo.agregarArista(4, 5, 3);   // Cliente D -> Cliente E (3 min)
        grafo.agregarArista(7, 6, 2);   // Sucursal G -> Cliente F (2 min)


        System.out.println("Grafo de rutas:");
        grafo.mostrarGrafo();

        int origen = 0; // Nodo de inicio: centro logístico
        // Usar Dijkstra con camino
        Clases.AlgoritmoDijkstra.ResultadoDijkstra resultado = Clases.AlgoritmoDijkstra.dijkstraConCamino(grafo, origen);
        System.out.println("\nTiempos mínimos y caminos óptimos desde el centro logístico:");
        for (Integer destino : resultado.distancias.keySet()) {
            int distancia = resultado.distancias.get(destino);
            if (distancia == Integer.MAX_VALUE) {
                System.out.println("Destino " + grafo.getNombreNodo(destino) + ": Inalcanzable");
            } else {
                // Reconstruir camino
                List<String> camino = new ArrayList<>();
                int actual = destino;
                while (actual != origen) {
                    camino.add(0, grafo.getNombreNodo(actual));
                    actual = resultado.predecesores.getOrDefault(actual, origen);
                    if (actual == origen) camino.add(0, grafo.getNombreNodo(origen));
                }
                if (destino == origen) camino.add(grafo.getNombreNodo(origen));
                System.out.println("Destino " + grafo.getNombreNodo(destino) + ": " + distancia + " minutos. Camino óptimo: " + String.join(" -> ", camino));
            }
        }
        System.out.println("\nConclusión: El algoritmo de Dijkstra permite determinar de forma eficiente el tiempo mínimo de entrega y el recorrido óptimo desde el centro logístico a cada destino, optimizando la logística y mejorando la planificación de las rutas de reparto.");

        // Ejemplo de uso de obtenerPesoArista
        int pesoA1A3 = grafo.obtenerPesoArista(1, 3);
        System.out.println("\nPeso de la arista Sucursal A -> Cliente C: " + (pesoA1A3 != -1 ? pesoA1A3 + " minutos" : "No existe"));


        // Ejemplo de uso de eliminarArista
        System.out.println("\nEliminando arista Sucursal A -> Cliente C...");
        grafo.eliminarArista(1, 3);
        grafo.mostrarGrafo();

        // Ejemplo de uso de eliminarNodo
        System.out.println("\nEliminando nodo Cliente F...");
        grafo.eliminarNodo(6);
        grafo.mostrarGrafo();

        // Ejemplo de uso de agregarVecino y eliminarVecino directamente en Nodo
        Clases.Nodo nodoA = (Clases.Nodo)grafo.getNodos().get(1);
        Clases.Nodo nodoE = (Clases.Nodo)grafo.getNodos().get(5);
        nodoA.agregarVecino(nodoE, 13);
        System.out.println("\nAgregado vecino Cliente E a Sucursal A (13 min):");
        grafo.mostrarGrafo();
        nodoA.eliminarVecino(nodoE);
        System.out.println("\nEliminado vecino Cliente E de Sucursal A:");
        grafo.mostrarGrafo();
    }
}