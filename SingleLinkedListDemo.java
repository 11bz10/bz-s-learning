package linkedList;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试一把
        //看看代码是不是正确的！
        //先创建几个节点
        HeroNode icon1 = new HeroNode(1,"Yoona","linyuner");
        HeroNode icon2 = new HeroNode(2,"Taeyeon","jintaiyan");
        HeroNode icon3 = new HeroNode(3,"Karina","liuzhimin");
        HeroNode icon4 = new HeroNode(4,"Yeji","hanglizhi");

        HeroNode icon5 = new HeroNode(4,"krystal","zhengxiujing");

        //加入节点之前我们要先创建一个链表！
        SingleLinkedList icons = new SingleLinkedList();
        icons.addByOrder(icon1);
        icons.addByOrder(icon4);
        icons.addByOrder(icon2);
        icons.addByOrder(icon3);
        icons.list();
        //显示一下！
        System.out.println();
        //测试的是修改节点的代码！
        icons.update(icon5);
        icons.list();
        //测试一下删除节点的代码！
        System.out.println();
        icons.delete(4);
        icons.list();

        //测试一下求链表长度的程序
        System.out.println("有效的节点个数="+getLength(icons.getHead()));

        //测试下看看是否得到了倒数第k个元素
        HeroNode res =findLastKNode(icons.getHead(),2);
        System.out.println("res="+res);

        ////测试一下是否可以完成链表的反转
        //System.out.println();
        //reverseList(icons.getHead());
        //System.out.println("反转后的链表为：");
        //icons.list();

        //测试一下利用栈来进行逆序打印！
        System.out.println("逆序打印后的链表形式为:");
        reversePrint(icons.getHead());
        icons.list();//没有改变原链表的结构！只是利用了栈的机制来进行打印！

    }

    //使用方式2：利用栈来进行链表的逆序打印
    public static void reversePrint(HeroNode head){
        if(head.next==null){
            return;//空链表，不能打印
        }
        //创建一个栈，将各个节点压入栈中
        Stack heroNodeStack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while(cur!=null){
            heroNodeStack.push(cur);
            cur=cur.next;
        }
        //然后取出！将栈中的节点进行打印
        while(heroNodeStack.size()>0){
            System.out.println(heroNodeStack.pop());
        }

    }


    //将单链表进行反转！
    public static void reverseList(HeroNode head){
        //如果当前链表为空or只有一个节点，无需反转，直接返回
        if(head.next==null||head.next.next==null){
            return;
        }
        //定义一个辅助指针，帮助我们遍历原来的链表
        HeroNode cur=head.next;
        HeroNode next=null;//指向当前节点[cur]的下一个节点
        HeroNode reverseHead =new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点就将其取出，并放在reverseHead的最前端
        while(cur!=null){
            next=cur.next;//暂时保存当前节点的下一个节点，后面需要使用
            cur.next=reverseHead.next;//剥离出来
            reverseHead.next=cur;//将cur连接到新的链表上！
            cur=next;//让cur后移一下
        }
        //将原来的头部head.next指向reverseHead的头部，实现了反转
        head.next=reverseHead.next;
    }


    //查找单链表中的倒数第k个节点
    //思路：
    //1.编写一个方法接受head节点，同时接受一个index，表示倒数第index个节点
    //2.先把链表从头到尾遍历一下，得到链表的总长度
    //3.得到size后，从链表的第一个开始遍历，遍历（size-index）个就得到了
    //4.找到的话，返回该节点，否则返回null
    public static HeroNode findLastKNode(HeroNode head,int index){
        //判断，如果链表为空，返回空
        if(head.next==null){
            return null;//没有找到！
        }
        //第一次遍历，得到链表的长度
        int size=getLength(head);
        //第二次遍历，只遍历(size-index)下，并将这个节点返回！
        //先做一个index的校验！
        if(index<=0||index>size){
            return null;
        }
        //定义一个辅助变量
        HeroNode cur =head.next;
        for(int i=0;i<size-index;i++){
            cur=cur.next;
        }
        return cur;
    }



    //获取到单链表的节点的个数（如果是有头结点的，不算数）
    //head就是链表的头节点，返回的就是有效节点的个数！
    public static int getLength(HeroNode head){
        if(head.next==null){
            return 0;
        }
        int length =0;
        //定义一个辅助变量,这里没有统计头节点
        HeroNode cur =head.next;
        while(cur!=null){
            length++;
            cur=cur.next;//遍历
        }
        return length;
    }


}

//定义一个SingleLinkedList管理我们的英雄人物
class SingleLinkedList{
    //先初始化一个头结点，一般头节点不动，如果懂的话就找不到了,不存放具体的数据
    private HeroNode head =new HeroNode(0,"","");

    //因为它的head是私有的，写一个方法来返回它的头节点！
    public HeroNode getHead(){
        return head;
    }


    //添加节点到单向链表的方法
    //当不考虑编号的顺序是，找到链表的最后一个节点，讲这个next域指向新的节点就ok
    public void addNode(HeroNode heroNode){
        //因为头节点我们是不能动的，所以就需要一个辅助变量temp来帮助遍历！
        HeroNode temp=head;
        //遍历链表，找到最后的一个点
        while(true){
            //找到链表的最后了！
            if (temp.next ==null){
                break;
            }
            //如果没有找到最后
            temp = temp.next;
        }
        //前面的代码可以保证，当我们退出while循环的时候，temp一定指向了链表的最后！
        temp.next = heroNode;
    }

    //第二种方法在添加时，根据排名的先后来进行添加！有重复就失败！
    public void addByOrder(HeroNode heroNode){
        //因为头节点不可移动，所以我们依然是通过一个辅助指针(变量)来实现！
        //寻找的是添加位置的前一个结点！
        HeroNode temp = head;
        boolean flag = false;//表示添加的编号是否存在，默认为错！
        while(true){
            if(temp.next==null){
                //说明已经到了链表的最后了！
                break;
            }
            if(temp.next.num> heroNode.num){
                //位置就找到了！就在temp的后面插入就对了！
                break;
            }else if(temp.next.num== heroNode.num){
                //说明希望添加的这个编号已经存在了！
                flag =true;
                break;
            }
            temp=temp.next;//后移来实现对当前链表的遍历！
        }
        //判断lag的值
        if(flag){
            //不能添加，因为编号存在
            System.out.printf("准备插入的英雄的编号%d存在\n",heroNode.num);
        }else{
            //加入！
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息（根据编号来修改名字和昵称！）
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据num编号来找，还是需要辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示这个num是否找到了！
        while(true){
            if (temp == null) {
                break;//表示链表已经遍历完毕了！
            }
            if(temp.num == newHeroNode.num){
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag来判断是否找到
        if(flag){
            temp.name= newHeroNode.name;
            temp.nickname= newHeroNode.nickname;
        }else{//根本没有找到这个节点
            System.out.printf("没有找到编号为%d的节点\n",newHeroNode.num);
        }
    }

    //删除节点
    public void delete(int num){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据num编号来找，还是需要辅助变量
        HeroNode temp = head;
        boolean flag=false;//表示是否可以找到这个编号的前一个节点
        while(true){
            if(temp.next==null){
                break;//没有找到想要的节点
            }
            if(temp.next.num==num){
                //找到了
                flag=true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next=temp.next.next;
        }else{
            System.out.printf("没有找到编号为%d的节点\n",num);
        }
    }

    //显示链表（遍历）
    public void list(){
        //先判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //也是需要辅助变量来完成的，因为head节点是不可以动的！
        HeroNode temp = head.next;
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


//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode{
    public int num;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

//    构造器
    public HeroNode(int num,String name,String nickname){
        this.num = num;
        this.name = name;
        this.nickname = nickname;
    }
    //为了显示方法，我们重写了toString()
    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +"'}" ;
    }
}
