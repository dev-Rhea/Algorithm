import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int cnt;
    static char[][] map;
    static boolean[][] visited;
    static boolean[] keys;
    static Queue<int[]>[] doors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken()); 
            int w = Integer.parseInt(st.nextToken()); 
            
            map = new char[h + 2][w + 2];
            visited = new boolean[h + 2][w + 2];
            keys = new boolean[26];
            doors = new LinkedList[26];
            cnt = 0;
            
            for(int i = 0; i < 26; i++) {
                doors[i] = new LinkedList<>();
            }

            for(int y = 1; y <= h; y++) {
                String input = br.readLine();
                for(int x = 1; x <= w; x++) {
                    map[y][x] = input.charAt(x - 1);
                }
            }
            
            String k = br.readLine();
            for(int i = 0; i < k.length(); i++) {
                char c = k.charAt(i);
                if(c == '0') break;
                int key = c - 'a';
                keys[key] = true;
            }

            dfs(new int[]{0, 0}, w, h);
            System.out.println(cnt);
        }
    }

    static void dfs(int[] at, int w, int h) {
        int x = at[0];
        int y = at[1];

        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || nx > w + 1) continue;
            if(ny < 0 || ny > h + 1) continue;
            if(visited[ny][nx]) continue;
            
            visited[ny][nx] = true;
            char c = map[ny][nx];
            
            if(c == '*') continue;
            else if(c >= 'a' && c <= 'z') {
                int key = c - 'a';
                keys[key] = true;
                
                while(!doors[key].isEmpty()) {
                    dfs(doors[key].poll(), w, h);
                }
                dfs(new int[]{nx, ny}, w, h);
            }
            else if(c >= 'A' && c <= 'Z') {
                int door = c - 'A';
                
                if(keys[door]) {
                    dfs(new int[]{nx, ny}, w, h);
                }
                else {
                    doors[door].offer(new int[]{nx, ny});
                }
            }
            else if(c == '$') {
                cnt++;
                dfs(new int[]{nx, ny}, w, h);
            }
            else {
                dfs(new int[]{nx, ny}, w, h);
            }
        }
    }
}