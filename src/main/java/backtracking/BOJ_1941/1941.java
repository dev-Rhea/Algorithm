package backtracking.BOJ_1941;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    static int ans;
    static int[] selected = new int[7];
    static char[][] map = new char[5][5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<5;i++) {
            String input = br.readLine();
            for(int j=0;j<5;j++) {
                map[i][j] = input.charAt(j);
            }
        }

        comb(0, 0);

        System.out.println(ans);
    }

    static void comb(int start, int cnt) {
        if(cnt == 7) {
            if(isValid()) ans++;
            return;
        }

        for(int i=start;i<25;i++) {
            selected[cnt] = i;
            comb(i+1, cnt+1);
        }
    }

    static boolean isValid() {
        int s = 0;
        for(int now : selected) {
            int x = now / 5;
            int y = now % 5;
            if(map[x][y] == 'S') s++;
        }
        if(s < 4) return false;
        return bfs();
    }

    static boolean bfs() {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[7];
        queue.add(selected[0]);
        visited[0] = true;
        int cnt = 1;

        while(!queue.isEmpty()) {
            int now = queue.poll();
            int x = now / 5;
            int y = now % 5;

            for(int i=0;i<4;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int next = nx * 5 + ny;

                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                
                for(int j=0;j<7;j++) {
                    if(!visited[j] && selected[j] == next) {
                        visited[j] = true;
                        queue.add(next);
                        cnt++;
                    }
                }
            }
        }
        return cnt == 7;
    }
}