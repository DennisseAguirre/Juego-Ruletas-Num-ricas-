
package ruleta_numerica;

import clases.CircularDoublyLinkedList;
import static clases.anillo.eliminar;
import static clases.anillo.rotarDerecha;
import static clases.anillo.rotarIzquierda;

/**
 *
 * @author Juan Pisco
 */
public class pruebas {
    public static void main (String[] args){
        CircularDoublyLinkedList<Integer> lista1 = new CircularDoublyLinkedList<>();
        lista1.addLast(1);
        lista1.addLast(4);
        lista1.addLast(7);
        lista1.addLast(11);
        lista1.addLast(25);

        System.out.println(lista1.toString());
        CircularDoublyLinkedList<Integer> lista2= rotarIzquierda(lista1);
        System.out.println(lista2.toString());
        CircularDoublyLinkedList<Integer> lista3= rotarDerecha(lista1);
        System.out.println(lista3.toString());

        CircularDoublyLinkedList<CircularDoublyLinkedList<Integer>> listadlista=new CircularDoublyLinkedList<>();
        listadlista.addLast(lista1);
        listadlista.addLast(lista2);
        listadlista.addLast(lista3);
        for(int i=0; i<=listadlista.size()-1;i++){
            System.out.println(eliminar(listadlista.get(i), 2));
        }
    }
}
