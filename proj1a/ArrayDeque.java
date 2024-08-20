public class ArrayDeque<T>  {
    private int size;
    private T[] items;
    ArrayDeque() {
        size = 0;
        items = (T []) new Object[8];
    }
    public void resize(int capacity){
        T[] tmpItems = (T []) new Object[capacity];
        System.arraycopy(items, 0, tmpItems, 0, items.length);
        items = tmpItems;
    }
    public void addFirst(T item){
        if(size==items.length){
            resize(size*2);
        }
        T[] tmpItems = (T[]) new Object[size];
        System.arraycopy(items,0,tmpItems,1,items.length);
        tmpItems[0] = item;
        items = tmpItems;
    }
    public void addLast(T item){
        if(size==items.length){
            resize(size*2);
        }
        T[] tmpItems = (T[]) new Object[size];
        System.arraycopy(items,0,tmpItems,0,items.length);
        tmpItems[items.length-1] = item;
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
        T[] tmpItems = (T []) new Object[size];
        T item = items[0];
        System.arraycopy(items,1,tmpItems,0,size-1);
        items = tmpItems;
        if(items.length<(size/4)){
            resize(size/2);
        }
        return item;
    }
    public T removeLast(){
        T[] tmpItems = (T []) new Object[size];
        T item = items[size-1];
        System.arraycopy(items,0,tmpItems,0,size-1);
        items = tmpItems;
        if(items.length<(size/4)){
            resize(size/2);
        }
        return item;
    }
    public T get(int index){
        return items[index];
    }

}
