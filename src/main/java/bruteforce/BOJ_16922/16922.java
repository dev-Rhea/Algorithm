package bruteforce.BOJ_16922;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main {

    static Set<Integer> set;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        int[] nums = new int[4];
        set = new HashSet<>();

        nums[0] = 1;
        nums[1] = 5;
        nums[2] = 10;
        nums[3] = 50;

        makeNumber(nums, 0, 0, 0);

        System.out.println(set.size());
    }

    static void makeNumber(int[] nums, int sum, int idx, int cnt) {
        if(N == cnt) {
            set.add(sum);
            return;
        }

        for(int i=idx;i<4;i++) {
            makeNumber(nums, sum + nums[i], i, cnt+1);
        }
    }
}