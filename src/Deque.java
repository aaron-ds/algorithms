import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by aarondesouza on 27/06/2015.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size = 0;

    public Deque() {                        // construct an empty deque
    }

    public boolean isEmpty() {                // is the deque empty?
        return size == 0;
    }

    public int size() {                        // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {          // add the item to the front
        if (item == null)
            throw new NullPointerException();

        final Node node = new Node(item);

        if (size == 0) {
            first = node;
            last = node;
        } else {
            final Node oldFirst = first;
            first = node;
            first.next = oldFirst;
            oldFirst.previous = first;
        }
        size++;
    }

    public void addLast(Item item) {           // add the item to the end
        if (item == null)
            throw new NullPointerException();

        final Node node = new Node(item);

        if (size == 0) {
            first = node;
            last = node;
        } else {
            final Node oldLast = last;
            last = node;
            last.previous = oldLast;
            oldLast.next = node;
        }

        size++;
    }

    public Item removeFirst() {                // remove and return the item from the front
        if (isEmpty())
            throw new NoSuchElementException();

        final Node oldFirst = first;
        first = oldFirst.next;
        if (first != null)
            first.previous = null;

        size--;
        return oldFirst.item;
    }

    public Item removeLast() {               // remove and return the item from the end
        if (isEmpty())
            throw new NoSuchElementException();

        final Node oldLast = last;
        last = oldLast.previous;
        if (last != null)
            last.next = null;

        size--;
        return oldLast.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator(this);
    }


    public static void main(String[] args) {  // unit testing

        Deque<String> test = new Deque<String>();
        test.addFirst("middle");
        test.addFirst("first");
        test.addLast("something");
        test.addLast("last");

        for(String s : test)
        {
            System.out.println(s);
        }

        System.out.println("\n");
        System.out.println(test.removeFirst());
        System.out.println(test.removeFirst());
        System.out.println(test.removeFirst());
        System.out.println(test.removeFirst());

    }

    private class Node {

        Item item;
        Node previous, next;

        Node(Item item) {
            this.item = item;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        Node current;

        DequeIterator(final Deque<Item> deque) {
            current = deque.first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            final Node node = current;
            current = current.next;
            return node.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
