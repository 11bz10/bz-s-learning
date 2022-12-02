package stack;

public class Calculator {
    public static void main(String[] args) {
        //根据前面的思路完成这个表达式的计算！
        String expression = "30+2*6-2";
        //创建两个栈，一个是数栈，一个是符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量！
        int index = 0;//用于进行扫描的索引！
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描到的字符保存到这个char之中！
        String keepNum = "";//用于拼接多位数的！
        //开始用while循环来扫描这个表达式！
        while (true) {
            //以此得到每一个字符
            ch = expression.substring(index, index + 1).charAt(0);//拿到了每一个的字符！
            //判断ch，然后做相应的处理！
            if (operStack.isOper(ch)) {//如果是运算符！
                //判断符号栈是否为空
                if (!operStack.isEmpty()) {
                    //处理
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //从数栈pop出两个数来进行计算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //入栈
                        numStack.push(res);
                        //当前扫描到的符号入符号栈
                        operStack.push(ch);
                    } else {
                        //如果优先级大那么直接入栈！
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接入栈
                    operStack.push(ch);
                }
            } else {
                //如果是数字的话，直接入数栈
                //numStack.push(ch - 48);//ASCII码的原因！
                //当处理多位数时候，不能够发现一个数直接入栈！处理数的时候，需要在表达式再看一位
                //如果还是数字的话，就不可以入栈！
                //定义一个变量，应该是字符串变量，用于拼接多位数

                //处理多位数
                keepNum += ch;
                //判断下一个字符是不是数字，如果是数字 继续扫描！运算符进行入栈！
                //但还是要判断是不是最后一位！
                if(index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //如果后一位是运算符的话，就入栈keepNum
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的！！清空！
                        keepNum="";
                    }
                }

            }
            //让index+1 并判断是否扫描到表达式的最后了
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈来pop出相应的数和符号进行计算
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈也就只有一个数字了！
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);

        }
        int res2 = numStack.pop();
        System.out.printf("表达式%s=%d", expression, res2);
    }
}

//先创建一个栈，需要在扩展一下功能，例如判断符号的优先级这种功能/判断数字还是符号的这种功能
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就是放在这个数组之中的
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法，可以观察栈顶的值，但不是真正的出栈
    public int peek() {
        return stack[top];
    }

    //判断栈是否满了
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈是否空了
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断是不是满了
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop 将栈顶的数据返回
    public int pop() {
        //先判断栈是不是空的
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈，取得时候不是从0开始的，是从栈顶往下进行遍历！
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级的功能！优先级是程序确定的！优先级使用数字表示，越大就优先级越高！
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //假定目前的表达式只有+ - * / 没有加其他的符号在里面！
        }
    }

    //判断是不是一个运算符！
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算的方法！
    public int cal(int num1, int num2, int oper) {
        int res = 0;//用于存放计算的结果！
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意两个数的顺序
                break;
            case '*':
                res = num1 * num2;//注意两个数的顺序
                break;
            case '/':
                res = num2 / num1;//注意两个数的顺序
                break;
        }
        return res;
    }
}