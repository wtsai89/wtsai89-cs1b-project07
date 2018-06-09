package cellularData;

/**
 * Node of the LinkedList class
 */
public class Node<T> {
    private T data;
    private Node next;

    /**
     * this node is not connected to another node
     *
     * @param data
     */
    public Node(T data) {
        this.data = data;
        next = null;
    }

    /**
     * this node is connected to another node
     *
     * @param data
     * @param next
     */
    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }

    /**
     * getter method for data
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * getter method for the next node
     *
     * @return
     */
    public Node getNext() {
        return next;
    }

    /**
     * setter method for the next node
     *
     * @param next
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * setter method for data
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * data in String format
     *
     * @return
     */
    public String toString() {
        return this.data.toString();
    }

}
