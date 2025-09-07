package dfs.BOJ_16637;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {

    static int ans = Integer.MIN_VALUE;
    static List<Character> ops;
    static List<Integer> nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        ops = new ArrayList<>();
        nums = new ArrayList<>();

        for(int i=0;i<N;i++) {
            char c = input.charAt(i);
            if(c == '+' || c == '-' || c == '*') {
                ops.add(c);
                continue;
            }
            nums.add(c - '0');
        }

        dfs(nums.get(0), 0);

        System.out.println(ans);
    }

    static void dfs(int n, int op) {
        if(op >= ops.size()) {
            ans = Math.max(ans, n);
            return;
        }

        int res1 = cal(ops.get(op), n, nums.get(op + 1));
        dfs(res1, op + 1);

        if(op + 1 < ops.size()) {
            int res2 = cal(ops.get(op+1), nums.get(op+1), nums.get(op + 2));
            dfs(cal(ops.get(op), n, res2), op+2);
        }
    }

    static int cal(char op, int n1, int n2) {
        switch(op) {
            case '+': return n1+n2;
            case '-': return n1-n2;
            case '*': return n1*n2;
        }
        return -1;
    }
}