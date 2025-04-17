package twopointer.BOJ_10942;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if(!isTrue(s, e)) sb.append(0).append("\n");
            else sb.append(1).append("\n");
        }
        System.out.println(sb);
    }

    static boolean isTrue(int s, int e) {

        while(s < e) {
            if(nums[s] != nums[e]) return false;
            s++;
            e--;
        }
        return true;
    }
}