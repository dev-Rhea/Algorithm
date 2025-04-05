package greedy.BOJ_11501;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] stock = new int[N];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            long max = Integer.MIN_VALUE;

            for(int i=0;i<N;i++) {
                stock[i] = Integer.parseInt(st.nextToken());
            }

            long buy = 0;
            
            for(int i = N-1;i>=0;i--) {
                if(stock[i] > max) max = stock[i];
                else buy += (max - stock[i]);
            }

            System.out.println(buy);
        }
    }
}