package bruteforce.BOJ_27172;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        int max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, nums[i]);
        }

        int[] score = new int[N+1];
        int[] player = new int[max+1];

        for(int i=0;i<N;i++) {
            player[nums[i]] = i+1;
        }

        for(int mod : nums) {
            for(int i=mod*2;i<=max;i+=mod) {
                if(score[i] != 0) {
                    player[score[mod]]++;
                    player[score[i]]--;
                }
            }
        }

        for(int i=1;i<=N;i++) {
            System.out.print(player[i] + " ");
        }
    }
}