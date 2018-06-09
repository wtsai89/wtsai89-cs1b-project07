package cellularData;

import java.util.Iterator;

/**
 * Organizes multiple nodes into a linked list
 */
public class LinkedList<T> implements Iterable<T> {
    private Node<T> head, walker;
    private int size;

    /**
     * initializes an empty list
     */
    public LinkedList()
    {
        head = null;
        size = 0;
    }

    /**
     * add a node to the end of the list
     * @param data
     */
    public void add(T data)
    {
        Node<T> node = new Node<>(data);

        if(head == null)
            head = node;
        else {
            walker = head;
            while (walker.getNext() != null)
                walker = walker.getNext();
            walker.setNext(node);
        }

        size++;
    }

    /**
     * checks if the list contains the specific data and returns that data. Otherwise return null
     * @param data
     * @return
     */
    public T contains(T data)
    {
        Iterator<T> walker = iterator();
        T foundData;
        while(walker.hasNext())
        {
            foundData = walker.next();
            if(foundData.equals(data))
                return foundData;
        }

        return null;
    }

    /**
     * returns the data at the specified index
     * @return
     */
    public T getIndex(int index)
    {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Iterator<T> walker = iterator();
        T data = null;
        for(int i = 0; i <= index; i++)
            data = walker.next();
        return data;
    }

    /**
     * overrides Object class
     * @return
     */
    public String toString()
    {
        String s = "";
        Iterator<T> walker = iterator();
        while(walker.hasNext())
            s += walker.next();

        return s;
    }

    /**
     * getter method for size
     * @return
     */
    public int size()
    {
        return size;
    }

    /**
     * creates a new list iterator for the linked list
     * @return
     */
    public Iterator<T> iterator()
    {
        return new ListIterator();
    }

    /**
     * nested ListIterator class for generic LinkedList class
     */
    private class ListIterator implements Iterator<T>
    {
        private Node<T> current;

        /**
         * initialize the iterator to start from the beginning
         */
        public ListIterator()
        {    current = head; }

        /**
         * test if more elements can be traversed
         */
        public boolean hasNext()
        {
            // check if the next node is valid
            // if node is invalid, return false
            if (current == null)
                return false;

            // otherwise we haven't reached the end of the list
            return true;
        }

        /**
         * return the next available element
          */
        public T next()
        {
            if (current == null)
            {
                throw new java.util.NoSuchElementException();
            }

            // if we're here, then we're looking at a valid current node
            // so grab the data portion, to give to the caller
            T data = current.getData();

            // move in preparation for the next time.
            current = current.getNext();

            return data;
        }
    }

}
