
package clases;

/**
 *
 * @author 
 */
public interface List <E>{
    int size();
    boolean isEmpty();
    boolean addFirst(E e);
    boolean addLast(E e);
    boolean removeFirst();
    boolean removeLast() throws NullPointerException;
    E getFirst();
    E getLast();
    //Node<E> getNode(int index);
    E get(int index);
    String toString();


    boolean add(E element, int index);
    boolean remove(int index);
    boolean contains(E element);
    List<E> slicing(int start,int end);
    
    
     
    
}
