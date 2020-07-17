
public class ArrayDeque<T> {

    private T[] items;

    private int size;

    private int nextFirst;

    private int nextLast;

    private static final int INIT_CAPACITY = 8;

    private static final int RFACTOR = 2;

    private static final double MIN_USAGE_RATIO = 0.25;



    /** Creates an empty array deque.

     *  The starting size of your array should be 8.

     */

    public ArrayDeque() {

        items = (T[]) new Object[INIT_CAPACITY];

        size = 0;

        nextFirst = 4;

        nextLast = 5;

    }



    /** Creates a deep copy of other. */

    public ArrayDeque(ArrayDeque other) {

        items = (T[]) new Object[INIT_CAPACITY];

        size = 0;

        nextFirst = 4;

        nextLast = 5;

        for (int i = 0; i < other.size(); i++) {

            addLast((T) other.get(i));

        }

    }



    private int minusOne(int index) {

        return (index - 1 + items.length) % items.length;

    }



    private int plusOne(int index) {

        return (index + 1) % items.length;

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



    /** Adds an item of type T to the front of the deque. */

    public void addFirst(T item) {

        if (size == items.length) {

            resize(size * RFACTOR);

        }



        items[nextFirst] = item;

        size += 1;

        nextFirst = minusOne(nextFirst);

    }



    /** Adds an item of type T to the back of the deque. */

    public void addLast(T item) {

        if (size == items.length) {

            resize(size * RFACTOR);

        }



        items[nextLast] = item;

        size += 1;

        nextLast = plusOne(nextLast);

    }



    /** Returns true if deque is empty, false otherwise. */

    public boolean isEmpty() {

        return size == 0;

    }



    /** Returns the number of items in the deque.

     *  Must take constant time.

     */

    public int size() {

        return size;

    }



    /** Prints the items in the deque from first to last, separated by a space.

     *  Once all the items have been printed, print out a new line.

     */

    public void printDeque() {

        int i = plusOne(nextFirst);



        while (i != nextLast) {

            System.out.print(items[i] + " ");

            i = plusOne(i);

        }

        System.out.println();

    }



    /** Removes and returns the item at the front of the deque.

     *  If no such item exists, returns null.

     */

    public T removeFirst() {

        if (size == 0) {

            return null;

        }



        int first = plusOne(nextFirst);

        T firstItem = items[first];

        items[first] = null;

        nextFirst = first;

        size -= 1;



        if (items.length >= 16 && size < items.length * MIN_USAGE_RATIO) {

            resize(items.length / 2);

        }



        return firstItem;

    }



    /** Removes and returns the item at the back of the deque.

     *  If no such item exists, returns null.

     */

    public T removeLast() {

        if (size == 0) {

            return null;

        }



        int last = minusOne(nextLast);

        T lastItem = items[last];

        items[last] = null;

        nextLast = last;

        size -= 1;



        if (items.length >= 16 && size < items.length * MIN_USAGE_RATIO) {

            resize(items.length / 2);

        }



        return lastItem;

    }



    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.

     *  If no such item exists, returns null.

     *  Must not alter the deque and must take constant time.

     */

    public T get(int index) {

        if (index > size) {

            return null;

        }



        index = (plusOne(nextFirst) + index) % items.length;

        return items[index];

    }

}

//
//public class ArrayDeque<T> {
//
//    private T[] items;
//    private int size;
//    private int nextFirst;
//    private int nextLast;
//
//    public ArrayDeque() {
//        items = (T[]) new Object[8];
//        size = 0;
//        nextFirst = 0;
//        nextLast = 1;
//    }
//
//    /** Computes the index immediately before a given index */
//    private int minusOne(int index) {
//        if (index == 0) {
//            return (items.length - 1); //before 0 is the last item
//        } else {
//            return index - 1;
//        }
//    }
//
//    /** Computes the index immediately after a given index */
//    private int plusOne(int index) {
//        if (index == (items.length - 1)) {
//            return 0; // after the last item is 0
//        } else {
//            return index + 1;
//        }
//    }
//
//    private boolean isFull() {
//        return size == items.length;
//    }
//
//    private void resize(int capacity) {
//
//        T[] newArray = (T[]) new Object[capacity];
//
//        int curr = plusOne(nextFirst);
//
//        for (int i = 0; i < size; i++) {
//
//            newArray[i] = items[curr];
//
//            curr = plusOne(curr);
//        }
//
//        items = newArray;
//
//        nextFirst = capacity - 1;
//
//        nextLast = size;
//    }
//
//    public void addFirst(T item) {
//        if (isFull()) {
//            resize(size * 2);
//        }
//        if (items[nextFirst] == null) {
//            items[nextFirst] = item;
//            nextFirst = minusOne(nextFirst);
//            size++;
//        }
//    }
//
//    /** Inserts X into the back of the list. */
//    public void addLast(T item) {
//        if (isFull()) {
//            resize(size * 2);
//        }
//        if (items[nextLast] == null) {
//            items[nextLast] = item;
//            nextLast = plusOne(nextLast);
//            size++;
//        }
//    }
//
//    public boolean isEmpty() {
//        return (size == 0);
//    }
//
//    /** Gets the ith item in the list (0 is the front). */
//    public T get(int i) {
//        if (i > size){
//            return null;
//        }
//        int index = (plusOne(nextFirst) + i) % items.length;
//        return items[index];
//    }
//
//    /** Returns the number of items in the list. */
//    public int size() {
//        return size;
//    }
//
//    public void printDeque() {
//        for (int p = plusOne(nextFirst); p != nextLast; p = plusOne(p)) {
//            System.out.print(items[p] + " ");
//        }
//        System.out.println();
//    }
//
//    public T removeFirst() {
//        if (size == 0){
//            return null;
//        }
//        int first = plusOne(nextFirst);
//        T item = get(first);
//        items[first] = null;
//        nextFirst = first;
//        size = size - 1;
//        return item;
//    }
//
//    /** Deletes item from back of the list and
//     * returns deleted item. */
//    public T removeLast() {
//        if (size == 0){
//            return null;
//        }
//
//        int last = minusOne(nextLast);
//        T item = get(last);
//        items[last] = null;
//        nextLast = last;
//        size = size - 1;
//
//        return item;
//    }
//}
//
