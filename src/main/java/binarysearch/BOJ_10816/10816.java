package binarysearch.BOJ_10816;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int buffer = 10000000;
        nums = new int[buffer * 2 + 1];

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[Integer.parseInt(st.nextToken())+ buffer]++;
        }

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            sb.append(nums[Integer.parseInt(st.nextToken())+ buffer]).append(" ");
        }

        System.out.println(sb);
        
    }
}