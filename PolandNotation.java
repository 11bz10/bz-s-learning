package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式变成后缀表达式的一个功能
        //说明
        //1.  1+((2+3)*4)-5 ==> 转成 1 2 3 + 4 * + 5 -
        //2.因为直接去扫描字符串不方便，先将这个字符串转成一个中缀表达式对应的list，在进行遍历！
        //ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3.将得到的一个中缀表达式子的list转成一个后缀表达式的list
        //即 前面我们得到了ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]==>现在是要[1,2,3,+,4,*,+,5,-]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list="+infixExpression);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpression);
        System.out.println("后缀表达式对应的list="+suffixExpressionList);

        System.out.printf("expression=%d",calculate(suffixExpressionList));


        ////先定义一个逆波兰表达式，肯定是个字符串
        ////(3+4)*5-6 ==> 3 4 + 5 * 6 -
        ////为了方便，把表达式里面的数字和符号进行空格隔开！
        ////可以测试一下多位数的
        //String suffixExpression = "30 4 + 5 * 6 -";
        ////思路
        ////1.先把表达式放在一个ArrayList里面
        ////2.将这个传递给一个方法，遍历这个队列配合栈来完成计算！
        //
        //List<String> rpnList = getListString(suffixExpression);
        //System.out.println("rpnList=" + rpnList);//这里的工作就是整个表达式放在了list里面，方便后面好取！
        //
        ////测试一下对不对
        //int res = calculate(rpnList);
        //System.out.println("res="+res);
    }

    //方法：将中缀表达式转化成相应的list
    public static List<String> toInfixExpressionList(String s) {
        //定义一个list
        List<String> ls = new ArrayList<String>();
        int i = 0;//相当于是一个指针，用于遍历这个中缀字符串
        String str;//进行多位数的拼接工作
        char ch;//每遍历一个字符，就放到c里面！
        //这个地方就可以把扫描的工作搞定！
        do {
            //如果c是一个非数字，我们就需要加入到ls里面去！
            if ((ch = s.charAt(i)) < 48 || (ch = s.charAt(i)) > 57) {
                ls.add("" + ch);//转成一个字符串的作用！
                i++;
            } else {//如果是一个数字，需要考虑多位数的问题！
                str = "";//将str置成空串
                while (i < s.length() && (ch = s.charAt(i)) >= 48 && (ch = s.charAt(i)) <= 57) {
                    str += ch;//拼接！
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //写一个方法！将得到的一个中缀表达式子的list转成一个后缀表达式的list
    //即 前面我们得到了ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]==>现在是要[1,2,3,+,4,*,+,5,-]
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        //因为s2在整个转换过程之中就没有pop操作，还要逆序输出，直接使用list<String>就行了！
        List<String> s2 = new ArrayList<String>();//待处理数据的栈

        //遍历ls
        for (String item : ls) {
            //如果是一个数就加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //右括号的时候就一直弹出s1栈顶的运算符，放到s2，直到遇到左括号为止，然后将这一对括号丢弃！
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将( 弹出s1栈，消除小括号！
            } else {
                //当item的优先级≤s1栈顶优先级的时候，将栈顶的弹出来加入到s2之中，再与前面的进行比较反复做！
                //问题我们缺少一个比较优先级高低的方法！
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中！
                s1.push(item);
            }
        }
        //将s1剩余的运算符都加入到s2之中
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//因为是存放到list中，本来正常按顺序输出就ok了！

    }


    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList之中
    public static List<String> getListString(String suffixExpression) {
        //这里是不管什么类型的都当作是字符串来进行处理！
        //将这个表达式进行分割,按照空格来进行分割，得到一个字符串的数组！
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            //每循环一次就可以取出来这个数组里面也就是表达式的一个元素！
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的一个运算！
    public static int calculate(List<String> ls) {
        //创建一个栈
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item : ls) {
            //这里使用一个正则表达式来取出数据！
            if (item.matches("\\d+")) {//匹配的是多位数！
                //直接入栈
                stack.push(item);
            } else {
                //pop出来两个数并进行计算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(res + "");//把数字转成字符串的一个小操作！
            }
        }
        //最后留在栈里面的就是计算结果！
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation 返回一个运算符对应的优先级！
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回相应的优先级数字！
    public static int getValue(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("错误");
                break;
        }
        return res;
    }
}
