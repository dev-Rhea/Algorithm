package pre.Week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12789 {

    static int N;
    static int idx = 1;
    static String[] arr;
    static String ans = "Nice";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new String[N];
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(arr[i]);

            if (idx != now) {
                if (!stack.isEmpty() && idx == stack.peek()) {
                    idx++;
                    i--;
                    stack.pop();
                } else {
                    stack.push(now);
                }
            } else {
                idx++;
            }
        }

        while (!stack.isEmpty()) {
            int pop = stack.pop();
            if (pop == idx) {
                idx++;
            } else {
                ans = "Sad";
                break;
            }
        }
        System.out.println(ans);
    }
}
