package backtracking.BOJ_16936;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        long[] B = new long[N];
        boolean[] visited = new boolean[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        findSeq(0, B, new long[N], visited);
    }

    static boolean findSeq(int depth, long[] B, long[] ans, boolean[] visited) {
        if(depth == N) {
            for(int i=0;i<N;i++) {
                System.out.print(ans[i] + " ");
            }
            return true;
        }

        for(int i=0;i<N;i++) {
            if(visited[i]) continue;

            visited[i] = true;
            ans[depth] = B[i];

            if(depth == 0 || isValid(ans[depth-1], ans[depth])) {
                if(findSeq(depth+1, B, ans, visited)) return true;
            }

            visited[i] = false;
            ans[depth] = 0;
        }
        return false;
    }

    static boolean isValid(long a, long b) {
        if(a == b * 3 || a * 2 == b) return true;
        return false;
    }
}