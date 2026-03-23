package implementation.BOJ_17825;
import java.io.*;
import java.util.*;

class Main {

    static int[] dice = new int[10];
    static int[][] map = new int[42][2];
    static int max = Integer.MIN_VALUE;
    static int[][] dots = new int[5][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=4;i++) {
            dots[i][0] = 0; // 위치
            dots[i][1] = 0; // 파란 경로 여부
        }

        dfs(0, 0);

        System.out.println(max);
    }

    static void dfs(int depth, int score) {
        if(depth == 10) {
            max = Math.max(max, score);
            return;
        }

        int[][] temp = new int[42][2];
        for(int i=1;i<=41;i++) {
            temp[i][0] = map[i][0];
            temp[i][1] = map[i][1];
        }

        int[][] td = new int[5][2];
        for(int i=1;i<=4;i++) {
            td[i][0] = dots[i][0];
            td[i][1] = dots[i][1];
        }

        boolean moved = false;

        for(int i=1;i<=4;i++) {
            int n = dots[i][0];
            int blue = dots[i][1];

            if(n == 41) continue;

            int[] res = move(i, dice[depth]);
            if(res == null) continue;

            moved = true;
            int s = res[0];
            dfs(depth+1, score + s);

            for(int j=1;j<=41;j++) {
                map[j][0] = temp[j][0];
                map[j][1] = temp[j][1];
            }
    
            for(int j=1;j<=4;j++) {
                dots[j][0] = td[j][0];
                dots[j][1] = td[j][1];
            }
        }

        if(!moved) max = Math.max(max, score);
    }

    static int[] move(int n, int step) {
        int w = dots[n][0];
        int b = dots[n][1];

        if(b == 0 && w != 0 && w % 10 == 0 && w < 40) {
            if(w == 10) w = 13;
            else if(w == 20) w = 22;
            else if(w == 30) w = 28;
            step--;
            b = 1;
        }

        for(int i=0;i<step;i++) {
            if(w == 40) {
                w = 41;
                break;
            }
            if(b == 1) {
                if(w == 13) w = 16;
                else if(w == 16) w = 19;
                else if(w == 19) w = 25;
                else if(w == 22) w = 24;
                else if(w == 24) w = 25;
                else if(w == 28) w = 27;
                else if(w == 27) w = 26;
                else if(w == 26) w = 25;
                else if(w == 25) w = 30;
                else if(w == 30) w = 35;
                else if(w == 35) w = 40;
                else w = 41;
            }
            else {
                if(w == 0) w = 2;
                else w += 2;
            }
        }

        if(w != 41) {
            if(w == 40) {
                if(map[40][0] != 0 || map[40][1] != 0) return null;
            }
            else {
                if(map[w][b] != 0) return null;
            }
        }

        if(dots[n][0] != 0 && dots[n][0] != 41) map[dots[n][0]][dots[n][1]] = 0;

        if(w != 41) map[w][b] = n;

        dots[n][0] = w;
        dots[n][1] = b;

        return new int[]{w == 41 ? 0 : w};
    }
}