package stack.BOJ_1874;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> seq = new Stack<>();
        boolean flag = false;
        int temp = 1;

        for(int i=1;i<=n;i++) {
            int num = Integer.parseInt(br.readLine());

            for(;temp<=num;temp++) {
                seq.push(temp);
                sb.append("+\n");
            }

            if(seq.peek() == num) {
                seq.pop();
                sb.append("-\n");
            }
            else flag = true;
        }
        System.out.println(flag ? "NO" : sb);        
    }
}