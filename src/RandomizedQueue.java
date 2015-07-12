import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by aarondesouza on 28/06/2015.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first, last;
    private int size = 0;
    private int rearIndex = 0;

    public RandomizedQueue() {                          // construct an empty randomized queue

    }


    public boolean isEmpty() {                          // is the queue empty?
        return size == 0;
    }


    public int size() {                                 // return the number of items on the queue
        return size;
    }


    public void enqueue(Item item) {                    // add the item
        if (item == null)
            throw new NullPointerException();

        final Node n = new Node(item);
        if (size == 0) {
            first = n;
            last = n;
        } else {
            last.next = n;
            n.previous = last;
            last = n;
        }
        size++;
    }


    public Item dequeue() {                             // remove and return a random item
        if (isEmpty())
            throw new NoSuchElementException();

        final int index = StdRandom.uniform(size);

        Node current = first;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                Node previous = current.previous;
                Node next = current.next;
                if (previous != null) {
                    previous.next = next;
                    if (index == size - 1)
                        last = previous;
                }
                if (next != null) {
                    next.previous = previous;
                    if (index == 0)
                        first = next;
                }
                if (size == 1) {
                    first = null;
                    last = null;
                }
                break;
            }
            current = current.next;
        }
        size--;
        return current.item;
    }


    public Item sample() {                              // return (but do not remove) a random item
        if (isEmpty())
            throw new NoSuchElementException();
        
        final int index = StdRandom.uniform(size);
        Node current = first;
        for (int i = 0; i <= index; i++) {
            if (i == index)
                break;
            current = current.next;
        }
        return current.item;
    }


    public Iterator<Item> iterator() {                  // return an independent iterator over items in random order
        return new RandomizedQueueIterator(this);
    }


    public static void main(String[] args) {           // unit testing

        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("hello");
        q.enqueue("world");
        q.enqueue("what's");
        q.enqueue("happening");

        for(String s : q) {
            System.out.println(s);
        }

        for (int i = 0; i < 4; i++) {
            System.out.println(q.dequeue());
        }
        System.out.println("is empty is " + q.isEmpty());
    }
    private class Node {
        Item item;
        Node next, previous;

        Node(final Item item) {
            this.item = item;
        }
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] items;
        private int position = 0;

        RandomizedQueueIterator(final RandomizedQueue queue) {
            Node n = queue.first;
            items = (Item[]) new Object[queue.size];
            for (int i = 0; i < queue.size; i++) {
                items[i] = n.item;
                n = n.next;
            }
            StdRandom.shuffle(items);
        }

        @Override
        public boolean hasNext() {
            return position < items.length;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return items[position++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
