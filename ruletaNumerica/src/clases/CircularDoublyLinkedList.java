package clases;

import java.util.Iterator;

/**
 *
 * @author Juan Pisco
 */
public class CircularDoublyLinkedList<E> implements List<E> {

    private Node<E> last;
    private int current;
////////////////////////////////////////////////////////////////////////////////

    public CircularDoublyLinkedList() {
        current = 0;
        last = null;
    }
////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean add(E element, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(int index) {
        if (!isEmpty()) {
            if (index >= size()) {
                System.out.println("El Indice es muy grande.");
            } else if (index < 0) {
                System.out.println("El indice no puede ser tan pequenio.");
            } else if (index == size() - 1) {
                removeLast();
            } else if (index == 0) {
                removeFirst();
            } else {
                int i = 0;

                for (Node<E> j = last.next.getNext(); j != null; j = j.next) {
                    if (index - 1 == i) {
                        Node<E> nodoAE = j.getNext();
                        j.setNext(nodoAE.getNext());
                        nodoAE.getNext().setPrevious(j);
                        nodoAE.setNext(null);
                        nodoAE.setPrevious(null);
                        current--;
                        return true;
                    }
                    i++;
                }
            }

        }
        return false;
    }

    @Override
    public List<E> slicing(int start, int end) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

////////////////////////////////////////////////////////////////////////////////    
    private class Node<E> {

        private E data;
        private Node<E> next;
        private Node<E> previous;

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
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
////////////////////////////////////////////////////////////////////////////////        

        public Node(E data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

        public Node(E data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }
////////////////////////////////////////////////////////////////////////////////

    public List<E> slicing1(int start, int end) {
        CircularDoublyLinkedList<E> nuevo = new CircularDoublyLinkedList<>();
        if (start > end || start < 0 || end < 0) {
            return nuevo;
        }
        Node<E> i = getNode(start);
        int conteo = 0;
        while (conteo < end - 1) {
            nuevo.addLast(i.data);
            conteo++;
            i = i.next;
        }
        return nuevo;
    }

////////////////////////////////////////////////////////////////////////////////
    /*private class Node {

        private int data;
        private Node next;
        private Node previous;

        public Node(int data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }*/
////////////////////////////////////////////////////////////////////////////////    
    @Override
    public boolean contains(E element) {
        if (isEmpty() || element == null) {
            return false;
        } else {
            int i = 0;
            for (Node<E> q = last.next; i < current; q = q.next) {
                if (q.data.equals(element)) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////

    @Override
    public int size() {
        return current;
    }
////////////////////////////////////////////////////////////////////////////////      

    @Override
    public boolean addLast(E e) {
        Node<E> n = new Node<>(e);
        if (isEmpty()) {
            last = n;
            last.next = last;
            last.previous = last;
        } else {
            n.next = last.next;
            last.next.previous = n;
            last.next = n;
            n.previous = last;
            last = n;
        }
        current++;
        return true;
    }
////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean addFirst(E e) {
        Node<E> n = new Node<>(e);
        if (isEmpty()) {
            last = n;
            last.next = last;
            last.previous = last;
        } else {
            Node<E> first = last.next;
            last.next = n;
            n.next = first;
            first.previous = n;
            n.previous = last;
        }
        current++;
        return true;

    }
////////////////////////////////////////////////////////////////////////////////

    public void insertNodeAtSpecificIndexOfDoublyLinkedList(E element, int insertionIndex) {
        Node<E> newNode = new Node(element);
        if (insertionIndex < 0 || insertionIndex > current) {
            System.out.println("Insertion at index " + insertionIndex + " is not possible.");
            return;
        } else if (insertionIndex == 0) {
            addFirst(element);
        } else if (insertionIndex == current) {
            addLast(element);
        } else {
            Node traversalNode = last;
            for (int i = 0; i < insertionIndex - 1; i++) {
                traversalNode = traversalNode.getNext();
            }
            newNode.setNext(traversalNode.getNext());
            traversalNode.getNext().setPrevious(newNode);
            traversalNode.setNext(newNode);
            newNode.setPrevious(traversalNode);
            current++;
        }
    }
////////////////////////////////////////////////////////////////////////////////    

    @Override
    public boolean removeLast() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException();
        } else if (current == 1) {
            last.data = null;
            last.next = last.previous = null;
        } else {
            Node<E> n = last.previous;
            n.next = last.next;
            last.next.previous = n;
            last.data = null;
            last.next = last.previous = null;
            last = n;
        }
        current--;
        return true;
    }
////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        }
        if (current == 1) {
            last.next.data = null;
            last.next = last = null;
        } else {
            last.next.data = null;
            Node<E> tmp = last.next.next;
            last.next.next = null;
            tmp.previous = null;
            last.next = tmp;
        }
        current--;
        return true;
    }
////////////////////////////////////////////////////////////////////////////////

    @Override
    public E getFirst() {
        if (isEmpty()) {
            return null;
        } else {
            return last.next.data;
        }
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            return null;
        } else {
            return last.data;
        }

    }

////////////////////////////////////////////////////////////////////////////////    
    private Node<E> getNode(int index) {
        if (isEmpty() || (index < 0 || index >= current)) {
            return null;
        } else {
            int i = 0;
            for (Node<E> q = last.next; i < current; q = q.next) {
                if (i == index) {
                    return q;
                }
                i++;
            }
            return null;
        }
    }

    @Override
    public E get(int index) {
        return getNode(index).data;
    }
////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean isEmpty() {
        return current == 0;
    }
////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node<E> p = last.next; p != last; p = p.next) {
            sb.append(p.data);
            sb.append(", ");
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();

    }
////////////////////////////////////////////////////////////////////////////////            
//  LITERAL 10    

    public void switchFirstandLast() {
        Node<E> first = last.next;
        Node<E> lst = last;
        lst.next = first.next;
        first.next.previous = lst;
        lst.previous.next = first;
        first.previous = lst.previous;
        first.next = lst;
        lst.previous = first;
        last = first;
    }
////////////////////////////////////////////////////////////////////////////////

    public void clear() {
        last = null;
        current = 0;

    }

////////////////////////////////////////////////////////////////////////////////
}
