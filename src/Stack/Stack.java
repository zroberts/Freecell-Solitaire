package Stack;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>>{
  Node head = null;
  Node tail = null;

    /**
     * Stack() creates an empty stack.
     */
  public Stack(){}

    /**
     * Creates a new Stack, and assigns the first Item
     * @param newItem - the item to be added to the stack.
     */
  public Stack(T newItem){
     Node newNode = new Node(newItem);
     head = newNode;
     tail = newNode;
  }

    /**
     * empty() determines if the stack is empty.
     * @return boolean True if the stack is empty, false if it is not
     */
  public boolean empty(){
     return head == null;
  }

    /**
     * peek() - returns, but does not remove, the top item in the stack.
     * @return T - the item in the stack
     */
  public T peek(){
     return (T)head.value;
  }

    /**
     * pop() - returns and removes, the top item in the stack
     * @return T - the item in the stack
     */
  public T pop(){
     T value = (T)head.value;
     head = head.next;
     return value;
  }

    /**
     * push() - pushes the item into the Stack
     * @param newItem - the Item to be pushed into the Stack.
     */
  public void push(T newItem){
     Node newNode = new Node(newItem);
     if(this.empty()) {
         tail = newNode;
     }
     if(head != null){ head.prev = newNode; }
     newNode.next = head;
     head = newNode;
  }

    /**
     * deleteStack() - deletes the stack
     */
  public void deleteStack(){
      head = null;
      tail = null;
  }

    /**
     * reverseIterator() - returns an Iterator, that iterates through the stack in reverse
     * @return an iterator in reverse order
     */
  public Iterator<T> reverseIterator(){
      return new FileToIteratorReverse<T>();
  }
  private final class FileToIteratorReverse<T> implements Iterator<T>{
        private Node curr;
        public FileToIteratorReverse(){
            this.curr = Stack.this.tail;
        }
        @Override
        public boolean hasNext(){
            return curr != null;
        }
        public T next(){
            if(this.curr == null){
                throw new NoSuchElementException("There is no next element");
            }
            T t = (T)this.curr.value;
            curr = curr.prev;
            return t;
        }
  }

    /**
     * iterator() - returns an Iterator, with the files in order
     * @return an Iterator
     */
  public Iterator<T> iterator(){
        return new FileToIterator<T>();
    }
  private final class FileToIterator<T> implements Iterator<T>{
      private Node curr;
      public FileToIterator(){
        this.curr = Stack.this.head;
      }
      @Override
      public boolean hasNext(){
          return curr != null;
      }
      public T next(){
          if(this.curr == null){
              throw new NoSuchElementException("There is no next element");
          }
          T t = (T)this.curr.value;
          curr = curr.next;
          return t;
      }
  }
}

