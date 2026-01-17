package implementation.BOJ_17825;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] map = {
        0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0,  // 0~21
        10, 13, 16, 19, 25, 30, 35, 40, 0,  // 22~30
        20, 22, 24, 25, 30, 35, 40, 0,      // 31~38
        30, 28, 27, 26, 25, 30, 35, 40, 0   // 39~47
    };
    
    static int[] dice = new int[10];
    static int[] piece = new int[10];
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int depth) {
        if(depth == 10) {
            game();
            return;
        }

        for(int i=0; i<4; i++) {
            piece[depth] = i;
            dfs(depth + 1);
        }
    }

    static void game() {
        boolean[] visited = new boolean[map.length];
        int score = 0;
        int[] now = new int[4];

        for(int i=0; i<10; i++) {
            int move = dice[i];
            int np = piece[i];
            
            if(isFinish(now[np])) return;

            int next = getNext(now[np], move);
            
            if(isFinish(next)) {
                setVisited(visited, now[np], false);
                now[np] = next;
                continue;
            }

            if(visited[next]) return;
            setVisited(visited, now[np], false);
            setVisited(visited, next, true);

            now[np] = next;
            score += map[now[np]];
        }

        ans = Math.max(ans, score);
    }

    static int getNext(int now, int move) {
        int next = now + move;

        if(now<21) {
            if(next >= 21) next = 21;
        }
        else if(now < 30) {
            if(next >= 30) next = 30;
        }
        else if(now < 38) {
            if(next >= 38) next = 38;
        }
        else if(now < 47) {
            if(next >= 47) next = 47;
        }

        if(next == 5) return 22;
        if(next == 10) return 31;
        if(next == 15) return 39;
        return next;
    }

    static boolean isFinish(int idx) {
        return idx == 21 || idx == 30 || idx == 38 || idx == 47;
    }

    static void setVisited(boolean[] visited, int idx, boolean flag) {
        // 40 지점 (20, 29, 37, 46)
        if(idx == 20 || idx == 29 || idx == 37 || idx == 46) {
            visited[20] = flag;
            visited[29] = flag;
            visited[37] = flag;
            visited[46] = flag;
        }
        // 25 지점 (26, 34, 43)
        else if(idx == 26 || idx == 34 || idx == 43) {
            visited[26] = flag;
            visited[34] = flag;
            visited[43] = flag;
        }
        // 30 지점 (27, 35, 44)
        else if(idx == 27 || idx == 35 || idx == 44) {
            visited[27] = flag;
            visited[35] = flag;
            visited[44] = flag;
        }
        // 35 지점 (28, 36, 45)
        else if(idx == 28 || idx == 36 || idx == 45) {
            visited[28] = flag;
            visited[36] = flag;
            visited[45] = flag;
        }
        else {
            visited[idx] = flag;
        }
    }
}