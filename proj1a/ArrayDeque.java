
public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Computes the index immediately before a given index */
    private int minusOne(int index) {
        if (index == 0) {
            return (items.length - 1); //before 0 is the last item
        } else {
            return index - 1;
        }
    }

    /** Computes the index immediately after a given index */
    private int plusOne(int index) {
        if (index == (items.length - 1)) {
            return 0; // after the last item is 0
        } else {
            return index + 1;
        }
    }

    private boolean isFull() {
        return size == items.length;
    }

    private void resizing(int s) {
        T[] a = (T[]) new Object[s];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public void addFirst(T item) {
        if (isFull()) {
            resizing(size * 2);
        }
        if (items[nextFirst] == null) {
            items[nextFirst] = item;
            nextFirst = minusOne(nextFirst);
            size++;
        }
    }

    /** Inserts X into the back of the list. */
    public void addLast(T item) {
        if (isFull()) {
            resizing(size * 2);
        }
        if (items[nextLast] == null) {
            items[nextLast] = item;
            nextLast = plusOne(nextLast);
            size++;
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    /** Gets the ith item in the list (0 is the front). */
    public T get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    public void printDeque() {
        for (int p = plusOne(nextFirst); p != nextLast; p = plusOne(p)) {
            System.out.print(items[p] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        T item = get(nextFirst + 1);
        size = size - 1;
        return item;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        T item = get(nextLast - 1);
        size = size - 1;
        return item;
    }
}
