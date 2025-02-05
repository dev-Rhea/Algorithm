package pre.Week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_9012{
    static int T;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        arr = new String[T];

        for(int i = 0; i < T; i++) {
            arr[i] = br.readLine();
        }

    for(int i=0;i<T;i++) {
        if(isVPS(arr[i])) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }



    }

    static boolean isVPS(String str) {
        Stack<Character> stack = new Stack<Character>();
        for(char c:str.toCharArray()) {
            if(c == '(') {
                stack.push(c);
            } else {
                if(stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

}
