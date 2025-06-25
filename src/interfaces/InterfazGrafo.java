package interfaces;

public interface InterfazGrafo {

    public void agregarNodo(int nodo);
    public void eliminarNodo(int nodo);
    public void agregarArista(int nodo1, int nodo2, int peso);
    public void eliminarArista(int nodo1, int nodo2);
    public void mostrarGrafo();
    public int obtenerPesoArista(int nodo1, int nodo2);
}
