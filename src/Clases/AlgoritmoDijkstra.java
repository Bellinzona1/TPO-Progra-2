package Clases;

import java.util.*;

public class AlgoritmoDijkstra {
    public static Map<Integer, Integer> dijkstra(Grafo grafo, int origen) {
        Map<Integer, Integer> distancias = new HashMap<>();
        Set<Integer> visitados = new HashSet<>();
        PriorityQueue<int[]> cola = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (Integer id : grafo.getNodos().keySet()) {
            distancias.put(id, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);
        cola.add(new int[]{origen, 0});

        while (!cola.isEmpty()) {
            int[] actual = cola.poll();
            int nodoActual = actual[0];
            int distanciaActual = actual[1];

            if (visitados.contains(nodoActual)) continue;
            visitados.add(nodoActual);

            Nodo nodo = (Nodo) grafo.getNodos().get(nodoActual);
            for (Map.Entry<interfaces.InterfazNodo, Integer> vecino : nodo.getVecinos().entrySet()) {
                int idVecino = vecino.getKey().getId();
                int peso = vecino.getValue();
                if (distanciaActual + peso < distancias.get(idVecino)) {
                    distancias.put(idVecino, distanciaActual + peso);
                    cola.add(new int[]{idVecino, distancias.get(idVecino)});
                }
            }
        }
        return distancias;
    }

    public static class ResultadoDijkstra {
        public Map<Integer, Integer> distancias;
        public Map<Integer, Integer> predecesores;
        public ResultadoDijkstra(Map<Integer, Integer> distancias, Map<Integer, Integer> predecesores) {
            this.distancias = distancias;
            this.predecesores = predecesores;
        }
    }

    public static ResultadoDijkstra dijkstraConCamino(Grafo grafo, int origen) {
        Map<Integer, Integer> distancias = new HashMap<>();
        Map<Integer, Integer> predecesores = new HashMap<>();
        Set<Integer> visitados = new HashSet<>();
        PriorityQueue<int[]> cola = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (Integer id : grafo.getNodos().keySet()) {
            distancias.put(id, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);
        cola.add(new int[]{origen, 0});

        while (!cola.isEmpty()) {
            int[] actual = cola.poll();
            int nodoActual = actual[0];
            int distanciaActual = actual[1];

            if (visitados.contains(nodoActual)) continue;
            visitados.add(nodoActual);

            Nodo nodo = (Nodo) grafo.getNodos().get(nodoActual);
            for (Map.Entry<interfaces.InterfazNodo, Integer> vecino : nodo.getVecinos().entrySet()) {
                int idVecino = vecino.getKey().getId();
                int peso = vecino.getValue();
                if (distanciaActual + peso < distancias.get(idVecino)) {
                    distancias.put(idVecino, distanciaActual + peso);
                    predecesores.put(idVecino, nodoActual);
                    cola.add(new int[]{idVecino, distancias.get(idVecino)});
                }
            }
        }
        return new ResultadoDijkstra(distancias, predecesores);
    }
}
