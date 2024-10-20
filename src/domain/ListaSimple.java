package domain;

import java.util.Objects;

public class ListaSimple<T> {
    private Nodo<T> cabeza;
    private int tamanno;

    public void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamanno++;
    }

    public boolean eliminar(T dato) {
        if (cabeza == null) return false;

        if (Objects.equals(cabeza.getDato(), dato)) {
            cabeza = cabeza.getSiguiente();
            tamanno--;
            return true;
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (Objects.equals(actual.getSiguiente().getDato(), dato)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamanno--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    // Otros métodos útiles
}