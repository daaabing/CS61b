public class LinkedListDeque<T> {
    /** Invariant:
     * 1. sentinel reference always points to a sentinel node
     * 2. The first node (if exist) is always sentinel.next
     * 3. size variable is always the total number of items that have been added */

    private final IntNode sentinel;
    private int size;

    private class IntNode {
        public IntNode prev;
        public final T item;
        public IntNode next;

        public IntNode(IntNode p, T i, IntNode n){
            prev = p;
            item = i;
            next = n;
        }
    }

    /**  Creates an empty linked list deque. Circular sentinel*/
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        //create node and set it as next for the sentinel node
        sentinel.next = new IntNode(sentinel, item, sentinel.next);
        //set prev for the node after the new added node
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item){
        sentinel.prev.next = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public int size(){
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque(){
        IntNode p = sentinel;
        while (p.next != sentinel){
            System.out.print(p.next.item);
            System.out.print(' ');
            p = p.next;
        }
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst(){
        T p = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return p;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast(){
        T p = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return p;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index){
        IntNode p = sentinel;
        int i = 0;
        while (i < index) {
            p = p.next;
            i += 1;
        }
        return p.item;
    }


    public T getRecursive(int index){
        return _getRecursive(index, sentinel);
    }

    private T _getRecursive(int index, IntNode p) {
        if (index == 0) {
            return p.item;
        }
        return _getRecursive(index - 1, p.next);
    }
}