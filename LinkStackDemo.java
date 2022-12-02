package stack;

public class LinkStackDemo {
    public static void main(String[] args) {
        ListStack listStack = new ListStack();
        listStack.push(5);
        listStack.push(6);
        listStack.push(9);
        System.out.println("出栈前");
        listStack.list();
        System.out.println("出栈后");
        listStack.pop();
        listStack.pop();
        listStack.list();
    }
}

class ListStack {
    //定义一个头结点
    Node head = new Node(0);
    //定义一个辅助变量
    Node temp = head;

    //判空
    public boolean isEmpty() {
        return head.next == null;
    }

    //入栈
    public void push(int value) {
        Node node = new Node(value);
        temp.next = node;
        temp = temp.next;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        Node pre = head;
        while (true) {
            //让pre指向temp的前一个节点  ,然后把temp出栈后temp前移
            if (pre.next == temp) {
                break;
            }
            pre = pre.next;
        }
        pre.next = temp.next;
        int value = temp.no;
        temp = pre;
        return value;
    }

    public void list() {
        Node temp = head;
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        while (true) {
            if (temp.next == null) {
                break;
            }
            System.out.println(temp.next.no);
            temp = temp.next;
        }
    }

}

//链表  定义节点
class Node {
    public int no;
    public Node next;//指向下一个节点

    public Node(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }
}
