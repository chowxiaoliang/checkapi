package algorithm;

public class LinkedNode<T> {

    class Node {
        /**
         * 数据信息
         */
        private T data;

        /**
         * 下一个节点
         */
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(T data) {
            this(data, null);
        }
    }

    /**
     * 头节点
     */
    private Node head;

    /**
     * 链表元素个数
     */
    private int size;

    public LinkedNode(){
        this.head = null;
        this.size = 0;
    }

    /**
     * 获取链表元素的个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 判断链表元素是否为空
     * @return
     */
    private boolean isEmpty(){
        return size == 0;
    }

    /**
     *
     * @param data
     */
    public void addFirst(T data){
            // 节点元素
            Node node = new Node(data);
            // 赋值引用
            node.next = this.head;
            // 赋值节点
            this.head = node;
            size ++;
        }

    /**
     * 向链表中间添加元素
     * @param data
     * @param index
     */
    public void add(T data, int index){

        if (index < 0 || index > size){
            throw new IllegalArgumentException("index is error!");
        }

        if (index == 0){
            addFirst(data);
        }

        // 当前要插入的节点
        Node currentNode = new Node(data);

        Node preNode = this.head;
        // 找到要插入节点的前一个节点
        for (int i = 0;i<index-1; i++){
            preNode = preNode.next;
        }

        // 找到要插入节点的后一个节点
        Node nextNode = preNode.next;

        // 插入节点
        preNode.next = currentNode;
        currentNode.next = nextNode;

        size ++;
    }

    public static void main(String[] args) {

        LinkedNode<Integer> linkedNode = new LinkedNode<>();
        for (int i=0;i< 100;i++){
            linkedNode.add(i+i, i);
        }

        System.out.println(linkedNode.size);

        while(linkedNode.head != null){
            System.out.println(linkedNode.head.data);
            linkedNode.head = linkedNode.head.next;
        }
    }
}
