package floydwarshall.BOJ_1613;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[][] history = new boolean[n+1][n+1];

        for(int i=0;i<k;i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int nex = Integer.parseInt(st.nextToken());

            history[pre][nex] = true;
        }

        for(int i=1;i<=n;i++) {
            for(int s=1;s<=n;s++) {
                if(!history[s][i]) continue;
                for(int e=1;e<=n;e++) {
                    if(!history[i][e]) continue;
                    history[s][e] = true;
                }
            }
        }

        int s = Integer.parseInt(br.readLine());
        while(s-- > 0) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int nex = Integer.parseInt(st.nextToken());

            if(history[pre][nex]) System.out.println(-1);
            else if(history[nex][pre]) System.out.println(1);
            else System.out.println(0);
        }
    }
}