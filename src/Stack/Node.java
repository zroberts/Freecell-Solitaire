package Stack;

public class Node<T extends Comparable<T>> {
    protected Node next = null;
    protected Node prev = null;
    protected T value;

    /**
     * default constructor for a Node, used by the stack
     * @param givenValue - theValue assigned to the Node
     */
    public Node(T givenValue){
        value = givenValue;
    };

    /**
     * Overloaded constructor for a Node, used by the stack.
     * @param givenValue - the value assigned to the Node
     * @param givenNext - Reference to the next object in the stack, in relation to this node
     * @param givenPrev - Reference to the previous object in the stack, in relation to this node
     */
    public Node(T givenValue, Node givenNext, Node givenPrev) {
        value = givenValue;
        next = givenNext;
        prev = givenPrev;
    }
}
