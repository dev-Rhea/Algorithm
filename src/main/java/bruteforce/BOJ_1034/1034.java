package bruteforce.BOJ_1034;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static int[] map, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] map = new String[N];
        int[] cnt = new int[N];

        for(int i=0;i<N;i++) {
            map[i] = br.readLine();
            for(int j=0;j<M;j++) {
                if(map[i].charAt(j) =='0') cnt[i]++;
            }
        }

        int K = Integer.parseInt(br.readLine());
        int max = 0;

        for(int i=0;i<N;i++) {
            if(cnt[i] <= K && (K - cnt[i]) % 2 == 0) {
                int sameCnt = 1;
                for(int j=0;j<N;j++) {
                    if(j == i) continue;

                    if(map[i].equals(map[j])) sameCnt++;
                }
                max  = Math.max(max, sameCnt);
            }
        }

        System.out.println(max);
    }
}