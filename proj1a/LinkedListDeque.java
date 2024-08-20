public class LinkedListDeque<T> {
    public class Node{
        T value;
        Node next;
        Node prev;
        public Node(T value){
            this.value = value;
            this.next=null;
            this.prev=null;
        }
    }
    private Node head;
    private Node last;
    private int size;
    public LinkedListDeque() {
        head = null;
        last = null;
        size = 0;
    }
    public void addFirst(T item) {
        Node newNode = new Node(item);
        if (head==null) {
            // 当链表为空时，head 和 last 都指向新节点
            head = newNode;
            last = newNode;
        } else {
            // 插入新节点到链表的头部
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    public void addLast(T item){
        Node newNode = new Node(item);
        if(head==null){
            head = newNode;
            last = newNode;
        }
        else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }
    public boolean isEmpty(){
        return head==null;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for(Node p = head;p!=null;p=p.next){
            System.out.print(p.value+" ");
        }
    }
    public T removeFirst(){
        T tmp = head.value;
        head = head.next;
        return tmp;
    }
    public T removeLast(){
        T tmp = last.value;
        last.prev = last;
        return tmp;
    }
    public T get(int index){
        int i = 0;
        Node p = head;
        while(i<index){
            p = p.next;
        }
        return p.value;
    }
    public T getRecursive(int index){
        Node p = head;
        return realGetRecursive(index,p);
    }
    public T realGetRecursive(int index,Node p){
        if (index > 0) {
            return realGetRecursive(index - 1, p.next);
        }
        else{
            return p.value;
        }
    }
}
