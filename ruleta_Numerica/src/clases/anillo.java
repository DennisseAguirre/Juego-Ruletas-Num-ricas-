
package clases;

public class anillo {

    public static CircularDoublyLinkedList<Integer> rotarIzquierda(CircularDoublyLinkedList<Integer> lista1) {
        Integer primero = lista1.getFirst();
        CircularDoublyLinkedList<Integer> lista2= new CircularDoublyLinkedList<>();
        for(int i=1; i<=lista1.size()-1;i++){
            lista2.addLast(lista1.get(i)-1);
        }
        lista2.addLast(primero-1);
        return lista2;
    }

    public static CircularDoublyLinkedList<Integer> rotarDerecha(CircularDoublyLinkedList<Integer> lista1) {
        Integer ultimo = lista1.getLast();
        CircularDoublyLinkedList<Integer> lista2= new CircularDoublyLinkedList<>();
        lista2.addLast(ultimo+1);
        for(int i=0; i<=lista1.size()-2;i++){
            lista2.addLast(lista1.get(i)+1);
        }

        return lista2;
    }

    public static CircularDoublyLinkedList<Integer> eliminar(CircularDoublyLinkedList<Integer> lista1, int k) {
        lista1.remove(k);
        return lista1;
    }
}
