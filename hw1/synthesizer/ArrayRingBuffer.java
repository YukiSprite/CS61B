// TODO: Make sure to make this class a part of the synthesizer package:done
 package synthesizer;
import org.hamcrest.internal.ArrayIterator;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public:done
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>:done
public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
            rb[last] = x;
            last = (last + 1) % capacity;
            fillCount++;
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T tmp = rb[first];
        rb[first] = null;
        first++;
        if(first == capacity) {
            first = 0;
        }
        fillCount--;
        return tmp;
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        return rb[first];
        // TODO: Return the first item. None of your instance variables should change.
    }
    @Override
    public int fillCount() {
        return fillCount;
    }
    @Override
    public int capacity(){
        return capacity;
    }
    // TODO: When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        return new myIterator();
    }
   private class  myIterator implements Iterator<T>  {
        private int pos;
        public myIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < capacity;
        }
        @Override
        public T next() {
            T returnValue = rb[pos];
            pos++;
            return returnValue;
        }
    }
}
