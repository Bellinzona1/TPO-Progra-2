package interfaces;

public interface InterfazNodo {
    void agregarVecino(InterfazNodo nodo, int peso);
    void eliminarVecino(InterfazNodo nodo);
    int getId();
}
