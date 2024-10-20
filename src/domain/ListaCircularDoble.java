package domain;

public class ListaCircularDoble<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;

    public void insertar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = cola = nuevoNodo;
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            nuevoNodo.setSiguiente(cabeza);
            nuevoNodo.setAnterior(cola);
            cabeza.setAnterior(nuevoNodo);
            cola.setSiguiente(nuevoNodo);
            cola = nuevoNodo;
        }
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public void eliminar(T dato) {
        if (cabeza == null) return;
        Nodo<T> current = cabeza;
        do {
            if (current.getDato().equals(dato)) {
                if (current == cabeza && current == cola) {
                    cabeza = cola = null;
                } else if (current == cabeza) {
                    cabeza = cabeza.getSiguiente();
                    cabeza.setAnterior(cola);
                    cola.setSiguiente(cabeza);
                } else if (current == cola) {
                    cola = cola.getAnterior();
                    cola.setSiguiente(cabeza);
                    cabeza.setAnterior(cola);
                } else {
                    current.getAnterior().setSiguiente(current.getSiguiente());
                    current.getSiguiente().setAnterior(current.getAnterior());
                }
                break;
            }
            current = current.getSiguiente();
        } while (current != cabeza);
    }

    public Nodo<T> buscar(T dato) {
        if (cabeza == null) return null;
        Nodo<T> current = cabeza;
        do {
            if (current.getDato().equals(dato)) {
                return current;
            }
            current = current.getSiguiente();
        } while (current != cabeza);
        return null;
    }
}