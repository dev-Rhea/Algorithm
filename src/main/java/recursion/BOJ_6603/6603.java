package recursion.BOJ_6603;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[] S, nums;
    static int k;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());
            nums = new int[6];
            S = new int[k+1];

            if(k == 0) break;

            for(int i=1;i<=k;i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }


            lotto(1, 0);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void lotto(int start, int depth) {
        boolean[] visited = new boolean[k + 1];

        if(depth == 6) {
            for(int i=0;i<6;i++) {
                sb.append(nums[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start;i<=k;i++) {
            nums[depth] = S[i];

            lotto(i+1, depth+1);
        }
    }
}