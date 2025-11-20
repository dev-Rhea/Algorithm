package stack.BOJ_2504;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        int ans = 0;
        int temp = 1;

        Stack<Character> stack = new Stack<>();

        for(int i=0;i<str.length;i++) {
            if(str[i] == '(') {
                stack.add(str[i]);
                temp *= 2;
            }
            else if(str[i] == '[') {
                stack.add(str[i]);
                temp *= 3;
            }
            else if(str[i] == ')') {
                if(stack.isEmpty() || stack.peek() != '(') {
                    ans = 0;
                    break;
                }
                if(str[i-1] == '(') ans += temp;
                stack.pop();
                temp /= 2;
            }
            else if(str[i] == ']') {
                if(stack.isEmpty() || stack.peek() != '[') {
                    ans = 0;
                    break;
                }
                if(str[i-1] == '[') ans += temp;
                stack.pop();
                temp /= 3;
            }
        }

        if(!stack.isEmpty()) ans = 0;
        System.out.println(ans);
    }
}