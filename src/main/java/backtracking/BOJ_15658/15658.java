package backtracking.BOJ_15658;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[] nums, op;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        op = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        calculate(1, nums[0]);

        System.out.println(max);
        System.out.println(min);
    }

    static void calculate(int depth, int sum) {

        if(depth == N) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for(int i=0;i<4;i++) {
            if(op[0] > 0) {
                op[0] -= 1;
                calculate(depth + 1, sum + nums[depth]);
                op[0] += 1;
            }
            if(op[1] > 0) {
                op[1] -= 1;
                calculate(depth+1, sum - nums[depth]);
                op[1] += 1;
            }
            if(op[2] > 0) {
                op[2] -= 1;
                calculate(depth+1, sum*nums[depth]);
                op[2] += 1;
            }
            if(op[3] > 0) {
                op[3] -= 1;
                calculate(depth+1, sum/nums[depth]);
                op[3] += 1;
            }
        }
    }


}