package backtracking.BOJ_9663;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cnt = 0;

        dfs(0, 0, 0);
        System.out.println(cnt);
    }

    static void dfs(int left, int mid, int right) {
        if(mid == ((1 << N) - 1)) {
            cnt++;
            return;
        }
        int not = left|mid|right;
        for(int i=0;i<N;i++) {
            int pos = 1 << i;
            if((not&pos) == 0) {
                dfs((left|pos) <<1, (mid|pos), (right|pos)>>1);
            }
        }
    }

}
