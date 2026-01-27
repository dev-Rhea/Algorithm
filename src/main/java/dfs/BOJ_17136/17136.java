package dfs.BOJ_17136;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[][] map = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int cnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i=0;i<10;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<10;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(cnt == Integer.MAX_VALUE ? -1 : cnt);
    }

    static void dfs(int x, int y, int depth) {
        if(x >= 9 && y > 9) {
            cnt = Math.min(depth, cnt);
            return;
        }

        if(cnt <= depth) return;

        if(y > 9) {
            dfs(x+1, 0, depth); // 다음 행으로 이동 
            return;
        }

        if(map[x][y] == 1) {
            for(int i=5;i>=1;i--) {
                if(paper[i] > 0 && isValid(x, y, i)) {
                    attach(x, y, i, 0);
                    paper[i]--;
                    dfs(x, y+1, depth+1);
                    attach(x, y, i, 1);
                    paper[i]++;
                }
            }
        }
        else dfs(x, y+1, depth);
    }

    static void attach(int x, int y, int size, int state) {
        for(int i=x;i<x+size;i++) {
            for(int j=y;j<y+size;j++) {
                map[i][j] = state;
            }
        }
    }

    static boolean isValid(int x, int y, int size) {
        for(int i=x;i<x+size;i++) {
            for(int j=y;j<y+size;j++) {
                if(i < 0 || i>=10 || j < 0 || j >= 10) return false;
                if(map[i][j] != 1) return false;
            }
        }
        return true;
    }
}