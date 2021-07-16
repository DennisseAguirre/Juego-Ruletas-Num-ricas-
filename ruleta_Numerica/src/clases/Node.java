
package clases;
/**
 *
 * @author Grupo 6
 */
// Los elemento que tendrán un nodo
public class Node<E>{
    private E content;
    private Node<E> next;
    private Node<E> previous;
    public Node(E info){
        this.content=info;
        this.next=null;
        this.previous=null;
    }  

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }
    

    
   
    
    
}
