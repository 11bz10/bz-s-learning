package linkedList;

import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        stack.add("Jack");
        stack.add("Tom");
        stack.add("Smith");

        //取出
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
