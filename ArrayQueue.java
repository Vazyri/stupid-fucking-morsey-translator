/**
 * Morsey
 * ArrayQueue class of generic type K implements Queue of generic type K
 * @author Vazyboi
 */
public class ArrayQueue<K> implements Queue<K> {
    private static final int CAP = 1000; // defaut array capacity
    private K[] data;
    private int f = 0; // index of front element
    private int sz = 0; // current number of elements

    /**
     * Constructs empty queue with default capacity.
     */
    public ArrayQueue() {
        data = (K[]) new Object[CAP];
    }

    /**
     * Constructs empty queue with provided capacity.
     * 
     * @param capacity integer value
     */
    public ArrayQueue(int capacity) {
        data = (K[]) new Object[capacity];
    }

    /**
     * Returns int expressing amount of elements in queue.
     * 
     * @return Integer value
     */
    public int size() {
        return sz;
    }

    /**
     * Returns boolean value based on whether queue is empty.
     * 
     * @return boolean value
     */
    public boolean isEmpty() {
        return sz == 0;
    }

    /**
     * Inserts provided element at rear of queue.
     * 
     * @param k generic type K value
     */
    public void enqueue(K k) throws IllegalStateException {
        if(sz == data.length) {
            throw new IllegalStateException("Queue is full! AHHHH!!!");
        }

        int avail = (f + sz) % data.length; // access next available position in queue
        data[avail] = k; // assign position to provided k
        sz++;
    }

    /**
     * Returns first element in queue (null if empty).
     * 
     * @return generic type K value
     */
    public K first() {
        if(isEmpty()) {
            return null;
        }

        return data[f];
    }

    /**
     * Removes and returns first element of queue (null if empty).
     * 
     * @return generic type K value
     */
    public K dequeue() {
        if(isEmpty()) {
            return null;
        }

        K temp = data[f]; // garbage collection
        data[f] = null;
        f = (f+1) % data.length;
        sz--;

        return temp;
    }

    /**
     * Returns a String representation of ArrayQueue. Includes all elements in Queue.
     * 
     * @return String value
     */
    public String toString() {
        StringBuilder print = new StringBuilder();
        
        for(int i = 0; i<data.length; i++) {
            print.append(data[i] + ", ");
        }
        print.delete(print.length()-2, print.length()-1);

        return print.toString();
    }
    
}
