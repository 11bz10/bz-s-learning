package linkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //双向链表代码的测试
        System.out.println("双向链表的测试:");
        HeroNode2 icon1 = new HeroNode2(1,"Yoona","linyuner");
        HeroNode2 icon2 = new HeroNode2(2,"Taeyeon","jintaiyan");
        HeroNode2 icon3 = new HeroNode2(3,"Karina","liuzhimin");
        HeroNode2 icon4 = new HeroNode2(4,"Yeji","huanglizhi");

        DoubleLinkedList doubleLinkedList=new DoubleLinkedList();
        //doubleLinkedList.addNode(icon1);
        //doubleLinkedList.addNode(icon2);
        //doubleLinkedList.addNode(icon3);
        //doubleLinkedList.addNode(icon4);


        //按顺序进行添加！
        doubleLinkedList.addByOrder(icon3);
        doubleLinkedList.addByOrder(icon2);
        doubleLinkedList.addByOrder(icon4);
        doubleLinkedList.addByOrder(icon1);
        doubleLinkedList.list();


        //System.out.println("双向链表更新后的:");
        //HeroNode2 icon5 = new HeroNode2(4,"Krystal","zhengxiujing");
        //doubleLinkedList.update(icon5);
        //doubleLinkedList.list();
        //
        ////删除
        //System.out.println("双向链表删除后的:");
        //doubleLinkedList.delete(3);
        //doubleLinkedList.delete(2);
        //doubleLinkedList.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头结点，一般头节点不动，如果懂的话就找不到了,不存放具体的数据
    private HeroNode2 head =new HeroNode2(0,"","");

    //因为它的head是私有的，写一个方法来返回它的头节点！
    public HeroNode2 getHead(){
        return head;
    }

    //添加节点到双向链表
    //当不考虑编号的顺序是，找到链表的最后一个节点，讲这个next域指向新的节点就ok
    public void addNode(HeroNode2 heroNode){
        //因为头节点我们是不能动的，所以就需要一个辅助变量temp来帮助遍历！
        HeroNode2 temp=head;
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
        heroNode.pre = temp;
    }

    //第二种方法在添加时，根据排名的先后来进行添加！有重复就失败！
    public void addByOrder(HeroNode2 heroNode){
        //因为头节点不可移动，所以我们依然是通过一个辅助指针(变量)来实现！
        //寻找的是添加位置的前一个结点！
        HeroNode2 temp = head;
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
        //判断flag的值
        if(flag){
            //不能添加，因为编号存在
            System.out.printf("准备插入的英雄的编号%d存在\n",heroNode.num);
        }else{
            //加入！
            if(temp.next==null){
                temp.next=heroNode;
                heroNode.pre=temp;
            }else{
                heroNode.pre=temp;
                heroNode.next = temp.next;
                heroNode.next.pre=heroNode;
                heroNode.pre.next=heroNode;
            }
        }
    }

    //修改一个结点的内容！双向链表的内容基本上和前面的单向链表是一样的！
    //只是节点的类型不一样而已！
    //修改节点的信息（根据编号来修改名字和昵称！）
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据num编号来找，还是需要辅助变量
        HeroNode2 temp = head.next;
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

    //从双向链表中删除一个节点！
    public void delete(int num){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据num编号来找，还是需要辅助变量
        //多于双向链表我们直接找到就删除！
        HeroNode2 temp = head.next;//辅助指针！
        boolean flag=false;//表示是否可以找到这个编号的前一个节点
        while(true){
            if(temp==null){
                break;//没有找到想要的节点
            }
            if(temp.num==num){
                //找到了
                flag=true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            //删除节点的代码！
            temp.pre.next=temp.next;
            //这里我们的代码是有风险的！加入删除的是最后一个节点！
            //如果是最后一个节点就不需要执行下面这句话！否则会出现空指针异常！
            if(temp.next!=null){
                temp.next.pre=temp.pre;
            }
        }else{
            System.out.printf("没有找到编号为%d的节点\n",num);
        }
    }


    //遍历双向链表的方法
    //显示链表（遍历）
    public void list(){
        //先判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //也是需要辅助变量来完成的，因为head节点是不可以动的！
        HeroNode2 temp = head.next;
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

//定义HeroNode2,每个HeroNode对象就是一个节点
class HeroNode2{
    public int num;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点，默认为空
    public HeroNode2 pre; //指向上一个节点，默认为空

    //    构造器
    public HeroNode2(int num,String name,String nickname){
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