public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private T[] items;
    private int head;
    private int tail;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        size = 0;
        capacity = 8;
        items = (T[]) new Object[capacity];
        head = 0;
        tail = 0;
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(head + i) % capacity];
        }
        items = newArray;
        head = 0;
        tail = size;
        capacity = newCapacity;
    }

    public void addFirst(T item) {
        if (size == capacity) {
            resize(size * 2);
        }
        head = (head - 1 + capacity) % capacity;  // 环绕回到数组末尾
        items[head] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == capacity) {
            resize(size * 2);
        }
        items[tail] = item;
        tail = (tail + 1) % capacity;  // 环绕回到数组开头
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(head + i) % capacity] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[head];
        items[head] = null;
        head = (head + 1) % capacity;  // 环绕回到数组开头
        size--;
        if (size > 0 && size == capacity / 4) {
            resize(capacity / 2);
        }
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        tail = (tail - 1 + capacity) % capacity;  // 环绕回到数组末尾
        T item = items[tail];
        items[tail] = null;
        size--;
        if (size > 0 && size == capacity / 4) {
            resize(capacity / 2);
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(head + index) % capacity];
    }
}
