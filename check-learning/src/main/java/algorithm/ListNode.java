package algorithm;

/**
 * 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class ListNode {

    public static void main(String[] args) {
        algorithm.ListNode listNode = new algorithm.ListNode();
        algorithm.ListNode listNode1 = new algorithm.ListNode();

        listNode.addData(2).addData(4).addData(3);
        listNode1.addData(5).addData(6).addData(4);

        ListNode result = listNode.getListByTwoListNode(listNode, listNode1);
        Node temp = result.head.next;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }

    }

    private ListNode getListByTwoListNode(ListNode listNode1, ListNode listNode2){
        Node node = new Node();
        ListNode result = new ListNode(node);

        Node listTempNode1 = listNode1.head;
        Node listTempNode2 = listNode2.head;

        if (listTempNode1 == null && listTempNode2 == null){
            return result;
        }

        if (listTempNode1 == null || listTempNode2 == null){
            return listTempNode1 == null ? listNode2 : listNode1;
        }

        // 进位
        int carry = 0;
        Node resultTempNode = result.head;
        while (listTempNode1 != null || listTempNode2 != null || carry != 0){
            // 创建一个临时节点用于最终追加到结果链表
            Node tempNode = new Node();
            int sum = (listTempNode1 == null ? 0 : listTempNode1.data) + (listTempNode2 == null ? 0 : listTempNode2.data) + carry;
            // 进位
            carry = sum / 10;
            // 当前位
            int current = sum % 10;
            tempNode.data = current;
            listTempNode1 = listTempNode1 == null ? listTempNode1 : listTempNode1.next;
            listTempNode2 = listTempNode2 == null ? listTempNode2 : listTempNode2.next;
            resultTempNode.next = tempNode;
            resultTempNode = resultTempNode.next;
        }
        return result;
    }

    /**
     * 头节点
     */
    Node head;
    /**
     * 链表大小
     */
    int size = 0;

    public ListNode(Node head){
        this.head = head;
        size ++;
    }

    public ListNode(){

    }

    /**
     * 向链表尾部追加数据
     * @param data
     */
    public ListNode addData(int data){
        Node node = new Node(data);
        if (this.head == null){
            this.head = node;
        }else {
            Node tempNode = this.head;
            while (tempNode.next != null){
                tempNode = tempNode.next;
            }
            tempNode.next = node;
        }
        size ++;
        return this;
    }

    class Node{
            int data;
            Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

        public Node(int data){
            this(data, null);
        }

        public Node(){}

    }
}
