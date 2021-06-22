/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    Node<E> getNode(int index);
    E get(int index);
    String toString();
    
    
     
    
}
