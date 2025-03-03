package stack.BOJ_2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        Stack<int[]> stack = new Stack<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int top = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty()) {
                if (stack.peek()[0] > top) {
                    sb.append(stack.peek()[1] + " ");
                    break;
                }
                stack.pop();
            }
            if (stack.empty()) {
                sb.append("0 ");
            }
            stack.push(new int[]{top, i});
        }
        System.out.println(sb);
    }
}
