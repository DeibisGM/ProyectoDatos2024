package data;

import domain.Event;
import domain.ListaCircularDoble;
import domain.Nodo;

public class FileData {
    private static FileData instance;
    private ListaCircularDoble<Event> eventos;
    private Nodo<Event> currentEventNode;

    private FileData() {
        eventos = new ListaCircularDoble<>();
    }

    public static FileData getInstance() {
        if (instance == null) {
            instance = new FileData();
        }
        return instance;
    }

    public ListaCircularDoble<Event> getEventos() {
        return eventos;
    }

    public void setCurrentEventNode(Nodo<Event> node) {
        this.currentEventNode = node;
    }

    public Nodo<Event> getCurrentEventNode() {
        return currentEventNode;
    }

    public void agregarEvento(Event event) {
        eventos.insertar(event);
        // Al agregar un evento nuevo, actualizamos el nodo actual
        if (currentEventNode == null) {
            currentEventNode = eventos.getCabeza();
        }
    }
}