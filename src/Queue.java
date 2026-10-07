package algs4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {

        Node<Item> oldLast = last;

        last = new Node<>();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

        n++;
    }

    public Item dequeue() {

        if (isEmpty()) {
            throw new NoSuchElementException("Fila vazia.");
        }

        Item item = first.item;

        first = first.next;
        n--;

        if (isEmpty()) {
            last = null;
        }

        return item;
    }

    @Override
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {

            private Node<Item> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Item item = current.item;
                current = current.next;

                return item;
            }
        };
    }
}
