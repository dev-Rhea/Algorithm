package dfs.BOJ_14926;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[][] visited = new boolean[N+1][N+1];
        pick(N, visited);

        System.out.println(sb);
    }

    static void pick(int N, boolean[][] visited) {
        int len = (N*(N-1)) / 2;
        int idx = 0;
        visited[N][1] = visited[1][N] = true;

        for(int i=0;i<len;i++) {
            for(int j=1;j<=N;j++) {
                if(idx == j || visited[idx][j]) continue;

                visited[idx][j] = visited[j][idx] = true;
                idx = j;
                break;
            }
            sb.append('a').append(idx).append(" ");
        }
        sb.append("a1");
    }
}