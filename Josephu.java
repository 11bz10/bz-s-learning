package linkedList;

public class Josephu {
    public static void main(String[] args) {
        //测试一把，看看构建and遍历是不是ok的？
        CircleSingleLinkedList circleSingleLinkedList=new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试一把小孩出圈是否正确！
        System.out.println();
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表！
class CircleSingleLinkedList{
    //创建一个first节点！
    private Boy first=null;
    //添加小孩节点，构建一个环形的链表
    public void addBoy(int nums){
        //对nums进行一个数据校验！
        if(nums<1){
            System.out.println("Numbers error!");
            return;
        }
        //使用一个for循环创建我们的环形链表！
        Boy curBoy = null;//辅助指针，帮助构建环形链表！
        for(int i=1;i<=nums;i++){
            //根据编号创建小孩节点！
            Boy boy=new Boy(i);
            //第一个小孩要单独考虑一下
            if(i==1){
                first=boy;
                first.setNext(first);//这句话就可以构成一个环状;只是这个环状里面只有一个小孩！
                curBoy=first;//让curBoy指向第一个小孩！
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }

    //遍历当前的环形链表！
    public void showBoy(){
        //先判断链表是否为空
        if(first==null){
            System.out.println("List empty!");
            return;
        }
        //因为first不能动，仍然需要使用一个辅助指针！
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩的编号%d\n",curBoy.getNum());
            if(curBoy.getNext()==first){
                break;
            }
            curBoy=curBoy.getNext();
        }
    }

    //环形链表中出圈的动作写在这里！根据用户的输入计算出小孩出圈的顺序
    /*
    * startNum 从第几个小孩开始
    * countNum 数几下
    * nums 最初有几个在圈里
    */
    public void countBoy(int startNum,int countNum,int nums){
        //先对数据进行校验
        if(first==null||startNum<1||startNum>nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        Boy helper =first;
        //先让helper指向链表最后的那个节点！
        while(true){
            if(helper.getNext()==first){//说明指向了最后一个节点！
                break;
            }
            helper=helper.getNext();
        }
        //小孩报数前，先让两个指针移动k-1次
        for(int j=0;j<startNum-1;j++){
            first=first.getNext();
            helper=helper.getNext();
        }
        //当小孩报数时候，让first和helper指针同时移动m-1次，然后出圈
        //这里是一个循环的操作，直到圈中只有一个节点
        while(true){
            if(helper==first){
                break;//说明圈中只有一个人了！
            }
            //让first和helper同时移动countNum-1次数
            for(int j=0;j<countNum-1;j++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时候first指向的节点就是我们想要出圈的小孩，所以要开始进行操作
            System.out.printf("小孩%d出圈\n",first.getNum());
            //将其出圈！
            first=first.getNext();
            helper.setNext(first);//完成了连接！
        }
        System.out.printf("最后留在圈中的小孩编号是%d\n",helper.getNum());
    }


}
//先定义一个节点，小孩节点！
class Boy{
    private int num;
    private Boy next;
    //写一个构造方法
    public Boy(int num){
        this.num=num;
    }
    public int getNum() {
        return num;
    }
    public Boy getNext() {
        return next;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public void setNext(Boy next) {
        this.next = next;
    }
}