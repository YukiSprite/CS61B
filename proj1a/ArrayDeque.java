public class ArrayDeque<T>  {
    private int size;
    private T[] items;
    private int head;
    private int tail;
    private int capacity;
    public ArrayDeque() {
        size = 0;
        items = (T []) new Object[8];
        head = 0;
        tail = 0;
        capacity = 8;
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
    public void addFirst( T item ){
        if( size >= (items.length-1) ){
            resize(size * 2);
        }
        head--;
        if(head<0){
            head = capacity-1;
        }
        items[head] = item;
        size++;
    }
    public void addLast(T item){
        if( size >= (items.length-1) ){
            resize(size*2);
        }
        tail++;
        if(tail>(capacity-1)){
            tail = 0;
        }
        items[tail] = item;
        size++;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for(int i = 0; i < items.length; i++){
            System.out.print(items[i]+" ");
        }
    }
    public T removeFirst(){
        if(size==0){
            return null;
        }
        T item = items[head];
        items[head] = null;
        head++;
        if( head > (capacity-1)){
            head = 0;
        }
        if (size > 0 && size == capacity / 4) {
            resize(capacity / 2);
        }
        size--;
        return item;

    }
    public T removeLast(){
        if(size==0){
            return null;
        }
        T item = items[tail];
        items[tail] = null;
        tail--;
        if(tail<0){
            tail = capacity-1;
        }
        if (size > 0 && size == capacity / 4) {
            resize(capacity / 2);
        }
        size--;
        return item;
    }
    public T get(int index){
        return items[(head+index)%capacity];
    }

}
