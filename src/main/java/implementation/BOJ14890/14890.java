package implementation.BOJ14890;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, L;
    static int[][] map;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        cnt = 0;

        map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++) {
            if(findRoute(map[i])) cnt++;
        }

        for(int i=0;i<N;i++) {
            if(findRoute(getCol(i))) cnt++;
        }

        System.out.println(cnt);
    }

    private static int[] getCol(int j) {
        int[] col = new int[N];
        for(int i=0;i<N;i++) {
            col[i] = map[i][j];
        }
        return col;
    }

    private static boolean findRoute(int[] road) {
        boolean[] slide = new boolean[N];
        
        for(int i=0;i<N-1;i++) {
            int diff = road[i] - road[i+1];

            if(diff == 0) continue;
            else if(diff == 1) {
                if(!sliding(road, i+1, road[i+1], slide)) return false;
            }
            else if(diff == -1) {
                if(!sliding(road, i-L+1, road[i], slide)) return false;
            }
            else return false;
        }

        return true;
    }

    private static boolean sliding(int[] road, int start, int height, boolean[] slide) {
        if(start < 0 || start + L > N) return false;

        for(int i=start;i<start+L;i++) {
            if(road[i] != height || slide[i]) return false;
        }

        for(int i=start;i<start+L;i++) {
            slide[i] = true;
        }

        return true;
    }
}