package implementation.BOJ_15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[101][101];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            int[] stack = new int[(int) Math.pow(2, g) + 1];

            stack[0] = d;
            map[x][y] = true;

            // 첫번째 선 
            x += dx[d];
            y += dy[d];

            stack[1] = d;
            map[x][y] = true; // 끝점 

            int top = 1;

            while(g > 0) {
                for(int j=top;j>0;j--) {
                    d = (stack[j] + 1) % 4;
                    x += dx[d];
                    y += dy[d];
                    stack[++top] = d;
                    map[x][y] = true;
                }
                g--;
            }
        }

        int cnt = 0;

        for(int i=0;i<100;i++) {
            for(int j=0;j<100;j++) {
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) cnt++;
            }
        }

        System.out.println(cnt);
    }
    
}
