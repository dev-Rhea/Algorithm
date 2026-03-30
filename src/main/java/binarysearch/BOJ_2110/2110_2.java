package binarysearch.BOJ_2110;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N, C;
    static int[] home;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        home = new int[N];
        
        for(int i=0;i<N;i++) {
            home[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(home);
        int start = 0;
        int inter = home[N-1] - home[0];
        int ans = 0;

        while(start <= inter) {
            int mid = (start + inter) / 2;
            
            if(wifi(mid)) {
                ans = mid;
                start = mid + 1;
            }
            else inter = mid - 1;
        }

        System.out.println(ans);
    }

    static boolean wifi(int dist) {
        int cnt = 1;
        int last = home[0];

        for(int i=1;i<N;i++) {
            if(home[i] - last >= dist) {
                cnt++;
                last = home[i];
            }
        }

        return cnt >= C;
    }
}