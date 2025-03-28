package etc.BOJ_1027;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[] building;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N= Integer.parseInt(br.readLine());
        building = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            building[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;

        for(int i=1;i<=N;i++) {
            ans = Math.max(ans, count(i));
        }
        System.out.println(ans);
    }

    static int count(int idx) {
        int cnt = 0;
        double tmpL = -Double.MAX_VALUE;

        for(int i=idx-1;i>=1;i--) {
            double top = (double) (building[i] - building[idx]) / (idx - i);
            if(i == idx - 1 || tmpL > top) {
                cnt++;
                tmpL = top;
            }
        }

        double tmpR = -Double.MAX_VALUE;
        for(int i=idx+1;i<=N;i++) {
            double top = (double) (building[i] - building[idx]) / (i - idx);
            if(i == idx + 1 || tmpR < top) {
                cnt++;
                tmpR = top;
            }
        }
        return cnt;
    }
}