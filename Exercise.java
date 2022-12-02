package linkedList;

public class Exercise {
    public static void main(String[] args) {
        SingleLinkedListMerge singleLinkedListMerge1 = new SingleLinkedListMerge();
        singleLinkedListMerge1.addByOrder(new Node(1));
        singleLinkedListMerge1.addByOrder(new Node(3));
        singleLinkedListMerge1.addByOrder(new Node(2));
        singleLinkedListMerge1.addByOrder(new Node(4));
        singleLinkedListMerge1.addByOrder(new Node(5));

        System.out.println("第一个链表");
        singleLinkedListMerge1.show();

        SingleLinkedListMerge singleLinkedListMerge2 = new SingleLinkedListMerge();
        singleLinkedListMerge2.addByOrder(new Node(1));
        singleLinkedListMerge2.addByOrder(new Node(3));
        singleLinkedListMerge2.addByOrder(new Node(6));
        singleLinkedListMerge2.addByOrder(new Node(8));
        singleLinkedListMerge2.addByOrder(new Node(7));

        System.out.println("第二个链表");
        singleLinkedListMerge2.show();

        //合并后的链表
        withList(singleLinkedListMerge1.getHead(), singleLinkedListMerge2.getHead());
        System.out.println("合并后的链表");
        singleLinkedListMerge2.show();

    }
    public static Node withList(Node head1, Node head2) {  //还是要在主方法中来建立方法！
        if (head1 == null && head2 == null){
            System.out.println("两个链表为空");
            return null;
        }
        //下面是如果两个链表中有一个是空的话，就返回另外一个！
        if (head1 == null){
            System.out.println("head2");
            return head2;
        }
        if (head2 == null){
            System.out.println("head1");
            return head1;
        }
        Node head;
        //利用递归来解决的！
        if (head1.no > head2.no) {
            head = head2;
            head.next = withList(head1, head2.next);

        } else {
            head = head1;
            head.next = withList(head1.next, head2);
        }
        return head;
    }
}

class SingleLinkedListMerge {
    //先初始化一个头结点,头结点不能动
    private Node head = new Node(0);//定义链表的！

    public Node getHead() {
        return head;
    }

    public void addByOrder(Node node) {
        Node temp = head;
        boolean flag = false;
        while(true) {
            if (temp.next == null) {
                break;//这个就是直接空链表
            }
            if (temp.next.no > node.no) {
                break;//如果下一个节点比想加进去的节点大，那很好，准备往里面加，跳出循环
            } else if (temp.next.no == node.no) {
                flag = true;//如果相等的话，那我们就直接设置为true，已经存在了
                break;
            }
            temp = temp.next;//在循环内部进行向后的遍历
        }
        if (flag) {
            System.out.printf("欲插入的编号%d 已经存在不能加入\n",node.no);
        }else {
            node.next = temp.next;//在上面的条件都过了的情况下，选择了插入进去！
            temp.next = node;
        }
    }

    public void show() {  //遍历来显示链表的节点！
        Node temp = head.next;
        if (temp == null) {
            System.out.println("该链表为空");
            return;
        }
        while(temp != null) {
            System.out.println(temp.no);
            temp = temp.next;
        }
    }
}

//定义HeroNode,每个HeroNode对象就是一个节点
class Node {
    public int no;
    public Node next;//next是对象变量，凡是对象变量,保存的皆是指针

    public Node() {
    }
    //构造器
    public Node(int no) {
        this.no = no;
    }


    //为了显示方法，重写toString方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                '}';
    }
}

