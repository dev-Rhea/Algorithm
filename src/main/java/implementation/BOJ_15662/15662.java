package implementation.BOJ_15662;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[][] gear;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        gear = new int[T+1][8];

        for(int i=1;i<=T;i++) {
            String input = br.readLine();
            for(int j=0;j<8;j++) {
                gear[i][j] = input.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());

        while(K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int[] turn = new int[T+1];
            turn[t] = d;

            // 왼편 톱니바퀴
            for(int i=t-1;i>=1;i--) {
                if(gear[i][2] != gear[i+1][6]) turn[i] = -turn[i+1];
                else break;
            }

            // 오른편 톱니바퀴
            for(int i=t+1;i<=T;i++) {
                if(gear[i-1][2] != gear[i][6]) turn[i] -= turn[i-1];
                else break;
            }

            for(int i=1;i<=T;i++) {
                if(turn[i] != 0) rotate(i, turn[i]);
            }
        }
        int cnt = 0;
        for(int i=1;i<=T;i++) {
            if(gear[i][0] == 1) cnt++;
        }
        System.out.println(cnt);
    }

    static void rotate(int t, int d) {
        if(d == 1) {
            int temp = gear[t][7];
            for(int i=7;i>0;i--) {
                gear[t][i] = gear[t][i-1];
            }
            gear[t][0] = temp;
        }
        else {
            int temp = gear[t][0];
            for(int i=0;i<7;i++) {
                gear[t][i] = gear[t][i+1];
            }
            gear[t][7] = temp;
        }
    }
}