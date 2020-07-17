
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

    private void resize(int capacity) {

        T[] newArray = (T[]) new Object[capacity];

        int curr = plusOne(nextFirst);

        for (int i = 0; i < size; i++) {

            newArray[i] = items[curr];

            curr = plusOne(curr);
        }

        items = newArray;

        nextFirst = capacity - 1;

        nextLast = size;
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize(size * 2);
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
            resize(size * 2);
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
        if (i > size){
            return null;
        }
        int index = (plusOne(nextFirst) + i) % items.length;
        return items[index];
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
        if (size == 0){
            return null;
        }
        int first = plusOne(nextFirst);
        T item = get(first);
        items[first] = null;
        nextFirst = first;
        size = size - 1;
        return item;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        if (size == 0){
            return null;
        }

        int last = minusOne(nextLast);
        T item = get(last);
        items[last] = null;
        nextLast = last;
        size = size - 1;

        return item;
    }
}
