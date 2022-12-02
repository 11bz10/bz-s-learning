package queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        Arrayqueue queue1 = new Arrayqueue(3);
        char key = ' '; //接受用户的输入
        Scanner scanner = new Scanner(System.in);//定义了一个扫描器，可以接受
        boolean loop = true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列的头部数据");
            key = scanner.next().charAt(0);//接受一个字符
            switch (key){
                case 's':
                    queue1.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    queue1.addQueue(value);
                    break;
                case 'g'://取出数据
                    //这里还要做一个错误的处理
                    try{
                        int res = queue1.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                        //如果取得时候直接抛出了异常，那么上面这句话是不会执行的！
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据是什么！
                    try {
                        int res = queue1.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e'://退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

//使用数组模拟队列，首先编写一个类ArrayQueue
class Arrayqueue{
    private int maxSize; //表示数组的最大容量
    private int front; //指向队列的头部
    private int rear; //指向队列的尾部
    private int[] arr;//该数组用于存放数据，模拟队列

    //1.创建队列的构造器
    public Arrayqueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr =new int[maxSize];
        front = -1;//指向队列头部，前一个位置，不包含头部
        rear = -1;//指向队列的尾部具体位置，包含尾部数据
    }

    //判断队列是否满了
    public boolean isFull(){
        return rear == maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
    //    先判断队列是否已经满了
        if(isFull()){
            System.out.println("队列满，不能加入数据~");
            return;
        }
        rear++;//让rear后移
        arr[rear]=n;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否为空
        if(isEmpty()){
            //通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");//这里会自动终止，马上返回了，不需要再加return
        }
        front++;//让这个指针后移动一下
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        //简单的遍历
        if(isEmpty()){
            System.out.println("队列为空，无法遍历");
        }
        for(int i=0;i<maxSize;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列的头部的数据，注意不是取出数据
    public int headQueue(){
        //判断
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法遍历");
        }
        return arr[front+1];
    }
}
