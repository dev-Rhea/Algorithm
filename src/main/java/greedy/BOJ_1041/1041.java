package greedy.BOJ_1041;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int[] nums;
    static long[] total = new long[3];
    static int[] min =  new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        nums = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<6;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        cal();

        total[0] = 4L;
        total[1] = 8L * (N-2) + 4;
        total[2] = 5L * (N-2) * (N-2) + 4L * (N-2);
        
        if(N == 1) {
            Arrays.sort(nums);
            System.out.println(nums[0] + nums[1] + nums[2] + nums[3] + nums[4]);
        } else {
            System.out.println(
                    total[0] * (min[0] + min[1] + min[2]) +
                    total[1] * (min[0] + min[1]) +
                    total[2] * min[0]);
        }
    }

    private static void cal() {
        min[0] = Math.min(nums[0], nums[5]);
        min[1] = Math.min(nums[1], nums[4]);
        min[2] = Math.min(nums[2], nums[3]);

        Arrays.sort(min);
    }
}