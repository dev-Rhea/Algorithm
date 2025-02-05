package pre.Week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531 {
    static int N, d, k, c, cnt;
    static int[] sushi;
    static int[] eat;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            sushi = new int[N];
            eat = new int[d+1];
            eat[c] = 3001;

            for (int i = 0; i < N; i++) {
                sushi[i] = Integer.parseInt(br.readLine());
            }

            cnt = 1;
            for(int i=0;i<k;i++) {
                int sushiNum = sushi[i];
                if(eat[sushiNum] == 0) cnt++;
                eat[sushiNum]++;
            }

            int max = cnt;

            for(int i=1;i<N;i++) {
                int sushiNum = sushi[i];
                int eatNum = eat[i+k<N ? i+k : (i+k) % N];

                if(--eat[sushiNum] == 0) cnt--;
                if(++eat[eatNum] == 1) cnt++;

                max = Math.max(max, cnt);
            }
            System.out.println(max);
        }
}
