package bitmask.BOJ_1941;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[] map = new String[5];
    static boolean[] selected = new boolean[1 << 25];
    static int ans = 0;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<5;i++) {
            map[i] = br.readLine();
        }

        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                if(map[i].charAt(j) == 'S') {
                    int s = 1 << (i * 5 + j);
                    selected[s] = true;
                    dfs(s, 1, 1);
                }
            }
        }
        
        System.out.println(ans);
    }

    static void dfs(int bit, int cnt, int s) {
        if(7 - cnt + s < 4) return;
        if(cnt == 7) {
            ans++;
            return;
        }

        for(int i=0;i<25;i++) {
            if((bit & (1 << i)) == 0) continue;
            int x = i / 5;
            int y = i % 5;

            for(int j=0;j<4;j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

                int num = nx * 5 + ny;
                int numBit = bit | (1 << num);
                if(selected[numBit]) continue;
                selected[numBit] = true;

                if(map[nx].charAt(ny) == 'S') dfs(numBit, cnt + 1, s + 1);
                else dfs(numBit, cnt + 1, s);
            }
        }
    }
}