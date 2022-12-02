package linkedList;

public class TestMerge {
    public static void main(String[] args) {
        SingleNumLinkedList l1 = new SingleNumLinkedList();
        SingleNumLinkedList l2 = new SingleNumLinkedList();
        SingleNumLinkedList mergeL = new SingleNumLinkedList();


        IntNode i1=new IntNode(1);
        IntNode i2=new IntNode(2);
        IntNode i5=new IntNode(5);
        IntNode i3=new IntNode(3);
        IntNode i4=new IntNode(4);
        IntNode i7=new IntNode(7);

        l1.addNode(i1);
        l1.addNode(i2);
        l1.addNode(i5);

        l2.addNode(i3);
        l2.addNode(i4);
        l2.addNode(i7);

        l1.list();
        System.out.println();
        l2.list();

        SingleNumLinkedList.MergeLinkedList(l1.getHead(),l2.getHead());

    }
}
class SingleNumLinkedList {
    //先初始化一个头结点，一般头节点不动，如果懂的话就找不到了,不存放具体的数据
    private IntNode head = new IntNode(0);

    //因为它的head是私有的，写一个方法来返回它的头节点！
    public IntNode getHead() {
        return head;
    }


    //合并两个链表 按顺序
    public static IntNode MergeLinkedList(IntNode h1,IntNode h2){
        h1 =new IntNode(0);
        h2 =new IntNode(0);
        IntNode cur1 =h1.next;
        IntNode cur2 =h2.next;
        IntNode h =new IntNode(0);
        IntNode cur =h.next;
        while(h1.next!=null||h2.next!=null){
            if(cur1.num<cur2.num){
                cur=cur1;
                cur=cur.next;
                cur1=cur1.next;
            }else{
                cur=cur2;
                cur=cur.next;
                cur2=cur.next;
            }
        }
        if(cur1.next==null){
            cur.next=cur2;
        }else{
            cur.next=cur1;
        }
        return h.next;
    }

    public void addNode(IntNode intNode) {
        //因为头节点我们是不能动的，所以就需要一个辅助变量temp来帮助遍历！
        IntNode temp = head;
        //遍历链表，找到最后的一个点
        while (true) {
            //找到链表的最后了！
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后
            temp = temp.next;
        }
        //前面的代码可以保证，当我们退出while循环的时候，temp一定指向了链表的最后！
        temp.next = intNode;
    }

    //显示链表（遍历）
    public void list(){
        //先判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //也是需要辅助变量来完成的，因为head节点是不可以动的！
        IntNode temp = head.next;
        while(true){
            //判断是否到链表最后了
            if(temp==null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将next后移
            temp=temp.next;
        }
    }
}

//定义一个intNode，每个对象就是一个节点！
class IntNode{
    public int num;
    public IntNode next;

    public IntNode(int num){
        this.num=num;
    }

    @Override
    public String toString() {
        return "intNode{" +
                "num=" + num + '}';
    }
}


